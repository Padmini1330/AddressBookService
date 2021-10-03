package AddressBookService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AddressBookService.IOServicesException.ExceptionType;


public class AddressBookDBService 
{
	AddressBookDirectoryImpl contactDetails=new AddressBookDirectoryImpl();
	private static AddressBookDBService addressBookDBService;
	private PreparedStatement readContactPreparedStatement;
	private PreparedStatement contactAddedGivenRange;
	private PreparedStatement contactsInGivenCity;
	private PreparedStatement contactsInGivenState;
	private PreparedStatement insertIntoContactTablePreparedStatement;
	private PreparedStatement insertIntoAddressTablePreparedStatement;

	public AddressBookDBService() 
	{

	}

	public static AddressBookDBService getDBInstance() 
	{
		addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}
	
	//normal file read and write service
	public void writeService(String fileName, HashMap<String, Contact> addressBook, IOServiceEnum ioService) {
		
		if(fileName==null || fileName=="")
		{
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "Null or Empty filename not allowed!");
		}
		
		if (ioService == IOServiceEnum.CSV_IO)
			contactDetails.writeToCsvFile(fileName, addressBook);
		else if (ioService == IOServiceEnum.FILE_IO)
			contactDetails.writeAddressDataToFile(fileName, addressBook);
		else if (ioService == IOServiceEnum.JSON_IO) 
		{
			try 
			{
				contactDetails.writeToJson(fileName, addressBook);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void readService(String fileName, HashMap<String, Contact> addressBook, IOServiceEnum ioService) {
		
		if(fileName==null || fileName=="")
		{
			throw new IOServicesException(ExceptionType.ENTERED_NULL_OR_EMPTY, "Null or Empty filename not allowed!");
		}
		if (ioService == IOServiceEnum.CSV_IO)
			contactDetails.readFromCsvFile(fileName, addressBook);
		else if (ioService == IOServiceEnum.FILE_IO)
			contactDetails.readAddressDataFromFile(fileName, addressBook);
		else if (ioService == IOServiceEnum.JSON_IO) 
		{
			try 
			{
				contactDetails.readFromJson(fileName, addressBook);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}

	//connecting java program to database
	public static Connection getConnection() throws SQLException, ClassNotFoundException 
    {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/addressBookSystem?useSSL=false";
		String userName = "root";
		String password = "Padmini_1330";
		Connection connection = DriverManager.getConnection(jdbcURL, userName, password);		
		return connection;
	}

	
	//read contacts from database
	
	public List<Contact> readContacts(String addressBookName) 
	{
		ResultSet resultSet;
		if (readContactPreparedStatement == null)
			this.preparedStatementToExecuteQuery();
		try 
		{
			readContactPreparedStatement.setString(1, addressBookName);
			resultSet = readContactPreparedStatement.executeQuery();
		} 
		catch (SQLException e)
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}

		return getContactList(resultSet);
	}

	public List<Contact> getContactList(ResultSet resultSet)
	{
		List<Contact> contactsList = new ArrayList<Contact>();
		try 
		{
			while(resultSet.next())
			{
				contactsList.add(new Contact(resultSet.getString("AddressBookName"),resultSet.getString("FirstName"), resultSet.getString("LastName"),resultSet.getString("PhoneNumber"),resultSet.getString("Email"),resultSet.getDate("DateAdded").toLocalDate()));
			}
		} 
		catch (SQLException e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
		return contactsList;

	}
	
	public Contact getContact(ResultSet resultSet)
	{
		Contact contact = null;
		try 
		{
			while(resultSet.next())
			{
				new Contact(resultSet.getString("AddressBookName"),resultSet.getString("FirstName"), resultSet.getString("LastName"),resultSet.getString("PhoneNumber"),resultSet.getString("Email"),resultSet.getDate("DateAdded").toLocalDate());
				return contact;
			}
		} 
		catch (SQLException e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
		return contact;

	}
	

		
	public Contact writeAddressBookDB(Contact contact,String phoneNumber)
	{
		ResultSet resultSet ;
		if (insertIntoContactTablePreparedStatement == null) 
		{
			this.preparedStatementToExecuteQuery();
		}
		try 
		{
			insertIntoContactTablePreparedStatement.setObject(1, contact.getAddressBookName());
			insertIntoContactTablePreparedStatement.setObject(2, contact.getFirstName());
			insertIntoContactTablePreparedStatement.setObject(3, contact.getLastName());
			insertIntoContactTablePreparedStatement.setObject(4, contact.getPhoneNumber());
			insertIntoContactTablePreparedStatement.setObject(5, contact.getEmail());
			insertIntoContactTablePreparedStatement.setObject(6, contact.getDate());
			insertIntoAddressTablePreparedStatement.setObject(1, contact.getPhoneNumber());
			insertIntoAddressTablePreparedStatement.setObject(2, contact.getAddress().getCity());
			insertIntoAddressTablePreparedStatement.setObject(3, contact.getAddress().getState());
			insertIntoAddressTablePreparedStatement.setObject(4, contact.getAddress().getZip());
						
			Connection connection;
			try 
			{
				connection = this.getConnection();
				connection.setAutoCommit(false);
			} 
			catch (Exception e)
			{
				throw new IOServicesException(ExceptionType.CONNECTION_FAILED,e.getMessage());
			}

			try (Statement statement = connection.createStatement())
			{
				resultSet = insertIntoAddressTablePreparedStatement.executeQuery();
				connection.commit();
			} 
		
			catch (Exception e) 
			{
				try 
				{
					connection.rollback();
				} 
				catch (SQLException e1) 
				{
					throw new IOServicesException(ExceptionType.CONNECTION_FAILED,e.getMessage());
				}
				throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
			}

			finally
			{
				try
				{
					connection.close();
				} 
				catch (SQLException e)
				{
					throw new IOServicesException(ExceptionType.CONNECTION_FAILED,e.getMessage());
				}
			}
			System.out.println("transaction complete!");
			return this.getContact(resultSet);
		}
		
		catch (Exception e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
		
	}
		


	public List<Contact> readContactsAddedInRange(Date startDate, Date endDate) 
	{
        if (contactAddedGivenRange == null) 
        {
            this.preparedStatementToExecuteQuery();
        }
        try 
        {
        	contactAddedGivenRange.setDate(1, startDate);
        	contactAddedGivenRange.setDate(2, endDate);
            ResultSet resultSet = contactAddedGivenRange.executeQuery();
            return this.getContactList(resultSet);
        } 
        catch (Exception e)
        {
            throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
        }
    }
	


	public List<Contact> readContactsInGivenCity(String city) 
	{
		if (contactsInGivenCity == null) 
		{
			this.preparedStatementToExecuteQuery();
		}
		try 
		{
			contactsInGivenCity.setString(1, city);
			ResultSet resultSet = contactsInGivenCity.executeQuery();
					
			return this.getContactList(resultSet);
		} 
		catch (Exception e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
	}
	

	public List<Contact> readContactsInGivenState(String state) 
	{
		if (contactsInGivenState == null) 
		{
			this.preparedStatementToExecuteQuery();
		}
		try 
		{
			contactsInGivenState.setString(1, state);
			ResultSet resultSet = contactsInGivenState.executeQuery();
			return this.getContactList(resultSet);
		} 
		catch (Exception e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
	}
	
	private void preparedStatementToExecuteQuery()
	{
		try
		{
			Connection connection=this.getConnection();
			String readContacts = "SELECT * from Contact where AddressBookName=?";
			readContactPreparedStatement = connection.prepareStatement(readContacts);
			
			String retrieveContactsFromGivenState = "SELECT * from Contact c ,ContactAddress a where c.PhoneNumber = a.PhoneNumber and State=?";
			contactsInGivenState = connection.prepareStatement(retrieveContactsFromGivenState);
			
			String retrieveContactsFromGivenCity = "SELECT * from Contact c ,ContactAddress a where c.PhoneNumber = a.PhoneNumber and City =?";
			contactsInGivenCity = connection.prepareStatement(retrieveContactsFromGivenCity);
			
			String retireveContactsInGivenRange = "SELECT * from Contact where DateAdded between ? and ?";
	        contactAddedGivenRange = connection.prepareStatement(retireveContactsInGivenRange);
	        
	        String insertIntoContactDetails = "INSERT INTO Contact values(?,?,?,?,?,?)";
	        
			String insertIntoAddress= "INSERT INTO ContactAddress values(?,?,?,?)";
			
			insertIntoContactTablePreparedStatement = connection.prepareStatement(insertIntoContactDetails);
			
			insertIntoAddressTablePreparedStatement = connection.prepareStatement(insertIntoContactDetails);	
	        
		}
		catch (Exception e) 
		{
			throw new IOServicesException(ExceptionType.QUERY_EXECUTION_FAILED,e.getMessage());
		}
	}
}
