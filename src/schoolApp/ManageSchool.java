package schoolApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ManageSchool {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		School ourSchool= null;
		int choice;
		String departmentID;
		String deptName;
		String faxNumber;
		String phoneNumber;
		String teacherID;
		String location;
		
		System.out.println("school was set up?  enter Y or N.....");
		char response = keyboard.next().charAt(0);
		
		if (response == 'N'){
			keyboard.nextLine();  //flush the buffer between next() and nextLine()
			System.out.println("enter school name");
			String name = keyboard.nextLine();
			System.out.println("enter school phone number");
			phoneNumber =  keyboard.next();
			while (!Verifier.isValidPhoneNumber(phoneNumber)){
				System.out.println("enter school phone number");
				phoneNumber = keyboard.next();
			}
			try{
			   ourSchool = new School(name,phoneNumber);
			   ourSchool.connectToData("courses.dat", "departments.dat");//connect to the data files
			   //prime the system from the text files
			   Scanner departmentFile = new Scanner (new File("departments.txt"));
			   while(departmentFile.hasNext()){
				   Department aDepartment = new Department(departmentFile);
				   ourSchool.addDepartment(aDepartment);
			   }
			   departmentFile.close();
			   
			   Scanner courseFile = new Scanner(new File("courses.txt"));
			   while (courseFile.hasNext()){
				   Course aCourse = new Course(courseFile,ourSchool.getDepartments());
				   ourSchool.addCourse(aCourse);		   
			   }
			   courseFile.close();
			}
			catch (FileNotFoundException ex1){
				System.out.println("couldnt set up school, file not found");
				System.exit(1);
			}
			catch (InvalidDataException ex2){
				System.out.println("couldn't set up school, data was invalid");
			    System.exit(1);
			}
			catch(MissingDataException ex3){
				System.out.println("file missing data contact IT");
				System.exit(1);
			}
			catch(DuplicateDataException ex4){
				System.out.println("duplicate data exception");
				System.exit(1);
			}
			catch(IOException ex5){
				System.out.println("couldnt read data from file or write data to file, ioexception");
				System.exit(1);
			}
			
		}
		else{  //data was stored on disk, school was set up already
			try{
			     ObjectInputStream input = new ObjectInputStream(new FileInputStream("school.ser"));
			     //readObject reads a series of bytes.
			     //The typecast splits up the bytes properly - applying the structure - so that it
			     //can be processed as a School
			     try{
			         ourSchool = (School)input.readObject();
			         ourSchool.connectToData("courses.dat", "departments.dat");
			         input.close();//close the connection to the serialized file
			     }
			     catch(ClassNotFoundException ex3){
			    	 System.out.println("the data in the file doesn't correspond to the class structure that currently exists  .... contact IT");
			    	 System.exit(1);
			     }
			
			}
			catch(IOException ex2){
				System.out.println("couldn't locate file to restore school....contact IT");
				System.exit(1);
			}
		}  //end else - data was stored on disk and restored from disk
		
		
		while (true){
			//display menu()
			choice = menu(keyboard);
			
		    //process user's request
			switch(choice){
			case 1:  //add a department
				System.out.println("Enter department id");
				departmentID = keyboard.next(); 
				System.out.println("Enter department name");
				deptName = keyboard.nextLine(); //might contain blanks
				System.out.println("enter location");
				location = keyboard.nextLine();
				System.out.println("enter phone number - include area code, don't include - or ()");
				keyboard.nextLine();//flush buffer
				phoneNumber = keyboard.next();
				System.out.println("enter faxnumber");
				faxNumber = keyboard.next();
				System.out.println("chairperson assigned y or n");
				char answer = keyboard.next().charAt(0);
				if (answer == 'y' || answer == 'Y'){
					System.out.println("enter chairperson id");
					teacherID = keyboard.next();
				}
				else {
					teacherID = null;
				}
				try{
				ourSchool.addDepartment(departmentID, deptName, location, phoneNumber, faxNumber, teacherID);
				}
				catch(DuplicateDataException ex1a){
					System.out.println("department id is duplicate - reeneter data");
				}
				catch (InvalidDataException ex1b){
					System.out.println("data invalid - check syntax of phonenumbers ");
				}
				catch (MissingDataException ex1c){
					System.out.println("required data values not provided - reenter");
				}
				catch(IOException ex1d){
					System.out.println("Can't add Department - technical problem with data file contact IT");
				}
				break;
			case 2: //view all departments
				System.out.println(ourSchool.getDepartments());
				break;
			case 3:   //modify department data
				System.out.println("enter department id");
				String deptID = keyboard.next();
				choice = modifyDeptMenu(keyboard);
				
				switch (choice){
				case 0: break;
				case 1://modify phone number
					System.out.println("enter new phone number");
					String newNumber =keyboard.next();
					try{
					ourSchool.changeDepartmentPhoneNumber(deptID, newNumber);
					}
					catch(NotFoundException ex31){
						System.out.println("department with this id not found - reenter");
					}
					catch(IOException ex32){
						System.out.println("data file I/O error- can't locate this data or rewrite the record - contact IT");
					}
					catch (InvalidDataException ex33){
						System.out.println("phone number incorrect syntax - reenter data");
					}
					break;
				case 2:  //modify fax number
					System.out.println("enter new fax number");
					 newNumber =keyboard.next();
					try{
					    ourSchool.changeDepartmentFaxNum(deptID, newNumber);
					}
					catch(NotFoundException ex34){
						System.out.println("department with this id not found - reenter");
					}
					catch(IOException ex32){
						System.out.println("data file I/O error- can't locate this data or rewrite the record - contact IT");
					}
					catch (InvalidDataException ex35){
						System.out.println("fax number incorrect syntax - reenter data");
					}
					break;
				case 3:  //modify chairperson
					System.out.println("enter chairperson id");
					newNumber = keyboard.next();
					try{
						ourSchool.changeDepartmentChair(deptID, newNumber);
					}
					catch(NotFoundException ex36){
						System.out.println("can't find the record that you wish to modify or the id of the chairperson you have indicated");
					}
					catch(IOException ex37){
						System.out.println("file I/O error - contact IT");
					}
					break;
				case 4: //modify department location
					System.out.println("Enter department location");
					String newLocation = keyboard.nextLine();
					keyboard.nextLine();  //flush the buffer
					try{
						ourSchool.changeDepartmentLocation(deptID, newLocation);
					}
					catch(NotFoundException ex38){
						System.out.println("can't find the department ID that you wish to modify");
					}
					catch(IOException ex39){
						System.out.println("file I/O error - contact IT");
					}
					break;
				}
				break;
			case 4: //view department information
				System.out.println("enter department id");
				departmentID = keyboard.next();
				try{
				System.out.println(ourSchool.getDepartmentInfo(departmentID));
				}
				catch(IOException ex51){
					System.out.println("File I/O error - data can't be retrieved");
				}
				break;
			case 5:  //add a course
				System.out.println("enter course id");
				String courseID = keyboard.next();
				System.out.println("enter course name");
				String courseName = keyboard.nextLine();
				keyboard.nextLine();
				System.out.println("enter department ID that offers this course");
				departmentID = keyboard.next();
				System.out.println("Number of Credits: ");
				Integer numCredits = keyboard.nextInt();
				try{
				ourSchool.addCourse(courseID,courseName,departmentID,numCredits);
				}
				catch(IOException ex41){
					System.out.println("file I/O error - contact IT");
				}
				catch (MissingDataException ex42){
					System.out.println("not all required data values were provided, request not processed");
					
				}
				catch(DuplicateDataException ex43){
					System.out.println("course id already assigned to a different course");
				}
				catch(InvalidDataException ex44){
					System.out.println("department id isn't valid");
				}
				
				
				break;
			case 6://view information about one course
				System.out.println("enter course id");
				courseID = keyboard.next();
				try{
				System.out.println(ourSchool.getCourseInfo(courseID));
				}
				catch(IOException ex51){
					System.out.println("File I/O error - data can't be retrieved");
				}
				break;
			case 7:  //list of available courses
				System.out.println(ourSchool.getCourses());
				break;
			case 0:
				System.out.println("Exiting application");
				try{
				    ourSchool.closeFiles();
				    //write out the school object as a complete object
				    ObjectOutputStream outStream = new ObjectOutputStream (new FileOutputStream("school.ser"));
				    outStream.writeObject(ourSchool);
				    outStream.close();
				    System.exit(0);  //shut down the application
				}
				catch(IOException ex0){
					System.out.println("can't shut down properly - contact IT");
				}
				break;
				
				
				
			}
		
		}
	}
	
	public static int menu(Scanner keyboard){
		int choice =0;
		do{
		System.out.println("1.Add a Department" +
				"\n2.View all Departments" +
				"\n3. Modify Department Data " +
				"\n4. View Department Information " +
				"\n5. Add a Course " +
				"\n6. View Information About Course" +
				"\n7. List Available Courses" +
				"\n0. EXIT"
				);
		choice = keyboard.nextInt();
		}while(choice >7);//end while
		return choice;
			
		
		
	}  //end menu()

	public static int modifyDeptMenu(Scanner keyboard){
		int choice =0 ;
		do{
		System.out.println("1. Modify Phone Number" +
		                   "\n2. Modify Fax Number" +
				           "\n3. Modify ChairPerson ID" +
		                   "\n4. Modify Location"  +
				           "\n0. Exit"
				
				);
		
		choice = keyboard.nextInt();
	} while (choice > 4); //end while
	return choice;
	}
}
