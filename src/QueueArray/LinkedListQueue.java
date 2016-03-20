package QueueArray;

public class LinkedListQueue<T> {

	private Node<T> head;
	private Node<T> tail;

	public LinkedListQueue() {
		head = null;
		tail = null;

	}

	public void enqueue(T data) {

		//if empty, set up a new queue
		if ((head == null) && (tail == null)) {
			Node<T> newNode = new<T> Node(data);
			head = tail = newNode;
		}

		//If not empty,
		//step one hook them, step two move the tail
		else {

			Node<T> newNode = new Node<T>(data);

			tail.setNext(newNode);
			tail = newNode;

		}

	}

	public T dequeue(Node<T> data) {

		if (isEmpty()) {
			throw new QueueEmptyException();
		}

		//order is very imp!
		T value = head.getData();

		head = head.getNext();
		if (head == null) {
			tail = null;
		} // emptied the list
		return value;

	}

	public boolean isEmpty() {

		return head == null;
	}
}
