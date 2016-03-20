package QueueArray;
public class UseArrayQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<String> myList;
		myList = new Queue<String>();
		try{
		myList.enqueue("Rachel");
		myList.enqueue("Gabi");
		myList.enqueue("Tzippy");
		myList.enqueue("Rivki");
		myList.enqueue("Avigayl");
		myList.enqueue("Nechama");
		}
		catch(QueueFullException e){
		  try{
			  while (true){
				  System.out.println("Serving " + myList.peek());
				  myList.dequeue();
				  
				  
			  }
			  
			  
		  }	
		  catch(QueueEmptyException qe){
			  System.out.println("end queue application");
			  
		  }
			
		}
		// TODO Auto-generated method stub

	}

}
