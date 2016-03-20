package realEstate;

public class RealEstate {
   private Integer blockNumber;
   private Integer lotNumber;
   private Address address;
   protected Double price;
   
   public RealEstate(Integer blockNumber,Integer lotNumber){
	   this.blockNumber = blockNumber;
	   this.lotNumber = lotNumber;
	   this.address = null;
	   this.price = null;
   }
   
   
   public RealEstate (Integer blockNumber, Integer lotNumber, String street,  String state, String zipCode){
	   this.blockNumber = blockNumber;
	   this.lotNumber = lotNumber;
	   setAddress(street,state,zipCode);
	   this.price = null;
   }
   
   public Integer getBlock(){
	   return this.blockNumber;
   }
   
   public Integer getLot(){
	   return this.lotNumber;
   }
   
   public void setAddress(String street, String state, String zipCode){
	  setAddress(street,null,state,zipCode);
	  
   }
   
   public void setAddress(String street, String city, String state,String zipCode){
	  if(this.address == null)
	  {  this.address = new Address(street,city,state,zipCode);
	  }
	  }
   
   public void setStreet(String street){  //allow to rename street of the realestate instance
	   //delegation
	   if (address != null){
	      this.address.setStreet(street);
	   }
   }
   
   public String toString(){
	   StringBuffer info = new StringBuffer();
	   info.append("\n RealEstate " );
	   info.append(" Block Number: " + blockNumber);
	   info.append( " Lot Number: " + lotNumber);
	   if (address == null){
		   info.append("Address : Not Available");
	   }
	   else{
	   info.append(address.toString());
	   }
	   return info.toString();
	   
   }

   
   public int compareTo(RealEstate other){
	   int i;
	   i = blockNumber.compareTo(other.blockNumber);
	   if (i !=0){
		   return i;   //sort based on i
	   }
	   i= lotNumber.compareTo(other.lotNumber);
	  
	   return i;  //sort based on lotNumber
	  
	   
   }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((blockNumber == null) ? 0 : blockNumber.hashCode());
	result = prime * result + ((lotNumber == null) ? 0 : lotNumber.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RealEstate other = (RealEstate) obj;
	if (blockNumber == null) {
		if (other.blockNumber != null)
			return false;
	} else if (!blockNumber.equals(other.blockNumber))
		return false;
	if (lotNumber == null) {
		if (other.lotNumber != null)
			return false;
	} else if (!lotNumber.equals(other.lotNumber))
		return false;
	return true;
}
   
   
}
