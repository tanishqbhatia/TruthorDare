package com.tanishqbhatia.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import kotlinx.android.extensions.LayoutContainer;

public class CustomViewHolder extends RecyclerView.ViewHolder implements LayoutContainer {

  private CustomCell cell;

  public CustomViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  void bind(CustomCell cell) {
    this.cell = cell;
  }

  void unbind() {
    cell = null;
  }

  CustomCell getCell() {
    return cell;
  }

  @Nullable
  @Override
  public View getContainerView() {
    return itemView;
  }

}
