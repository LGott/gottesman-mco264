package simpleSorts;

import java.util.Random;

public class UseBubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] messages = new String[3];
		int[] values  = new int[10];
		Random randomGenerator = new Random();
		for (int i = 0; i < values.length; i++)
		{
			values[i]= randomGenerator.nextInt(100);
		}
		Integer[] integerValues = new Integer[values.length];
		for (int i =0; i< integerValues.length; i++)
			integerValues[i] = values[i];    //demonstrates autoboxing
        //display the unsorted list of values
		for (int i =0;i < values.length;i++)
			System.out.print(values[i] + "\t");
		System.out.println();
		//now sort the values
		BubbleSort.bubbleSort(values);
		//display the sorted list of values
		for (int i =0;i < values.length;i++)
			System.out.print(values[i] + "\t");
		
		messages[0]= "hello";
		messages[1] = "goodbye";
		messages[2] = "shalom";
		
		GenericBubbleSort.genericBubbleSort(messages);
		System.out.println("\nSorted messages");
		for (int i=0; i< messages.length;i++)
			System.out.print(messages[i] + "\t");
		
		System.out.println("\nUnsorted Integer Array Values");
		for (int i =0; i< integerValues.length; i++)
			System.out.print(integerValues[i]+ "\t");  //autounboxing    
	
		GenericBubbleSort.genericBubbleSort(integerValues);
		
		System.out.println("\nSorted Integer Array Values");
		for (int i =0; i< integerValues.length; i++)
			System.out.print(integerValues[i]+ "\t");  //autounboxing    
	
	}

}
