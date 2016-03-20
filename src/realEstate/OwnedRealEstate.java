package realEstate;

public class OwnedRealEstate extends RealEstate{
	private Owner theOwner;
	
	public OwnedRealEstate(Integer blockNumber, Integer lotNumber,
			String firstName, String lastName,
			String street, String state, String zipCode
			){
		//first you must set up the foundation
		//parents provide the foundation upon which you continue to build
			
		super(blockNumber,lotNumber);
		theOwner = new PrivateOwner(firstName,lastName,street,state,zipCode);
		
		
		
	}
	
	public OwnedRealEstate(
			String corporateName, String street,  String state,String zipCode,
			String phoneNumber,Integer blockNumber, Integer lotNumber
			){
		super(blockNumber,lotNumber);
		theOwner= new CorporateOwner(corporateName,street,state,zipCode,phoneNumber);
		
		
	}
	
	public String toString(){
		StringBuffer info = new StringBuffer();
		info.append(super.toString());
		info.append(theOwner.toString());
		if (price != null){
			info.append("Current Price: " + price);
		}
		return info.toString();
	}
	
	public void setCurrentPrice(Double price){
		this.price = price;   
	}
	
	public Double  getCurrentPrice(){
		return price;
	}
	

	public Owner getOwner (){
		if (theOwner instanceof PrivateOwner){
			PrivateOwner typecast = (PrivateOwner)theOwner;
			Address theAddress = typecast.getAddress();
			return new PrivateOwner(typecast.getFirstName(),typecast.getLastName(),theAddress.getStreet(),theAddress.getState(),theAddress.getZipCode());
		}
		else{
			CorporateOwner typecast = (CorporateOwner)theOwner;
			Address anAddress = typecast.getAddress();
			return new CorporateOwner(typecast.getName(),anAddress.getStreet(),anAddress.getState(),anAddress.getZipCode(),typecast.getPhoneNumber());
		}
		
	}
	
	public void setOwner(OwnerType type, String name, String nameOrPhone,String street, String state, String zipCode){
		if (type == OwnerType.INDIVIDUAL){
		theOwner = new PrivateOwner(name,nameOrPhone,street,state,zipCode);
		}
		else{
			theOwner = new CorporateOwner(name, street,state,zipCode,nameOrPhone);
		}
	}
	
	public void setOwnerPhoneNumber(String phoneNumber){
		if (theOwner instanceof PrivateOwner){
		((PrivateOwner)theOwner).setPhoneNumber (phoneNumber);
		}
		else{
			//assume it must be CorporateOwner
			((CorporateOwner)theOwner).setPhoneNumber(phoneNumber);
		}
	}
	
	public void setAddress(String street, String state, String zipCode){
		super.setAddress(street, state, zipCode);
	}
	

	
	
}
