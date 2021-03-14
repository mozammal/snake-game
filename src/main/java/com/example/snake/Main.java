package com.example.snake;

import java.util.Scanner;

// mvn -e compile exec:java
// Add Android GUI

public class Main {
  public static void main(String[] args) {
    System.out.println("Enter Board size (row and column)");
    Scanner scanner = new Scanner(System.in);
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    Game game = new Game(new Board(row, col));
    game.play();
    scanner.close();
  }
}
