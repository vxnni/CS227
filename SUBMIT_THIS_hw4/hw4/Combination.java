  package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Group of dice for a dice game such as MaxiYatzy in which 
 * multiple rolls per turn are allowed.  The dice are partitioned
 * into "available" dice and "complete" dice.  The available
 * dice can be re-rolled (that is, they can get new values via the
 * <code>update</code> method). The client can select which dice 
 * to move from available to complete.
 * Once all dice are complete, as determined
 * by the <code>isComplete()</code> method, the <code>getAvailable()</code>
 * method always returns an empty array, and the state of the
 * combination can no longer be modified.
 * 
 * @author Vincent Wang
 */
public class Combination
{  
	private ArrayList<Integer> available = new ArrayList<>();
	private ArrayList<Integer> completed = new ArrayList<>();
  /**
   * Constructs a new Combination in which each die initially has 
   * the (invalid) value zero and all dice are available. In normal usage, 
   * a client would replace the available die values with randomly generated
   * numbers in an appropriate range, to simulate rolling the dice.
   * @param numDice
   *   number of dice in this group
   */
  public Combination(int numDice)
  {
    available = new ArrayList<>(numDice);
  }   
  
  /**
   * Constructs a new Combination in which each die initially has 
   * the value given by the <code>initialValues</code> array.
   * All dice are initially available. The number of dice is determined
   * by the size of the given array.
   * <p>
   * This version of the constructor is primarily intended for testing.
   * @param initialValues
   *   initial values for the dice
   */
  public Combination(int[] initialValues)
  {
	  for(int i = 0; i < initialValues.length; i++) {
		  available.add(initialValues[i]);
	  }
  }  
  
  /**
   * Returns the number of dice in this group.
   * @return
   *   number of dice in this group
   */
  public int getNumDice()
  {
    return available.size();
  }

  /**
   * Selects a die value to be moved from the available dice to the
   * completed dice. Has no effect if the given value is 
   * not among the values in the available dice. 
   * @param value
   *   die value to be moved
   */
  public void choose(int value)
  {
	if(available.contains(value)) {
		completed.add(value);
		int pos = available.indexOf(value);
		available.remove(pos);
		
	}
  }

  /**
   * Causes all die values be moved from the available dice to the
   * completed dice. After this method is called, <code>getAvailable</code>
   * always returns an empty array, and <code>isComplete</code> returns true.
   */
  public void chooseAll()
  {
	  for(int i = 0; i < available.size(); i++) {
		  completed.add(available.get(i));
	  }
	  available.removeAll(available);
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this combination.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete()
  {
    if(available.toArray().length > 0) {
    	return false;
    }else {
    	return true;
    }
  }

  /**
   * Returns the values of the dice that are not available
   * to be re-rolled, in ascending order.
   * @return
   *   values of the dice that are not available to be re-rolled
   */
  public int[] getCompletedDice()
  {
	completed.sort(null);
	int [] completedDice = new int[completed.size()];
	int index = 0;
	for(int i: completed) {
		completedDice[index] = i;
		index++;
	}
    return completedDice;
  }
  
  /**
   * Returns the values of the dice that are available to
   * be re-rolled, in ascending order.
   * @return
   *   dice that are available to be re-rolled
   */
  public int[] getAvailableDice()
  {
	  available.sort(null);
	  int [] availableDice = new int[available.size()];
		int index = 0;
		for(int i: available) {
			availableDice[index] = i;
			index++;
		}
	    return availableDice;
  }
 
  /**
   * Returns all die values in this combination, in ascending order.
   * @return
   *   all die values in this group
   */
  public int[] getAll()
  {
	  ArrayList<Integer> all = new ArrayList<>();
	  for(int i = 0; i < available.size(); i++) {
		  all.add(available.get(i));
	  }
	  for (int i = 0; i < completed.size(); i++) {
		  all.add(completed.get(i));
	  }
	  all.sort(null);
	  
	  int [] availableDice = new int[all.size()];
		for(int i = 0; i < all.size(); i++) {
			availableDice[i] = all.get(i);
		}
	    return availableDice;
  }
  
  /**
   * Replaces the available dice with the given values.
   * Throws an IllegalStateException if the length of
   * the given array does not match the number of available
   * dice in this Combination.
   * @param newValues 
   *   array of new die values for available dice
   * @throws IllegalStateException
   *   if the given array has the wrong length
   */
  public void updateAvailableDice(int[] newValues)
  {
	  if(newValues.length != available.size()) {
		  throw new IllegalStateException();
	  }else {
		  for(int i = 0; i < newValues.length; i++) {
			  available.set(i, newValues[i]);
		  }
	  }
  }
  
  /**
   * Returns a string representation of the die values in
   * this combination, in the form <em>available</em>(<em>complete</em>).
   * (For example, if there are two completed dice with values 2
   * and 4, and there are 3 available dice with values 1, 1, and 6,
   * then the method returns the string
   * <pre>
   * 1 1 6 (2 4)
   * </pre>
   * @return 
   *   string representation of the available and completed dice,
   *   with the completed values in parentheses
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    int[] avail = getAvailableDice();
    int[] completed = getCompletedDice();
    if (avail.length > 0)
    {
      for (int value : avail)
      {
        sb.append(value + " ");
      }
    }
    sb.append("(");
    if (completed.length > 0)
    {
      // use an index so we can add the first one without a leading space
      sb.append(completed[0]);
      for (int i = 1; i < completed.length; ++i)
      {
        sb.append(" " + completed[i]);
      }
    }
    sb.append(")");
    return sb.toString();
  }
  
  public static void main(String[] args) {
	  Combination c = new Combination(new int[] {1, 3, 2, 5, 2});
	  System.out.println(Arrays.toString(c.getAvailableDice()));
	  System.out.println("Expected [1, 2, 2, 3, 5]");
	  System.out.println(Arrays.toString(c.getCompletedDice()));
	  System.out.println("Expected []");
	  System.out.println();
	  
	  c.choose(3);
	  c.choose(2);
	  System.out.println(Arrays.toString(c.getAvailableDice()));
	  System.out.println("Expected [1, 2, 5]");
	  System.out.println(Arrays.toString(c.getCompletedDice()));
	  System.out.println("Expected [2, 3]");
	  System.out.println(Arrays.toString(c.getAll()));
	  System.out.println("Expected [1, 2, 2, 3, 5]");
	  System.out.println();
	  
	  System.out.println(c.isComplete());
	  System.out.println("Expected false");
	  System.out.println();
	  
	  c.updateAvailableDice(new int[] {6, 4, 2});
	  System.out.println(Arrays.toString(c.getAvailableDice()));
	  System.out.println("Expected [2, 4, 6]");
	  System.out.println(Arrays.toString(c.getCompletedDice()));
	  System.out.println("Expected [2, 3]");
	  System.out.println(Arrays.toString(c.getAll()));
	  System.out.println("Expected [2, 2, 3, 4, 6]");
	  System.out.println();
	 
	  c.chooseAll();
	  System.out.println(Arrays.toString(c.getAvailableDice()));
	  System.out.println("Expected []");
	  System.out.println(Arrays.toString(c.getCompletedDice()));
	  System.out.println("Expected [2, 2, 3, 4, 6]");
	  System.out.println(Arrays.toString(c.getAll()));
	  System.out.println("Expected [2, 2, 3, 4, 6]");
	  System.out.println();
	  // should be complete now
	  System.out.println(c.isComplete());
	  System.out.println("Expected true");
	  System.out.println();
  }
}
