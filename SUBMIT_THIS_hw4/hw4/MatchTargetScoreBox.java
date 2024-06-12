package hw4;



/**
 * Score box that is based on counting dice that match
 * a particular target value (as specified in the constructor).
 * This category is satisfied by any Combination.  The score
 * is the sum of the dice that match the target.
 * 
 * @author Vincent Wang
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class MatchTargetScoreBox extends Box
{
  /**
   * Constructs a MatchTargetScoreBox with the given display name and 
   * target value.
   * @param displayName
   *   name for this score box
   * @param whichValue
   *   target value that must be matched for the dice to count toward the
   *   score
   */
	private String display;
	private int target;
	private int score = 0;
	
  public MatchTargetScoreBox(String displayName, int whichValue)
  {
    display = displayName;
    target = whichValue;
  } 

  
  public static void main(String[] args) {
	  Box sb = new MatchTargetScoreBox("Threes", 3);
	  int[] test = {1, 3, 3, 3, 3, 5, 6};
	  System.out.println(sb.isSatisfiedBy(test));
	  System.out.println("Expected true");
	  System.out.println(sb.getPotentialScore(test));
	  System.out.println("Expected 12");
	  
	  System.out.println(sb.isFilled());
	  System.out.println("Expected false");
	  System.out.println(sb.getScore());
	  System.out.println("Expected 0");
	  
  }





@Override
public boolean isSatisfiedBy(int[] arr) {
	return true;
}

@Override
public int getPotentialScore(int[] arr) {
	for(int i = 0; i < arr.length; i++) {
		if(arr[i] == target) {
			score+=arr[i];
		}
	}
	return score;
}


public String getDisplayName() {
	return display;
}
}
