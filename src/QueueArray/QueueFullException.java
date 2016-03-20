package QueueArray;

public class QueueFullException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueFullException(){
		super("queue full");
	}

}
