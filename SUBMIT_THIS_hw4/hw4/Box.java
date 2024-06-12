package hw4;


import api.ScoreBox;

/**
 * This is my abstract class. Duplicate scorebox methods are included in this class. 
 * @author Vincent Wang
 */
public abstract class Box implements ScoreBox {
	//Display name of the superbox
	private String display;
	//Score for the scorebox
	private int score = -1;
	//Variable with type combination
	private Combination var;
	 //Variable to check if a score box is filled
	private boolean filled = false;

	
	/**
	 * Determines whether this score box is filled.
	 * @return true if this score box has a fixed dice group and score, false otherwise
	 */
	public boolean isFilled() {
		if(filled && score != -1) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returns the score for this score box, or zero if the category is not filled.
	 * @return score for the score box or zero if not filled
	 */
	public int getScore() {
		if(isFilled()) {
			return score;
		}else {
			return 0;
		}
	}

	/**
	 * Returns the combination used to satisfy this category, or null if not filled.
	 * @return combination satisfying this category, or null if not done
	 */
	public Combination getDice() {
		if(isFilled()) {
			return var;
		}else {
			return null;
		}
	}

	/**
	 * Returns the name for this category.
	 * @return Score box name
	 */
	public abstract String getDisplayName();

	/**
	 * Permanently sets the combination being used to satisfy this category. The score is set to the value of getPotentialScore for this combination's completed dice.
	 * @param dice - combination to be used to satisfy this category
	 */
	public void fill(Combination dice) {
		var = dice;	
		filled = true;
	}

	/**
	 * Determines whether the given array of die values satisfies this score box. This method does not modify the state of this score box.
	 * @return true if the given values can be used to satisfy the score box, false otherwise
	 * @param arr - array to check, in increasing order
	 */
	public abstract boolean isSatisfiedBy(int[] arr);
		

	/**
	 * Returns the potential score that would result from using the given array of die values to satisfy this score box. Always returns zero if the isSatisfiedBy() method returns false for the given array. This method does not modify the state of this score box.
	 * @return potential score for the given die values
	 * @param arr - array to check, in increasing order
	 */
	public abstract int getPotentialScore(int[] arr);

	
}
