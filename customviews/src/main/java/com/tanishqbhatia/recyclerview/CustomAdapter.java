package com.tanishqbhatia.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.tanishqbhatia.recyclerview.behavior.DragAndDropHelper;
import com.tanishqbhatia.recyclerview.behavior.OnItemDismissListener;
import com.tanishqbhatia.recyclerview.behavior.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>
  implements OnItemMoveListener, OnItemDismissListener, CellOperations {

  private List<CustomCell> cells;
  private SparseArray<CustomCell> cellTypeMap;
  private DragAndDropHelper dragAndDropHelper;

  CustomAdapter() {
    this.cells = new ArrayList<>();
    this.cellTypeMap = new SparseArray<>();
    setHasStableIds(true);
  }

  void setDragAndDropHelper(DragAndDropHelper dragAndDropHelper) {
    this.dragAndDropHelper = dragAndDropHelper;
  }

  @Override
  public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    CustomCell cell = cellTypeMap.get(viewType);
    View view = LayoutInflater.from(parent.getContext()).inflate(cell.getLayoutRes(), parent, false);
    final CustomViewHolder viewHolder = cell.onCreateViewHolder(parent, view);

    if (dragAndDropHelper != null && dragAndDropHelper.getDragHandleId() != 0) {
      View dragHandle = viewHolder.itemView.findViewById(dragAndDropHelper.getDragHandleId());
      dragHandle.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
            dragAndDropHelper.onStartDrag(viewHolder);
          }
          return false;
        }
      });
    }

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull  final CustomViewHolder holder, @NonNull int position, @Nullable List<Object> payloads) {
    final CustomCell cell = cells.get(position);

    holder.bind(cell);

    if (cell.getOnCellClickListener() != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          cell.getOnCellClickListener().onCellClicked(cell.getItem());
        }
      });
    }

    if (cell.getOnCellLongClickListener() != null) {
      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          cell.getOnCellLongClickListener().onCellLongClicked(cell.getItem());
          return true;
        }
      });
    }

    Object payload = null;
    if (payloads != null && payloads.size() > 0) {
      payload = payloads.get(0);
    }

    cell.onBindViewHolder(holder, position, holder.itemView.getContext(), payload);
  }

  @Override
  public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    onBindViewHolder(holder, position, null);
  }

  @Override
  public void onViewRecycled(@NonNull CustomViewHolder holder) {
    holder.getCell().onUnbindViewHolder(holder);
    holder.unbind();
  }

  @Override
  public int getItemCount() {
    return cells.size();
  }

  @Override
  public int getItemViewType(int position) {
    return cells.get(position).getLayoutRes();
  }

  @Override
  public long getItemId(int position) {
    return cells.get(position).getItemId();
  }

  private void addCellType(CustomCell cell) {
    if (!isCellTypeAdded(cell)) {
      cellTypeMap.put(cell.getLayoutRes(), cell);
    }
  }

  private void addCellTypes(List<CustomCell> cells) {
    for (CustomCell cell : cells) {
      addCellType(cell);
    }
  }

  private void removeCellType(CustomCell cell) {
    boolean hasCellType = false;

    for (CustomCell customCell : cells) {
      if (customCell.getClass().equals(cell.getClass())) {
        hasCellType = true;
      }
    }

    if (isCellTypeAdded(cell) && !hasCellType) {
      cellTypeMap.remove(cell.getLayoutRes());
    }
  }

  private boolean isCellTypeAdded(CustomCell cell) {
    return cellTypeMap.indexOfKey(cell.getLayoutRes()) >= 0;
  }

  @Override
  public void addCell(CustomCell cell) {
    addCell(cells.size(), cell);
  }

  @Override
  public void addCell(int atPosition, CustomCell cell) {
    cells.add(atPosition, cell);
    addCellType(cell);
    notifyItemInserted(atPosition);
  }

  @Override
  public void addCells(List<? extends CustomCell> cells) {
    if (cells.isEmpty()) {
      notifyDataSetChanged();
      return;
    }

    int initialSize = this.cells.size();
    for (CustomCell cell : cells) {
      this.cells.add(cell);
      addCellType(cell);
    }

    notifyItemRangeInserted(initialSize, cells.size());
  }

  @Override
  public void addCells(CustomCell... cells) {
    addCells(Arrays.asList(cells));
  }

  @Override
  public void addCells(int fromPosition, List<? extends CustomCell> cells) {
    if (cells.isEmpty()) {
      notifyDataSetChanged();
      return;
    }

    int pos = fromPosition;
    for (CustomCell cell : cells) {
      this.cells.add(pos++, cell);
      addCellType(cell);
    }

    notifyItemRangeInserted(fromPosition, cells.size());
  }

  @Override
  public void addCells(int fromPosition, CustomCell... cells) {
    addCells(fromPosition, Arrays.asList(cells));
  }

  @Override
  public <T extends CustomCell & Updatable> void addOrUpdateCell(T cell) {
    addOrUpdateCells(Collections.singletonList(cell));
  }

  @Override
  public <T extends CustomCell & Updatable> void addOrUpdateCells(List<T> cells) {
    CustomDiffCallbackDelegate callbackDelegate = new CustomDiffCallbackDelegate(this, cells);
    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callbackDelegate);
    diffResult.dispatchUpdatesTo(this);
  }

  @Override
  public <T extends CustomCell & Updatable> void addOrUpdateCells(T... cells) {
    addOrUpdateCells(Arrays.asList(cells));
  }

  @Override
  public void removeCell(CustomCell cell) {
    int position = cells.indexOf(cell);
    cells.remove(cell);
    removeCellType(cell);
    notifyItemRemoved(position);
  }

  @Override
  public void removeCell(int atPosition) {
    removeCell(cells.get(atPosition));
  }

  @Override
  public void removeCells(int fromPosition, int toPosition) {
    for (int i = toPosition; i >= fromPosition; i--) {
      CustomCell cell = cells.get(i);
      cells.remove(cell);
      removeCellType(cell);
    }
    notifyItemRangeRemoved(fromPosition, toPosition - fromPosition + 1);
  }

  @Override
  public void removeCells(int fromPosition) {
    removeCells(fromPosition, cells.size() - 1);
  }

  @Override
  public void removeAllCells() {
    cells.clear();
    cellTypeMap.clear();
    notifyDataSetChanged();
  }

  @Override
  public void updateCell(int atPosition, Object payloads) {
    notifyItemChanged(atPosition, payloads);
  }

  @Override
  public void updateCells(int fromPosition, int toPosition, Object payloads) {
    notifyItemRangeChanged(fromPosition, toPosition - fromPosition + 1, payloads);
  }

  @Override
  public CustomCell getCell(int atPosition) {
    return cells.get(atPosition);
  }

  @Override
  public List<CustomCell> getCells(int fromPosition, int toPosition) {
    return cells.subList(fromPosition, toPosition + 1);
  }

  @Override
  public List<CustomCell> getAllCells() {
    return cells;
  }

  public boolean isEmpty() {
    return cells.isEmpty();
  }

  public int getCellCount() {
    return cells.size();
  }

  void setCells(List<? extends CustomCell> cells) {
    this.cells.clear();
    this.cells.addAll(cells);
    addCellTypes(this.cells);
  }

  @Override
  public void onItemMoved(int fromPosition, int toPosition) {
    Collections.swap(cells, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onItemDismissed(int position) {
    cells.remove(position);
    notifyItemRemoved(position);
  }

}