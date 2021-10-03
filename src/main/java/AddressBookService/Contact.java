package AddressBookService;

import java.time.LocalDate;
import java.util.HashMap;

import com.opencsv.bean.CsvBindByName;

public class Contact 
{
	@CsvBindByName
	private LocalDate date;
	@CsvBindByName
	private String addressBookName;
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String lastName;
	@CsvBindByName
	private String phoneNumber;
	@CsvBindByName
	private String email;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private String zip;
	
	ContactAddress address;
	
	

	
	public String getAddressBookName() 
	{
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) 
	{
		this.addressBookName = addressBookName;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public LocalDate getDate() 
	{
		return date;
	}
	public void setDate(LocalDate date) 
	{
		this.date = date;
	}
	
	
	
	
	
	public ContactAddress getAddress() 
	{
		return address;
	}
	public void setAddress(ContactAddress address) 
	{
		this.address = address;
	}
	
	public String getCity() 
	{
		return getAddress().getCity();
	}

	public void setCity(String city) 
	{
		this.getAddress().setCity(city);
	}


	public String getState() 
	{
		return getAddress().getState();
	}

	public void setState(String state)
	{
		this.getAddress().setState(state);
	}
	
	public String getZipCode()
	{
		return getAddress().getZip();
	}

	public void setZipCode(String zip) {
		this.getAddress().setZip(zip); 
	}
	
	

	public Contact(String addressBookName,String firstName, String lastName,String phoneNumber, String email,LocalDate date) 
	{
		this.addressBookName=addressBookName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.email=email;
		this.date=date;
	}
	
	public Contact(String addressBookName,String firstName, String lastName,String phoneNumber, String email,LocalDate date,
					String city,String state,String zip) 
	{
		this.addressBookName=addressBookName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.email=email;
		this.date=date;
		address=new ContactAddress(phoneNumber,city,state,zip);
	}
	

	public Contact() 
	{
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object anotherObject) 
	{
		HashMap<String, Contact> addressBook = (HashMap<String, Contact>) anotherObject;
		if (addressBook.keySet().stream().anyMatch(s -> (s.equals(firstName)))) 
		{
			return true;
		}
		return false;

	}

	@Override
	public String toString() 
	{
		return "Contact [ addressBookName="+addressBookName+",firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ",date=" + date + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", address=" + address + "]";
	}


}	
