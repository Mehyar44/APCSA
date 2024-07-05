import java.util.*;
class Main { 
  public static void main(String[] args) {
    int num=0;
    int[] lottery1 = new int[6];
    int[] lottery2 = new int[6];
    
   //lottery numbers
    for (int i=0; i<6; i++) {
      if (i==5) {
        lottery1[i] = (int)(Math.random()*26) + 1;
      }
      else {
        while (repeat(num,lottery1)) {
          num = (int)(Math.random()*69) + 1;
        }
        lottery1[i] = num;
      }
    }
    
    //player numbers
    Scanner s = new Scanner(System.in);
    int count = 0;
    int choice;
    while (count<6) {
      if (count!=5) {
        System.out.print("Choose a number for the lottery between 1 and 69: ");
        choice = s.nextInt();
        if(choice<1 || choice>69 || repeat(choice,lottery2)){
          System.out.println("Number is invalid.\n");
        }
        else {
          lottery2[count] = choice;
          count++;
        }
      }
      else {
        System.out.print("Choose a number for the lottery between 1 and 26: ");
        choice = s.nextInt();
        if(choice<1 || choice>26){
          System.out.println("Number is invalid.\n");
        }
        else {
          lottery2[count] = choice;
          count++;
        }
      }
    }
    s.close(); 
    
    //result
    System.out.println("\nLottery Numbers:" + Arrays.toString(lottery1));
    System.out.println("Your Numbers:" + Arrays.toString(lottery2));
    prize(white(lottery1,lottery2),red(lottery1,lottery2));     
  }
  
  public static boolean repeat(int n, int[] lottery) {
    for (int i=0; i<lottery.length; i++) {
      if (lottery[i]==n) {
        return true;
      }
    }
    return false;    
  }
  
  public static int white(int[] array1, int[] array2) {
    int count=0;
    int[] new1 = new int[5];
    int[] new2 = new int[5];
    for(int a=0; a<5; a++) {
      new1[a]=array1[a];
      new2[a]=array2[a];
    }    
    for (int b=0; b<5; b++) {
      for (int c=0; c<5; c++) {
        if (new1[b]==new2[c]) {
          count++;
        } 
      }
    }    
    return count;    
  }
  
  public static boolean red(int[] array1, int[] array2) {
    if (array1[(array1.length)-1] == array2[(array2.length)-1]) {
      return true;
    }
    return false;  
  }
  
  public static void prize(int white, boolean red) {
    if (white==5 && red) System.out.println("\nYou won the GRAND PRIZE!!!!");
      
    else if (white==5) System.out.println("\nYou won one million dollars.");
  
    else if (white==4 && red) System.out.println("\nYou won fifty thousand dollars.");
    
    else if (white==4 || white==3 && red) System.out.println("\nYou won one hundred dollars.");
    
    else if (white==3 || white==2 && red) System.out.println("\nYou won seven dollars.");
    
    else if (red) System.out.println("\nYou won four dollars.");
    
    else System.out.println("\nYou won nothing :(");
  } 
}
