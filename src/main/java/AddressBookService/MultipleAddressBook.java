package AddressBookService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import AddressBookService.IOServicesException.ExceptionType;


public class MultipleAddressBook 
{
	private AddressBookContent addressBook;
	private Map<String, AddressBookContent> addressBooksArray;
	String addressBookName;
	Scanner scanner = new Scanner(System.in);
	
	public MultipleAddressBook() 
	{
		addressBooksArray = new HashMap<String, AddressBookContent>();
	}
	public String getAddressBookName() 
	{
		return this.addressBookName;
	}
	
	public void setAddressBookName(String addressBookName) 
	{
		this.addressBookName = addressBookName;
	}

	public void addAddressBooks() 
	{
		System.out.println("enter address book name");
		String name = scanner.next();
		
		if(name==null || name=="")
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "Null or empty address book name not allowed");
		int index = 0;
		if (addressBooksArray.containsKey(name)) 
		{
			System.out.println("address book "+name+" already exists!");
			return;
		}
		System.out.println("created address book "+name);
		AddressBookDirectoryImpl addContactDetails=new AddressBookDirectoryImpl();
		addContactDetails.setAddressBookName(name);
		addressBooksArray.put(name, new AddressBookContent(name));
		String fileName = name+".txt";
		StringBuffer addressBookBuffer = new StringBuffer();
		addressBooksArray.values().stream().forEach(contact -> {
			String personDataString = contact.toString().concat("\n");
			addressBookBuffer.append(personDataString);
		});

		try 
		{
			Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void showAddressBook() 
	{
		System.out.println("Addressbooks:");
		for (String addressBookName : addressBooksArray.keySet()) 
		{
			System.out.println(addressBookName);
		}
	}
	
	public void searchPersonByState(String name, String state) 
	{
		if(name==null || name=="")
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "null or empty addressbook name not allowed!");
		
		for(AddressBookContent addressBook : addressBooksArray.values()) 
		{
			List<Contact> contactList = addressBook.getContact();
			contactList.stream()
				.filter(person -> person.getFirstName().equals(name) && person.getState().equals(state))
				.forEach(person -> System.out.println(person));
		}
	}	
		
	public void searchPersonByCity(String name, String city) 
	{
		if(name==null || name=="")
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "null or empty addressbook name not allowed!");
		
		for(AddressBookContent addressBook : addressBooksArray.values())
		{
			List<Contact> contactList = addressBook.getContact();
			contactList.stream()
				.filter(person -> person.getFirstName().equals(name) && person.getCity().equals(city))
				.forEach(person -> System.out.println(person));
		}
	}

	public AddressBookContent selectAddressBook(String name) 
	{	
		if(name==null || name=="")
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "null or empty addressbook name not allowed!");
		
		if (addressBooksArray.containsKey(name)) 
		{
			addressBook = addressBooksArray.get(name);
			this.setAddressBookName(name);
			return addressBook;
		}
		System.out.println("Address book "+name+" not found");
		return null;
	}
}
