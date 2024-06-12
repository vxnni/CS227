package hw3;

import java.util.ArrayList;

import java.util.Random;
import api.Direction;
import api.ShiftDescriptor;
import api.TileInfo;

import java.util.Arrays;
/**
 * Utility class containing some elements of the basic logic for 
 * performing moves in the game AlphabetSoup.
 * @author Vincent Wang
 */
public class AlphabetUtil
{  
  /**
   * Constructor does nothing, since this object is stateless.
   */
  public AlphabetUtil()
  {
    // do nothing
  }  
  
  /**
   * Returns the result of merging the two given tile values, or zero
   * if they can't be merged.  The rules are: two values can be merged
   * if they are the same, and are not zero.  The resulting value of a 
   * merge is always the current value plus 1.
   * @param a
   *   given tile value
   * @param b
   *   given tile value
   * @return
   *   result of merging the two values, or zero if no merge is possible
   */
  public int findMergedValue(int a, int b)
  {
    if((a == b) && (a != 0)) {
    	return a + 1;
    }else {
    	return 0;
    }
  }

  /**
   * Returns the score for a single tile. If the given value is 
   * greater than zero, the result is three to the power (value - 1).
   * (For example, if the given value is 4, the method returns 27.)
   * Otherwise the method returns zero. 
   * @param value
   *   tile value for which to compute the score
   * @return
   *   score for the given tile value
   */
  public int getScoreForOneTile(int value)
  {
    if(value > 0) {
    	return (int)Math.pow(3, value - 1);
    }else {
    	return 0;
    }
  }
  
  /**
   * Generate a new tile value using the given instance
   * of Random.  Values generated are either 1 or 2,
   * with equal probability.
   * @param rand
   *   random number generator to use
   * @return
   *   the value 1 or 2, with equal probability
   */
  public int randomNewTileValue(Random rand)
  {
    return rand.nextInt(2) + 1;
  }
  
  /**
   * Shifts the array elements to the left according to the rules of the 
   * AlphabetSoup game. This method only operates on a one-dimensional array 
   * of integers representing the tile values in one row or column,
   * and it only shifts to the left.
   * The AlphabetSoup class can use this method to shift a row or column, in any 
   * direction, by copying that row or column, either forward or backward, 
   * into a temporary one-dimensional array to be passed to this method. 
   * The rules are that if there is a pair of adjacent cells
   * that can be merged (according to the method <code>findMergedValues()</code>), 
   * and it has no empty (zero) cells to its left,
   * then the leftmost such pair of cells is merged and everything to 
   * its right is shifted left by one cell. Otherwise, if there is an empty
   * cell, then everything to the right of the leftmost empty cell is 
   * shifted left by one cell. Otherwise, the array is unmodified and
   * an empty list is returned.
   * <p>
   * The new value for a pair of merged cells
   * is determined by the method <code>findMergedValues()</code>.
   * The method returns a list of ShiftDescriptor objects representing
   * the moved cells.  All returned ShiftDescriptor objects will have 
   * unspecified row/column and direction (typically filled in later by
   * the associated AlphabetSoup instance).  The list of returned
   * descriptor objects is in no particular order.
   * 
   * @param arr
   *   array to be shifted
   * @return
   *   list of all moves and/or merges performed in the shift
   */
  public ArrayList<ShiftDescriptor> doShift(int[] arr)
  {
	ArrayList<ShiftDescriptor> shift = new ArrayList<ShiftDescriptor>();
	
	int zeroIndex = findIndex(arr, 0);
	boolean canMerge = false;
	int temp;
	
	temp = arr[0];
	for(int i = 0; i < arr.length - 1; i++) 
	{ 
		if(arr[i] == 0) {
			zeroIndex = i;
			
		}
		if(arr[i] == arr[i + 1]) {
			canMerge = true;
		
		}
		if(canMerge && i > zeroIndex) {
			shift.add(new ShiftDescriptor(i + 1, arr[i + 1], findMergedValue(arr[i], arr[i+1])));
			arr[i] = findMergedValue(arr[i], arr[i+1]);
			arr[i+1] = 0;
			
		}else {
			shift.add(new ShiftDescriptor(arr[i + 1], arr[i]));
			
			arr[i]=arr[i+1];
			
		}
		
	} 
	
	arr[arr.length - 1] = 0;
	if(temp != 0) {
		arr[0] =temp; 
	}
	
	return shift;
  }

  private static int findIndex(int arr[], int t) 
  { 

 
      if (arr == null) { 
          return -1; 
      } 

      int length = arr.length; 
      int i = 0; 

      while (i < length) { 
 
          if (arr[i] == t) { 
              return i; 
          } 
          else { 
              i = i + 1; 
          } 
      } 
      return -1; 
  }
  
  
  public static void main(String args[]) {
	 AlphabetUtil util = new AlphabetUtil();
	// test get score for a tile
	int score = util.getScoreForOneTile(4);
	System.out.println(score);
	System.out.println("Expected 27");
	// test merge
	int canMerge = util.findMergedValue(5, 5);
	System.out.println(canMerge);
	System.out.println("Expected 6");
	canMerge = util.findMergedValue(2, 3);
	System.out.println(canMerge);
	System.out.println("Expected 0");
	
	int[] test2 = {0, 1, 3, 0, 2};
	util.doShift(test2);
	System.out.println(Arrays.toString(test2));
	System.out.println("Expected [1, 3, 0, 2, 0]");
	
	int[] test3 = {7, 0, 3, 0, 2};
	util.doShift(test3);
	System.out.println(Arrays.toString(test3));
	System.out.println("Expected [7, 3, 0, 2, 0]");
	
	int[] test = {1, 3, 3, 2};
	util.doShift(test);
	System.out.println(Arrays.toString(test));
	System.out.println("Expected [1, 4, 2, 0]");
	  
	
	int[] test4 = {7, 0, 3, 0, 2};
	ArrayList<ShiftDescriptor> descriptors = util.doShift(test4);
	System.out.println(descriptors);
	System.out.println("Expected:");
	System.out.println("[Move c 2 to 1, Move b 4 to 3]");
	  
	int[] test5 = {1, 3, 3, 2};
	descriptors = util.doShift(test5);
	System.out.println(descriptors);
	System.out.println("Expected:");
	System.out.println("[Merge c 2 to 1, Move b 3 to 2]");
	
	
	
	
  }
}
