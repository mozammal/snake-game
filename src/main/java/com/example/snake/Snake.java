package com.example.snake;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Snake {

  private Deque<Cell> body = new LinkedList<>();
  private Cell head;
  private int boardWith, boardHeight;

  public Snake(Cell curPosition) {
    head = curPosition;
    body.addLast(head);
    head.setCellType(CellType.SNAKE);
  }

  public void removeTail() {
    Cell tail = body.removeFirst();
    tail.setCellType(CellType.EMPTY);
  }

  public void setBoardWith(int boardWith) {
    this.boardWith = boardWith;
  }

  public void setBoardHeight(int boardHeight) {
    this.boardHeight = boardHeight;
  }

  public int getBoardWith() {
    return boardWith;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void moveTo(Cell nextCell) {
    head = nextCell;
    head.setCellType(CellType.SNAKE);
    body.addLast(head);
  }

  public boolean checkIfCrossItSelf(Cell nextCell) {
    return body.stream().anyMatch(cell -> cell == nextCell);
  }

  public Deque<Cell> getBody() {
    return body;
  }

  public void setBody(Deque<Cell> body) {
    this.body = body;
  }

  public Cell getHead() {
    return head;
  }

  public void setHead(Cell head) {
    this.head = head;
  }
}
