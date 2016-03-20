package doubleLinkedList;

import java.io.Serializable;

public class DoubleLinkedList<T extends Serializable & Comparable<T>> {

	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;


	public DoubleLinkedList() {
		head = null;
		tail = null;

	}

	public void add(T element) {

		//If there is nothing in the list, set head to be the first node

		if (head == null) {
			head = new DoubleLinkNode<T>(element);

			//set the tail to be the head also

			tail = head;

		}

		//Next option: If the value is less than the head of the list, then insert it at the 
		//head of the list without traversing thorugh the list

		else if (element.compareTo(head.getData()) < 0) {

			DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(element);

			newNode.setNext(head); //Link the new node to the head by setting the reference to head

			head.setPrev(newNode); //Link the head to the newNode by setting its previous

			head = newNode; //Make the newNode into the head of the list 

		}

		else {

			//Next Option- The Node belongs somewhere in the middle: Traverse the list to find the right place

			DoubleLinkNode<T> current = head; //Create a current node in order to traverse the list

			while (current != null) {

				//Once the current node hits a node that is greater than the newNode, it attaches it before that node 
				if ((element.compareTo(current.getData()) <= 0)) {

					DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(element);

					//Now we have to make a four way connection, and make sure that each node gets attached in two ways

					newNode.setNext(current); //Link the node on the right by setting the next to be the current node
					newNode.setPrev(current.getPrev()); //Link the node on the left by setting its previous using the previous of the current node

					//Now link the previous to the newNode by getting the previous of the current and then setting it's next to NewNode
					current.getPrev().setNext(newNode);

					//Link the current node to the newNode by setting the previous of the current to newNode
					current.setPrev(newNode);

					return; //If found, return so that the element is not placed in the list multiple times 
				}

				// Continue traversing the list if place is not found. Set current to be the next of that current

				current = current.getNext();
			}

			//If the element was not less than any of the elements, that means that it belongs at the tail. 
			if (element.compareTo(head.getData()) > 0) {

				DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(element);

				//Link the newNode to the tail by setting its previous to tail 
				newNode.setPrev(tail);

				//Link the tail to the newNode by setting the next of the old tail to the newNode
				tail.setNext(newNode);

				//Make the newNode the tail of the list
				tail = newNode;

			}

		}

	}

	public void remove(T data) throws NotFoundException, ListEmptyException {

		if (isEmpty()) { //if the list is empty, we cannot remove anything 
			throw new ListEmptyException();
		}
		DoubleLinkNode<T> current = head; //Create a current node that is equal to the head of the list 

		while (current != null) {
			if (current.getData().equals(data)) {

				//If the current is equal to the head, then remove the head by setting it's reference to the next node
				if (current == head) {
					head = head.getNext();
					head.setPrev(null); //Set the previous to null
					return;
				}
				//If the current is equal to the tail, then remove the tail by setting tail to be the previous of the old tail
				else if (current == tail) {
					tail = tail.getPrev();
					return;
				}
				//Otherwise, create a temp node to store the node thats previous to the one you we 
				//to remove, so that we can remove the node without the list falling apart. 
				else {

					DoubleLinkNode<T> temp = current.getPrev();

					//We remove the node by linking the node previous to the 'current' and next of the 'current' to eachother 

					temp.setNext(current.getNext()); //Set the next of the temp using the next of the current node in order to link it 
					current.getNext().setPrev(current.getPrev()); //then link the 'next' of the current with the 'previous' of the current
					return;
				}
			}
			//if not found, continue traversing the list 

			else {
				current = current.getNext();

			}
		}
		throw new NotFoundException(); //

	}

	public T find(T data) throws NotFoundException {
		DoubleLinkNode<T> currentNode = head;
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return currentNode.getData(); //Return the data if it matches. Otherwise keep searching
			}
			currentNode = currentNode.getNext();
		}
		throw new NotFoundException();
	}

	public ReverseIterator<T> revIter() {

		//Pass in the tail instead of the usual head so that it can traverse the list backwards 
		ReverseIterator<T> theIter = new ReverseIterator<T>(tail);
		return theIter;

	}

	public boolean isEmpty() {
		return (head == null);
	}
}
