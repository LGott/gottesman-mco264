package simpleSorts;

public class GenericBubbleSort {
	
	public static <T extends Comparable<T>> void genericBubbleSort(T[] values ){
		T temp;
		boolean swapped;
		
		do{
			swapped = false;
			for (int inner =0;inner < values.length-1 ; inner++)
			{
				//compare adjacent values, if not in order swap them
				if (values[inner].compareTo( values[inner+1]) > 0)
				{ 
					temp = values[inner];
					values[inner] = values[inner+1];
					values[inner+1] = temp;
					//two values were swapped, list was not completely in order
					swapped = true;
				}
			}
	}while (swapped);
		
	}

}
