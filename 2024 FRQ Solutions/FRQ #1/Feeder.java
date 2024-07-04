public class Feeder {
  private int currentFood;

  public Feeder(int f) {
    currentFood = f;
  }

  public void simulateOneDay(int numBirds) {
    int condition = (int)(Math.random()*100)+1;

    if (condition <= 5) currentFood = 0;
    else {
      int eaten = ((int)(Math.random()*41)+10) * numBirds;
      if (currentFood < eaten) currentFood = 0;
      else currentFood -= eaten;
    }
  }

  public int simulateManyDays(int numBirds, int numDays) {
    for (int day=0; day<numDays; day++) {
      if (currentFood == 0) return day;
      simulateOneDay(numBirds);
    }
    return numDays;
  }
}
