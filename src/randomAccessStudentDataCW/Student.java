package randomAccessStudentDataCW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class Student implements Comparable<Student>{
	private Integer studentID;
	private Double studentBalance;

	public static Double MAXBALANCE = 10000.00;   //no student can owe more than this amount

	/**
	 * instantiate a Student based on data read from a Random Access file
	 * @param random
	 */
	public Student (RandomAccessFile random, Long location)throws IOException{
		//if something goes wrong with the read, RandomAccessFile will throw an IOException 

		random.seek(location);
		this.studentID = random.readInt();
		this.studentBalance = random.readDouble();


		Student newStudent = new Student(this.studentID, this.studentBalance);

	}

	/**
	 * 
	 * @param studentID
	 * @param studentBalance
	 */
	public Student(Integer studentID, Double studentBalance){
		this.studentID =studentID;
		this.studentBalance = studentBalance;		
	} 

	public Student(Double amount) {
		addToBalance(amount);
		// TODO Auto-generated constructor stub
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	/**@exception IOException or FileNotFoundException
	 * store data about a Student in a Random Access File
	 * @param fileName
	 * @return location at which record was written in  the file
	 */
	public Long writeStudentRecord(String randomfileName)throws FileNotFoundException,IOException{
		//write this record out to the RandomAccess data file , it will be appended to the end of the file

		RandomAccessFile raFile = new RandomAccessFile(new File(randomfileName), "rw");
		long location = raFile.length();
		raFile.seek(location);
		raFile.writeInt((this.studentID));
		raFile.writeDouble(this.studentBalance);
		raFile.close();
		return location;

	}

	/**
	 * rewrite an existing Student record with updated information
	 * Student class must be informed of the location of this particular Student's record
	 * @exception IOException If the location can't be found  or data cant be written properly
	 * @exception FileNotFoundException if file can't be found
	 * @param random
	 * @param location
	 */
	public void rewriteStudent(String fileName, long location)throws FileNotFoundException,IOException{
		//get active connection to an existing or new file

		RandomAccessFile ra = new RandomAccessFile(new File(fileName), "rw");

		ra.seek(location);
		ra.writeInt(this.studentID);
		ra.writeDouble(this.studentBalance);
		ra.close();
	}

	public void reduceBalance(Double amount){
		this.studentBalance -= amount;
	}

	public void addToBalance(Double amount){
		this.studentBalance += amount;
	}

	/**
	 * Must have proper supervisor override to be allowed to modify maximum allowable balance
	 * Information provided is verified against a file that includes the ids and passwords
	 * of supervisor with this permission
	 * The file must be stored in working directory with name supervisorsInfo.txt
	 * @exception FileNotFoundException  if file cant be found
	 *    
	 * @param amount
	 * @param supervisorID
	 * @param supervisorPassword
	 * @return  true if mission accomplished, false if not completed
	 */
	public static boolean changeMaxBalance (Double amount, String supervisorID, String supervisorPassword)throws FileNotFoundException{
		String id, password;   //assumption , do not contain spaces
		Scanner inputFile = new Scanner (new File("./supervisorsInfo.txt"));
		while (inputFile.hasNext()){
			id = inputFile.next();
			password = inputFile.next();
			if (id.equalsIgnoreCase(supervisorID)){
				if (password.equals(supervisorPassword)){
					//found a match , supervisor override valid , modify the current max allowable balance
					MAXBALANCE = amount;
					inputFile.close();   //no longer need to access the file
					return true;   //break out of the method, we are done
				}
			}
		}
		//file was read and the supervisor id + matching password wasnt found
		inputFile.close();
		return false;

	}

	@Override
	public int compareTo(Student s){
		return this.studentID.compareTo(s.studentID);  //can use compareTo bec. id is stored in an Integer
	}

	public Integer getStudentID() {
		return studentID;
	}

	public Double getStudentBalance() {
		return studentBalance;
	}


	@Override
	public String toString(){
		StringBuffer info = new StringBuffer("Student:");
		info.append( studentID);
		info.append(" Current Balance: ");
		info.append(studentBalance);
		return info.toString();

	}
}
