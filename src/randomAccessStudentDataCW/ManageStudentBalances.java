package randomAccessStudentDataCW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class ManageStudentBalances {
	private StudentsIndex index;
	private String randomFileName;

	/**
	 * set up a new file with student balance data
	 * 
	 * @param quantity
	 * @param randomFileName
	 */
	public ManageStudentBalances(String randomFileName) {
		index = new StudentsIndex(); //delegate the job to set up a new  index
		this.randomFileName = randomFileName; //record file where student data will eventually be stored
	}

	/**
	 * set up system to be able to process student balance data that has previously been entered and stored on disk
	 * 
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @param indexFileName
	 * @param randomFileName
	 */

	public ManageStudentBalances(String indexFileName, String randomFileName)
			throws ClassNotFoundException, FileNotFoundException, IOException {

		ObjectInputStream indexFile = new ObjectInputStream(new FileInputStream(indexFileName));
		index = (StudentsIndex) indexFile.readObject(); //reads in entire index and restructures it in RAM
		indexFile.close();

		this.randomFileName = randomFileName; //no need to read in data, just record the full path of the file

	}

	/**
	 * @exception DuplicateDataException
	 *                - if try adding same Student record more than once
	 * @exception FileNotFoundException
	 *                - if cant find file
	 * @exception IOException
	 *                - if cant seek to the location specified or can't write the data to the file
	 * @param studentID
	 * @param balance
	 * @throws pharmaceuticalCo.DuplicateDataException
	 */

	public void addStudentRecord(Integer studentID, Double balance)
			throws DuplicateDataException, FileNotFoundException, IOException, pharmaceuticalCo.DuplicateDataException {
		//check to see if this student has already been entered into the system
		if (index.hasStudent(studentID)) {
			throw new DuplicateDataException();
		} else {
			//if not found, then this is good
			//set up a new Student record
			Student studentInfo = new Student(studentID, balance);
			//ask Student record to write itself to the RandomAccessFile
			Long recordLocation = studentInfo.writeStudentRecord(randomFileName);
			//record the record's location to the index					
			index.addStudentToIndex(studentID, recordLocation);
		}
	}

	/**
	 * @exception NotFoundException - student data not recorded in system
	 * @exception IOException - file location not found, record couldnt be read from the data file
	 * @FileNotFoundException - data file could not be found
	 * @param studentID
	 * @param amount
	 */
	public void addToStudentBalance(Integer studentID, Double amount)throws FileNotFoundException,NotFoundException,IOException{

		RandomAccessFile ra = new RandomAccessFile(randomFileName, "rw");
		Long location= getStudentFileLocation(studentID);
		Student st = new Student(ra, location);
		ra.close();
		if (st.getStudentID() == (studentID)) {
			st.addToBalance(amount);
			st.rewriteStudent(randomFileName, location);
		}

		else {
			throw new IOException();
		}

	}

	/**
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	private Long getStudentFileLocation(Integer id) throws NotFoundException {

		return index.findStudentLocation(id);
	}

	/**
	 * @exception NotFoundException
	 *                - student data not recorded in the system
	 * @exception FileNotFoundException
	 *                - cant find the data file
	 * @exception IOException
	 *                - can't find the designated location in the data file or cant read the data or data inconsistentcy
	 *                found in file
	 * @param studentID
	 * @return @
	 */
	public Double getStudentBalance(Integer studentID) throws FileNotFoundException, IOException, NotFoundException {

		RandomAccessFile ra = new RandomAccessFile(randomFileName, "rw");
		Long location = getStudentFileLocation(studentID);
		Student st = new Student(ra, location);
		if (st.getStudentID() == studentID) {
			Double amount = st.getStudentBalance();
			ra.close();
			return amount;
		}
 else {
			ra.close();
			throw new IOException();
		}
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		index.removeStudent(studentID); //delegate the job
	}

	/**
	 * @exception NotFoundException
	 *                - student data not recorded in system
	 * @exception IOException
	 *                - file location not found, record couldnt be read from the data file
	 * @FileNotFoundException - data file could not be found
	 * @param studentID
	 * @param amount
	 */

	public void payStudentBalance(Integer studentID, Double amount)
			throws FileNotFoundException, IOException, NotFoundException {
		Long studentRecLocation = getStudentFileLocation(studentID);

		RandomAccessFile randomFile = new RandomAccessFile(new File(randomFileName), "rw");

		Student studentRec = new Student(randomFile, studentRecLocation);
		randomFile.close();

		if (studentRec.getStudentID().equals(studentID)) {

			studentRec.reduceBalance(amount);

			studentRec.rewriteStudent(randomFileName, studentRecLocation);

		} else {

			throw new IOException();
		}
	}
	/**
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @param indexFileName
	 */
	public void shutdown(String indexFileName) throws FileNotFoundException, IOException {
		System.out.println("writing out to file " + indexFileName);
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(indexFileName));
		System.out.println("set up reference to the file");
		output.writeObject(index);
		System.out.println("wrote out the data");
		output.close();
	}
}
