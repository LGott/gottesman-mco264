package schoolApp;

import java.io.Serializable;

public class CourseIndex implements Comparable<CourseIndex>,Serializable{
	private String courseID;
	private Long recLocation;
	private Boolean isActive;
	
	public CourseIndex(String courseID, Long recLocation){
		this.courseID = courseID;
		this.recLocation = recLocation;
		this.isActive = true;
		
	}

	public String getCourseID() {
		return courseID;
	}

	public Long getRecLocation() {
		return recLocation;
	}
	
	public int compareTo(CourseIndex ndx){
		//sort the records according to courseID
		return courseID.compareTo(ndx.courseID);
	}
	
	public Boolean isActive(){
		return this.isActive;
	}
	public void setNotActive(){
		this.isActive = false;
	}
	
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}
		else{
			if(obj instanceof CourseIndex){
				CourseIndex index = (CourseIndex)obj;
				if (index.courseID.equals(this.courseID)){
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
