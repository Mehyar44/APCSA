public class StepTracker {
  private int stepsNeeded;
  private int activeDays;
  private double totalSteps;
  private int totalDays;

  public StepTracker(int sN) {
    stepsNeeded = sN;
    activeDays = 0;
    totalSteps = 0.0;
    totalDays = 0;
  }

  public int activeDays() {
    return activeDays;
  }

  public double averageSteps() {
    if (totalDays == 0) {
      return 0.0;
    }
    return totalSteps/totalDays;
  }

  public void addDailySteps(int steps) {
    if (steps >= stepsNeeded) {
      activeDays++;
    }
    totalSteps += steps;
    totalDays++;
  }
}
