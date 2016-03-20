package LinkedList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import randomAccessExceptions.DuplicateDataException;
import randomAccessExceptions.NotFoundException;
import randomAccessStudentDataCW.StudentIndexRec;

public class StudentsIndex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<StudentIndexRec> index;
	public StudentIndexInternalIterator iter;

	/**
	 * set up an index  for the first time
	 * 
	 */
	public StudentsIndex (){
		//initialize the index array
		index = new LinkedList<StudentIndexRec>();   //no need to specify a quantity
		iter = new StudentIndexInternalIterator();

	}

	/**restore an index from a file
	 * @exception FileNotFoundException - file cant be found
	 * @exception IOException problem closing file
	 * @exception ClassNotFoundException - inconsistency between way data was stored and current class definition
	 * @param fileName
	 */
	public StudentsIndex (String fileName)throws ClassNotFoundException,FileNotFoundException,IOException{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
		//read in the array as a series of bits, will reconstruct the array and all the references
		//and the StudentIndexRec instances it is referencing
		index =(LinkedList<StudentIndexRec>) inputStream.readObject();
		inputStream.close();




	}

	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception if duplicate or no more room in the index array
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation)throws DuplicateDataException{
		//make sure not duplicate
		StudentIndexRec indexRec = new StudentIndexRec(studentID, fileLocation);
		try{

			index.find(indexRec);
			//if found student , this is an error
			throw new DuplicateDataException();
		}
		catch(NotFoundException notfound){

			index.add( indexRec);

		}
	}

	/**
	 * 
	 * @param studentID
	 * @return  Long  - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation (Integer studentID)throws NotFoundException{
		StudentIndexRec dummyIndexRecord = new StudentIndexRec(studentID, 0L);
		StudentIndexRec indexRecord = index.find(dummyIndexRecord);
		return indexRecord.getFileLocation();

	}



	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID){
		StudentIndexRec dummyIndexRecord = new StudentIndexRec(studentID, 0L);
		try {
			index.find(dummyIndexRecord);
			return true;
		}
		catch(NotFoundException e){
			return false;
		}
	}

	public void removeStudent(Integer studentID)throws NotFoundException{
		StudentIndexRec dummyIndexRecord = new StudentIndexRec(studentID, 0L);
		index.remove(dummyIndexRecord);
	}



	class StudentIndexInternalIterator implements Serializable{

		public StudentIndexInternalIterator(){
			index.iter.reset();
		}

		public boolean hasNext(){
			//delegate the job
			return index.iter.hasNext();
		}

		public StudentIndexRec next(){
			return index.iter.next();
		}

		public  void reset(){
			index.iter.reset();
		}
	}

}
