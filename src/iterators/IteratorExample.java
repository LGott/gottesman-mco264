package iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {

	public static void main(String[] args) {
		Integer[] values = {10,20,30,40,50,60,70,80,90,100}; //initialize the array with values 1-10
		
		ArrayList<Integer> moreValues = new ArrayList<Integer>(); //instantiate the ArrayList
		for (int i=10;i<=100;i=i+10){
			moreValues.add(i);
		}
		
		//iterate through the array to display the values
		System.out.println("array values:");
		for (int elementNum=0;elementNum<10;elementNum++){
			System.out.print(" [" + elementNum + "]=" + values[elementNum] );
		}
		System.out.println("\narraylist values");
		//iterate through the ArrayList to display the values
		for (int elementNum=0;elementNum<10;elementNum++){
			System.out.print(" [" + elementNum + "]=" + moreValues.get(elementNum) );
		}
		
		//iterate using "for each" iterator  - can use this only when array is completely filled with data
		System.out.println("\narray values:");
		for (Integer value : values){
			System.out.print(value + " " );
		}
		
		System.out.println("\narraylist values");
		for (Integer value : moreValues){   //notice the for loop looks the same
			System.out.print(value + " ");
		}
		
		//now we wish to iterate through the ArrayList using an Iterator
		Iterator<Integer> iter = moreValues.iterator();
		
		System.out.println("\nArrayList values accessed with Iterator");
		//all Iterators must implement these methods:  hasNext(), next()
		while (iter.hasNext()){
			System.out.print(iter.next() + " ");
		}
		
		//It would be nice if the array had such an Iterator , then we could 
		//use it to Iterate through an array that is not completely filled because
		//we could use the hasNext() method to determine when to stop.
	    //we will set up our own Array class and provide an Iterator for the Array class
		Array<Integer> numbers  = new Array<Integer>(10,20,30,40,50,60,70,80,90,100);
		
		Iterator<Integer> iterNumbers = numbers.iterator();
		System.out.println("\nOur array values accessed with Iterator");
		while (iterNumbers.hasNext()){
			System.out.print(iterNumbers.next() + " ");
		}
		
		Array<Integer> moreNumbers = new Array<Integer>(10);
		moreNumbers.add(10);
		moreNumbers.add(20);
		moreNumbers.add(30);
		
		ArrayIterator<Integer> iterMoreNumbers = moreNumbers.iterator();
		System.out.println("\nOur partially filled array, values accessed with Iterator");
		while (iterMoreNumbers.hasNext()){
			System.out.print(iterMoreNumbers.next() + " ");
		}
		
		
		//accomplish the same thing using an internal iterator
		moreNumbers.iter.reset();
		System.out.println("\nArray data accessed using internal iterator");
		while (moreNumbers.iter.hasNext()){
			System.out.print(moreNumbers.iter.next() + " ");
		}
		
	
		
		
		
		
		
		
		

	}

}
