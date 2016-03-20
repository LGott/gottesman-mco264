package randomAccessStudentDataCW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import pharmaceuticalCo.DuplicateDataException;

public class StudentsIndex implements Serializable{
	private ArrayList<StudentIndexRec> index;


	/**
	 * set up an index for the first time
	 * 
	 */
	public StudentsIndex (){

		this.index = new ArrayList<StudentIndexRec>();

	}


	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception if duplicate 
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation)throws DuplicateDataException{

		StudentIndexRec indexRec = new StudentIndexRec(studentID, fileLocation);

		if (index.contains(indexRec)) {
			throw new DuplicateDataException();
		}
		this.index.add(indexRec);

		sortIndex(); //Sort the index


	}

	/**
	 * 
	 * @param studentID
	 * @return  Long  - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation (Integer studentID)throws NotFoundException{

		int number = findStudent(studentID);
		if (number >= 0) {
			return index.get(number).getFileLocation();
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * 
	 * @param studentID
	 * @return  element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	public int findStudent(Integer studentID) throws NotFoundException {

		StudentIndexRec rec = new StudentIndexRec(studentID, 0L);
		int number = Collections.binarySearch(index, rec);
		if (number >= 0) {
			return number;
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID){

		//findStudent()

		for (StudentIndexRec student : this.index) {
			if (studentID == student.getStudentID()) {
				return true;
			}
		}
		return false;
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		int stuIndex = findStudent(studentID);
		index.remove(stuIndex);
	}

	private void sortIndex(){}{
		Collections.sort(index);
	}


	private int findStudentBinSearch(Integer studentID){

		StudentIndexRec rec = new StudentIndexRec(studentID, 0L);
		int number = Collections.binarySearch(index, rec);
		return number;
	}

	public ArrayList<StudentIndexRec> returnList() {
		return index;
	}
}
