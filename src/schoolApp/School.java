package schoolApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class School implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CourseIndex> courses;
	private ArrayList<DepartmentIndex> departments;
	private String name;
	private String phoneNumber;
	transient private RandomAccessFile courseDataFile;
	transient private RandomAccessFile departmentDataFile;

	public School(String name, String phoneNumber)throws InvalidDataException{
		this.name = name;
		if(Verifier.isValidPhoneNumber(phoneNumber)){
			this.phoneNumber = phoneNumber;
		} else {
			throw new InvalidDataException();
		}

		//set up the data that will be stored and managed in RAM
		courses= new ArrayList<CourseIndex>();
		departments = new ArrayList<DepartmentIndex>();

	}

	public void connectToData(String courseFileName, String deptFileName)throws FileNotFoundException{
		courseDataFile = new RandomAccessFile (courseFileName,"rw"  );
		departmentDataFile = new RandomAccessFile(deptFileName,"rw");

	}

	public void closeFiles()throws IOException{
		courseDataFile.close();
		departmentDataFile.close();
	}

	public void addDepartment(String departmentID,String departmentName, String location,String phoneNumber,String faxNumber,
			String teacherID
			)throws MissingDataException, InvalidDataException,DuplicateDataException,IOException{
		//first make sure that this departmentID is unique
		Long recLocation = findDepartment(departmentID);
		if (recLocation == null){
			//set up another Department instance and write it to the data file
			//constructor might throw MissingDataException
			Department aDepartment = new Department(departmentID,departmentName,location,phoneNumber,faxNumber,teacherID);
			recLocation= departmentDataFile.length();  //data will be added to end of file
			recLocation = aDepartment.writeToFile(departmentDataFile, recLocation);

			//set up the index record
			DepartmentIndex index = new DepartmentIndex(departmentID, recLocation);
			//add the index record that references the new department and its location
			//in the data file to the ArrayList of index records
			departments.add(index);
			Collections.sort(departments);
		} //end if
		else{
			throw new DuplicateDataException();
		}


	}

	public void addDepartment(Department aDepartment)
			throws IOException,DuplicateDataException{
		//first make sure the department hasn't already been entered
		Long recLocation = findDepartment(aDepartment.getDepartmentID());
		if (recLocation == null){
			//add this department to the file , at the end of the data file
			recLocation= departmentDataFile.length();
			recLocation = aDepartment.writeToFile(departmentDataFile, recLocation);
			//for debugging
			System.out.println("Department record added at " + recLocation);
			//set up an index record for this department 
			DepartmentIndex index = new DepartmentIndex(aDepartment.getDepartmentID(),recLocation);
			//add the index record that references the new department and its location
			//in the data file to the ArrayList of index records
			departments.add(index);
			Collections.sort(departments);
		}
		else{
			throw new DuplicateDataException();
		}
	}


	public Department getDepartmentInfo(String departmentID)throws IOException{
		//return data about the department with ID = departmentID

		//invoke the method that looks through the index to find an index record
		//that references this department
		Long recLocation = findDepartment(departmentID);
		if (recLocation == null){
			return null;  //if no active department with this id can be found
		}
		else{

			//following statement may throw IOException, if yes, simply throw the exception further
			//if all goes okay, the Department class will read in the data from the data file
			//and set up a Department instance
			Department aDepartment = new Department(departmentDataFile, recLocation);
			//return a reference to the Department instance so that the data can be accessed.
			return aDepartment;
		}
	}

	public void changeDepartmentFaxNum (String departmentID,String newNumber)throws NotFoundException,IOException,InvalidDataException{
		//invoke method that will search through the index to find the record that 
		//references the department with this id
		Long recLocation = findDepartment (departmentID);
		if (recLocation == null){
			throw new NotFoundException();
		}
		else{
			//next statement might throw IOException if data record can't be read
			Department aDepartment = new Department(departmentDataFile,recLocation);
			//next statement might throw InvalidDataException, if phoneNumber is not valid and can't be modified
			aDepartment.setFaxNumber(newNumber);
			//now rewrite the record to the file, might throw IOException if record can't be rewritten
			aDepartment.writeToFile(departmentDataFile, recLocation);


		}
	}

	public void changeDepartmentPhoneNumber(String departmentID, String newNumber)
			throws InvalidDataException,IOException, NotFoundException{
		//invoke method that will search through the index to find the record that 
		//references the department with this id
		Long recLocation = findDepartment (departmentID);
		if (recLocation == null){
			throw new NotFoundException();
		}
		else{
			//next statement might throw IOException if data record can't be read
			Department aDepartment = new Department(departmentDataFile,recLocation);
			//next statement might throw InvalidDataException, if phoneNumber is not valid and can't be modified
			aDepartment.setPhoneNumber(newNumber);
			//now rewrite the record to the file, might throw IOException if record can't be rewritten
			aDepartment.writeToFile(departmentDataFile, recLocation);
		}
	}

	public void changeDepartmentChair(String departmentID,String teacherID)
			throws IOException,NotFoundException{
		//invoke method that will search through the index to find the record that 
		//references the department with this id
		Long recLocation = findDepartment (departmentID);
		if (recLocation == null){
			throw new NotFoundException();
		}
		else{
			//next statement might throw IOException if data record can't be read
			Department aDepartment = new Department(departmentDataFile,recLocation);
			//next statement might throw InvalidDataException, if phoneNumber is not valid and can't be modified
			aDepartment.setTeacherID(teacherID);
			//now rewrite the record to the file, might throw IOException if record can't be rewritten
			aDepartment.writeToFile(departmentDataFile, recLocation);
		}
	}

	public void changeDepartmentLocation(String departmentID, String location)
			throws IOException,NotFoundException{
		//invoke method that will search through the index to find the record that 
		//references the department with this id
		Long recLocation = findDepartment (departmentID);
		if (recLocation == null){
			throw new NotFoundException();
		}
		else{
			//next statement might throw IOException if data record can't be read
			Department aDepartment = new Department(departmentDataFile,recLocation);
			//next statement might throw InvalidDataException, if phoneNumber is not valid and can't be modified
			aDepartment.setLocation(location);
			//now rewrite the record to the file, might throw IOException if record can't be rewritten
			aDepartment.writeToFile(departmentDataFile, recLocation);
		}

	}

	private Long findDepartment(String departmentID){
		//search through the ndex, if found return reclocation
		//if not, return null
		//utilize a binary search to find the department
		int start =0;
		int last = departments.size() -1;
		int mid;
		while (start <= last){
			mid = (start + last)/2;  //where is the middle element
			DepartmentIndex indexRec = departments.get(mid);
			String dept = indexRec.getDepartmentID();

			if (departmentID.equals(dept)) //found the departmentid in the mid element of the index
			{  if (indexRec.getIsActive()){
				return departments.get(mid).getRecLocation();
			}
			else{
				//return null because record was found but it is no longer active
				return null;
			}
			}
			else{  //the department id was not found in the middle of the list
				if (departmentID.compareTo(dept)<0){
					//comes before the middle value , eliminate values from mid and on
					last = mid -1;
				}
				else{
					if (departmentID.compareTo(dept)>0){
						//comes after the middle value, eliminate values before mid
						start = mid+1;
					}
				}
			}
		}  //end while

		return null ; //not in the list so there is no record location
	}

	public ArrayList<String> getDepartments(){
		//local variable, departments , references an ArrayList of String
		//field, departments, references an ArrayList of DepartmentIndex
		//to differentiate between the two , qualify the field name using keyword, this.
		ArrayList<String> departments = new ArrayList<String>();
		for (DepartmentIndex index : this.departments){
			departments.add(index.getDepartmentID());
		}
		return departments;
	}


	public void addCourse(Course aCourse)throws DuplicateDataException,IOException{
		//first make sure that the courseID is unique
		Long recLocation = findCourse(aCourse.getCourseID());
		if (recLocation == null){   //this course hasn't been entered yet

			//now write the data to the data file
			recLocation = courseDataFile.length();
			aCourse.writeToFile(courseDataFile, recLocation) ;
			//for debugging
			System.out.println("Course record added at " + recLocation);
			//now add the location to the course index collection
			courses.add(new CourseIndex(aCourse.getCourseID(), recLocation));
			Collections.sort(courses);


		}
		else{
			throw new DuplicateDataException();
		}


	}


	public void addCourse(String courseid, String CourseName, String DepartmentID, Integer numCredits)
			throws DuplicateDataException,InvalidDataException, MissingDataException,IOException{
		//first make sure that the courseID is unique
		Long recLocation = findCourse(courseid);
		if (recLocation == null){   //this course hasn't been entered yet
			//set up a new Course based on the data provided, make sure that the departmentID exists in this school
			Course aCourse = new Course(courseid, CourseName,DepartmentID, numCredits,getDepartments());
			//now write the data to the data file
			recLocation = courseDataFile.length();
			aCourse.writeToFile(courseDataFile, recLocation) ;
			//now add the location to the course index collection
			courses.add(new CourseIndex(courseid, recLocation));

			Collections.sort(courses);
		}

		else{
			throw new DuplicateDataException();
		}

	}



	private Long findCourse(String courseID){
		//search through the ndex, if found return reclocation
		//if not, return null
		int start =0;
		int last = courses.size() -1;
		int mid;
		while (start <= last){
			mid = (start + last)/2;  //where is the middle element
			CourseIndex indexRec = courses.get(mid);

			String foundcourseID = indexRec.getCourseID();
			if (courseID.equals(foundcourseID)) //if courseid we are looking for is at the mid element of the list
			{   if (indexRec.isActive()){
				return courses.get(mid).getRecLocation();
			}
			else{
				//found the course but it is marked inActive
				return null;
			}
			}
			else{
				if (courseID.compareTo(foundcourseID)<0){
					//comes before the middle value , eliminate values from mid and on
					last = mid -1;
				}
				else{
					if (courseID.compareTo(foundcourseID)>0){
						//comes after the middle value, eliminate values before mid
						start = mid+1;
					}
				}
			}
		}  //end while

		return null ; //not in the list so there is no record location


	}

	public Course getCourseInfo(String courseID)
			throws IOException{

		//return data about the course with ID = courseID

		//invoke the method that looks through the index to find an index record
		//that references this course
		Long recLocation = findCourse(courseID);
		if (recLocation == null){
			return null;  //if no active course with this id can be found
		}
		else{

			//following statement may throw IOException, if yes, simply throw the exception further
			//if all goes okay, the Course class will read in the data from the data file
			//and set up a Course instance
			Course aCourse = new Course(courseDataFile, recLocation);
			//return a reference to the Course instance so that the data can be accessed.
			return aCourse;
		}



	}


	public ArrayList<String> getCourses(){
		//local variable, courses , references an ArrayList of String
		//field, courses, references an ArrayList of CourseIndex
		//to differentiate between the two , qualify the field name using keyword, this.
		ArrayList<String> courses = new ArrayList<String>();
		for (CourseIndex index : this.courses){
			courses.add(index.getCourseID());
		}
		return courses;
	}





}
