package doubleLinkedList;

public class UseDoubleList {

	public static void main(String args[]){

		DoubleLinkedList<Integer> doubleList = new DoubleLinkedList<Integer>();

		doubleList.add(10);
		doubleList.add(25);
		doubleList.add(5);
		doubleList.add(20);
		doubleList.add(15);


		try {
			doubleList.remove(5);
		} catch (NotFoundException e) {

			e.printStackTrace();
		} catch (ListEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReverseIterator<Integer> revIter = doubleList.revIter();

		while (revIter.hasNext()) { //iterate through the list and print each element out 

			System.out.println(revIter.next());
		}

		try {
			System.out.println("\nInteger found is: " + doubleList.find(10));
		} catch (NotFoundException e) {

			e.printStackTrace();
		}
	}
}
