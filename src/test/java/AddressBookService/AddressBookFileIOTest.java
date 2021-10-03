package AddressBookService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookFileIOTest 
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
	public void whenDetailsAreProper_contentIsWrittenIntoTXTFile()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1",null, "def", "123123","email",LocalDate.now());
		addressBook.put("addressBook1", contact);
		contactDetails.writeAddressDataToFile("addressBook1", addressBook);
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenDetailsAreProper_contentIsReadFromTXTFile()
	{
		addressBooksArray.put("addressBook2", new AddressBookContent("AddressBook2"));
		Contact contact = new Contact("addressBook2","umico", "chan", "974251","email2123",LocalDate.now());
		addressBook.put("addressBook1", contact);
		contactDetails.readAddressDataFromFile("addressBook2", addressBook);
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenDetailsAreProper_contentIsWrittenIntoCsvFile()
	{
		addressBooksArray.put("a", new AddressBookContent("a"));
		Contact contact = new Contact("addressBook1","amy", "chan", "111111","amy",LocalDate.now());
		addressBook.put("addressBook1", contact);
		contactDetails.writeToCsvFile("addressBook1.csv", addressBook);
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenDetailsAreProper_contentIsReadFromCsvFile()
	{
		addressBooksArray.put("a", new AddressBookContent("a"));
		Contact contact = new Contact("a","umico", "chan", "123456","umico",LocalDate.now());
		addressBook.put("a", contact);
		contactDetails.readFromCsvFile("addressbook.csv", addressBook);
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenDetailsAreProper_contentIsWrittenIntoJsonFile()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","padmini",LocalDate.now());
		addressBook.put("addressBook1", contact);
		try 
		{
			contactDetails.writeToJson("addressBook1", addressBook);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenDetailsAreProper_contentIsReadFromJsonFile()
	{
		addressBooksArray.put("addressBook1", new AddressBookContent("AddressBook1"));
		Contact contact = new Contact("addressBook1","padmini", "sharma", "974251","padmini",LocalDate.now());
		addressBook.put("addressBook1", contact);
		try 
		{
			contactDetails.readFromJson("addressBook1", addressBook);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		System.out.println(addressBook);	
	}
	
	@Test
	public void whenValueEntered_isEmpty_throwsCustomException()
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
	
	@Test
	public void whenValueEntered_isNull_throwsCustomException()
	{
		try
		{
			addressBooksArray.put("address", new AddressBookContent("a"));
			Contact contact = new Contact("address",null, "v", "111111","padmini",LocalDate.now());
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	
	@Test
	public void whenAddressBookValue_isNull_throwsCustomException()
	{
		try
		{
			addressBooksArray.put(null, new AddressBookContent(null));
			Contact contact = new Contact("address",null, "v", "111111","padmini",LocalDate.now());
		}
		catch(IOServicesException e)
		{
			e.getMessage();
		}
		
	}
	
}
