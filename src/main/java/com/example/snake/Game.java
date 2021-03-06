package com.example.snake;

import java.util.Scanner;

public class Game {

  private Snake snake;
  private Board board;
  private static String direction;
  private boolean gameOver;

  public Game(Board board) {
    this.board = board;
    this.snake = new Snake(board.getCells().get(0).get(0));
  }

  public Cell getNextCell(Cell curCell) {
    int row = curCell.getHeight();
    int col = curCell.getWidth();

    switch (direction.toUpperCase()) {
      case "R":
        col++;
        break;
      case "L":
        col--;
        break;
      case "U":
        row--;
        break;
      case "D":
        row++;
        break;
    }
    if (row < 0 || row >= board.getRowCount() || col < 0 || col >= board.getColCount()) {
      return null;
    }
    return board.getCells().get(row).get(col);
  }

  public void play() {
    Scanner scanner = new Scanner(System.in);

    getBoard().generateFood();

    while (true) {
      System.out.println("Current Board: ");
      getBoard().printGameBoard();
      System.out.println("Enter one of the characters U/D/L/R, for quit enter Q");

      if (takeInputAndCheckIfQuit(scanner)) break;
      updateBoardAndSnake();
      if (isGameOver() || board.isAllCellOccupiedBySnake()) {
        System.out.println("Game is Over");
        break;
      }
    }
    System.out.println("Current Board: ");
    getBoard().printGameBoard();
    System.out.printf("Your score is: %d%n", board.calculateScore());
    scanner.close();
  }

  private boolean takeInputAndCheckIfQuit(Scanner scanner) {
    direction = scanner.next();
    if ("Q".equals(direction.toUpperCase())) {
      return true;
    }
    return false;
  }

  public void updateBoardAndSnake() {
    Cell nextCell = getNextCell(snake.getHead());
    if (nextCell == null) {
      gameOver = true;
      return;
    }

    if (nextCell.getCellType() != CellType.FOOD) {
      snake.removeTail();
    }

    if (snake.checkIfCrossItSelf(nextCell)) {
      gameOver = true;
      return;
    }
    CellType nextCellType = nextCell.getCellType();
    snake.moveTo(nextCell);

    if (nextCellType == CellType.FOOD) {
      board.generateFood();
    }
  }

  public Snake getSnake() {
    return snake;
  }

  public void setSnake(Snake snake) {
    this.snake = snake;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
