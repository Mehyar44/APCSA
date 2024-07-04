import java.util.*;

public class Container {
  private ArrayList<Candy> container;

  public Container() {
    container = new ArrayList<Candy>();
  }

  public void addCandy(Candy c) {
    container.add(c);
  }
  
  public int totalTotalCals() {
    int sum = 0;
    for (Candy candy : container) sum += candy.getCals() * candy.getQuantity(); 
    return sum;
  }

  public double totalTotalCost() {
    double sum = 0;
    for (Candy candy : container) sum += candy.totalCost(); 
    return sum;
  }

  public String removeBrand(String b) {
    int sum = 0;
    for (int i=container.size()-1; i>=0; i--) {
      if (container.get(i).getBrand().equals(b)) {
        sum += container.get(i).getQuantity();
        container.remove(i);
      }
    }
    return sum + "pieces of candy were removed";
  }
}
