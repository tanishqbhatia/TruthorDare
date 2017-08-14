package com.tanishqbhatia.truthordare.utils.toast;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 14-08-2017 at 10:34.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class ToastBuilder {
    /**
     * Show the view or text notification for a short period of time. This time
     * could be user-definable. This is the default.
     *
     * @see #setDuration
     */
    public static final int LENGTH_SHORT = 3000;

    /**
     * Show the view or text notification for a long period of time. This time
     * could be user-definable.
     *
     * @see #setDuration
     */
    public static final int LENGTH_LONG = 5000;

    /**
     * <p>Show the view or text notification for an undefined amount of time
     * -Usually until an invocation of {@link #cancel()}, {@link #cancelAll(android.app.Activity)},
     * {@link #cancelAll()} or {@link android.app.Activity#onDestroy()}-,
     * stacking on top of any other {@link ToastBuilder} with this duration.</p>
     *
     * <p><b>Note</b>: You are responsible
     * for calling {@link #cancel()} on such {@link ToastBuilder}.</p>
     *
     * @see #setDuration
     */
    public static final int LENGTH_STICKY = -1;

    /**
     * Lowest priority, messages with this priority will be showed after all messages with priority
     * {@link #PRIORITY_HIGH} and {@link #PRIORITY_NORMAL} have been shown.
     *
     * @see #setPriority(int)
     */
    public static final int PRIORITY_LOW = Integer.MIN_VALUE;
    /**
     * Normal priority, messages with this priority will be showed after all messages with priority
     * {@link #PRIORITY_HIGH} but before {@link #PRIORITY_LOW} have been shown.
     *
     * @see #setPriority(int)
     */
    public static final int PRIORITY_NORMAL = 0;
    /**
     * Highest priority, messages with this priority will be showed before any other message.
     *
     * @see #setPriority(int)
     */
    public static final int PRIORITY_HIGH = Integer.MAX_VALUE;

    /**
     * Show the text notification for a long period of time with a negative style.
     */
    public static final Style STYLE_ALERT = new Style(LENGTH_LONG, R.color.red_500);

    /**
     * Show the text notification for a short period of time with a positive style.
     */
    public static final Style STYLE_CONFIRM = new Style(LENGTH_SHORT, R.color.orange_500);

    /**
     * Show the text notification for a short period of time with a neutral style.
     */
    public static final Style STYLE_INFO = new Style(LENGTH_SHORT, R.color.blue_500);

    private final Activity mActivity;
    private int mDuration = LENGTH_SHORT;
    private View mView;
    private ViewGroup mParent;
    private LayoutParams mLayoutParams;
    private boolean mFloating;
    Animation mInAnimation, mOutAnimation;
    int mPriority = PRIORITY_NORMAL;

    /**
     * Construct an empty ToastBuilder object. You must call {@link #setView} before
     * you can call {@link #show}.
     *
     * @param activity {@link android.app.Activity} to use.
     */
    public ToastBuilder(Activity activity) {
        mActivity =  activity;
    }

    /**
     * Make a {@link ToastBuilder} that just contains a text view.
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style) {
        return makeText(context, text, style, R.layout.toast_builder);
    }


    /**
     * Make a {@link ToastBuilder} that just contains a text view.
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, OnClickListener clickListener) {
        return makeText(context, text, style, R.layout.toast_builder);
    }

    /**
     * @author mengguoqiang
     * Make a {@link ToastBuilder} that just contains a text view.
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, float textSize) {
        return makeText(context, text, style, R.layout.toast_builder, textSize);
    }


    /**
     * @author mengguoqiang
     * Make a {@link ToastBuilder} that just contains a text view.
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, float textSize, OnClickListener clickListener) {
        return makeText(context, text, style, R.layout.toast_builder, textSize, clickListener);
    }

    /**
     * Make a {@link ToastBuilder} with a custom layout. The layout must have a {@link TextView} com id {@link R.id.toastMessage}
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, int layoutId) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true);
    }


    /**
     * Make a {@link ToastBuilder} with a custom layout. The layout must have a {@link TextView} com id {@link R.id.toastMessage}
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, int layoutId, OnClickListener clickListener) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true);
    }



    /**
     * @author mengguoqiang
     * Make a {@link ToastBuilder} with a custom layout. The layout must have a {@link TextView} com id {@link R.id.toastMessage}
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, int layoutId, float textSize, OnClickListener clickListener) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true, textSize, clickListener);
    }

    /**
     * @author mengguoqiang
     * Make a {@link ToastBuilder} with a custom layout. The layout must have a {@link TextView} com id {@link R.id.toastMessage}
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param text    The text to show. Can be formatted text.
     * @param style   The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, int layoutId, float textSize) {
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(layoutId, null);

        return makeText(context, text, style, v, true, textSize);
    }




    /**
     * Make a non-floating {@link ToastBuilder} with a custom view presented inside the layout.
     * It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param customView
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, View customView) {
        return makeText(context, text, style, customView, false);
    }



    /**
     * Make a non-floating {@link ToastBuilder} with a custom view presented inside the layout.
     * It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param customView
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     */
    public static ToastBuilder makeText(Activity context, CharSequence text, Style style, View customView, OnClickListener clickListener) {
        return makeText(context, text, style, customView, false, clickListener);
    }


    /**
     * Make a {@link ToastBuilder} with a custom view. It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param view
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     * @param floating true if it'll float.
     */
    private static ToastBuilder makeText(Activity context, CharSequence text, Style style, View view, boolean floating) {
        return makeText(context, text, style, view, floating, 0);
    }



    /**
     * Make a {@link ToastBuilder} with a custom view. It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param view
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     * @param floating true if it'll float.
     */
    private static ToastBuilder makeText(Activity context, CharSequence text, Style style, View view, boolean floating, OnClickListener clickListener) {
        return makeText(context, text, style, view, floating, 0, clickListener);
    }

    /**
     *
     * @author mengguoqiang
     * Make a {@link ToastBuilder} with a custom view. It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param view
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     * @param floating true if it'll float.
     */
    private static ToastBuilder makeText(Activity context, CharSequence text, Style style, View view, boolean floating, float textSize) {
        ToastBuilder result = new ToastBuilder(context);

        view.setBackgroundResource(style.background);

        TextView tv = (TextView) view.findViewById(R.id.toastMessage);
        if(textSize > 0) tv.setTextSize(textSize);
        tv.setText(text);

        result.mView = view;
        result.mDuration = style.duration;
        result.mFloating = floating;

        return result;
    }



    /**
     *

     * Make a {@link ToastBuilder} with a custom view. It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param view
     *                 View to be used.
     * @param text     The text to show. Can be formatted text.
     * @param style    The style with a background and a duration.
     * @param floating true if it'll float.
     * @param clickListener Clicklistener
     */
    private static ToastBuilder makeText(Activity context, CharSequence text, Style style, View view, boolean floating,
                                         float textSize, OnClickListener clickListener) {
        ToastBuilder result = new ToastBuilder(context);

        view.setBackgroundResource(style.background);
        view.setClickable(true);

        TextView tv = (TextView) view.findViewById(R.id.toastMessage);
        if(textSize > 0) tv.setTextSize(textSize);
        tv.setText(text);

        result.mView = view;
        result.mDuration = style.duration;
        result.mFloating = floating;

        view.setOnClickListener(clickListener);

        return result;
    }






    /**
     * Make a {@link ToastBuilder} with a custom view. It can be used to create non-floating notifications if floating is false.
     *
     * @param context  The context to use. Usually your
     *                 {@link android.app.Activity} object.
     * @param resId    The resource id of the string resource to use. Can be
     *                 formatted text.
     * @param style    The style with a background and a duration.
     * @param floating true if it'll float.
     */
    public static ToastBuilder makeText(Activity context, int resId, Style style, View customView, boolean floating) {
        return makeText(context, context.getResources().getText(resId), style, customView, floating);
    }

    /**
     * Make a {@link ToastBuilder} that just contains a text view with the text from a
     * resource.
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param resId   The resource id of the string resource to use. Can be
     *                formatted text.
     * @param style   The style with a background and a duration.
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    public static ToastBuilder makeText(Activity context, int resId, Style style)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), style);
    }

    /**
     * Make a {@link ToastBuilder} with a custom layout using the text from a
     * resource. The layout must have a {@link TextView} com id {@link R.id.toastMessage}
     *
     * @param context The context to use. Usually your
     *                {@link android.app.Activity} object.
     * @param resId   The resource id of the string resource to use. Can be
     *                formatted text.
     * @param style   The style with a background and a duration.
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    public static ToastBuilder makeText(Activity context, int resId, Style style, int layoutId)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), style, layoutId);
    }

    /**
     * Show the view for the specified duration.
     */
    public void show() {
        ToastManager manager = ToastManager.obtain(mActivity);
        manager.add(this);
    }

    /**
     * @return <code>true</code> if the {@link ToastBuilder} is being displayed, else <code>false</code>.
     */
    public boolean isShowing() {
        if (mFloating) {
            return mView != null && mView.getParent() != null;
        } else {
            return mView.getVisibility() == View.VISIBLE;
        }
    }

    /**
     * Close the view if it's showing, or don't show it if it isn't showing yet.
     * You do not normally have to call this.  Normally view will disappear on its own
     * after the appropriate duration.
     */
    public void cancel() {
        ToastManager.obtain(mActivity).clearMsg(this);

    }

    /**
     * Cancels all queued {@link ToastBuilder}s, in all Activities. If there is a {@link ToastBuilder}
     * displayed currently, it will be the last one displayed.
     */
    public static void cancelAll() {
        ToastManager.clearAll();
    }

    /**
     * Cancels all queued {@link ToastBuilder}s, in given {@link android.app.Activity}.
     * If there is a {@link ToastBuilder} displayed currently, it will be the last one displayed.
     * @param activity
     */
    public static void cancelAll(Activity activity) {
        ToastManager.release(activity);
    }

    /**
     * Return the activity.
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * Set the view to show.
     *
     * @see #getView
     */
    public void setView(View view) {
        mView = view;
    }

    /**
     * Return the view.
     *
     * @see #setView
     */
    public View getView() {
        return mView;
    }

    /**
     * Set how long to show the view for.
     *
     * @see #LENGTH_SHORT
     * @see #LENGTH_LONG
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    /**
     * Return the duration.
     *
     * @see #setDuration
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * Update the text in a ToastBuilder that was previously created using one of the makeText() methods.
     *
     * @param resId The new text for the ToastBuilder.
     */
    public void setText(int resId) {
        setText(mActivity.getText(resId));
    }

    /**
     * Update the text in a ToastBuilder that was previously created using one of the makeText() methods.
     *
     * @param s The new text for the ToastBuilder.
     */
    public void setText(CharSequence s) {
        if (mView == null) {
            throw new RuntimeException("This ToastBuilder was not created with ToastBuilder.makeText()");
        }
        TextView tv = (TextView) mView.findViewById(R.id.toastMessage);
        if (tv == null) {
            throw new RuntimeException("This ToastBuilder was not created with ToastBuilder.makeText()");
        }
        tv.setText(s);
    }

    /**
     * Gets the crouton's layout parameters, constructing a default if necessary.
     *
     * @return the layout parameters
     */
    public LayoutParams getLayoutParams() {
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        return mLayoutParams;
    }

    /**
     * Sets the layout parameters which will be used to display the crouton.
     *
     * @param layoutParams The layout parameters to use.
     * @return <code>this</code>, for chaining.
     */
    public ToastBuilder setLayoutParams(LayoutParams layoutParams) {
        mLayoutParams = layoutParams;
        return this;
    }

    /**
     * Constructs and sets the layout parameters to have some gravity.
     *
     * @param gravity the gravity of the Crouton
     * @return <code>this</code>, for chaining.
     * @see android.view.Gravity
     */
    public ToastBuilder setLayoutGravity(int gravity) {
        mLayoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, gravity);
        return this;
    }

    /**
     * Return the value of floating.
     *
     * @see #setFloating(boolean)
     */
    public boolean isFloating() {
        return mFloating;
    }

    /**
     * Sets the value of floating.
     *
     * @param mFloating
     */
    public void setFloating(boolean mFloating) {
        this.mFloating = mFloating;
    }

    /**
     * Sets the Animations to be used when displaying/removing the Crouton.
     * @param inAnimation the Animation resource ID to be used when displaying.
     * @param outAnimation the Animation resource ID to be used when removing.
     */
    public ToastBuilder setAnimation(int inAnimation, int outAnimation) {
        return setAnimation(AnimationUtils.loadAnimation(mActivity, inAnimation),
                AnimationUtils.loadAnimation(mActivity, outAnimation));
    }

    /**
     * Sets the Animations to be used when displaying/removing the Crouton.
     * @param inAnimation the Animation to be used when displaying.
     * @param outAnimation the Animation to be used when removing.
     */
    public ToastBuilder setAnimation(Animation inAnimation, Animation outAnimation) {
        mInAnimation = inAnimation;
        mOutAnimation = outAnimation;
        return this;
    }

    /**
     * @return
     * Current priority
     *
     * @see #PRIORITY_HIGH
     * @see #PRIORITY_NORMAL
     * @see #PRIORITY_LOW
     */
    public int getPriority() {
        return mPriority;
    }

    /**
     * <p>Set priority for this message</p>
     * <p><b>Note</b>: This only affects the order in which the messages get shown,
     * not the stacking order of the views.</p>
     *
     * <p>Example: In the queue there are 3 messages [A, B, C],
     * all of them with priority {@link #PRIORITY_NORMAL}, currently message A is being shown
     * so we add a new message D with priority {@link #PRIORITY_HIGH}, after A goes away, given that
     * D has a higher priority than B an the reset, D will be shown, then once that D is gone,
     * B will be shown, and then finally C.</p>
     *
     * @param priority
     * A value indicating priority, although you can use any integer value, usage of already
     * defined is highly encouraged.
     *
     * @see #PRIORITY_HIGH
     * @see #PRIORITY_NORMAL
     * @see #PRIORITY_LOW
     */
    public void setPriority(int priority) {
        mPriority = priority;
    }

    /**
     * @return
     * Provided parent to add {@link #getView()} to using {@link #getLayoutParams()}.
     */
    public ViewGroup getParent() {
        return mParent;
    }

    /**
     * Provide a different parent than Activity decor view
     * @param parent
     * Provided parent to add {@link #getView()} to using {@link #getLayoutParams()}.
     *
     */
    public void setParent(ViewGroup parent) {
        mParent = parent;
    }

    /**
     * Provide a different parent than Activity decor view
     *
     * @param parentId
     * Provided parent id to add {@link #getView()} to using {@link #getLayoutParams()}.
     *
     */
    public void setParent(int parentId) {
        setParent((ViewGroup) mActivity.findViewById(parentId));
    }

    /**
     * The style for a {@link ToastBuilder}.
     *
     * @author e.shishkin
     */
    public static class Style {

        private final int duration;
        private final int background;

        public Style(int background) {
            this.duration = LENGTH_SHORT;
            this.background = background;
        }

        /**
         * Construct an {@link ToastBuilder.Style} object.
         *
         * @param duration How long to display the message. Either
         *                 {@link #LENGTH_SHORT} or {@link #LENGTH_LONG}
         * @param resId    resource for ToastBuilder background
         */
        public Style(int duration, int resId) {
            this.duration = duration;
            this.background = resId;
        }

        /**
         * Return the duration in milliseconds.
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Return the resource id of background.
         */
        public int getBackground() {
            return background;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ToastBuilder.Style)) {
                return false;
            }
            Style style = (Style) o;
            return style.duration == duration
                    && style.background == background;
        }

    }
}
