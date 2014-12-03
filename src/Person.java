import java.util.GregorianCalendar;

public class Person {

	/**
	 * Personal information
	 */
	private String 	name, firstName;
	
	private GregorianCalendar 	birthDate;
	
	/**
	 * We use a string because phone numbers do have lots 
	 * of characters and this would cause a problem with 
	 * Int or Long Int representation.
	 */
	private String 	phoneNumber; 
	
	/**
	 * A given ID. It can be anything the user wants to (passport number, for example).
	 */
	private String 	id;
	
	//-------------------------------------------------
	public Person(String name, String firstName, String id, String phoneNumber, GregorianCalendar birthday) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthday;
	}
	
	public Person() {
		this("#defaultName", "#defaultName", "#defaultID", "#defaultNumber", new GregorianCalendar(0,0,0));
	}
	
	/**
	 * Copy constructor
	 */
	public Person(Person e) {
		this(e.getName(), e.getFirstName(), e.getId(), e.getPhoneNumber(), e.getBirthDate());
	}
	
	//-------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
