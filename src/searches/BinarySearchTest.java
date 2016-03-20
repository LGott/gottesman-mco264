package searches;

import junit.framework.TestCase;

public class BinarySearchTest extends TestCase{
	
	Integer[] values ;
    String[] names;
    
    public void setUp(){
   	 values = new Integer[5];
   	 values[0] = 1;
   	 values[1] =5;
   	 values[2] =6;
   	 values[3] = 11;
   	 values[4] =15;
   	 
   	 names = new String[4];
   	 names[0] = "Avrohom";
   	 names[1] = "Bracha";
   	 names[2] = "Elisheva";
   	 names[3] = "Leah";
    }
    
    public void testFound(){
   	 int position;
   	
   		 position = BinarySearchIter.binSearchfind(values, 1);
   	     assertEquals(position,0);
   	     position = BinarySearchIter.binSearchfind(values,15);
   	     assertEquals(position,4);
   	     position = BinarySearchIter.binSearchfind(values,6);
   	     assertEquals(position,2);
   	     
   
   		 position = BinarySearchIter.binSearchfind(names,"Elisheva");
   		 assertEquals(position,2);
   		 position = BinarySearchIter.binSearchfind(names, "Avrohom");
   		 assertEquals(position,0);
   		 position = BinarySearchIter.binSearchfind(names,"Leah");
   		 assertEquals(position,3);
   		
   	
    }
    
    
    public void testNotFound(){
   	 int position;
   	 
   		 position = BinarySearchIter.binSearchfind(values, 4);
   		 assertEquals(position,-1);
   		 position = BinarySearchIter.binSearchfind(values, 0);
   		 assertEquals(position,-1);
   		 position = BinarySearchIter.binSearchfind(values, 30);
   		 assertEquals(position,-1);
   		 
   		   	 
   	 
   		 position = BinarySearchIter.binSearchfind(names,"Abe");
		 assertEquals(position,-1);
		 position = BinarySearchIter.binSearchfind(names,"Boruch");
		 assertEquals(position,-1);
		 position = BinarySearchIter.binSearchfind(names,"Mark");
		 assertEquals(position,-1);
		 
   		 
   	 }
    
    public void testFoundRecur(){
    	int position;
       	
  		 position = BinarySearchRecur.binSearchfind(values, 1);
  	     assertEquals(position,0);
  	     position = BinarySearchRecur.binSearchfind(values,15);
  	     assertEquals(position,4);
  	     position = BinarySearchRecur.binSearchfind(values,6);
  	     assertEquals(position,2);
  	     
  
  		 position = BinarySearchRecur.binSearchfind(names,"Elisheva");
  		 assertEquals(position,2);
  		 position = BinarySearchRecur.binSearchfind(names, "Avrohom");
  		 assertEquals(position,0);
  		 position = BinarySearchRecur.binSearchfind(names,"Leah");
  		 assertEquals(position,3);
  		
    }
    
    public void testNotFoundRecur(){
    	int position;
      	 
  		 position = BinarySearchRecur.binSearchfind(values, 4);
  		 assertEquals(position,-1);
  		 position = BinarySearchRecur.binSearchfind(values, 0);
  		 assertEquals(position,-1);
  		 position = BinarySearchRecur.binSearchfind(values, 30);
  		 assertEquals(position,-1);
  		 
  		   	 
  	 
  		 position = BinarySearchRecur.binSearchfind(names,"Abe");
		 assertEquals(position,-1);
		 position = BinarySearchRecur.binSearchfind(names,"Boruch");
		 assertEquals(position,-1);
		 position = BinarySearchRecur.binSearchfind(names,"Mark");
		 assertEquals(position,-1);
    }
   	 
}


