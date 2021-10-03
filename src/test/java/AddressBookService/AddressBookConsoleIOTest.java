package AddressBookService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookConsoleIOTest 
{
	AddressBookDirectoryImpl contactDetails;
	private Map<String, AddressBookContent> addressBooksArray=new HashMap<String, AddressBookContent>();
	private HashMap<String, Contact> addressBook=new HashMap<String, Contact>();

	@Before
	public void setUp() throws Exception
	{
		contactDetails=new AddressBookDirectoryImpl();
	}

	@Test
	public void whenDetailsAreProper_contentIsStoredInAddressBook()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","email",LocalDate.now());
		addressBook.put("addressBook1", contact);
	}
	
	@Test
	public void whenDetailsAreProper_numberOfPersonsInState_ShouldMatchTheActualSize()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","email",LocalDate.now());
		addressBook.put("addressBook1", contact);
		contactDetails.showPersonList("addressBook1",contactDetails.personWithState);
		Assert.assertEquals(1, addressBook.size());
	}
	
	@Test
	public void whenDetailsAreProper_numberOfPersonsInCity_ShouldMatchTheActualSize()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","email",LocalDate.now());
		addressBook.put("addressBook1", contact);
		contactDetails.showPersonList("addressBook1",contactDetails.personWithCity);
		Assert.assertEquals(1, addressBook.size());
	}
	
	@Test
	public void whenDetailsAreNotProper_contentIsStoredInAddressBook()
	{
		try
		{
			addressBooksArray.put("123", new AddressBookContent("abc"));
			Contact contact = new Contact("","", "sharma", "974251","email",LocalDate.now());
			addressBook.put("", contact);
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	
	@Test
	public void whenCityValue_isNotProper_throwsCustomException()
	{
		try
		{
			addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
			Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","email",LocalDate.now());
			addressBook.put("addressBook1", contact);
			contactDetails.showPersonList("123",contactDetails.personWithCity);
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	
	@Test
	public void whenStateValue_isNotProper_throwsCustomException()
	{
		try
		{
			addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
			Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","email",LocalDate.now());
			addressBook.put("addressBook1", contact);
			contactDetails.addContact(addressBook);	
			contactDetails.showPersonList("addressBook1",contactDetails.personWithState);
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	@Test
	public void whenFirstNameEntered_isNull_throwsCustomException()
	{
		try
		{
			Contact contact = new Contact("addressBook1",null, "def", "123123","email",LocalDate.now());
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	
	@Test
	public void whenFirstNameEntered_isEmpty_throwsCustomException()
	{
		try
		{
			Contact contact = new Contact("addressBook1","", "def", "123123","email",LocalDate.now());
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}


}