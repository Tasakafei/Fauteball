import java.io.BufferedReader;
import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* Code for opening and reading files in this class was based
 * on the tutorial available at:
 * 	
 * 		http://www.javaschubla.de/2007/javaerst0250.html
 * 
 * Code for string tokenization was based upon official Oracle
 * documentation, available at:
 * 
 * 		http://docs.oracle.com/javase/7/docs/api/java/util/StringTokenizer.html
 * 
 * 
 * 
 * 
 * http://www.opensource.apple.com/source/Libc/Libc-320/include/sysexits.h
 */

public class FileReader {

	private static String[] getTokens(String line) {
		if(line.isEmpty()) return null;
		
		StringTokenizer st = new StringTokenizer(line);
		String[] tokens = new String[st.countTokens()];
		int count = 0;
		
		while(st.hasMoreTokens())
			tokens[count++] = st.nextToken();
		
		return tokens;
	}
	
	public static List<Group> buildGroupsFromFile(String filepath) {
		
		//Open file
		if(filepath == null || filepath.isEmpty()) return null;
		
		BufferedReader reader = null; 
		try {
			reader = new java.io.BufferedReader(new java.io.FileReader(filepath));
		} catch(Exception e) {
			View.errorOut( "Error while opening this file: " + e.getMessage() + 
							"\n Groups couldn't be built. Aborting.");
			System.exit( 74 );
		}
		
		//Read line per line, tokenize and create objects
		List<Group> groups = new LinkedList<>();
		Group currentGroup = null;
		Team currentTeam = null;
		
		String line = "--";
		try {
			//String line;
			while((line = reader.readLine()) != null) {
				
				String[] tok = getTokens(line);
				
				//First token is always a label to indicate what object we shall construct
				switch(tok[0]) {
				
					case "GROUP": {
						currentGroup = new Group(tok[1]);
						break;
					}
					
					case "ENDGROUP": {
						groups.add(currentGroup);
						break;
					}
					
					case "TEAM": {				
						currentTeam = new Team();
						currentTeam.setName(tok[1]);
						break;
					}
					
					case "ENDTEAM": {
						currentGroup.addTeam(currentTeam);
						break;
					}
					
					case "MANAGER": {
						Person p = new Person();
						fillPersonFields(p, tok);
						currentTeam.setManager(p);
						break;
					}
					
					case "PLAYER": {
						/*
						 * If we find a tag PLAYER, we create an object
						 * Player, we fill its fields inherited from Person
						 * and then we fill its specific fields.
						 */
						Player p = new Player();
						
						fillPersonFields(p, tok);
						fillPlayerFields(p, tok);
	
						currentTeam.addPlayer(p);
						break;
					}
					
					case "DOCTOR": {
						Person p = new Person();
						fillPersonFields(p, tok);
						currentTeam.setDoctor(p);
						break;
					}
					
					case "STRENGTH": {
						
						int strength = 0;
						
						try {
							strength = Integer.parseInt(tok[1]);
						} catch(NumberFormatException e) {
							View.errorOut( "Error while reading file: Expected integer in parameter STRENGTH. Setting it to zero." );
						} finally {
							currentTeam.setStrength(strength);
						}
						
						break;
					}
					
					case "GOALKEEPER": {
						currentTeam.setGoalkeeper(tok[1]);
						break;
					}
					
					default: {
						break;
					}
				}
			}		
		} catch (Exception e) {
			View.errorOut("Error while reading this file: " + e.getMessage() +
								"\n While reading this line: " + line + 
								"\n Groups couldn't be build correctly. Aborting.");
			System.exit(74);
		} finally {
						
			try { 
				reader.close(); 
			} catch(Exception e) {
				View.errorOut( "Error while closing this file: " + e.getMessage() + 
								"\n Nothing can be done. Execution will continue normally.");
			}
			
		}
		
		return groups;
	}

	/**
	 * Fill an object <Code>Player</Code> with the attributes stored in tokens and in an object <Code>Person</Code>.
	 * @param person An object <Code>Person</Code> which will fill the <Code>Player</Code>'s attributes inherited from <Code>Person</Code>.
	 * @param tokens A set of tokens from where we'll extract player informations.
	 */
	private static void fillPlayerFields(Player person, String[] tokens) {
		
		int i = 1; //Start in token 1 as token 0 is the field label (MANAGER, PLAYER, etc.)
		String flag = null, value = null;
		Player p = new Player(person);
		
		do {
			//Get current flag
			flag = tokens[i]; 
			value = tokens[i+1];
			
			switch(flag) {
				case "-starter": {
					boolean b = !(value.compareTo("0") == 0); //Works in a C/C++ fashion: 0 is false, 
															  //any other value is true
					p.setStarter( b );
					break;
				}
				
				case "-number": {
					int n = Integer.parseInt(value);
					p.setNumber(n);
					break;
				}
				
				default: {
					break;
				}
			}
			
			//Get next group of tokens
			i += 2;
		} while(i < tokens.length);
	}
	
	/**
	 * Returns an object <Code>Person</Code> with the attributes stored in tokens.
	 * @param tokens A set of tokens from where we'll extract informations.
	 */
	private static void fillPersonFields(Person p, String[] tokens) {
		
		int i = 1; //Start in token 1 as token 0 is the field label (MANAGER, PLAYER, etc.)
		String flag = null, value = null;
		
		do {
			//Get current flag
			flag = tokens[i]; 
			value = tokens[i+1];
			
			switch(flag) {
				case "-name": {
					p.setName(value);
					break;
				}
				
				case "-fname": {
					p.setFirstName(value);
					break;
				}
				
				case "-id": {
					p.setId(value);
					break;
				}
				
				case "-pnum": {
					p.setPhoneNumber(value);
					break;
				}
				
				case "-dbirt": {
					int day = Integer.parseInt(value);
					p.getBirthDate().set(Calendar.DAY_OF_MONTH, day);
					break;
				}
				
				case "-mbirt": {
					int month = Integer.parseInt(value);
					p.getBirthDate().set(Calendar.MONTH, month);
					break;
				}
				
				case "-ybirt": {
					int year = Integer.parseInt(value);
					p.getBirthDate().set(Calendar.YEAR, year);
					break;
				}
				
				default: {
					break;
				}
			}
			
			//Get next group of tokens
			i += 2;
		} while(i < tokens.length);
	}
}
