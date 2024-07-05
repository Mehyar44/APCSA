import java.util.*;

public class Main {
  public static void main(String[] args) {
    int[][] binary = new int[10][5];

    for (int i=0 ; i<binary.length; i++) {
      for (int j=0; j<binary[0].length; j++) {
        binary[i][j] = (int) (Math.random()*2);
      }
    }

    System.out.println(Arrays.deepToString(binary));
    System.out.println("----------------");
    rowByRow(binary);
    System.out.println("----------------");
    columnByColumn(binary);
    System.out.println("----------------");
    System.out.println(fourCorners(binary));
  }

  public static void rowByRow(int[][] arr) {
    for (int i=0; i<arr.length; i++)
      System.out.println(Arrays.toString(arr[i]));
  }

  public static void columnByColumn(int[][] arr) {
    for (int i=0; i<arr[0].length-1; i++) {
      for (int j=0; j<arr.length; j++) {
        if (j==0) System.out.print("[" + arr[j][i]);
        else System.out.print(", " + arr[j][i]);
      }
      System.out.println("]");
    }
  }

  public static boolean fourCorners(int[][] arr) {  
    return arr[0][0]==arr[0][arr[0].length-1] && arr[0][0]==arr[arr.length-1][0] && arr[arr.length-1][0]== arr[arr.length-1][arr[0].length-1];
  }
}
