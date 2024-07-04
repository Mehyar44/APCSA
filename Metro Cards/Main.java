class Main {
  public static void main(String[] args) {
    MetroCard m1 = new MetroCard("Mehyar", 100.0, "1/31/2010", "yellow");
    
    System.out.println(m1+"\n");
    
    for (int i=0; i<100; i++) m1.isUsed(); 
  }
}
