package realEstate;

public class Address {
	private String street;
	private String city;
	private USState state;
	private String zipCode;

	public Address(String street, String state,String zipCode){
		this(street,null, state,zipCode); 
	}

	public Address(String street, String city,String state,String zipCode){
		//perform validation here......throw exceptions if 
		//data provided is invalid or missing

		USState stateCode = getStateCode(state);

		if (stateCode == null) {  //no corresponding state code could be found
			throw new InvalidDataException();
		}


		if (!isZipCodeValid(zipCode)){
			throw new InvalidDataException();
		}

		//assume passed validation

		this.street =street;
		this.city =city;
		this.state = stateCode;
		this.zipCode = zipCode;
	}

	//setters 
	public void setStreet(String street){
		this.street = street;
	}

	public void setCity (String city){
		this.city =city;
	}

	public void setState(String state){
		this.state = getStateCode(state);
		if (this.state == null){
			throw new InvalidDataException();
		}

	}

	public void setZipCode(String zipCode){
		if (!isZipCodeValid(zipCode)){
			throw new InvalidDataException();
		}
		this.zipCode = zipCode;
	}
	//getters

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state.getName();
	}

	public String getZipCode() {
		return zipCode;
	}

	private static USState getStateCode(String state){
		for (USState theState : USState.values()){
			if (theState.name().equalsIgnoreCase(state)){
				return theState;

			}
		}
		return null;   //couldn't find the state
	}

	//all zipcodes are verified same way, regardless of the instance
	private static boolean isZipCodeValid(String zipCode){
		if ((zipCode.length()==5) || (zipCode.length()==9)){
			return true;
		}
		else{
			return false;
		}
	}
	//toString
	@Override
	public String toString(){
		StringBuffer info = new StringBuffer();
		info.append("\nAddress: ");
		info.append(" Street: " + street);
		if (city != null){
			info.append(" City: " + city);
		}
		info.append(" State: " + state.name());
		info.append(" ZipCode: " + zipCode);
		return info.toString();
	}


}
