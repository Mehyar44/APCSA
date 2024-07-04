public class Main {
  public static void main(String[] args) {
    System.out.println("2024 Question 1!\n");

    Feeder f1 = new Feeder(2400);
    System.out.println(f1.simulateManyDays(10,4));

    Feeder f2 = new Feeder(250);
    System.out.println(f2.simulateManyDays(10,5));

    Feeder f3 = new Feeder(0);
    System.out.println(f3.simulateManyDays(10,5));
  }
}
