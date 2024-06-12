package hw4;

/**
 * Score box for a given number N of matching dice, where N is specified
 * in the constructor.  A Combination satisfies this category only if it has 
 * (at least) N dice that are the same.  For a Combination that satisfies 
 * this category, the score is the sum of the N dice that have the same value.
 * If there are multiple groups of N with the same value, the group with highest value 
 * is used for the score.  For example, if N is 3 and the combination
 * is (2, 2, 2, 5, 5, 5, 5, 6), the score is 15.
 * 
 * @author Vincent Wang
 */

//TODO: this class must implement ScoreBox or extend another class that does
public class NOfAKindScoreBox extends Box
{
	private String display;
	private int amount;
	private int counter;
	private int dup;
  /**
   * Constructs a NOfAKindScoreBox with the given display name
   * and score.
   * @param displayName
   *   name of this score box
   * @param howMany
   *   how many dice must match to satisfy this score box
   */  
  public NOfAKindScoreBox(String displayName, int howMany)
  {
    display = displayName;
    amount = howMany;
  }
@Override
public boolean isSatisfiedBy(int[] arr) {
	int dup = arr[0];
	for(int i = 0; i < arr.length - 1; i++) {
		if(arr[i] == dup) {
			counter++;
		}else if(arr[i] == dup) {
			dup = arr[i];
		}
	}
	
	if(counter >= amount) {
		return true;
	}else {
		return false;
	}
}
@Override
public int getPotentialScore(int[] arr) {
	return dup*counter;
}

public String getDisplayName() {
	return display;
}

public static void main(String[] args) {
	Box sb = new NOfAKindScoreBox("3 of a kind", 3);
	System.out.println();
	System.out.println(" 3 of a kind");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 3, 3, 4}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 4, 4, 4, 5}));
	System.out.println("Expected true");
}
}
