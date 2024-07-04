public class Scoreboard {
  private String name1;
  private String name2;
  private int score1=0;
  private int score2=0;
  private boolean isTeamOne = true;

  public Scoreboard(String n1, String n2) {
    name1 = n1;
    name2 = n2;
  }

  public String getScore() {
    if (isTeamOne) return score1 + "-" + score2 + "-" + name1;
    return score1 + "-" + score2 + "-" + name2;
  }

  public void recordPlay(int score) {
    if (score==0) isTeamOne = !isTeamOne;
    else if (isTeamOne) score1 += score;
    else score2 += score;
  }
}
