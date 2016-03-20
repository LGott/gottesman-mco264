package pharmaceuticalCo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;


public class PharmaceuticalCo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode;
	private String companyName;
	private String phoneNumber;

	public PharmaceuticalCo(String companyCode, String companyName, String phoneNumber) {

		this.companyCode = companyCode;
		this.companyName = companyName;

		// validate phone
		if (isValid(phoneNumber) == true) {
			this.phoneNumber = phoneNumber;
		} else {
			System.out.println("Invalid Phone Number. Number must be 10 digits");
			throw new InvalidDataException();
		}
	}

	/*
	 * public PharmaceuticalCo(Scanner readFile) {
	 * 
	 * while (readFile.hasNext()) { this.companyCode = readFile.next();
	 * this.phoneNumber = readFile.next(); this.companyName = readFile.next();
	 * 
	 * PharmaceuticalCo pharmCo = new PharmaceuticalCo(this.companyCode,
	 * this.phoneNumber, this.companyName);
	 * 
	 * } }
	 */

	public PharmaceuticalCo(RandomAccessFile raFile, Long location) throws IOException {

		raFile.seek(location);
		this.companyCode = raFile.readUTF();
		this.phoneNumber = raFile.readUTF();
		this.companyName = raFile.readUTF();

		PharmaceuticalCo pharm = new PharmaceuticalCo(this.companyCode, this.companyName, this.phoneNumber);

	}

	public boolean isValid(String phoneNumber) {
		if (phoneNumber.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String newNumber) throws InvalidDataException {
		if (newNumber.length() == 10) {
			this.phoneNumber = newNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public Long writeToFile(RandomAccessFile raFile, Long location) throws IOException {

		raFile.seek(location);
		raFile.writeUTF(String.format("%-3s", this.companyCode));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		raFile.writeUTF(String.format("%-25s", this.companyName));

		return location;
	}

	public int compareTo(PharmaceuticalCo otherCo) {
		return this.companyCode.compareTo(otherCo.getCompanyCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((companyCode == null) ? 0 : companyCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PharmaceuticalCo other = (PharmaceuticalCo) obj;
		if (companyCode == null) {
			if (other.companyCode != null) {
				return false;
			}
		} else if (!companyCode.equals(other.companyCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("Company Code:");
		buffer.append(companyCode + "\n");
		buffer.append("Company name: ");
		buffer.append(companyName + "\n");
		buffer.append("Phone Number: ");
		buffer.append(phoneNumber + "\n");

		return buffer.toString();
	}

}
