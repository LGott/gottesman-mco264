package searches;

import junit.framework.TestCase;

public class LinearSearchTest extends TestCase{
     Integer[] values ;
     String[] names;
     
     public void setUp(){
    	 values = new Integer[5];
    	 values[0] = 10;
    	 values[1] =5;
    	 values[2] =6;
    	 values[3] = 11;
    	 values[4] =4;
    	 
    	 names = new String[4];
    	 names[0] = "Elisheva";
    	 names[1] = "Avrohom";
    	 names[2] = "Leah";
    	 names[3] = "Bracha";
     }
     
     public void testFound()throws Exception{
    	 int position;
    	 try{
    		 position = LinearSearch.find(values, (Integer)10);
    	     assertEquals(position,0);
    	     position = LinearSearch.find(values,4);
    	     assertEquals(position,4);
    	     position = LinearSearch.find(values,6);
    	     assertEquals(position,2);
    
    	 }
    	 catch(Exception e){
    		 throw e;
    	 }
    	 
    	 try{
    		 position = LinearSearch.find(names,"Elisheva");
    		 assertEquals(position,0);
    		 position = LinearSearch.find(names, "Bracha");
    		 assertEquals(position,3);
    		 position = LinearSearch.find(names,"Leah");
    		 assertEquals(position,2);
    		
    	 }
    	 catch(Exception e){
    		 throw e;
    	 }
     }
     
     public void testNotFound()throws Exception{
    	 int position;
    	 try{
    		 position = LinearSearch.find(values, 15);
    		
    	 }
    	 catch(Exception e){
    		 System.out.println("exception thrown as expected");
    	 }
    	 try{
    		 position = LinearSearch.find(names,"Dovid");
    		 
    	 }
    	 catch(Exception e){
    		 System.out.println("exception thrown as expected");
    	 }
}
}
