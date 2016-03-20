package iterators;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T>{
	private T[] data;
	private int position;
	private int qtyElements;
	
	public ArrayIterator(T[] data,int qtyElements){
		this.data = data;
		this.position =0;
		this.qtyElements = qtyElements;
	}
	@Override
	public boolean hasNext() {
		return (position < qtyElements);
	}
	@Override
	public T next() {
		return data[position++];
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public void reset(){
		position =0;
	}
	
	
	

}
