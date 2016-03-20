package doubleLinkedList;
import java.io.Serializable;

public class ReverseIterator<T extends Serializable & Comparable<T>> {

	private DoubleLinkNode<T> currentNode;
	private DoubleLinkNode<T> tail;

	public ReverseIterator(DoubleLinkNode<T> tail) {
		this.tail = tail;
		this.currentNode = tail;
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub

		if (currentNode == null) {
			return false;

		}

		return true;
	}

	public T next() {
		// TODO Auto-generated method stub

		T data = currentNode.getData();
		currentNode = currentNode.getPrev();

		return data;


	}

	public void reset(){
		currentNode = tail;
	}

}
