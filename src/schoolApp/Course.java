package schoolApp;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String CourseID;
    private String CourseName;
    private String DepartmentID;
    private Integer numCredits;
    
    public Course(String CourseID, String CourseName, String DepartmentID, Integer numCredits, ArrayList<String> depts)
    throws InvalidDataException,MissingDataException{
    	//validate the data
    	if (CourseID==null || CourseName==null || DepartmentID==null || numCredits==null ){
    		throw new MissingDataException();
    	}
    	this.CourseID = CourseID;
    	this.CourseName = CourseName;
    	
    	//check if DepartmentID is valid
    	if (!isDeptIDValid(DepartmentID,depts)){
    		throw new InvalidDataException();
    	}
    	this.DepartmentID = DepartmentID;
    	if (!isNumCreditsValid(numCredits) ){
    		throw new InvalidDataException();
    	}
    	
    	  this.numCredits = numCredits;
    	
    }
    
    public Course(Scanner textFile,ArrayList<String> depts)throws MissingDataException,NumberFormatException, InvalidDataException{
    	//read the data from a text file
    	//structural dependence
    	//expects the data values to be separated by a ;
    	//expects the data values to appear in a specific order
    	
    	String data = textFile.nextLine();
    	String [] fields = data.split(";");
    	if (fields.length != 4){
    		throw new MissingDataException();
    	}
    	
    	this.CourseID = fields[0];
        this.CourseName = fields[1];
        
        if (!isDeptIDValid(fields[2],depts)){
        	throw new InvalidDataException();
        }
        else{
        	this.DepartmentID = fields[2];
        }
        //if the data in next field is not an Integer value will throw NumberFormatException
        System.out.println("read course " + this.CourseID);
        this.numCredits= Integer.valueOf(fields[3]);
        if (!isNumCreditsValid(this.numCredits)){
        	throw new InvalidDataException();
        }
        
    }
    
    //used by other methods to determine if number of credits is
    //valid amount
    private boolean isNumCreditsValid(Integer numCredits){
    	if (numCredits < 0 || numCredits > 5){
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    
    private boolean isDeptIDValid(String departmentID, ArrayList<String> depts){
    	//assume the arraylist is kept sorted , perform a binary search
    	//Collections has a binarySearch method but it can only search for DepartmentIndex within an ArrayList <DepartmentIndex>
    	//and we are searching for a String
    	int start =0;
    	int last = depts.size() -1;
    	int mid;
    	while (start <= last){
    		mid = (start + last)/2;  //where is the middle element
    		String dept = depts.get(mid);
    		if (departmentID.equals(dept))
    		{return true;
    		}
    		else{
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
    		
    		return false ; //not in the list so not valid department id
    	}
   
    public Course(RandomAccessFile raFile, Long recLocation)throws IOException{
    	//once the data was stored in RandomAccessFile the assumption is
    	//that the date was previously validated, no need to re-validate
    	//if location is invalid or data can't be read , the RandomAccessFile file with throw IOException
    	//this method will continue to throw the IOException further down the line
    	raFile.seek(recLocation);
    	this.CourseID = raFile.readUTF();
    	this.CourseName = raFile.readUTF();
    	this.DepartmentID = raFile.readUTF();
    	this.numCredits = raFile.readInt();
    }
    
    public Long writeToFile(RandomAccessFile raFile, Long recLocation)throws IOException{
    	raFile.seek(recLocation);
    	//write out a fixed length records with fixed length fields
    	//each field will be padded with blanks or truncated to make the 
    	//data fit in the space allotted for that particular field
    	raFile.writeUTF(String.format("%-10s", this.CourseID));
    	raFile.writeUTF(String.format("%-30s", this.CourseName));
    	raFile.writeUTF(String.format("%-4s",this.DepartmentID));
    	raFile.writeInt(this.numCredits);
    	return recLocation;  //location at which the record was written
    	
    }

	public String getCourseID() {
		return CourseID;
	}

	public String getCourseName() {
		return CourseName;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public Integer getNumCredits() {
		return numCredits;
	}
    
    public String toString(){
    	StringBuilder info = new StringBuilder();
    	info.append("Course: ");
    	info.append(" ID " );
    	info.append(this.CourseID);
    	info.append(" Name: ");
    	info.append(this.CourseName);
    	info.append(" DepartmentID: ");
    	info.append(this.DepartmentID);
    	info.append(" Number of Credits ");
    	info.append(this.numCredits);
    	
    	return info.toString();
       	
    }
    
    public int compareTo(Course object){
    	return CourseID.compareTo(object.CourseID);
       	
    }
    
    public boolean equals(Object obj){
    	if (obj == null){
    		return false;
    	}
    	else{
    		if(obj instanceof Course){
    			Course course = (Course)obj;
    			if (course.CourseID.equals(CourseID)){
    				return true;
    			}
    			else{
    				return false;
    			}
    		}
    		else{
    			return false;
    		}
    		
    		
    	}
    	
    	
    }
    
}
