public class MetroCard {
  
    // Instance Variables
    private String name; // Name of the cardholder
    private double balance; // Current balance on the card
    private String expiration; // When does the card expire
    private String color; // Color of card (Yellow is regular - Orange is half off - Green and Blue are free.)
    private int speed; // Speed of the swipe in inches per seconds

    // Constructor
    public MetroCard(String n, double b, String e, String c) {
      name = n;
      balance = b;
      expiration = e;
      color = c;
    }

    // Getter Methods
    public String toString() {
      return "Name: " + name + "\nBalance: " + balance + "\nExpiration Date: " + expiration + "\nColor: " + color;
    }
  
    public String getName() {
        return name;
    }

    public double getBalance() {
      return balance;
    }

    public String getExpiration() {
      return expiration;
    }
    
    public String getColor() {
      return color;
    }

    // Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
      this.balance = balance;
    }

    public void setExpiration(String expiration) {
      this.expiration = expiration;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public void addFunds(double funds) {
      balance += funds;
    }
    
    // Checker Methods
    public boolean checkExpiration(String e) {
      // Get Current Date
      int cMonth = (int) (Math.random()*12)+1;
      int cYear = (int) (Math.random()*20)+2000;
      int cDay;
      if (cMonth == 4 || cMonth == 6 || cMonth == 9 || cMonth == 11) {
        cDay = (int)(Math.random()*30) + 1;
      }
      else if (cMonth==2) {
        cDay = (int)(Math.random()*28) + 1;
      }
      else {
        cDay = (int)(Math.random()*31) + 1;
      }
      
      // Get Expiration Date
      int slash1 = e.indexOf("/");
      int slash2 = 0;
      for (int i=slash1+1; i<e.length(); i++) {
        if (e.substring(i,i+1).equals("/")) {
          slash2 = i;
        }
      }
      
      int eMonth = Integer.parseInt(e.substring(0,slash1));
      int eDay = Integer.parseInt(e.substring(slash1+1,slash2));
      int eYear = Integer.parseInt(e.substring(slash2+1,e.length()));

      // Compare
      if (cYear > eYear) {
        return true;
      }

      else if (cYear == eYear && cMonth > eMonth) {
        return true;
      }

      else if (cMonth == eMonth && cDay > eDay) {
        return true;
      }

      else {
        return false;
      }
    }

    public void checkColorAndBalance() {
      // Case Yellow
      if (color.equals("yellow")) {
        if (balance >= 2.9) {
          balance -= 2.9;
          System.out.println("GO");
        }
        else {
          System.out.println("NOT ENOUGH MONEY");
        }
      }

      // Case Orange
      else if (color.equals("orange")) {
        if (balance >= 1.45) {
          balance -= 1.45;
          System.out.println("GO");
        }
        else {
          System.out.println("NOT ENOUGH MONEY");
        }
      }

      // Case Green or Blue
      else {
        System.out.println("GO");
      }
    }

    public void checkSpeed() {
      // Get Random Speed
      speed = (int) (Math.random()*100)+1;

      // Correct swipe is between 10-40 inches per second
      while (speed>40 || speed<10) {
        System.out.println("SWIPE AGAIN"); // L
        speed = (int) (Math.random()*40)+1;
      }
    }

    // Using the metro card 
    public void isUsed() {
      if (checkExpiration(expiration)) {
        System.out.println("CARD EXPIRED");
      }

      else {
        checkSpeed();
        checkColorAndBalance();
      }

      System.out.println("------------------------------\n");
    }
  
} 
