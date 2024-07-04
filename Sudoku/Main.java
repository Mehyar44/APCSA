import java.util.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to Sudoku!!");

    Scanner s = new Scanner(System.in);
    System.out.print("\nChoose a difficulty (easy, medium, hard, empty): ");
    String diff = s.nextLine();
    int[][] sudoku = createBoard(diff);

    while (!win(sudoku)) {
      printBoard(sudoku);

      System.out.print("\nChoose a location (Answer in \"row,col\" from 1-9): ");
      String answer = s.nextLine();
      int rowAns = Integer.parseInt(answer.substring(0, 1)) - 1;
      int colAns = Integer.parseInt(answer.substring(2, 3)) - 1;

      if(sudoku[rowAns][colAns] != 0) {
        System.out.println("\nThere is already a number there.\n");
        continue;
      }

      else {
        System.out.print("\nChoose a number to insert into the location (1-9 or -1 to go back): ");
        int numAns = s.nextInt();

        if (numAns == -1) System.out.println("\nGoing back....\n");

        else if (megaFail(rowAns, colAns, numAns, sudoku)) System.out.println("\nThis number does not work here\n");

        else {
          sudoku[rowAns][colAns] = numAns;
          System.out.println("\nNumber has been placed.\n");
        }
      }

      s.nextLine();
    }
    s.close();
    
    System.out.println("\nCongratulations!! You won!!");
  }

  public static int[][] createBoard(String difficulty) {
    int[][] board = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = 0;
      }
    }

    int diff = 0;
    if (difficulty.equals("easy")) diff = 50;
    if (difficulty.equals("medium")) diff = 30;
    if (difficulty.equals("hard")) diff = 15;
    if (difficulty.equals("empty")) diff = 0;

    for (int j = 0; j < diff; j++) {
      int num = (int) (Math.random() * 9) + 1;
      int row = (int) (Math.random() * 9);
      int col = (int) (Math.random() * 9);

      while (megaFail(row, col, num, board)) {
        num = (int) (Math.random() * 9) + 1;
      }

      board[row][col] = num;
    }
    return board;
  }

  public static void printBoard(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      if (i % 3 == 0 && i != 0) {
        System.out.println("----+-----+----");
      }
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
        if (j % 3 == 2 && j != 8) {
          System.out.print(" | ");
        }
      }
      System.out.println();
    }
  }

  public static boolean verticalFail(int col, int num, int[][] arr) {
    for (int i = 0; i < 9; i++) {
      if (arr[i][col] == num) return true;
    }
    return false;
  }

  public static boolean horizontalFail(int row, int num, int[][] arr) {
    for (int i = 0; i < 9; i++) {
      if (arr[row][i] == num) return true;
    }
    return false;
  }

  public static boolean squareFail(int row, int col, int num, int[][] arr) {
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    for (int i = startRow; i < startRow + 3; i++) {
      for (int j = startCol; j < startCol + 3; j++) {
        if (arr[i][j] == num) return true;
      }
    }
    return false;
  }

  public static boolean megaFail(int row, int col, int num, int[][] arr) {
    return verticalFail(col, num, arr) || horizontalFail(row, num, arr) || squareFail(row, col, num, arr);
  }

  public static boolean win(int[][] arr) {
    for (int[] a : arr) {
      for (int num : a) {
        if (num == 0) return false;
      }
    }
    return true;
  }
}
