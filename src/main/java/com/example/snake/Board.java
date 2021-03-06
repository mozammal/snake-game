package com.example.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
  final int rowCount, colCount;
  private List<List<Cell>> cells;

  public Board(int rowCount, int colCount) {
    this.rowCount = rowCount;
    this.colCount = colCount;
    cells = new ArrayList<>();
    initializeBoard(rowCount, colCount);
  }

  private void initializeBoard(int rowCount, int colCount) {
    IntStream.range(0, rowCount)
        .forEach(
            i -> {
              cells.add(new ArrayList<>());
            });
    IntStream.range(0, rowCount)
        .forEach(
            i ->
                IntStream.range(0, colCount)
                    .forEach(
                        j -> {
                          cells.get(i).add(j, new Cell(i, j));
                        }));
  }

  public long calculateScore() {
    long score =
        cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell.getCellType() == CellType.SNAKE)
            .count();
    return score - 1;
  }

  public void printGameBoard() {
    cells.stream()
        .forEach(
            rowValues -> {
              rowValues.stream()
                  .forEach(
                      columnValues -> {
                        System.out.print("|");
                        System.out.print(
                            columnValues.getCellType() == CellType.SNAKE
                                ? "S"
                                : columnValues.getCellType() == CellType.FOOD ? "F" : ".");
                      });
              System.out.println("|");
            });
  }

  public int getRowCount() {
    return rowCount;
  }

  public int getColCount() {
    return colCount;
  }

  public List<List<Cell>> getCells() {
    return cells;
  }

  public boolean isAllCellOccupiedBySnake() {
    return !cells.stream()
        .flatMap(List::stream)
        .anyMatch(cell -> cell.getCellType() != CellType.SNAKE);
  }

  public void generateFood() {
    if (isAllCellOccupiedBySnake()) {
      return;
    }
    try {
      IntStream.iterate(0, i -> i + 1)
          .forEach(
              i -> {
                int row = (int) (Math.random() * rowCount);
                int col = (int) (Math.random() * colCount);
                if (cells.get(row).get(col).getCellType() != CellType.SNAKE) {
                  cells.get(row).get(col).setCellType(CellType.FOOD);
                  throw new RuntimeException();
                }
              });
    } catch (RuntimeException e) {

    }
  }
}
