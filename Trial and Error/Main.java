public class Main {
  public static void main(String[] args) {
    String phrase = "Hello World!";
    trialAndErrorOrdered(phrase);
    System.out.println("------------------");
    trialAndErrorRandom(phrase);
  }

  public static void trialAndErrorOrdered(String intendedPhrase) {
    String cuurentPhrase = "";
    int place = 0;
    int attempt = 0;
    String[] characters ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","?","!",".",","," "};

    while (!cuurentPhrase.equals(intendedPhrase)) {
      for (String character : characters) {
        System.out.println(cuurentPhrase + character);
        attempt++;
        
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        
        if (character.equals(intendedPhrase.substring(place,place+1))) {
          cuurentPhrase += character;
          break;
        } 
      }
      place++;
    }
    System.out.println("\nThis took " + attempt + " attempts.");
  }

  public static void trialAndErrorRandom(String intendedPhrase) {
    String cuurentPhrase = "";
    int place = 0;
    int attempt = 0;
    String[] characters ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","?","!","."," "};

    while (!cuurentPhrase.equals(intendedPhrase)) {
      String character = ":)";
      while  (!character.equals(intendedPhrase.substring(place,place+1))) {
        character = characters[(int) (Math.random() * characters.length)];
        System.out.println(cuurentPhrase + character);
        attempt++;
        
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        if (character.equals(intendedPhrase.substring(place,place+1))) {
          cuurentPhrase += character;
          break;
        } 
      }
      place++;
    }
    System.out.println("\nThis took " + attempt + " attempts.");
  }
}
