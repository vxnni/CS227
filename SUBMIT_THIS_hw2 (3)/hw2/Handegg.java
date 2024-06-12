package hw2;
/**
* This class encapsulates the logic and state for a highly simplified
* game of American football.
*
* @author Vincent Wang
*/
public class Handegg{
/**
* Number of points awarded for a touchdown.
*/
	public static final int TOUCHDOWN_POINTS = 6;
/**
* Number of points awarded for a successful extra point attempt
* after a touchdown.
*/
	public static final int EXTRA_POINTS = 1;
/**
* Number of points awarded for a field goal.
*/
	public static final int FIELD_GOAL_POINTS = 3;
/**
* Total length of the field from goal line to goal line, in yards.
*/
	public static final int FIELD_LENGTH = 100;
/**
* Initial position of the offensive team after a touchdown and extra point
attempt.
*/
	public static final int STARTING_POSITION = 30;
/**
* Yards required to get a first down.
*/
	public static final int YARDS_FOR_FIRST_DOWN = 10;
	/**
	 * Current yard the current team is at
	 */
	private int currentYards = 30;
	/**
	 * The yard for a first down
	 */
	private int firstDown = 40;
	/**
	 * Stores the current down from 1-4
	 */
	private int currentDown = 1;
	/**
	 * Stores the current team on offense
	 */
	private int currentOffenseTeam = 0;
	/**
	 * Stores the score for team one
	 */
	private int teamOneScore = 0;
	/**
	 * Stores the score for team two
	 */
	private int teamTwoScore = 0;
	/**
	 * Records the result of an extra point attempt, whether or not the attempt is successful, the defense gets the ball and starts with a first down
	 * @param true if the extra point was successful, false otherwise
	 */
	public void extraPoint(boolean success) {
		if(success) {
			if(currentOffenseTeam == 0) {
				teamOneScore += 1;
				currentOffenseTeam = 1;
				currentYards = 30;
			}else if(currentOffenseTeam == 1){
				teamTwoScore += 1;
				currentOffenseTeam = 0;
				currentYards = 30;
			}
		}else if(!success){
			if(currentOffenseTeam == 0) {
				currentOffenseTeam = 1;
				currentYards = 30;
			}else {
				currentOffenseTeam = 0;
				currentYards = 30;
			}
		}
		currentDown = 1;
	}
	/**
	 * Records the result of a field goal attempt, adding FIELD_GOAL_POINTS points if the field goal was successful. If the attempt is successful, the defense gets the ball and starts with a first down, STARTING_POSITION yards from its goal line. If the attempt is unsuccessful, the defense gets the ball at its current location.
	 * @param success true if the attempt was successful, false otherwise
	 */
	public void fieldGoal(boolean success) {
		if(success) {
			if(currentOffenseTeam == 0) {
				teamOneScore += 3;
				currentOffenseTeam = 1;
				currentYards = 30;
				firstDown = 40;
			}else {
				teamTwoScore += 3;
				currentOffenseTeam = 0;
				currentYards = 30;
				firstDown = 40;
			}
		}else if(success == false){
			if(currentOffenseTeam == 0) {
				currentOffenseTeam++;
				currentYards = 100 - currentYards;
				//firstDown = currentYards += 10;
			}else {
				currentOffenseTeam--;
				currentYards = 100 - currentYards;
			}
		}
	}
	/**
	 * Returns the location of the ball as the number of yards from the offense's own goal line.
	 * @return number of yards from the offense's goal line to the ball
	 */
	public int getLocation() {
		return currentYards;
	}
	/**
	 * Returns the points for the indicated team.
	 * @param whichTeam team index 0 or 1
	 * @return score for team 0 if the given argument is 0, score for team 1 otherwise
	 */
	public int getScore(int whichTeam) {
		if(whichTeam == 0) {
			return teamOneScore;
		}else if(whichTeam == 1){
			return teamTwoScore;
		}else {
			return 0;
		}
	}
	/**
	 * Returns the number of yards the offense must advance the ball to get a first down.
	 * @return number of yards to get a first down
	 */
	public int getYardsToFirstDown() {
		if(currentDown != 1) {
		//if((currentYards + 10) % 10 == 0){
			return Math.abs((firstDown - currentYards));
		}else {
			return 10;
		}
	}
	/**
	 * Simulates passing in football
	 * @param yards number of yards (possibly negative) the ball is advanced
	 * @param intercepted true if the pass is intercepted, false otherwise
	 */
	public void pass(int yards, boolean intercepted) {
		
		if(!intercepted) {
			currentYards += yards;
			if(currentYards >= firstDown) {
				firstDown = currentYards + 10;
				currentDown = 1;
			}else if (currentYards < firstDown) {
				currentDown++;
			}
			
			if(currentYards > 100) {
				if(currentOffenseTeam == 0) {
					teamOneScore += 6;
				}else {
					teamTwoScore += 6;
				}
			}
		}else {
			if(currentOffenseTeam == 1) {
				currentOffenseTeam = 0;
				currentDown = 1;
				currentYards = 100 - (currentYards + yards);
				firstDown = currentYards + 10;
			}else {
				currentOffenseTeam = 1;
				currentDown = 1;
				currentYards = 100 - (currentYards + yards);
				firstDown = currentYards + 10;
			}
			
			if(currentYards < 0) {
				currentYards = 0;
			}
		}
		
		if(currentDown > 4) {
			if(currentOffenseTeam == 1) {
				currentOffenseTeam = 0;
				currentDown = 1;
				currentYards = 100 - currentYards;
			}else {
				currentOffenseTeam = 1;
				currentDown = 1;
				currentYards = 100 - currentYards;
			}
		}
	}
	/**
	 * Records the result of a punt. The defense gets the ball with a first down after it has advanced the given number of yards; however, if the ball would have advanced past the defense's goal line, the defense starts with the ball at location 0 (i.e. it can't be behind their own goal line), and if the ball would have ended up behind the offense's own goal line, the defense starts at location FIELD_LENGTH.
	 * @param yards number of yards the ball is advanced by the punt
	 */
	public void punt(int yards) {
		if(currentOffenseTeam == 0) {
			currentOffenseTeam = 1;
		}else if(currentOffenseTeam == 1) {
			currentOffenseTeam = 0;
		}
		if(100 - (currentYards + yards) < 0) {
			currentYards = 0;
		}else {
			currentYards = (100 - (currentYards + yards));
		}
	}
	/**
	 * Simulates running in football
	 * @param yards number of yards (possibly negative) the ball is advanced
	 */
	public void run(int yards) {
		if(currentYards < 100) {
			currentYards += yards;
		}
		if(currentYards >= firstDown) {
			firstDown = currentYards + 10;
			currentDown = 1;
		}else if (currentYards < firstDown) {
			currentDown++;
		}
		if(currentYards < 0) {
			currentYards = 0;
		}
		
		if(currentDown > 4) {
			if(currentOffenseTeam == 1) {
				currentOffenseTeam = 0;
				currentDown = 1;
				currentYards = 100 - currentYards;
				firstDown = currentYards + 10;
			}else {
				currentOffenseTeam = 1;
				currentDown = 1;
				currentYards = 100 - currentYards;
				firstDown = currentYards + 10;
			}
		}
		if(currentYards > 100) {
			if(currentOffenseTeam == 0) {
				teamOneScore += 6;
			
			}else {
				teamTwoScore += 6;
				
			}
		}
	}
	/**
	 * Returns the current down. Possible values are 1 through 4.
	 * @return the current down as a number 1 through 4
	 */
	public int whichDown() {
			
			if(currentYards >= firstDown) {
				firstDown = currentYards + 10;
				return 1;
			}else if(currentYards < firstDown) {
				if(currentYards == 30) {
					
				}
			}else {
				if(currentOffenseTeam == 0) {
					currentOffenseTeam = 1;
					currentDown = 1;
					currentYards = 100 - currentYards;
				}else {
					currentOffenseTeam = 0;
					currentDown = 1;
					currentYards = 100 - currentYards;
				}
			}
		return currentDown;
	
	}
	/**
	 * Returns the index for the team currently playing offense.
	 * @return index of the team playing offense (0 or 1)
	 */
	public int whoIsOffense() {
		if(currentDown > 4) {
			if(currentOffenseTeam == 1) {
				return 0;
			}else {
				return 1;
			}
		}
		return currentOffenseTeam;
	}
	
	
	
	
	
	
	
	
}

