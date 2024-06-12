package hw4;

/**
 * Score box for all dice the same.  A Combination
 * with N dice satisfies this category only if all N
 * values are the same.  For a Combination that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Vincent Wang
 */
// TODO: this class must implement ScoreBox or extend another class that does
public class AllMatchScoreBox extends Box
{
  /**
   * Constructs a AllMatchScoreBox with the given display name
   * and score.
   * @param displayName
   *   name of this score box
   * @param points
   *   points awarded for a combination that satisfies this score box
   */  
	private String display;
	private int score;
	private boolean satisfied = true;
  public AllMatchScoreBox(String displayName, int points)
  {
    display = displayName;
    score = points;
  }

@Override
public boolean isSatisfiedBy(int[] arr) {
	int num = arr[0];
	for(int i = 0; i < arr.length; i++) {
		if(arr[i] != num) {
			satisfied = false;
		}
	}
	return satisfied;
}

@Override
public int getPotentialScore(int[] arr) {
	return score;
}

public String getDisplayName() {
	return display;
}

public static void main(String[] args) {
	Box sb = new AllMatchScoreBox("Match", 21);
	  int[] test1 = {3, 3, 3, 3, 3, 3, 3};
	  System.out.println(sb.isSatisfiedBy(test1));
	  System.out.println("Expected true");
	  int[] test = {3, 3, 3, 4, 3, 3, 3};
	  System.out.println(sb.isSatisfiedBy(test));
	  System.out.println("Expected false");
	  System.out.println(sb.getPotentialScore(test));
	  System.out.println("Expected 21");
	  
	  System.out.println(sb.getDisplayName());
}
}
