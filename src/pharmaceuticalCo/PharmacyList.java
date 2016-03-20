package pharmaceuticalCo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PharmacyList implements Serializable {

	private static final long serialVersionUID = 1L;
	ArrayList<CompanyCodeIndex> compCodes;
	ArrayList<CompanyNameIndex> compNames;
	RandomAccessFile raFile;
	PharmaceuticalCo pharmCo;

	transient private RandomAccessFile pharmacyDataFile;

	public PharmacyList() throws FileNotFoundException {

		compCodes = new ArrayList<CompanyCodeIndex>();
		compNames = new ArrayList<CompanyNameIndex>();
		raFile = new RandomAccessFile(new File("./reWritePharmacies.txt"), "rw");
	}

	public void addCompany(String companyCode, String companyName, String phoneNumber)
			throws DuplicateDataException, IOException {

		pharmCo = new PharmaceuticalCo(companyCode, companyName, phoneNumber); /* Set up a new Pharmaceutical Co */

		Long indexLocation = pharmCo.writeToFile(raFile,
				raFile.length()); /* Write the data to the RAF file and return the location */

		// Set up two separate index records CompanyCodeIndex and
		// CompanyNameIndex, each referencing the same location in the RandomAccessFile

		CompanyCodeIndex codeIndex = new CompanyCodeIndex(companyCode, indexLocation);

		// Verify that the company does not already exist

		if (!(compCodes.contains(codeIndex))) {
			compCodes.add(codeIndex);
			// Collections.sort(compCodes);
		} else {
			throw new DuplicateDataException();
		}

		CompanyNameIndex nameIndex = new CompanyNameIndex(companyName, indexLocation);

		// Verify that the company does not already exist

		if (!(compNames.contains(nameIndex))) {
			compNames.add(nameIndex);
			// Collections.sort(compNames);
		} else {
			throw new DuplicateDataException();
		}

	}

	/* Search through the arraylist of code indexes and if found, retrieve location of that record, instantiate a
	 * PharmaceuticalCo record from the RandomAccessFile and return the record */

	public PharmaceuticalCo findCompanyCode(String companyCode) throws IOException, NotFoundException {

		Collections.sort(compCodes);

		for (CompanyCodeIndex index : compCodes) {
			if (companyCode.equalsIgnoreCase(index.getCompanyCode())) {
				if ((index.isActive())) {

					PharmaceuticalCo pharmCo = new PharmaceuticalCo(raFile, index.getLocation());
					return pharmCo;
				}
			}
		}

		System.out.println("Nonexistant or Deleted Company");
		throw new NotFoundException();

	}

	/* Search through the arraylist of name indexes and if found, retrieve location of that record, instantiate a
	 * PharmaceuticalCo record from the RandomAccessFile and return the record */

	public PharmaceuticalCo findCompanyName(String companyName) throws IOException, NotFoundException {
		//Collections.sort(compNames);

		for (CompanyNameIndex index : compNames) {
			if (companyName.equalsIgnoreCase(index.getCompanyName().trim())) {
				if ((index.isActive())) {
					PharmaceuticalCo pharmCo = new PharmaceuticalCo(raFile, index.getLocation());
					return pharmCo;
				}
			}
		}
		System.out.println("Nonexistant or Deleted Company");
		throw new NotFoundException();

	}

	//Return the data for a specific company based on code
	public String displayCompanyCode(String companyCode) throws IOException, NotFoundException {

		PharmaceuticalCo pharmCo = findCompanyCode(companyCode);

		return pharmCo.toString();

	}

	//Return the data for a specific company based on name
	public String displayCompanyName(String companyName) throws IOException, NotFoundException {

		PharmaceuticalCo pharmCo = findCompanyName(companyName);

		return pharmCo.toString();

	}

	//Return an arrayList of all the data to be displayed to the user
	public ArrayList<PharmaceuticalCo> displayAll() throws IOException, NotFoundException {

		ArrayList<PharmaceuticalCo> printAll = new ArrayList<PharmaceuticalCo>();

		Collections.sort(compCodes, new CompanyComparator()); //Sort the codes alphabetically so it prints in order

		for (CompanyCodeIndex index : compCodes) {
			if (index.isActive()) {
				PharmaceuticalCo pharmCo = new PharmaceuticalCo(raFile, index.getLocation());
				printAll.add(pharmCo);

			}
		}
		return printAll;
	}

	//Remove a company by looping through the arrayLists and removing it 
	public void removeCompany(String companyCode) throws IOException, NotFoundException {

		PharmaceuticalCo company = findCompanyCode(companyCode);

		for (CompanyCodeIndex index : compCodes) {
			for (CompanyNameIndex index2 : compNames) {
				if (company.getCompanyCode().equalsIgnoreCase(index.getCompanyCode())) {

					compCodes.remove(index.getCompanyCode());
					compNames.remove(index2.getCompanyName());

					// index.setDeleted();

					// Set it to not active once its removed
					index.setNotActive();
					index.setNotActive();
				}
			}

			// Collections.sort(compCodes);

		}

	}
	/* Modify the phone number and re-write it to the file */

	public boolean modifyCompanyPhone(String companyCode, String newNumber) throws IOException, InvalidDataException {
		boolean check = false;
		for (CompanyCodeIndex index : compCodes) {
			if (companyCode.equalsIgnoreCase(index.getCompanyCode())) {
				if ((index.isActive())) {

					PharmaceuticalCo pharmCo = new PharmaceuticalCo(raFile, index.getLocation());

					pharmCo.setPhoneNumber(newNumber);
					pharmCo.writeToFile(raFile, index.getLocation());
					check = true;
				}
			}

		}

		return check;
	}

	//Shutdown the program and write out the file usign ObjectOutputStream

	public void shutdown(String indexFileName) throws FileNotFoundException, IOException {

		System.out.println("writing out to file " + indexFileName);
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(indexFileName));

		System.out.println("set up reference to the file");
		output.writeObject(pharmCo);

		System.out.println("wrote out the data");
		output.close();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(compCodes);
		buffer.append(compNames);

		return buffer.toString();
	}

	public void connectToData(String fileName) throws FileNotFoundException {
		pharmacyDataFile = new RandomAccessFile(fileName, "rw");

	}

	public void closeFiles() throws IOException {

		pharmacyDataFile.close();

	}

}
