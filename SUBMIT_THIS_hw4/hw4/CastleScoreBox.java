package hw4;

/**
 * Score box that is satisfied by a Combination including
 * at least three dice of one value and three of a different value.
 * The score is the sum of all die values.
 * 
 * @author Vincent Wang
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class CastleScoreBox extends Box
{
	private String display;
	private int score;
	
	private int counter1;
	private int counter2;
  /**
   * Constructs a CastleScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
  public CastleScoreBox(String displayName)
  {
	  display = displayName;
  }

@Override
public boolean isSatisfiedBy(int[] arr) {
	
	for(int i = 0; i < arr.length; i++) {
		if(arr[i] == arr[0]) {
			counter1++;
		}else if(arr[i] != arr[0]) {
			counter2++;
		}
	}
	if(counter1 >= 3 && counter2 >= 3) {
		return true;
	}else {
		return false;
	}
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
	
	Box sb = new CastleScoreBox("castle");
	System.out.println();
	System.out.println(" Castle");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 3, 4}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 3, 3, 4}));
	System.out.println("Expected true");
}
}
