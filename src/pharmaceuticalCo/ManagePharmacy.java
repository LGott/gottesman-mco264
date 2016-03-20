package pharmaceuticalCo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagePharmacy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PharmaceuticalCo company = null;
	static ArrayList<PharmaceuticalCo> printAll = new ArrayList<PharmaceuticalCo>();

	public static void main(String[] args)
			throws DuplicateDataException, IOException, NotFoundException, InvalidDataException {
		Scanner input = new Scanner(System.in);
		PharmacyList pharmList = new PharmacyList();

		// System.out.print("Is this your first time using this program? Y/N");
		//char answer = input.next().charAt(0);
		// if (answer == 'Y') { // Open and read the file containing the data // try {

		Scanner scan = new Scanner(
				new File("C:/Users/Leba Gottesman/Documents/Touro College/DataStructures1/HW/pharmacies.txt"));

		while (scan.hasNext()) {

			String companyCode = scan.next();
			String phoneNumber = scan.next();
			String companyName = scan.nextLine();

			pharmList.addCompany(companyCode, companyName, phoneNumber);
		}

		scan.close(); //} catch (FileNotFoundException e1) { // System.out.println("Error. File not found");}

		// if (answer == 'N') { ObjectInputStream input2 = new ObjectInputStream(new
		// FileInputStream("./OutputFile.ser")); try { pharmList = (PharmacyList) input2.readObject();
		// pharmList.connectToData("pharmacies.dat"); input.close();//close the connection to the serialized file

		//} catch (ClassNotFoundException e) { e.printStackTrace(); } 

		int menuChoice;

		menuChoice = menu();
		do {

			switch (menuChoice) {

			case 1: // Add company
				System.out.print("Enter company code");
				String companyCode = input.next();

				System.out.print("Enter company name");
				String companyName = input.next();
				System.out.print("Enter company phone number");
				String phoneNumber = input.next();

				try {

					pharmList.addCompany(companyCode, companyName, phoneNumber);
					System.out.println("Company added successfully.");

				} catch (DuplicateDataException e2) {
					System.out.println("Error. Duplicate Data. Company Code already Exists");
				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				}

				break;

			case 2: // Remove company
				System.out.print("Enter company code");
				companyCode = input.next();
				try {
					pharmList.removeCompany(companyCode);
				} catch (NotFoundException e3) {
					System.out.println("Error. Company Not Found.");
				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				}
				System.out.println("Company removed Successfully\n");
				break;

			case 3: // Modify phone number
				try {
					System.out.print("Enter company code:");
					companyCode = input.next();

					//Check if company entered exists
					PharmaceuticalCo company = pharmList.findCompanyCode(companyCode);

					//If yes, enter new number and call the modifyPhone method
					System.out.print("Enter new phone number:");
					String newNumber = input.next();

					pharmList.modifyCompanyPhone(companyCode, newNumber);

					System.out.println("Phone number modified Successfully.");

				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				} catch (NotFoundException e) {
					System.out.println("Error. Not Found.");
				} catch (InvalidDataException e5) {
					System.out.println("Invalid Data");
				}

				break;

			case 4: // Display Info using code

				System.out.print("Enter company code:");
				companyCode = input.next();
				try {
					System.out.println(pharmList.displayCompanyCode(companyCode).toString() + "\n");
				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				} catch (NotFoundException e4) {
					System.out.println("Error. Contact IT");
				}
				break;

			case 5: // Display Info using name
				System.out.print("Enter company name:");
				companyName = input.next();
				try {
					System.out.println(pharmList.displayCompanyName(companyName).toString());
				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				} catch (NotFoundException e4) {
					System.out.println("Error. Contact IT");
				}
				break;

			case 6: // Print the data about all the companies
				try {
					printAll = pharmList.displayAll();

				} catch (IOException eIO) {
					System.out.println("File Error. Contact IT");
				} catch (NotFoundException e4) {
					System.out.println("Error. Contact IT");
				}
				System.out.println(printAll.toString());

				break;
			case 7:
				// try {
				//pharmList.closeFiles();
				System.out.println("writing out to file ");

				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./OutputFile.ser"));

				System.out.println("set up reference to the file");

				output.writeObject(pharmList.toString());

				System.out.println("wrote out the data");
				output.close();

				// pharmList.shutdown("./OutputFile.txt");

				/* } catch (FileNotFoundException ex1) { System.out.println( "Error. File Not Found"); System.exit(1); }
				 * catch (IOException ex2) { System.out.println( "Error. Data cannot be written to file."); } */
				System.exit(0);

			}
			menuChoice = menu();
		} while (menuChoice <= 7);
	}

	public static int menu() {
		Scanner input = new Scanner(System.in);
		int menuChoice = 0;

		System.out.print("Enter the menu choice:\n " + "1. Add a pharmaceutical company\n" + "2. Remove a company\n"
				+ "3. Modify company phone number\n" + "4. Display company information(Using Company Code)\n"
				+ "5. Display company information(Using Company Name)\n"
				+ "6. List information about each company on file\n" + "7. End the application\n");

		menuChoice = input.nextInt();

		return menuChoice;
	}
}
