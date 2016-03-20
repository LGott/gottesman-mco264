package sorting;
import java.util.ArrayList;
import java.util.Comparator;

public class Sorter <T extends Comparable <T>>{

	public ArrayList<T> sort(ArrayList<T > collection){

		//insertion sort
		//at every round you insert the next value into the correct position, 
		//you may have to bump the remaining values down to make room 

		//8,9,5 7,3,2
		for (int i = 0; i < collection.size(); i++) {

			T value = collection.get(i);

			int position = 0;

			while ((position > 0) && (value.compareTo(collection.get(position - 1)) < 0)) {

				collection.set(position, collection.get(position - 1));
				position--;
			}

		}

		return null;}

	public ArrayList<T> sortByCompartor(ArrayList<T> collection, Comparator<T> comparator) {

		return null;

	}
}