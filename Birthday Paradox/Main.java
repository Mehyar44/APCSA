import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.print("How big do you want the group to be? ");
    int size = s.nextInt();
    String[] birthdays = new String[size]; 
    double count=0;
    s.close();

    for (int i=0; i<10000; i++) {
      for (int m=0; m<birthdays.length; m++) {
        birthdays[m]=birthday();
      }
      if (repeat(birthdays)) {
        count++;
      }
    }
    System.out.println("After 10,000 simulations, the probability of two people having the same birhtday in a group of " + size + " is " + count/100 + "%");
  }
  
  public static String birthday() {
    int month = (int)(Math.random()*12) + 1;
    int day;
    if (month==4 || month == 6 || month == 9 || month == 11) {
      day = (int)(Math.random()*30) + 1;
    }
    else if (month==2) {
      day = (int)(Math.random()*28) + 1;
    }
    else {
      day = (int)(Math.random()*31) + 1;
    }
    return month+"/"+day;
  }

  public static boolean repeat(String[] array) {
    for (int x = 0; x < array.length; x++) {
      for (int y = x + 1; y < array.length; y++) {
        if (array[x].equals(array[y])) {
          return true;
        }
      }
    }
    return false;
  }
}
