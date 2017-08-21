package com.tanishqbhatia.truthordare.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.realm_models.User;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.methods.RealmMethods;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    @BindView(R.id.profilePictureIv)
    ImageView profilePictureIv;
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
    Unbinder unbinder;
    private Activity activity;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = Methods.init(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        init();
        /*setListeners();
        setAdapters();*/
    }

    private void init() {
        User user = new RealmMethods().getUser();
        if (user != null) {
            getActivity().setTitle(user.getUsername());
            fullNameTv.setText(user.getFullName());
            postsCountTv.setText(String.valueOf(user.getMedia()));
            followersCountTv.setText(String.valueOf(user.getFollowers()));
            followingCountTv.setText(String.valueOf(user.getFollowing()));
            bioTv.setText(user.getBio());
        } else {
            Methods.cleanSlateProtocol();
            getActivity().finish();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
