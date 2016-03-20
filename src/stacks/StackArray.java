package stacks;

public class StackArray<E> {
       private int top;
       private E[] values;
       
       public StackArray(){
    	   top = -1;
    	   values = (E[])new Object[10];
       }
       
       public StackArray(StackArray<E>stack){
    	   //copy constructor
    	   this.top = stack.top;
    	   values =  (E[]) new Object[stack.values.length];
    	   for (int i=0;i<top;i++){
    		   values[i] = stack.values[i]; 
    	   }
       }
       
      public  StackArray(int qty){
    	   top = -1;
    	   values = (E[])new Object[qty];
       }
       
       public void push(E value){
    	   if (!isFull())
    		      values [++top] = value;
       }
       
       public void pop(){
    	   if (!isEmpty()){
    	     top--;
    	   
    	   }
    	   else {
    		   throw new EmptyStackException();
    	   }
       }
	
       public E peek(){
    	   if (!isEmpty())
    	   return values [top];
    	   else
    		   return null;
       }
       
       public boolean isEmpty(){
    	   if (top == -1) return true;
    	   else return false;
    	   
       }
       
       public boolean isFull(){
    	   if (top == values.length) return true;
    	   else return false;
       }
	
       public StackIterator<E> iterator(){
    	   
    	   return new StackIterator<E> (values,top);
       }
     
}
