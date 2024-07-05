import java.util.*;

class Main {
  public static void main(String[] args) {
    Airport capitalHub = new Airport();
    
    Flight a = new Flight(25,50.00,30);
    capitalHub.addFlight(a);
    
    Flight b = new Flight(10,100.50,60);
    capitalHub.addFlight(b);
    
    Flight c = new Flight(50,200.00,40);
    capitalHub.addFlight(c);
    
    Flight d = new Flight(20,100.00,120);
    capitalHub.addFlight(d);

    System.out.println(capitalHub.getTotalRevenue());

    capitalHub.updateFlights();

    System.out.println(capitalHub.getTotalRevenue());
  }
}
