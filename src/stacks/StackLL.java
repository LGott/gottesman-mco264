package stacks;

import java.util.LinkedList;

public class StackLL<T> {

	private LinkedList<T> values;
	private int top;

	public StackLL() {

		values = new LinkedList<T>();
		top = -1;

	}

	public void push(T element){

		values.add(element);
	}

	public void addLast(T element){
		values.addLast(element);
	}


	public T peek() {

		if (!(isEmpty())){
			return values.getFirst();
		}
		return null;

	}

	public void pop(){

		values.removeFirst();
	}

	public void removeLast(){
		values.removeLast();
	}

	public boolean isEmpty(){

		if(values.isEmpty() == true){
			return true;
		} else {
			return false;
		}
	}
}
