package mini2;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class OneThousandOneArraybianNights {
	private OneThousandOneArraybianNights()
	{
		
	}
	
	public static int findIt(int[] arr)
	{
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] == 0) {
				return i;
			}else if(arr[i] == arr[i+1]) {
				return i;
			}
		}
		return -1;
	}
	
	public static String[] removeMultiples(String[] arr)
	{
		LinkedHashSet<String> outputs = new LinkedHashSet<>();
		
		for(int i = 0; i < arr.length; i++) {
			outputs.add(arr[i]);
		}
		
		String[] newOutputs = new String[outputs.size()];
		return outputs.toArray(newOutputs);
	}
	
	public static void swizzle(int[] arr, int[] swizzler)
	{
		if(swizzler.length != arr.length) {
			return;
		}
		int[] stored = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			if(swizzler[i] >= arr.length || swizzler[i] < 0) {
				return;
			}else {
				stored[i] = arr[i];
			}
		}
		
		for(int i = 0; i < swizzler.length; i++) {
			
			arr[i] = stored[swizzler[i]];
			
			
		}
		
	}
	
	public static int[] findSwizzlerThatSorts(int[] arr)
	{
		int[] clone = new int[arr.length];
	
		
		for(int i = 0; i < arr.length; i++) {
			clone[i] = arr[i];
		}
		Arrays.sort(clone);
		
		for(int i = 0; i < clone.length; i++) {
			clone[i] = indexOf(arr, clone[i]);
		}

		return clone;
	}
	
	public static int[] findSubsequence(int[] arr, int[] t)
	{
		int[] indices = new int[t.length];
		int tIndex = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == t[tIndex]) {
				indices[tIndex] = i;
				tIndex++;
				
			if(tIndex == t.length) {
				break;
			}
			}

		}
			
		if(tIndex != t.length) {
			return new int[0];
		}
		
		return indices;
	}
	
	public static int[] condense(int[] arr, int k)
	{
		int[] sums = new int[arr.length / k];
		for(int i = 0; i < arr.length - 1; i++) {
			if (i / k >= arr.length / k) {
				break;
			}
			sums[(i / k)] += arr[i];
		}
		return sums;
	}
	
	private static int indexOf(int[] arr, int value) {
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == value) {
				index = i;
			}
		}
		return index;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
