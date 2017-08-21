package com.tanishqbhatia.recyclerview;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
class CustomDiffCallbackDelegate extends DiffUtil.Callback {

  private List<CustomCell> newList = new ArrayList<>();
  private List<CustomCell> oldList = new ArrayList<>();

  public CustomDiffCallbackDelegate(CustomAdapter adapter, List<? extends CustomCell> newCells) {
    this.oldList.addAll(adapter.getAllCells());
    this.newList.addAll(oldList);
    insertOrUpdateNewList(newCells);
    adapter.setCells(newList);
  }

  private void insertOrUpdateNewList(List<? extends CustomCell> newCells) {
    for (CustomCell newCell : newCells) {
      int index = indexOf(newList, newCell);
      if (index != -1) {
        newList.set(index, newCell);
      } else {
        newList.add(newCell);
      }
    }
  }

  private int indexOf(List<? extends CustomCell> cells, CustomCell cell) {
    for (CustomCell c : cells) {
      if (c.getItemId() == cell.getItemId()) {
        return cells.indexOf(c);
      }
    }
    return -1;
  }

  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).getItemId() == newList.get(newItemPosition).getItemId();
  }

  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return ((Updatable) oldList.get(oldItemPosition)).areContentsTheSame(newList.get(newItemPosition).getItem());
  }

  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    return ((Updatable) oldList.get(oldItemPosition)).getChangePayload(newList.get(newItemPosition).getItem());
  }

}
