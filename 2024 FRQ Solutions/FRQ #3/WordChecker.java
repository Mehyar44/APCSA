
import java.util.*;

public class WordChecker {
  private ArrayList<String> wordList;

  public WordChecker(ArrayList<String> words) {
    wordList = words;
  }

  public boolean isWordChain() {
    for (int i=1; i<wordList.size(); i++) {
      if (wordList.get(i).indexOf(wordList.get(i-1)) == -1) return false;
    }
    return true;
  }


  public ArrayList<String> createList(String target) {
    ArrayList<String> result = new ArrayList<String>();
    for (String word : wordList) {
      if (word.length() >= target.length()) 
        if (word.substring(0,target.length()).equals(target)) 
          result.add(word.substring(target.length()));
    }
    return result;             
  }
}
