package AddressBookService;

public class ContactAddress 
{
	public String phoneNumber;
	public String city;
	public String state;
	public String zip;
	
	public ContactAddress(String phoneNumber, String city, String state, String zip) 
	{
		super();
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public String getCity() 
	{
		return city;
	}
	
	public String getState() 
	{
		return state;
	}
	
	public String getZip() 
	{
		return zip;
	}
	
	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public void setState(String state) 
	{
		this.state = state;
	}
	
	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	
	@Override
	public String toString() 
	{
		return "ContactAddress [phoneNumber=" + phoneNumber + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ "]";
	}
	
}
