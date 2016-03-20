package simpleSorts;


import junit.framework.TestCase;

public class InsertionSortTest extends TestCase{
    private Integer[] values;
    private String[] messages;
    
    //public method setUp() sets up the values in the values[] array
    public void setUp(){
    	
    	values = new Integer[5];
    	values[0] = 10;
		values[1] = 5;
		values[2] =  1;
		values[3]  =20;
		values[4]  =2;
		
		messages = new String[3];
		messages[0] = "welcome back";
		messages[1] = "hello";
		messages[2] = "shalom";
    }
    
    public void testUnSorted(){
    	boolean sorted = true;
    	    for (int i =0; i< values.length-1;i++)
    	    	if (values[i].compareTo(values[i+1])>0)
    	    		sorted = false;
    	    assertEquals(sorted, false);	
    	    
    	    sorted = true;
    	    for(int i =0; i<messages.length-1; i++)
    	    	 if (messages[i].compareTo(messages[i+1])>0)
    	    		 sorted = false;
    	    assertEquals(sorted,false);
    	    
    	
    }
    
    public void testSorted(){
    	InsertionSort.insertionSort(values);
    	InsertionSort.insertionSort(messages);
    	boolean sorted = true;
    	 for (int i =0; i< values.length-1;i++)
 	    	if (values[i].compareTo(values[i+1])>0)
 	    		sorted = false;
 	    assertEquals(sorted, true);
 	    
 	    sorted = true;
 	    for (int i=0; i< messages.length-1;i++)
 	    	if (messages[i].compareTo(messages[i+1])>0)
 	    		sorted = false;
 	    assertEquals(sorted,true);
 	    
    }
}
