package AddressBookService;

public class IOServicesException extends RuntimeException
{
	public enum ExceptionType
	{
		ENTERED_NULL_OR_EMPTY, QUERY_EXECUTION_FAILED, CONNECTION_FAILED;
	}
	
	ExceptionType type;
	
	public IOServicesException(ExceptionType type,String message) 
	{
		super(message);
		this.type=type;
	}
	 
	 
}