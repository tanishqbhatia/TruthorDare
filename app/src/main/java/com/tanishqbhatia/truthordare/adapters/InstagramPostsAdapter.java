package com.tanishqbhatia.truthordare.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tanishqbhatia.recyclerview.CustomCell;
import com.tanishqbhatia.recyclerview.CustomViewHolder;
import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.utils.imageview.CustomImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tanishq Bhatia on 01-09-2017 at 12:50.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class InstagramPostsAdapter extends CustomCell<String, InstagramPostsAdapter.ViewHolder> {

    public InstagramPostsAdapter(String url) {
        super(url);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.instagram_post;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull View cellView) {
        return new ViewHolder(cellView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Context context, Object payload) {
        new CustomImageView(holder.instagramPostIv).setScaleType().load(null, getItem());
    }

    class ViewHolder extends CustomViewHolder {

        @BindView(R.id.instagramPostIv)
        SimpleDraweeView instagramPostIv;

        public ViewHolder(View cellView) {
            super(cellView);
            ButterKnife.bind(this, cellView);
        }
    }
}
