package iterators;

import java.util.Iterator;

public class Array <T> {
	private T[] data;
	private int numValues ;
	
	public InternalArrayIterator  iter;
	
	public Array(int quantity){
		data = (T[]) new Object[quantity];  //instantiate an array of a finite capacity
		numValues =0;                       //how many elements currently contain data
	
	   //instantiate the internal iterator
		iter = new InternalArrayIterator();
	}
	
	//a constructor that initializes itself with a set of values
	public Array( T ...dataValues){
		
		data = (T[]) new Object[dataValues.length];
		numValues =0;
		for (T value : dataValues){
			data[numValues++] = value;
		}
		
		//instantiate the internal iterator
		iter = new InternalArrayIterator();
	}
	
	//might throw ArrayIndexOutOfBounds
	public void add(T value){
		data[numValues++ ] = value;
	}
	
	public boolean isFull(){
		return (numValues == data.length);
	}
	
	public boolean isEmpty(){
		return (numValues == 0);
	}
	
	public void remove (T value){
		//student will write this code
	}
	
	public ArrayIterator<T> iterator ()
	{
		return new ArrayIterator<T>(data, numValues);
	}
	
	class InternalArrayIterator  {
		private int position;
		
		InternalArrayIterator(){
			this.position =0;
		}
		
		public void reset(){
			this.position=0;
		}
		
		public boolean hasNext(){
			if (this.position < numValues){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		public T next(){
			if (hasNext()){
				position++;
				return data[position-1];
			}
			else{
				return null;
			}
		}
	}
}
