package LinkedList;

import java.util.Iterator;
import java.util.LinkedList;


public class UseIterators{


	public static void main (String args[]){

		LinkedList<String> names = new LinkedList<String>();

		names.add("Libby");
		names.add("Leba");
		names.add("Rena");

		Iterator<String> iter = names.iterator();

		while (iter.hasNext()) {
			System.out.println(names.iterator().next() + " ");
		}
	}
}