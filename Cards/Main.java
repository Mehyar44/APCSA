public class Main {
  public static void main(String[] args) {
    String[] characters = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    String[] symbols = {"spades", "clubs", "diamonds", "hearts"};
    int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10}; 

    for (String symbol : symbols) {
      String line = null;
      for (int i = 0; i < characters.length; i++) {
        Card card = new Card(characters[i], symbol, values[i]);
        if (line != null) line += " ";
        else line = "";
        line += card.toString();
      }
      System.out.println(line + "\n");
    }
  }
}
