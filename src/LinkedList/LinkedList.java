package LinkedList;

import java.io.Serializable;
import java.util.Iterator;

import randomAccessExceptions.NotFoundException;


public class LinkedList<T extends Comparable<T>> implements Serializable{
	private Node<T> head;

	public LLIterator iter;

	public LinkedList(){
		head = null;
		iter = new LLIterator();
	}

	public void add(T data){
		Node<T> currentNode;
		Node<T> prevNode;
		if (head == null){   //the first Node in the list points to the student index record
			head = new Node<T>(data);
		}
		else{
			Node<T> newNode = new Node<T>(data);  //allocate a new Node
			//find the right spot for the Node inside the list
			currentNode = prevNode = head;

			while ((currentNode != null)  && (data.compareTo(currentNode.getData())>0)){
				prevNode = currentNode;
				currentNode = currentNode.getNext(); //move along to next Node
			}
			//found the right place , attach the links
			if (currentNode == head){
				//the new node will become the new head and point to the current head
				newNode.setNext(head);
				head = newNode;
			}
			else{  //goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);

			}



		}
	}

	public void remove (T data)throws NotFoundException{
		Node<T> currentNode = head;
		Node<T> prevNode = head;

		while (currentNode != null){
			if (currentNode.getData().equals(data)){
				//this is the Node that must be removed
				if (currentNode == head){
					head = head.getNext();  //must reset the head
					return;
				}
				else{
					prevNode.setNext(currentNode.getNext());
					return;
				}
			}
			else{
				//move further along in the list
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		throw new NotFoundException();
	}

	public T find(T data)throws NotFoundException{
		Node<T> currentNode = head;  //start to iterate through the list
		while (currentNode  != null){
			if (currentNode.getData().equals(data)){
				return currentNode.getData();
			}
			currentNode = currentNode.getNext(); //move further down the list
		}
		throw new NotFoundException();
	}

	public Iterator<T> iterator(){

		//Instantiate an external Itarator that can traverse the list starting from head

		LLExtIterator<T> theIter = new LLExtIterator<T>(head);
		return theIter;



	}
	class LLIterator implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Node<T> currentNode;


		public LLIterator(){
			currentNode = head;
		}

		public void reset(){
			currentNode = head;
		}

		public boolean hasNext(){
			if (currentNode != null){
				return true;
			}
			else{
				return false;
			}
		}

		public T next(){
			T data = currentNode.getData();
			currentNode = currentNode.getNext();//move to next Node
			return data;
		}
	}
}
