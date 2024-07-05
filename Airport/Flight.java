public class Flight {
  private int passengers;
  private double cost;
  private int capacity;
  
  public Flight(int p, double c, int cap) {
    passengers = p;
    cost = c;
    capacity = cap;
  }

  public int getNumPassengers() { return passengers; }
  public double getCost() { return cost; }
  public int getCapacity() { return capacity; }
}
