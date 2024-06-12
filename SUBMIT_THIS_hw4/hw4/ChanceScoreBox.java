package hw4;

import api.ScoreBox;

/**
 * Score box that is satisfied by any Combination.
 * The score is the sum of all die values.
 * 
 * @author Vincent Wang
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class ChanceScoreBox extends Box
{
  /**
   * Constructs a ChanceScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
	private String display;
	private int score;
  public ChanceScoreBox(String displayName)
  {
    display = displayName;
  }

    

  @Override
  public boolean isSatisfiedBy(int[] arr)
  {
    return true;
  }

  @Override
  public int getPotentialScore(int[] arr)
  {
    for(int i = 0; i < arr.length; i++) {
    	score+=arr[i];
    }
    return score;
  }
  
  public String getDisplayName() {
		return display;
	}

}
