package com.tanishqbhatia.recyclerview.behavior;

import android.support.annotation.NonNull;
import android.view.View;

import com.tanishqbhatia.recyclerview.CustomRecyclerView;

public abstract class DragAndDropCallback<T> {

  public boolean enableDefaultRaiseEffect() {
    return true;
  }

  public void onCellDragStarted(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int position) {
  }

  public void onCellMoved(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int fromPosition, int toPosition) {
  }

  public void onCellDropped(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int initialPosition, int toPosition) {
  }

  public void onCellDragCancelled(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int currentPosition) {
  }

}
