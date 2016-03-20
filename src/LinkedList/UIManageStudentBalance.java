package LinkedList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import randomAccessExceptions.DuplicateDataException;
import randomAccessExceptions.NotFoundException;

public class UIManageStudentBalance {
	final static int EXIT = 10;

	public static void main(String[] args) {
		ManageStudentBalances studBalances=null;
		int studentID;
		double studentBalance;

		Scanner keyboard = new Scanner(System.in);

		System.out.println("If this the first time you are using Student Balance Application type the file names as requested , otherwise select existing files");


		JFileChooser fileChooser = new JFileChooser();

		JOptionPane.showMessageDialog (null,"choose data file");
		fileChooser.showOpenDialog(null);
		String randomFile = fileChooser.getSelectedFile().getPath();
		JOptionPane.showMessageDialog(null,"choose index file");
		fileChooser.showOpenDialog(null);
		String indexFile = fileChooser.getSelectedFile().getPath();

		String response = JOptionPane.showInputDialog(null, "Is this the first time you are using the system? Y or N");

		if (response.equalsIgnoreCase("N")){
			try{
				studBalances = new ManageStudentBalances(indexFile,randomFile);
			}
			catch( FileNotFoundException ex1){
				System.out.println("file not found, contact IT");
				System.exit(1);
			}
			catch( IOException ex2 ){
				System.out.println("Cant read index file");
				System.exit(1);
			}
			catch(ClassNotFoundException ex3){
				System.out.println("class set up inconsistency - can't restore data..., contact IT");
				System.exit(1);
			}


		}  //end not first time
		else{    //first time system is being used

			studBalances = new ManageStudentBalances(randomFile);

		}
		int choice;
		do{
			choice = menu();
			switch(choice){
			case 1: //add another student and student balance to file
				System.out.println("enter student id: ");
				studentID = keyboard.nextInt();
				System.out.println("enter student balance:");
				studentBalance = keyboard.nextDouble();
				try{
					studBalances.addStudentRecord(studentID, studentBalance);
				}
				catch(FileNotFoundException ex1){
					System.out.println("cant enter student data , file not found");
				}
				catch(DuplicateDataException ex2){
					System.out.println("duplicate student id - verify the data");
				}
				catch( IOException ex3){
					System.out.println("contact IT - can't access data");
				} //end catch


				break;
			case 2:  //view student data
				System.out.println("enter student id :");
				studentID = keyboard.nextInt();
				try{
					double balance = studBalances.getStudentBalance(studentID);
					System.out.println("Student " + studentID + " Balalnce " + balance);
				}
				catch( NotFoundException ex1 ){
					System.out.println("student with id " + studentID + " not in the system");
				}
				catch( FileNotFoundException ex2){
					System.out.println("please verify the file name and path - file not found");
				}
				catch( IOException ex3){
					System.out.println("contact IT - data files are corrupt");
				} //end catch

				break;
			case 3:  //list current students
				System.out.println(studBalances.toString());
				break;
			case 4: //delete a student record
				System.out.println("enter studentid of student who must be removed from the system");
				studentID = keyboard.nextInt();
				try{
					studBalances.removeStudent(studentID);
				}
				catch(NotFoundException notFound){
					System.out.println("student " + studentID + " not found in system");
				}
				break;
			case 5:  //modify student balance
				System.out.println("enter student id");
				studentID = keyboard.nextInt();
				System.out.println("\n1. add to balance "+
						"\n2. pay part/all of balance");
				int transType = keyboard.nextInt();
				System.out.println("Enter $amount");
				double amount = keyboard.nextDouble();
				try{
					if (transType ==1){

						studBalances.addToStudentBalance(studentID, amount);

					}
					else{
						studBalances.payStudentBalance(studentID, amount);
					}
				}
				catch( NotFoundException ex1){
					System.out.println("student " + studentID + " record cant be found");
				}
				catch( IOException ex2 ){
					System.out.println("problem reading the data files");
				}

				break;
			case EXIT:
				System.out.println("thank you for using the Student Balances System");
				//close the files
				try{
					studBalances.shutdown(indexFile);
				}
				catch(FileNotFoundException ex1){
					System.out.println("files not found - bad news -  data cant be saved");
					System.exit(1);
				}
				catch( IOException ex2){
					System.out.println("problem writing data to files - contact IT");
					System.exit(1);
				}

				System.exit(0);  //end the application
				break;



			} 
		}while (choice != EXIT);





	}

	private static int menu(){

		int choice;
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("\n1. Add a Student" +
					"\n2. View Student Data"  +
					"\n3. List current students" +
					"\n4. Delete a student" +
					"\n5. Modify student balance" +
					"\n10. Shutdown System"
					);
			choice = input.nextInt();
		}while ((choice < 0) || ((choice > 5)  && (choice != EXIT)));
		return choice;

	}

}
