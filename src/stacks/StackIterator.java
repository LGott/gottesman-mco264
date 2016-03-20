package stacks;

public class StackIterator <E> {
	  //the design pattern of an iterator is to provide  hasNext() ,next(), reset() methods
	
	   private E[]  values;
	   private int top;
	   private int origtop;
	   
	   public StackIterator (E[] values, int top){
		  if (top >=0)
		    this.values = (E[])new Object[top+1];   //set up a new array of values
		  this.top = top;                                              //this is the location of topmost element
		  origtop = top;
		  for (int i=0;i<=top;i++)
			  this.values[i] = values[i];                       //copy over the object references
		   
		  
			   
		   }
	   
	     public boolean hasNext(){
	    	   if (top >=0 ) return true;
	    	   else 
	    		   return false;
	     }
	     
	     public E next(){
	    	 if (hasNext()){
	    		 return values[top--];
	    	 }
	    	 else
	    		 return null;
	     }
		   
		   public void reset(){
			   top = origtop;
		   }
	   }


