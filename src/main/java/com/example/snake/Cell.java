package com.example.snake;

import java.util.Objects;

public class Cell {

  private final int width, height;
  private CellType cellType;

  public Cell(int height, int width) {
    this.width = width;
    this.height = height;
    cellType = CellType.EMPTY;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public CellType getCellType() {
    return cellType;
  }

  public void setCellType(CellType cellType) {
    this.cellType = cellType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return width == cell.width && height == cell.height && cellType == cell.cellType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height, cellType);
  }
}
