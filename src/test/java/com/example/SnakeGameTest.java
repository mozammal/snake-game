package com.example;

import com.example.snake.Board;
import com.example.snake.Cell;
import com.example.snake.CellType;
import com.example.snake.Game;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGameTest {

  @Test
  public void board_ShouldCreateEmptyBoard_WhenGivenRowAndColumn() {
    Board board = new Board(3, 3);
    boolean actual =
        board.getCells().stream()
            .flatMap(List::stream)
            .anyMatch(cell -> cell.getCellType() != CellType.EMPTY);

    assertEquals(false, actual);
  }

  @Test
  public void getNextCell_ShouldMoveRight_WhenSnakeMoveRightOneStepFromLeftTopMost() {
    Game game = new Game(new Board(3, 3));
    game.setDirection("R");
    game.getBoard().getCells().get(2).get(2).setCellType(CellType.FOOD);
    game.updateBoardAndSnake();
    assertEquals(0, game.getSnake().getHead().getHeight());
    assertEquals(1, game.getSnake().getHead().getWidth());
    assertEquals(CellType.SNAKE, game.getSnake().getHead().getCellType());
  }
}
