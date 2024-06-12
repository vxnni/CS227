package hw4;

/**
 * Score box for a short straight.  A Combination
 * with N dice satisfies this category only if it includes
 * N - 1 distinct consecutive values.  For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Vincent Wang
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class ShortStraightScoreBox extends Box
{
	private String display;
	private int score;
	private int counter;
  /**
   * Constructs a SmallStraightScoreBox with the given display name
   * and score.
   * @param displayName
   *   name of this score box
   * @param points
   *   points awarded for a dice group that satisfies this score box
   */  
  public ShortStraightScoreBox(String displayName, int points)
  {
    display = displayName;
    score = points;
  }

@Override
public boolean isSatisfiedBy(int[] arr) {
	for(int i = 0; i < arr.length - 1; i++) {
		if(arr[i] + 1 != arr[i + 1]) {
			counter++;
		}
	}
	if(counter != 1) {
		return false;
	}else {
		return true;
	}
}

@Override
public int getPotentialScore(int[] arr) {
	return score;
}

public String getDisplayName() {
	return display;
}

public static void main(String[] args) {
	Box sb = new ShortStraightScoreBox("Short Straight Score Box", 20);
	System.out.println();
	System.out.println(" Short Straight");
	System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 3, 4, 5, 6, 7}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 3, 4, 5, 5, 6, 7}));
	System.out.println("Expected false");
}
}
