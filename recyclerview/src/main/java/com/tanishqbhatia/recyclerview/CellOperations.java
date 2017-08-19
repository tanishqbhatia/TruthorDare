package com.tanishqbhatia.recyclerview;

import java.util.List;

interface CellOperations {

  void addCell(CustomCell cell);

  void addCell(int atPosition, CustomCell cell);

  void addCells(List<? extends CustomCell> cells);

  void addCells(CustomCell... cells);

  void addCells(int fromPosition, List<? extends CustomCell> cells);

  void addCells(int fromPosition, CustomCell... cells);

  <T extends CustomCell & Updatable> void addOrUpdateCell(T cell);

  <T extends CustomCell & Updatable> void addOrUpdateCells(List<T> cells);

  <T extends CustomCell & Updatable> void addOrUpdateCells(T... cells);

  void removeCell(CustomCell cell);

  void removeCell(int atPosition);

  void removeCells(int fromPosition, int toPosition);

  void removeCells(int fromPosition);

  void removeAllCells();

  void updateCell(int atPosition, Object payload);

  void updateCells(int fromPosition, int toPosition, Object payloads);

  CustomCell getCell(int atPosition);

  List<CustomCell> getCells(int fromPosition, int toPosition);

  List<CustomCell> getAllCells();

}
