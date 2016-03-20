package QueueArray;

public class Node<T> {
	private T data;
	private Node<T> nextNode;

	public Node() {
	}

	public Node(T data) {
		this.data = data;
		nextNode = null;
	}

	public Node(T data, Node<T> next) {

		this.data = data;
		this.nextNode = next;

	}

	public T getData() {
		return data;
	}

	public Node<T> getNext() {

		return nextNode;

	}

	public void setNext(Node<T> next) {

		nextNode = next;
	}
}
