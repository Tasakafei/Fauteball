import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Team {
	
	//------------------------------------------------
	
	/**
	 * Team's strenght; it will be used to make match simulations less "random": a team with
	 * strength two times smaller then the its adversary has two times the chance of winning.
	 * Values between 50 and 150 usually results in reasonable scores.
	 */
	private int strength; 
	
	/**
	 * This is necessarily a reference to an element of <Code>List<Players> players</Code>.
	 */
	private Player goalkeeper;
	
	private Person doctor, manager;
	
	private String name;
	private int wins, losses, draws;
	private List<Player> players;

	//------------------------------------------------
	/**
	 * Constructors won't take goalkeeper because
	 * in order to set it we must before check if it's in players list
	 */
	public Team() {
		this("#defaultName", 0, null, null);
	}
	
	public Team(String name, int strength, Person doctor, Person manager) {
		this.goalkeeper = null;
		this.doctor = doctor;
		this.manager = manager;
		this.name = name;
		this.strength = strength;
		this.players = new LinkedList<>();
		this.wins = this.losses = this.draws = 0; //There is no reason why a Team should start with a non-zero
												  //quantity of wins/losses/draws
	}
	
	//------------------------------------------------
	public Person getDoctor() 			 	{ return doctor; }
	public void setDoctor(Person doctor) 	{ this.doctor = doctor; }

	public Person getManager() 			   	{ return manager; }
	public void setManager(Person manager) 	{ this.manager = manager; }

	public String getName() 			{ return name; }
	public void setName(String name) 	{ this.name = name; }

	public int getStrength() 				{ return strength; }
	public void setStrength(int strength) 	{ this.strength = strength; }
	
	public Player getGoalkeeper() { return goalkeeper; }
	
	public void setWins(int wins) 		{ this.wins = wins; }
	public void setLosses(int losses) 	{ this.losses = losses; }
	public void setDraws(int draws) 	{ this.draws = draws; }
	
	public int getWins() 	{ return wins; }
	public int getLosses() 	{ return losses; }
	public int getDraws() 	{ return draws; }

	public void incWins() 	{ wins++; }  	//It is useful having inc() methods in order 
	public void incLosses() { losses++; }	//not to type set_(get_() + 1) always
	public void incDraws() 	{ draws++; }
	
	//------------------------------------------------
	public List<Player> getStarters() {
		List<Player> starters = new LinkedList<>();
		
		for(Player p: players)
			if(p.isStarter())
				starters.add(new Player(p));
		
		return starters;
	}
	
	public boolean addPlayer(Player p) {
		return players.add(p);
	}

	//Returns true if we found some player to remove and false otherwise
	public boolean removePlayer(String id) {
		Iterator<Player> it = players.iterator();
		
		while(it.hasNext())
			if(it.next().getId().compareTo(id) == 0) {
				it.remove();
				return true;
			}
		
		return false; 
	}
	
	//A simple linear search for a player based on ID
	public Player searchPlayer(String id) {
		for(Player p: players)
			if(p.getId().compareTo(id) == 0) return p;
		return null;
	}
	
	public boolean setGoalkeeper(String id) {
		Player p = searchPlayer(id);
		
		if(p == null) return false;
		
		this.goalkeeper = p;
		return true;
	}
	
	public boolean changePlayers(String idOld, String idNew) {
		Player oldP, newP;
		
		oldP = searchPlayer(idOld);
		newP = searchPlayer(idNew);
		
		if(oldP == null || newP == null) return false;
		
		oldP.setStarter(false);
		newP.setStarter(true);
		
		return true;
	}
	
	//In a more robust version of this program we should check if
	//newID different from all other in the other teams; for this
	//version, checking if it's different from all other Players
	//in the same team is enough, 'though.
	public boolean changePersonID(String idOld, String newID) {
		
		for(Player p: players)
			if(newID.compareTo(p.getId()) == 0) return false;
		
		Player p = searchPlayer(idOld);
		p.setId(newID);
		return true;
		
	}
}