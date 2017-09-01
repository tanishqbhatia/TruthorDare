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
import com.tanishqbhatia.truthordare.abstracts.IGServerRequest;
import com.tanishqbhatia.truthordare.abstracts.ServerRequest;
import com.tanishqbhatia.truthordare.adapters.InstagramPostsAdapter;
import com.tanishqbhatia.truthordare.adapters.PostsAdapter;
import com.tanishqbhatia.truthordare.models.User;
import com.tanishqbhatia.truthordare.utils.imageview.CustomImageView;
import com.tanishqbhatia.truthordare.utils.methods.Methods;

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
    @BindView(R.id.postsRv)
    CustomRecyclerView postsRv;
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
        initInstagramPosts();
        initPosts();
        /*setListeners();
        setAdapters();*/
    }

    private void initPosts() {
        new IGServerRequest().getInstagramPosts();
    }

    private void initInstagramPosts() {
        new IGServerRequest().getInstagramPosts();
    }

    private void initHeader() {
        new ServerRequest().getUserHeader();
    }

    @Subscribe(priority = 1, sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserHeader(User user) {
        if (user != null) {
            getActivity().setTitle(user.getUsername());
            fullNameTv.setText(Methods.decode(user.getFullName()));
            postsCountTv.setText(String.valueOf(user.getPosts()));
            followersCountTv.setText(String.valueOf(user.getFollowers()));
            followingCountTv.setText(String.valueOf(user.getFollowing()));
            bioTv.setText(Methods.decode(user.getBio()));
            new CustomImageView(profilePictureIv).setBorder().setScaleType().load(null, user.getProfilePictureURL());
        } else {
            Methods.cleanSlateProtocol();
        }
    }

    @Subscribe(priority = 2, sticky = true, threadMode = ThreadMode.MAIN)
    public void onInstagramPosts(List<String> instagramPostsUrls) {
        if (instagramPostsUrls != null) {
            List<InstagramPostsAdapter> instagramPostsAdapterList = new ArrayList<>();
            List<PostsAdapter> postsAdapterList = new ArrayList<>();
            for(String url : instagramPostsUrls) {
                instagramPostsAdapterList.add(new InstagramPostsAdapter((url)));
                postsAdapterList.add(new PostsAdapter((url)));
            }
            instagramPostsRv.addCells(instagramPostsAdapterList);
            postsRv.addCells(postsAdapterList);
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