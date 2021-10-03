package AddressBookService;

import java.util.ArrayList;
import java.util.HashMap;

public interface AddressBookDirectoryIF 
{

		public void addContact(HashMap<String, Contact> addressBookContact);
		
		public void showContacts(HashMap<String, Contact> addressBookContact);
		
		public void editContact(HashMap<String, Contact> addressBookContact);
		
		public void deleteContact(HashMap<String, Contact> addressBookContact);
		
		public void personWithCity(Contact contact);
		
		public void personWithState(Contact contact);
		
		public void showPersonList(String location, HashMap<String, ArrayList<Contact>> contactsList);
		
		public void showCountofContacts(String location, HashMap<String, ArrayList<Contact>> contactsList);
		
		public void sortContacts(HashMap<String, Contact> addressBook, int sortKey);
		
		public void readAddressDataFromFile(String name, HashMap<String, Contact> addressBook) ;
		
		public void writeAddressDataToFile(String addressBookName, HashMap<String, Contact> addressBook) ;

}
