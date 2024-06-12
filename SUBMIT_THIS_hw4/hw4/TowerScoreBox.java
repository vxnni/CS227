package hw4;

import java.util.HashMap;


/**
 * Score box that is satisfied by a Combination including at least
 * four dice of one value and two of a different value
 * The score is the sum of all die values.
 * 
 * @author Vincent Wang
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class TowerScoreBox extends CastleScoreBox
{
  /**
   * Constructs a TowerScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
  public TowerScoreBox(String displayName)
  {
    super(displayName);
  }

@Override
public boolean isSatisfiedBy(int[] arr) {
	boolean fourOccurrences = false;
	boolean twoOccurrences = false;
	HashMap<Integer, Integer> counter = new HashMap<>();
    for (int element : arr) {
        counter.put(element, counter.getOrDefault(element, 0) + 1);
        
    }

    for (int count : counter.values()) {
        if (count >= 4) {
            fourOccurrences = true;
        }else if (count >= 2) {
            twoOccurrences = true;
        }
    }

    if(fourOccurrences == true && twoOccurrences == true) {
  	  return true;
    }else {
  	  return false;
    }
    }

public String getDisplayName() {
	return getDisplayName();
}

public static void main(String[] args) {
	Box sb = new TowerScoreBox("Tower Score Box");
	System.out.println();
	System.out.println(" Tower Score Box");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 2, 3, 3, 4}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 2, 3, 3, 3, 4}));
	System.out.println("Expected true");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 3, 4}));
	System.out.println("Expected false");
	System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 3, 3, 3, 3, 4, 4}));
	System.out.println("Expected true");
}
}
