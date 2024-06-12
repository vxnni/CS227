package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import api.GameConfiguration;
import api.ScoreBox;

/**
 * Game state for dice games such as MaxiYatzy.  The game includes a
 * <code>GameConfiguration</code> object along with two lists of 
 * <code>ScoreBox</code> objects, one for the upper section 
 * and one for the lower section. (Note that the only actual distinction
 * between the two sections is that the upper section scores are 
 * added up and checked to see whether they exceed the upper section 
 * bonus cutoff; if so, the upper section bonus is added to the score.)
 * This class is also responsible for
 * maintaining a current Combination (group of dice) and counting the number of 
 * rolls. 
 * <p>
 * Most of the game state is stored in the associated <code>ScoreBox</code>
 * objects, each of which knows its contribution to the overall
 * score, obtained via its <code>getScore</code> method.
 * 
 * @author Vincent Wang
 */
public class MaxiYatzy
{
	//Combination object
	private Combination combo;
	//Configuration object
	private GameConfiguration conf;
	//Random object
	private Random rando;
	//ScoreBox object
	private ScoreBox box;
	//ArrayList for uppersection scoreboxes
	private ArrayList<ScoreBox> upper = new ArrayList<>();
	//ArrayList for lowersection scoreboxes
	private ArrayList<ScoreBox> lower = new ArrayList<>();
	//Total score to be returned
	private int totalScore = 0;
  /**
   * Constructs a new MaxiYatzy game based on the given configuration. 
   * Initially the upper section and lower section lists are
   * empty.
   * @param config
   *   configuration to use for this game
   * @param rand
   *   random number generator to use for rolling dice
   */
	
  public MaxiYatzy(GameConfiguration config, Random rand)
  {
    conf = config;
    rando = rand;
  }
  
  /**
   * Adds a score box to the lower section of this game.
   * @param box
   *   score box to add
   */
  public void addLowerSectionScoreBox(ScoreBox box)
  {
    lower.add(box);
  }
  
  /**
   * Adds a score box to the upper section of this game.
   * @param box
   *   score box to add
   */  
  public void addUpperSectionScoreBox(ScoreBox box)
  {
    upper.add(box);
  }
  
  /**
   * Returns the list of score boxes in the upper section 
   * for this game.
   * @return
   *   list of score boxes in the upper section
   */
  public ArrayList<ScoreBox> getUpperSection()
  {
    return upper;
  }
  
  /**
   * Returns the list of score boxes in the lower section
   * for this game.
   * @return
   *   list of score boxes for the lower section
   */
  public ArrayList<ScoreBox> getLowerSection()
  {

	  return lower;
  }
  
  /**
   * Starts a new turn by creating a new Combination
   * and updating the available rolls according to this
   * game's configuration.  If there is already a
   * current Combination that is not complete, this
   * method does nothing.
   */
  public void startTurn()
  {
	  Combination newCombo = new Combination(conf.getNumDice());
	  int[] newValues = new int[combo.getNumDice()];
	  for(int i = 0; i < newValues.length; i++) {
		  newValues[i] = rando.nextInt();
	  }
	  newCombo.updateAvailableDice(newValues);
  }
  
  /**
   * Returns the remaining number of rolls for this turn.
   * The value returned is always zero if the current Combination
   * is complete, even if the configuration allows unused
   * rolls to be saved for future turns.
   * @return
   *   number of rolls
   */
  public int getRemainingRolls()
  {    
    return combo.getNumDice();
  }
  
  /**
   * Rolls the available dice in the current Combination.
   * That is, the available die values are replaced by randomly
   * generated values in the range 1 through the given maximum (according
   * to this game's configuration). If there are no more remaining
   * rolls, all available dice in the current Combination are
   * moved to the completed state.
   */
  public void rollAvailableDice()
  {
	  int[] newValues = new int[combo.getAvailableDice().length];
	  for(int i = 0; i < newValues.length; i++) {
		  newValues[i] = rando.nextInt(1, conf.getMaxValue());
	  }
	  combo.updateAvailableDice(newValues);
	  
	  if(combo.getNumDice() == 0) {
		  combo.chooseAll();
	  }
  }
  
  /**
   * Returns the current Combination (group of dice).  
   * @return
   *   current group of dice
   */
  public Combination getCurrentDice()
  {
    return box.getDice();
  }
  
  /**
   * Returns true if the game is over.  The game is considered over
   * when all score boxes are filled.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {
    if(box.isFilled()) {
    	return true;
    }else {
    	return false;
    }
  }
  
  /**
   * Returns the total score for the filled upper section score boxes
   * (not including the upper section bonus, if any).
   * @return
   *   upper section total score
   */
  public int getUpperSectionTotal()
  {
    for(int i = 0; i < upper.size(); i++) {
    	totalScore+=upper.get(i).getScore();
    }
    return totalScore;
  }
  
  /**
   * Returns the total score for the filled lower section score boxes.
   * @return
   *   lower section total score
   */
  public int getLowerSectionTotal()
  {
	  for(int i = 0; i < lower.size(); i++) {
	    	totalScore+=lower.get(i).getScore();
	    }
	    return totalScore;
  }
  
  /**
   * Returns the total score for all categories including the upper section
   * bonus, if any.
   * @return
   *   total score for all categories
   */
  public int getTotalScore()
  {
	return getLowerSectionTotal() + getUpperSectionTotal() + conf.getUpperSectionBonus();
  }
  
  /**
   * Returns the upper section bonus if one is currently being applied,
   * otherwise returns zero. 
   * @return
   *    upper section bonus if applicable, otherwise zero
   */
  public int getUpperSectionBonus()
  {
    return conf.getUpperSectionBonus();
  }

  public static void main(String[] args) {
	  GameConfiguration config = new GameConfiguration(
			  4, // four dice
			  5, // max value 5
			  4, // 4 rolls per turn
			  0, // upper section bonus
			  0, // upper section bonus cutoff
			  true); // extra rolls are saved for future turns
			  MaxiYatzy game = new MaxiYatzy(config, new Random(42));
			  game.addLowerSectionScoreBox(new MatchTargetScoreBox("Threes", 3));
			  game.addLowerSectionScoreBox(new ChanceScoreBox("Chance"));
			  ArrayList<ScoreBox> lower = game.getLowerSection();
			  for (ScoreBox b : lower)
			  {
			  System.out.println(b.getDisplayName());
			  }
			  System.out.println("Expected 'Threes', 'Chance' ");
			  
			  game.startTurn();
			  System.out.println(game.getRemainingRolls());
			  System.out.println("Expected 4");
			  Combination comb = game.getCurrentDice();
			  System.out.println(Arrays.toString(comb.getAvailableDice()));
			  System.out.println("Expected [0, 0, 0, 0]");

			  game.rollAvailableDice();
			  System.out.println(Arrays.toString(comb.getAvailableDice()));
			  System.out.println("(Expected four random nonzero values in range 1-5)");
			  System.out.println(game.getRemainingRolls());
			  System.out.println("Expected 3");
  }


}
