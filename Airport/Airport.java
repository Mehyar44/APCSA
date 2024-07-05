import java.util.*;

public class Airport {
  private ArrayList<Flight> allFlights;

  public Airport() {
    allFlights = new ArrayList<Flight>();
  }

  public ArrayList<Flight> getAllFlights() {
    return allFlights;
  }
  
  public void addFlight(Flight flight) {
    allFlights.add(flight);
  }
  public double getTotalRevenue() {
    double sum = 0.0;
    for (Flight flight : allFlights) {
      if (flight.getCapacity() < flight.getNumPassengers()) {
        sum += flight.getCapacity() * flight.getCost();
      }
      else {
        sum += flight.getNumPassengers() * flight.getCost();
      }
    }
    return sum;
  }

  public int updateFlights() {
    int sum = 0;
    for (int i=0; i<allFlights.size(); i++) {
      if ((double)(allFlights.get(i).getNumPassengers()) / allFlights.get(i).getCapacity() < 0.2) {
        sum += allFlights.get(i).getNumPassengers();
        allFlights.remove(i);
        i--;
      }
    }
    return sum;
  }
}
