package QueueArray;

//import Queue.QueueException;

public class Queue <T>{
	private final int MAX_QUEUE ;
	private  T items[];
	private int front, back , count;

	public Queue(){
		MAX_QUEUE = 5;  //arbitrary default amount
		items = (T[])new Object[MAX_QUEUE];
		front =count =0;
		back = MAX_QUEUE -1;

	}

	public Queue(int qty){
		if (qty > 0){
			MAX_QUEUE = qty;
		}
 else {
			throw new InvalidDataException();
		}
	}
	public boolean isEmpty(){
		return count ==0;


	}
	public boolean isFull(){
		return count == MAX_QUEUE;
	}
	public void enqueue(T newItem)throws QueueFullException{
		if (!isFull()){
			back = (back+1)% (MAX_QUEUE);
			items[back] = newItem;
			++count;

		} else {
			throw new QueueFullException();
		}


	}
	public T dequeue()throws QueueEmptyException{
		if (!isEmpty()){
			//queue is not empty , remove first element
			T queueFront = items[front];
			front = (front +1)% (MAX_QUEUE);
			--count;
			return queueFront;

		} else {
			throw new QueueEmptyException();
		}


	}
	public void dequeueAll(){
		//garbage collector will remove current array
		items = (T[]) new Object[MAX_QUEUE];
		front = count =0;
		back = MAX_QUEUE -1;

	}
	public T peek() throws QueueEmptyException{
		if (!isEmpty()){
			return items[front];
		} else {
			throw new QueueEmptyException();
		}

	}
}

