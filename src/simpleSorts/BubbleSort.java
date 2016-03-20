package simpleSorts;

public class BubbleSort {
	
//this method can be invoked even if the class isn't instantiated	
	public static void bubbleSort(int[] values ){
		int temp;
		boolean swapped;
		
		do{
			swapped = false;
			for (int inner =0;inner < values.length-1 ; inner++)
			{
				//compare adjacent values, if not in order swap them
				if (values[inner] > values[inner+1])
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
