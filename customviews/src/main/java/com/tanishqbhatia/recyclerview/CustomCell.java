package com.tanishqbhatia.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public abstract class CustomCell<T, VH extends CustomViewHolder> {

  public interface OnCellClickListener<T> {
    void onCellClicked(@NonNull T item);
  }

  public interface OnCellLongClickListener<T> {
    void onCellLongClicked(@NonNull T item);
  }

  private int spanSize = 1;
  private T item;
  private OnCellClickListener onCellClickListener;
  private OnCellLongClickListener onCellLongClickListener;

  public CustomCell(@NonNull T item) {
    this.item = item;
  }

  @LayoutRes protected abstract int getLayoutRes();

  @NonNull protected abstract VH onCreateViewHolder(@NonNull ViewGroup parent, @NonNull View cellView);

  protected abstract void onBindViewHolder(@NonNull VH holder, int position, @NonNull Context context, Object payload);

  protected void onUnbindViewHolder(@NonNull VH holder) {
  }

  @NonNull public T getItem() {
    return item;
  }

  public void setItem(@NonNull T item) {
    this.item = item;
  }

  protected long getItemId() {
    return item.hashCode();
  }

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  public void setOnCellClickListener(@NonNull OnCellClickListener onCellClickListener) {
    this.onCellClickListener = onCellClickListener;
  }

  public void setOnCellLongClickListener(@NonNull OnCellLongClickListener onCellLongClickListener) {
    this.onCellLongClickListener = onCellLongClickListener;
  }

  public OnCellClickListener getOnCellClickListener() {
    return onCellClickListener;
  }

  public OnCellLongClickListener getOnCellLongClickListener() {
    return onCellLongClickListener;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CustomCell<?, ?> cell = (CustomCell<?, ?>) o;

    return getItemId() == cell.getItemId();

  }

  @Override
  public int hashCode() {
    return (int) (getItemId() ^ (getItemId() >>> 32));
  }

}
