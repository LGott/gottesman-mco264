package simpleSorts;

public class InsertionSort {
	
	//generic method
	public static <T extends Comparable<T>> void insertionSort(T[] values){
	    	T unsortedValue;  //value we are trying to place in sorted order
	    	int scan;           //array position we are viewing
	    	
	    	//outer loop starts at position 1 since we assume that 
	    	//position 0 is in order , if there is only one value in the array
	    	
	    	for (int index =1; index < values.length; index++)
	    	{
	    		//store the value you are now trying to insert into proper place
	    		unsortedValue = values[index];
	    		scan = index;
	    		
	    		//start scanning the array to see where it belongs
	    		//you will be moving over values to vacate the spot where it
	            //belongs
	    		
	    		while (scan>0 && values[scan-1].compareTo(unsortedValue)>0)
	    		{
	    			//shift the value one position to the right to help vacate a spot
	    			//for unsortedValue
	    			//if you are dealing with objects then just the reference to the 
	    			//object is shifted over
	    			values[scan] = values[scan-1];
	    			scan--;
	    		}
	    		
	    		//insert the unsorted value into its proper position
	    		values[scan] = unsortedValue;
	    	}
	}

}
