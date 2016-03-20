package LinkedList;

import java.util.Iterator;

public class LLExtIterator<T extends Comparable<T>> implements Iterator<T> {

	private Node<T> currentNode;
	private Node<T> tail;

	public LLExtIterator(Node<T> tail) {
		this.tail = tail;
		this.currentNode = tail;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

		if(currentNode == null){
			return false;

		}

		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub

		T data = currentNode.getData();
		currentNode = currentNode.getNext();

		return data;


	}

	public void reset(){
		currentNode = tail;
	}

}