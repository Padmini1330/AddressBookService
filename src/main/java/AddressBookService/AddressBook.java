package AddressBookService;

public class AddressBook 
{
	public String addressBookName;
	public String addressBookType;
	
	public AddressBook(String addressBookName, String addressBookType) 
	{
		super();
		this.addressBookName = addressBookName;
		this.addressBookType = addressBookType;
	}
	public String getAddressBookName() 
	{
		return addressBookName;
	}
	public String getAddressBookType() 
	{
		return addressBookType;
	}
	public void setAddressBookName(String addressBookName)
	{
		this.addressBookName = addressBookName;
	}
	public void setAddressBookType(String addressBookType)
	{
		this.addressBookType = addressBookType;
	}
	@Override
	public String toString() 
	{
		return "AddressBook [addressBookName=" + addressBookName + ", addressBookType=" + addressBookType + "]";
	}
}
