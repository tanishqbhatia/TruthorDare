package com.tanishqbhatia.truthordare.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tanishqbhatia.recyclerview.CustomRecyclerView;
import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.adapters.InstagramPostsAdapter;
import com.tanishqbhatia.truthordare.adapters.UserPostsAdapter;
import com.tanishqbhatia.truthordare.models.GetUser;
import com.tanishqbhatia.truthordare.models.Post;
import com.tanishqbhatia.truthordare.models.User;
import com.tanishqbhatia.truthordare.models.UserPosts;
import com.tanishqbhatia.truthordare.utils.imageview.CustomImageView;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.server.IGServerRequest;
import com.tanishqbhatia.truthordare.utils.server.ServerRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    @BindView(R.id.profilePictureIv)
    SimpleDraweeView profilePictureIv;
    @BindView(R.id.fullNameTv)
    TextView fullNameTv;
    @BindView(R.id.postsCountTv)
    TextView postsCountTv;
    @BindView(R.id.followersCountTv)
    TextView followersCountTv;
    @BindView(R.id.followingCountTv)
    TextView followingCountTv;
    @BindView(R.id.bioTv)
    TextView bioTv;
    @BindView(R.id.instagramPostsRv)
    CustomRecyclerView instagramPostsRv;
    @BindView(R.id.userPostsRv)
    CustomRecyclerView userPostsRv;
    Unbinder unbinder;
    private Activity activity;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = Methods.init(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        initHeader();
        /*setListeners();
        setAdapters();*/
    }

    private void initUserPosts() {
        new ServerRequest().getUserPosts();
    }

    private void initUserInstagramPosts() {
        new IGServerRequest().getInstagramPosts();
    }

    private void initHeader() {
        new ServerRequest().getUserHeader();
    }

    @Subscribe(priority = 1, sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserHeader(GetUser getUser) {
        if (getUser != null) {
            User user = getUser.getUser();
            getActivity().setTitle(user.getUsername());
            fullNameTv.setText(Methods.decode(user.getFullName()));
            postsCountTv.setText(String.valueOf(user.getPosts()));
            followersCountTv.setText(String.valueOf(user.getFollowers()));
            followingCountTv.setText(String.valueOf(user.getFollowing()));
            bioTv.setText(Methods.decode(user.getBio()));
            new CustomImageView(profilePictureIv).setBorder().setScaleType().load(null, user.getProfilePictureURL());
            initUserInstagramPosts();
        } else {
            Methods.cleanSlateProtocol();
        }
    }

    @Subscribe(priority = 2, sticky = true, threadMode = ThreadMode.MAIN)
    public void onInstagramPosts(List<String> instagramPostsUrls) {
        if (instagramPostsUrls != null) {
            List<InstagramPostsAdapter> instagramPostsAdapterList = new ArrayList<>();
            for(String url : instagramPostsUrls) {
                instagramPostsAdapterList.add(new InstagramPostsAdapter((url)));
            }
            instagramPostsRv.addCells(instagramPostsAdapterList);
            initUserPosts();
        } else {
            Methods.cleanSlateProtocol();
        }
    }

    @Subscribe(priority = 3, sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserPostsList(UserPosts userPosts) {
        if (userPosts != null) {
            List<Post> userPostsList = userPosts.getUserPostsList();
            List<UserPostsAdapter> userPostsAdapterList = new ArrayList<>();
            for(int i=0; i<userPostsList.size(); i++) {
                Post post = userPostsList.get(i);
                String url = post.getPostUrl();
                userPostsAdapterList.add(new UserPostsAdapter((url)));
            }
            userPostsRv.addCells(userPostsAdapterList);
        } else {
            Methods.cleanSlateProtocol();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.profilePictureIv, R.id.postsCountTv, R.id.followersCountTv, R.id.followingCountTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profilePictureIv:
                break;
            case R.id.postsCountTv:
                break;
            case R.id.followersCountTv:
                break;
            case R.id.followingCountTv:
                break;
        }
    }
}