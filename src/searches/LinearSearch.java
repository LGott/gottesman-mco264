package searches;

public class LinearSearch {
	
	public static <T extends Comparable<T>>int find(T[]values, T value)throws Exception{
		for (int i=0; i<values.length; i++){
			if (values[i].compareTo(value)==0)
				return i;
		}
		throw new Exception("not found");
	}

}
