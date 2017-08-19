package com.tanishqbhatia.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

class InternalLoadMoreViewCell extends CustomCell<View, CustomViewHolder> {

  InternalLoadMoreViewCell(View view) {
    super(view);
  }

  @Override
  public int getLayoutRes() {
    return R.layout.interval_view;
  }

  @NonNull
  @Override
  public CustomViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new CustomViewHolder(getItem());
  }

  @Override
  public void onBindViewHolder(CustomViewHolder holder, int position, Context context, Object payload) {
  }

  @Override
  protected long getItemId() {
    return getItem().getId();
  }

}
