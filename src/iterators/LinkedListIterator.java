package iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class LinkedListIterator {

	public static void main(String[] args) {
		LinkedList<String> names = new LinkedList<String>();
		//how is the Java LinkedList inserting the data into the List?
		names.add("Malka");
		names.add("Leah");
		names.add("Batya");
		names.add("Chaya");
		names.add("Zelda");
		
		Iterator<String> iter = names.iterator();
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
		
		TreeSet<String> sortedNames = new TreeSet<String>();
		sortedNames.add("Malka");
		sortedNames.add("Leah");
		sortedNames.add("Batya");
		sortedNames.add("Chaya");
		sortedNames.add("Zelda");
		
		iter = sortedNames.iterator();
		System.out.println("sorted names - no duplicates");
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
		
		System.out.println("names sorted in descending order");
		iter = sortedNames.descendingIterator();
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
		
		
	}

}
