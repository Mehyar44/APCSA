import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.print("Do you want an empty board (1) or a started board (2): ");
    int style = s.nextInt();
    int[][] sudoku = createBoard(style);

    while (!win(sudoku)) {
      printBoard(sudoku);

      System.out.print("\nChoose a piece by picking a row and column (Ex - 73 where 7 is the row and 3 is the column): ");
      int ans = s.nextInt();
      int rowAns = ans / 10 - 1;
      int colAns = ans % 10 - 1;
      
      System.out.print("\nChoose a number to insert into the location (1-9 or -1 to go back or 0 to clear the space): ");
      int numAns = s.nextInt();

      if (numAns != -1) {
        if (megaFail(rowAns, colAns, numAns, sudoku)) System.out.println("\nThis number does not work here");

        else {
          sudoku[rowAns][colAns] = numAns;
          System.out.println("\nNumber has been placed.");
        }
      }
      

      s.nextLine();
    }
    s.close();
    
    System.out.println("\nCongratulations!! You won!!");
  }

  public static int[][] createBoard(int style) {
    int[][] board = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = 0;
      }
    }

    if (style == 1) return board;

    for (int j = 0; j < 44; j++) {
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
    System.out.println();
    for (int i = 0; i < board.length; i++) {
      if (i % 3 == 0 && i != 0)  System.out.println("       ----+-----+----");
      System.out.print("Row " + (i+1) + ": ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
        if (j % 3 == 2 && j != 8) System.out.print(" | ");
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
    if (num != 0) return verticalFail(col, num, arr) || horizontalFail(row, num, arr) || squareFail(row, col, num, arr);
    return false;
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
