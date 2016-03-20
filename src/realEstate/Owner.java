package realEstate;

public interface Owner {

	public String getName();
	public Address getAddress();
	public void setAddress(String street, String state,String zipCode);
    public String toString();
}
