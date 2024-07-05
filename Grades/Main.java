import java.util.*;

public class Main {
  public static void main(String[] args) {
    int[][] grades = {{90, 90, 95, 100, 100},
                      {80, 90, 80, 90, 85},
                      {100, 90, 95, 90, 100},
                      {100, 98, 100, 100, 98},
                      {99, 99, 99, 99, 99},
                      {55, 50, 65, 70, 25}
                     };

    for (int[] grade : grades) System.out.println(Arrays.toString(grade));
    System.out.println("----------------");
    averages(grades);
    System.out.println("----------------");
    tests(grades);
  }
  
  public static double getAverage(int[] arr) {
    int sum=0;
    for (int num : arr) {
      sum+=num;
    }
    return (double)(sum)/arr.length;
  }
  
  public static void averages (int[][] arr2D) {
    double highestAve=0;
    int row = 0;
    int hRow = 0;
    for (int[] grades: arr2D) {
      row++;
      if (getAverage(grades)<65) {
        System.out.println("Student in row #" + (row) + " has a failing average. Their curved avrage is " + 10 * Math.sqrt(getAverage(grades)));
      }
      else if (getAverage(grades)>highestAve) {
        highestAve=getAverage(grades);
        hRow = row;
      }
    }
    System.out.println("Student in row #" + hRow + " has the highest avrage which is " + highestAve + ".");
  }

  public static void tests (int[][] arr2D) {
    int sum;
    int leastNum=0;
    int mostNum=0;
    double leastAve = 100.0;
    double mostAve = 0.0;
    for (int i=0; i<arr2D[0].length; i++) {
      sum = 0;
      for (int j=0; j<arr2D.length; j++) {
        sum+=arr2D[j][i];
      }
      if ((double)(sum)/arr2D.length < leastAve) {
        leastAve = (double)(sum)/arr2D.length;
        leastNum = i+1;
      }
      if ((double)(sum)/arr2D.length > mostAve) {
        mostAve = (double)(sum)/arr2D.length;
        mostNum = i+1;
      }
    }
    System.out.println("Test #" + mostNum + " was the most sucessful with an average of " + mostAve + ".");
    System.out.println("Test #" + leastNum + " was the least sucessful with an average of " + leastAve + ".");
  }
}
