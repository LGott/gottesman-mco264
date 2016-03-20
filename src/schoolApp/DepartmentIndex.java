package schoolApp;

import java.io.Serializable;

public class DepartmentIndex implements Comparable<DepartmentIndex>,Serializable{
	 private String DepartmentID;
	 private Long recLocation;
	 private Boolean isActive;
	 
	 public DepartmentIndex (String DepartmentID, Long recLocation){
		 this (DepartmentID, recLocation, true);
	 }

	 public DepartmentIndex(String DepartmentID, Long recLocation,Boolean isActive){
		 this.DepartmentID = DepartmentID;
		 this.recLocation = recLocation;
		 this.isActive = isActive;
	 }

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public Long getRecLocation() {
		return recLocation;
	}
	
	public int compareTo(DepartmentIndex other){
		//sort the records according to the DepartmentID
		return this.DepartmentID.compareTo(other.getDepartmentID());
	}
	
	public String toString(){
		StringBuilder info = new StringBuilder();
		info.append("DepartmentIndex");
		info.append(" Dept: " );
		info.append(DepartmentID);
		info.append(" Record Location: ");
		info.append(recLocation);
		return info.toString();
	}
	 
	 
}
