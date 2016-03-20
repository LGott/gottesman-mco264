package searches;

public class BinarySearchRecur {
	
	public static <T extends Comparable<T>> int binSearchfind(T[]values, T value){
		
		return binRecur(values,value,0,values.length-1);
		
		
	}
	
	public static <T extends Comparable<T>> int binRecur(T[]values,T  value, int first, int last){
        int mid;
		if (last < first)
    	   return -1;    //there is no list, list was exhausted during search
       else{
    	   mid = (first + last)/2;
    	   if (value.compareTo(values[mid])==0)
    		   return mid;
    	   else
    		   if (value.compareTo(values[mid])>0)
    			   return binRecur(values,value,mid+1,last);
    		   else 
    		       return binRecur(values,value,first,mid-1);
    			   
    	   
       }
}
}
