package schoolApp;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Department {
	private String DepartmentID;
	private String DepartmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private String teacherID;
	
	public Department(String DepartmentID, String DepartmentName,String location, String phoneNumber,String faxNumber, String teacherID )
	throws InvalidDataException, MissingDataException{
		if (DepartmentID == null || DepartmentName == null){
			throw new MissingDataException();
		}
		else{  //have minimum amount of data , assign the data values
			this.DepartmentID = DepartmentID;
			this.DepartmentName = DepartmentName;
			this.location = location;
		    this.teacherID = teacherID;
			
		    //validate the phoneNumber
			if (phoneNumber == null){
				this.phoneNumber = null;
			}
			else{
				if(Verifier.isValidPhoneNumber(phoneNumber)){
				setPhoneNumber(phoneNumber);
				}
				else{
					throw new InvalidDataException();
				}
			}
			//validate the fax number	
			if (faxNumber == null){
					this.faxNumber = null;
				}
			
			else{
				 if (Verifier.isValidPhoneNumber(faxNumber))  {
				 setFaxNumber(faxNumber);
				 }
				 else{
					 throw new InvalidDataException();
				 }
				}
			}
}//end constructor

		
	    public Department(Scanner inputFile)throws MissingDataException{
	    	
	    	String data = inputFile.nextLine();
	    	String dataFields[] = data.split(";");
	    	if (dataFields.length != 6)
	    	{
	    		throw new MissingDataException();
	    	}
	    	//structural dependence - expect the fields
	    	//to appear in a predefined order within the file record
	    	//and the data values  are expected to be separated by ;
	    	DepartmentID = dataFields[0];
	    	DepartmentName= dataFields[1];
	    	location= dataFields[2];
	    	phoneNumber= dataFields[3];
	    	faxNumber= dataFields[4];
	    	teacherID = dataFields[5];
	    }
	    
	    public Department (RandomAccessFile raFile, Long recLocation)throws IOException{
	    	//read data from a random access file and set up the field of a new
	    	//instance of Department based on the data that was just read from disk into RAM
	    	//throws IOException if recLocation is invalid or data can't be read
	    	
	    	raFile.seek(recLocation);
	    	
	    	//structural dependence
	    	//expect to the data values to appear in a specific order
	    	//within the file
	    	DepartmentID = raFile.readUTF();
	    	DepartmentName= raFile.readUTF();
	    	location= raFile.readUTF();
	    	if (location.equals("NULL")){
	    		location = null;   //set it to null , not the String NULL
	    	}
	    	phoneNumber= raFile.readUTF();
	    	if (phoneNumber.equals("NULL")){
	    		phoneNumber = null;
	    	}
	    	faxNumber= raFile.readUTF();
	    	if (faxNumber.equals("NULL")){
	    		faxNumber = null;
	    	}
	    	teacherID = raFile.readUTF();
	    	if (teacherID.equals("NULL")){
	    		teacherID = null;
	    	}
	    }
	    
	    
		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber)throws InvalidDataException {
			//validate the phonenumber
			//first digit of an area code can't be 0 or 1 , must any digit from 2-9
			//this must be followed by any two digits
			//the first digit of a phone number must also be from 2-9
			//this will be followed by six more digits
			//we are not storing hyphens within the phone number
			
			if (Verifier.isValidPhoneNumber(phoneNumber))
			{
				this.phoneNumber  = phoneNumber;
			}
			else{
				throw new InvalidDataException();
			}
			
		}

		public String getFaxNumber() {
			return faxNumber;
		}

		public void setFaxNumber(String faxNumber) throws InvalidDataException{
			//validate the faxnumber
			//first digit of an area code can't be 0 or 1 , must any digit from 2-9
			//this must be followed by any two digits
			//the first digit of a phone number must also be from 2-9
			//this will be followed by six more digits
			//we are not storing hyphens within the fax number
			if (Verifier.isValidPhoneNumber(phoneNumber)){
				this.faxNumber = faxNumber;
			}
			else{
				throw new InvalidDataException();
			}
		}

		public String getTeacherID() {
			return teacherID;
		}

		public void setTeacherID(String teacherID) {
			this.teacherID = teacherID;
		}

		public String getDepartmentID() {
			return DepartmentID;
		}

		public String getDepartmentName() {
			return DepartmentName;
		}

		public Long writeToFile(RandomAccessFile raFile, Long recLocation)throws IOException{
		   raFile.seek(recLocation);  //might throw IOException if location is not in the file
		   raFile.writeUTF(String.format("%-4s",DepartmentID)); //insert extra blanks if missing some characters.
		   raFile.writeUTF(String.format("%-20s", DepartmentName));
		   //can't write out a null value 
		   if(location == null){
			   raFile.writeUTF(String.format("%-25s", "NULL"));
		   }
		   else{
		        raFile.writeUTF(String.format("%-25s", location));
		   }
		   if (phoneNumber == null){
			   raFile.writeUTF(String.format("%-10s", "NULL"));
		   }
		   else{
		       raFile.writeUTF(phoneNumber);
		   }
		   if (faxNumber == null){
			   raFile.writeUTF(String.format("%-10s", "NULL"));
		   }
		   else{
		   raFile.writeUTF(faxNumber);
		   }
		   if(teacherID == null){
			   raFile.writeUTF(String.format("%-4s","NULL"));
		   }
		   else{
		   raFile.writeUTF(String.format("%-4s",teacherID));
		   }
		   return recLocation;  //wrote out record at this location
		   
		   
		}
		
		
		public String toString(){
			StringBuilder info = new StringBuilder();
			info.append("\nDepartment: ");
			info.append("ID: ");
			info.append(DepartmentID);
			info.append(" Name: " );
			info.append(DepartmentName);
			info.append(" Location: ");
			if (location == null){
				info.append(" N/A ");
			}
			else{
			info.append(location);
			}
			info.append(" Phone Number: ");
			if (phoneNumber == null){
				info.append(" N/A ");
			}
			else{
			info.append(phoneNumber);
			}
			
			info.append(" Fax Number: ");
			if (faxNumber == null){
				info.append(" N/A ");
			}
			else{
			info.append(faxNumber);
			}
			
			info.append(" ChairPerson: ");
			if (teacherID == null){
				info.append(" N/A ");
			}
			else{
			info.append(teacherID);
			}
			
			return info.toString();
			
		}	
		
		public int compareTo(Department dept){
			return DepartmentID.compareTo(dept.DepartmentID);
		}
			
	    public boolean equals(Object obj){
	    	if(obj == null){
	    		return false;
	    	}
	    	else{
	    		if(obj instanceof Department){
	    			return false;
	    		}
	    		Department other = (Department)obj;
	    		return DepartmentID.equals(other.DepartmentID);
	    		
	    	}
	    }
			
		} //end class
	
	


