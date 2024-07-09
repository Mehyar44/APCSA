public class Card {
  private String character;
  private String symbol;

  public Card(String c, String s) {
    character = c;
    symbol = s;
  }

  public String toString() {
    String result = "";
    if (symbol.equals("spades")) result += "\u001B[47m\u001B[30m" + character + "♠\u001B[0m";
    else if (symbol.equals("clubs")) result += "\u001B[47m\u001B[30m" + character + "♣\u001B[0m";
    else if (symbol.equals("diamonds")) result += "\u001B[47m\u001B[31m" + character + "♦\u001B[0m";
    else result += "\u001B[47m\u001B[31m" + character + "♥\u001B[0m";
    return result;
  }
}
