package hw4;

/**
 * Score box for a large straight.  A Combination
 * with N dice satisfies this category only if it consists of
 * N distinct consecutive values.  For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author YOUR NAME HERE
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class LargeStraightScoreBox extends Box
{
	private String display;
	private int score;
	private int awardedPoints;
	private boolean satisfied = false;
  /**
   * Constructs a LargeStraightScoreBox with the given display name
   * and score.
   * @param displayName
   *   name of this score box
   * @param points
   *   points awarded for a dice group that satisfies this score box
   */  
  public LargeStraightScoreBox(String displayName, int points)
  {
	  display = displayName;
	  awardedPoints = points;
  }
@Override
public boolean isSatisfiedBy(int[] arr) {
	for(int i = 0; i < arr.length - 1; i++) {
		if(arr[i] + 1 == arr[i + 1]) {
			satisfied = true;
		}else {
			satisfied = false;
		}
	}
	return satisfied;
}
@Override
public int getPotentialScore(int[] arr) {
	for(int i = 0; i < arr.length; i++) {
		score+=arr[i];
	}
	return score;
}

public String getDisplayName() {
	return display;
}

public static void main(String[] args) {
	Box sb = new LargeStraightScoreBox("Large Straight", 10);
	System.out.println();
	System.out.println(" Large Straight");
	System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 5, 5}));
	System.out.println("Expected false");
	System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 5, 6}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {5, 6, 7, 8, 9, 10, 11, 12}));
	System.out.println("Expected true");
}
}
