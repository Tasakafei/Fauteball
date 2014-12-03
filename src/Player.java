import java.util.GregorianCalendar;

public class Player extends Person {

	//-------------------------------------------------
	int number, nGoals;
	boolean starter;
	
	//-------------------------------------------------
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNGoals() {
		return nGoals;
	}
	public void setNGoals(int nGoals) {
		this.nGoals = nGoals;
	}
	public boolean isStarter() {
		return starter;
	}
	public void setStarter(boolean starter) {
		this.starter = starter;
	}
	
	//-------------------------------------------------
	public Player(String name, String firstName, String id, 
				  String phoneNumber, GregorianCalendar birthday, int number, 
				  int nGoals, boolean starter) {
		super(name, firstName, id, phoneNumber, birthday);
		this.number = number;
		this.nGoals = nGoals;
		this.starter = starter;
	}
	
	public Player(int number, int nGoals, boolean starter) {
		super();
		this.number = number;
		this.nGoals = nGoals;
		this.starter = starter;
	}
	
	public Player() {
		this(-1, 0, false);
	}
	
	/**
	 * Copy constructor
	 */
	public Player(Player e) {
		super(e);
		this.nGoals = e.getNGoals();
		this.number = e.getNumber();
		this.starter = e.isStarter();
	}
	
	/**
	 * Copies the inherited fields values from an object <Code>Person</Code> and
	 * set the default values to the other fields.
	 * @param e The object whose fields we'll copy.
	 */
	public Player(Person e) {
		super(e);
		this.nGoals = -1;
		this.number = 0;
		this.starter = false;
	}
}
