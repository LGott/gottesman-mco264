package searches;

public class BinarySearchIter {
	
	public static <T extends Comparable<T>> int binSearchfind(T[]values, T value){
		
			int position = -1;
			int mid ,first, last;
			first =0;
			last = values.length -1;
			
			
			while (first <= last){
				//recalculate the midpoint
				mid = (first + last)/2;
				if (value.compareTo(values[mid])== 0) {
					//found the8 value
					return mid;
				} else 
					if (value.compareTo(values[mid])<0)
					{ //value is in first half of the remaining list
						last = mid -1;
						
					}
					else 
					{//value is in second half of the remaining list
						first = mid + 1;
						
					}
			}
			return -1;  //could not find the value
		}

       	
		
	}




