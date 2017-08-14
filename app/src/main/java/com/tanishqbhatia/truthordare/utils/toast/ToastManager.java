package com.tanishqbhatia.truthordare.utils.toast;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.WeakHashMap;

import static android.app.Application.ActivityLifecycleCallbacks;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import static com.tanishqbhatia.truthordare.utils.toast.ToastBuilder.LENGTH_STICKY;

/**
 * Created by Tanishq Bhatia on 14-08-2017 at 10:33.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

class ToastManager extends Handler implements Comparator<ToastBuilder> {

    private static final int MESSAGE_DISPLAY = 0xc2007;
    private static final int MESSAGE_ADD_VIEW = 0xc20074dd;
    private static final int MESSAGE_REMOVE = 0xc2007de1;

    private static WeakHashMap<Activity, ToastManager> sManagers;
    private static ReleaseCallbacks sReleaseCallbacks;

    private final Queue<ToastBuilder> msgQueue;
    private final Queue<ToastBuilder> stickyQueue;

    private ToastManager() {
        msgQueue = new PriorityQueue<ToastBuilder>(1, this);
        stickyQueue = new LinkedList<ToastBuilder>();
    }

    /**
     * @return A {@link ToastManager} instance to be used for given {@link android.app.Activity}.
     */
    static synchronized ToastManager obtain(Activity activity) {
        if (sManagers == null) {
            sManagers = new WeakHashMap<Activity, ToastManager>(1);
        }
        ToastManager manager = sManagers.get(activity);
        if (manager == null) {
            manager = new ToastManager();
            ensureReleaseOnDestroy(activity);
            sManagers.put(activity, manager);
        }

        return manager;
    }

    static void ensureReleaseOnDestroy(Activity activity) {
        if (SDK_INT < ICE_CREAM_SANDWICH) {
            return;
        }
        if (sReleaseCallbacks == null) {
            sReleaseCallbacks = new ReleaseCallbacksIcs();
        }
        sReleaseCallbacks.register(activity.getApplication());
    }


    static synchronized void release(Activity activity) {
        if (sManagers != null) {
            final ToastManager manager = sManagers.remove(activity);
            if (manager != null) {
                manager.clearAllMsg();
            }
        }
    }

    static synchronized void clearAll() {
        if (sManagers != null) {
            final Iterator<ToastManager> iterator = sManagers.values().iterator();
            while (iterator.hasNext()) {
                final ToastManager manager = iterator.next();
                if (manager != null) {
                    manager.clearAllMsg();
                }
                iterator.remove();
            }
            sManagers.clear();
        }
    }

    /**
     * Inserts a {@link ToastBuilder} to be displayed.
     *
     * @param appMsg
     */
    void add(ToastBuilder appMsg) {
        msgQueue.add(appMsg);
        if (appMsg.mInAnimation == null) {
            appMsg.mInAnimation = AnimationUtils.loadAnimation(appMsg.getActivity(),
                    android.R.anim.fade_in);
        }
        if (appMsg.mOutAnimation == null) {
            appMsg.mOutAnimation = AnimationUtils.loadAnimation(appMsg.getActivity(),
                    android.R.anim.fade_out);
        }
        displayMsg();
    }

    /**
     * Removes all {@link ToastBuilder} from the queue.
     */
    void clearMsg(ToastBuilder appMsg) {
        if(msgQueue.contains(appMsg) || stickyQueue.contains(appMsg)){
            // Avoid the message from being removed twice.
            removeMessages(MESSAGE_DISPLAY, appMsg);
            removeMessages(MESSAGE_ADD_VIEW, appMsg);
            removeMessages(MESSAGE_REMOVE, appMsg);
            msgQueue.remove(appMsg);
            stickyQueue.remove(appMsg);
            removeMsg(appMsg);
        }
    }

    /**
     * Removes all {@link ToastBuilder} from the queue.
     */
    void clearAllMsg() {
        removeMessages(MESSAGE_DISPLAY);
        removeMessages(MESSAGE_ADD_VIEW);
        removeMessages(MESSAGE_REMOVE);
        clearShowing();
        msgQueue.clear();
        stickyQueue.clear();
    }

    void clearShowing() {
        final Collection<ToastBuilder> showing = new HashSet<ToastBuilder>();
        obtainShowing(msgQueue, showing);
        obtainShowing(stickyQueue, showing);
        for (ToastBuilder msg : showing) {
            clearMsg(msg);
        }
    }

    static void obtainShowing(Collection<ToastBuilder> from, Collection<ToastBuilder> appendTo) {
        for (ToastBuilder msg : from) {
            if (msg.isShowing()) {
                appendTo.add(msg);
            }
        }
    }

    /**
     * Displays the next {@link ToastBuilder} within the queue.
     */
    private void displayMsg() {
        if (msgQueue.isEmpty()) {
            return;
        }
        // First peek whether the ToastBuilder is being displayed.
        final ToastBuilder appMsg = msgQueue.peek();
        final Message msg;
        if (!appMsg.isShowing()) {
            // Display the ToastBuilder
            msg = obtainMessage(MESSAGE_ADD_VIEW);
            msg.obj = appMsg;
            sendMessage(msg);
        } else if (appMsg.getDuration() != LENGTH_STICKY) {
            msg = obtainMessage(MESSAGE_DISPLAY);
            sendMessageDelayed(msg, appMsg.getDuration()
                    + appMsg.mInAnimation.getDuration() + appMsg.mOutAnimation.getDuration());
        }
    }

    /**
     * Removes the {@link ToastBuilder}'s view after it's display duration.
     *
     * @param appMsg The {@link ToastBuilder} added to a {@link ViewGroup} and should be removed.s
     */
    private void removeMsg(final ToastBuilder appMsg) {
        clearMsg(appMsg);
        final View view = appMsg.getView();
        ViewGroup parent = ((ViewGroup) view.getParent());
        if (parent != null) {
            appMsg.mOutAnimation.setAnimationListener(new OutAnimationListener(appMsg));
            view.clearAnimation();
            view.startAnimation(appMsg.mOutAnimation);
        }

        Message msg = obtainMessage(MESSAGE_DISPLAY);
        sendMessage(msg);
    }

    private void addMsgToView(ToastBuilder appMsg) {
        View view = appMsg.getView();
        if (view.getParent() == null) { // Not added yet
            final ViewGroup targetParent = appMsg.getParent();
            final ViewGroup.LayoutParams params = appMsg.getLayoutParams();
            if (targetParent != null) {
                targetParent.addView(view, params);
            } else {
                appMsg.getActivity().addContentView(view, params);
            }
        }
        view.clearAnimation();
        view.startAnimation(appMsg.mInAnimation);
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        final int duration = appMsg.getDuration();
        if (duration != LENGTH_STICKY) {
            final Message msg = obtainMessage(MESSAGE_REMOVE);
            msg.obj = appMsg;
            sendMessageDelayed(msg, duration);
        } else { // We are sticky, we don't get removed just yet
            stickyQueue.add(msgQueue.poll());
        }
    }

    @Override
    public void handleMessage(Message msg) {
        final ToastBuilder appMsg;
        switch (msg.what) {
            case MESSAGE_DISPLAY:
                displayMsg();
                break;
            case MESSAGE_ADD_VIEW:
                appMsg = (ToastBuilder) msg.obj;
                addMsgToView(appMsg);
                break;
            case MESSAGE_REMOVE:
                appMsg = (ToastBuilder) msg.obj;
                removeMsg(appMsg);
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }

    @Override
    public int compare(ToastBuilder lhs, ToastBuilder rhs) {
        return inverseCompareInt(lhs.mPriority, rhs.mPriority);
    }

    static int inverseCompareInt(int lhs, int rhs) {
        return lhs < rhs ? 1 : (lhs == rhs ? 0 : -1);
    }

    private static class OutAnimationListener implements Animation.AnimationListener {

        private final ToastBuilder appMsg;

        private OutAnimationListener(ToastBuilder appMsg) {
            this.appMsg = appMsg;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            final View view = appMsg.getView();
            if (appMsg.isFloating()) {
                final ViewGroup parent = ((ViewGroup) view.getParent());
                if (parent != null) {
                    parent.post(new Runnable() { // One does not simply removeView
                        @Override
                        public void run() {
                            parent.removeView(view);
                        }
                    });
                }
            } else {
                view.setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    interface ReleaseCallbacks {
        void register(Application application);
    }

    @TargetApi(ICE_CREAM_SANDWICH)
    static class ReleaseCallbacksIcs implements ActivityLifecycleCallbacks, ReleaseCallbacks {
        private WeakReference<Application> mLastApp;
        public void register(Application app) {
            if (mLastApp != null && mLastApp.get() == app) {
                return; // Already registered with this app
            } else {
                mLastApp = new WeakReference<Application>(app);
            }
            app.registerActivityLifecycleCallbacks(this);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            release(activity);
        }
        @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
        @Override public void onActivityStarted(Activity activity) {}
        @Override public void onActivityResumed(Activity activity) {}
        @Override public void onActivityPaused(Activity activity) {}
        @Override public void onActivityStopped(Activity activity) {}
        @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
    }
}