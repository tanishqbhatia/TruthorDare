package com.tanishqbhatia.recyclerview.behavior;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.View;

import com.tanishqbhatia.recyclerview.CustomRecyclerView;

public abstract class SwipeToDismissCallback<T> {

  public boolean enableDefaultFadeOutEffect() {
    return true;
  }

  public void onCellSwiping(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int position, @NonNull Canvas canvas, float dX, float dY, boolean isControlledByUser) {
  }

  public void onCellSettled(@NonNull CustomRecyclerView customRecyclerView, @NonNull View itemView, @NonNull T item, int position) {
  }

  public void onCellDismissed(@NonNull CustomRecyclerView customRecyclerView, @NonNull T item, int position) {
  }

}
