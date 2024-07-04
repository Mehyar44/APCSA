public class Candy {
  private String brand;
  private int quantity;
  private int calories;
  private double cost;

  public Candy(String b, int q, int cal, double c) {
    brand = b;
    quantity = q;
    calories = cal;
    cost = c;
  }

  public String getBrand() {
    return brand;
  }

  public int getQuantity() {
    return quantity;
  }
  
  public int getCals() {
    return calories;
  }

  public double totalCost() {
    return cost*quantity;
  }
}
