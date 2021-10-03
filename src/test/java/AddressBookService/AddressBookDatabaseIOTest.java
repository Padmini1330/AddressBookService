package AddressBookService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDatabaseIOTest 
{
	AddressBookDirectoryImpl contactDetails;
	
	@Before
	public void setUp() throws Exception
	{
		contactDetails=new AddressBookDirectoryImpl();
	}
	
	@Test
	public void givenAddressBookID_CheckIfAllContactsAreFetched_WithAddressBookSize()
	{
		List<Contact> contacts=contactDetails.readDb("AddressBook3",IOServiceEnum.DB_IO);
		System.out.println(contacts);
		Assert.assertEquals(3, contacts.size());
	}
	
	@Test
	public void givenAddressBookIDAndContactDetails_CheckIFContactIsInsertedIntoTable()
	{
		Contact contact= new Contact("AddressBook3","rachel","zane","97425","rachel@gmail.com",LocalDate.now(),"mysore","karnataka","171");
		System.out.println(contact);
		Contact insertedContact = contactDetails.writeAddressBookDB(contact,contact.getPhoneNumber(),IOServiceEnum.DB_IO);
		System.out.println("came after inserted contact");
		System.out.println("inserted contact"+insertedContact);
		Boolean result = contactDetails.compareContactSync(insertedContact,"AddressBook3",IOServiceEnum.DB_IO);
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void givenDateRange_WhenCorrect_RetrieveAllContactsAdded() 
	{
		LocalDate startDate = LocalDate.of(2021, 4, 19);
		LocalDate endDate = LocalDate.of(2021, 8, 19);
		List<Contact> contacts = contactDetails.readContactsAddedInRange(Date.valueOf(startDate), Date.valueOf(endDate),IOServiceEnum.DB_IO);
		System.out.println(contacts.size());
	}
	
	
	@Test
	public void givenCity_WhenCorrect_RetrieveAllContactsInCity() 
	{
		String city = "Bangalore";
		List<Contact> contactsInCity = contactDetails.readContactsAddedInGivenCity(city,IOServiceEnum.DB_IO);
		Assert.assertEquals(8, contactsInCity.size());
	}
	
	@Test
	public void givenState_WhenCorrect_RetrieveAllContactsInState() 
	{
		String state = "karnataka";
		List<Contact> contactsInState = contactDetails.readContactsAddedInGivenState(state,IOServiceEnum.DB_IO);
		Assert.assertEquals(9, contactsInState.size());
	}
	
	@Test
	public void whenValueEnteredIsNull_throwsCustomexception()
	{
		try
		{
			List<Contact> contacts=contactDetails.readDb(null,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void whenValueEnteredIsEmpty_throwsCustomexception()
	{
		try
		{
			Contact contact= new Contact("","","","","rachel@gmail.com",LocalDate.now(),"","","171");
			Contact insertedContact = contactDetails.writeAddressBookDB(contact,contact.getPhoneNumber(),IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void whenDateRangeEntered_isImproper_throwsCustomException() 
	{
		try
		{
			List<Contact> contacts = contactDetails.readContactsAddedInRange(Date.valueOf("123"), Date.valueOf("456"),IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void whenWrongCityEntered_throwsCustomException() 
	{
		try
		{
			String city = "1234";
			List<Contact> contactsInCity = contactDetails.readContactsAddedInGivenCity(city,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void whenWrongStateEntered_throwsCustomException() 
	{
		try
		{
			String state = "1234";
			List<Contact> contactsInState = contactDetails.readContactsAddedInGivenState(state,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Test
	public void whenStateEnteredISEmpty_throwsCustomException() 
	{
		try
		{
			String state = "";
			List<Contact> contactsInState = contactDetails.readContactsAddedInGivenState(state,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void whenStateEnteredISNull_throwsCustomException() 
	{
		try
		{
			String state = null;
			List<Contact> contactsInState = contactDetails.readContactsAddedInGivenState(state,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void whenCityEnteredISEmpty_throwsCustomException() 
	{
		try
		{
			String city = "";
			List<Contact> contactsInCity = contactDetails.readContactsAddedInGivenCity(city,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void whenCityEnteredISNull_throwsCustomException() 
	{
		try
		{
			String city = null;
			List<Contact> contactsInCity = contactDetails.readContactsAddedInGivenCity(city,IOServiceEnum.DB_IO);
		}
		catch(IOServicesException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
