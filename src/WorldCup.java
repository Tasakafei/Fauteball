import java.util.*;

public class WorldCup {

	/**
	 * 1) Build teams and groups from file; 
	 * 2) Simulate first stage matches for each group;
	 * 3) Simulate second stage with the best teams from first stage.
	 * 
	 * @param args A string with path of the file with teams and group information | 
	 * A integer meaning how many teams from each group will play the second stage. |
	 * <Code>-v</Code> flag to print scores as the matches goes on.
	 */
	public static void main(String[] args) {
		
		//0. Preprocess parameters
		if(args.length < 1 || args[0].isEmpty()) return;
		
		int playersSecondStage = 2; 
		if(args.length >= 2)
			try {
				playersSecondStage = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				System.err.println("Expected a positive number as second argument");
				return;
			}
		
		boolean verbose = false;
		if(args.length > 2 && args[2].equals("-v")) verbose = true;
		
		//1. Get information from file
		List<Group> l = FileReader.buildGroupsFromFile(args[0]);
		
		//2. Simulate group stage
		List<Team> bestTeams = new LinkedList<>();
		for(Group g: l) {
			
			List<Team> adv = g.simulateGroupStage(playersSecondStage, verbose, 90);
			
			System.out.print(g.getTeams().size() + " teams. Best teams from group " + g.getName() + " in Group stage: ");
			for(Team t: adv)
				System.out.print(t.getName() + ", ");
			System.out.println();
			
			bestTeams.addAll( adv );
		}
		
		//3. Simulate second stage
		Team win = WorldCup.simulateSecondStage(bestTeams, verbose, 90);
		
		System.out.println(win.getName());
		
		return;
	}
	
	/**
	 * Simulates round of 16, quarter-finals, semifinals and finals.
	 * Actually, it will simulate any "round of 2^k", k being a natural number. ;)
	 * 
	 * Code to check whether a number is a power of 2 in O(1) was based on the following article:
	 * 
	 * 		http://www.exploringbinary.com/ten-ways-to-check-if-an-integer-is-a-power-of-two-in-c/
	 * 
	 * @param teamList List of teams which will play. <Code>teamList</Code> size must be a power of 2, obviously!
	 * @param verbose Print match scores.
	 * @param nTimeSteps Number of time steps in each match.
	 * @return The winning team.
	 */
	public static Team simulateSecondStage(List<Team> teamList, boolean verbose, int nTimeSteps) {
		
		//Don't need to check if number is positive, 'because a size value is always non-negative
		int sz = teamList.size();
		if( (sz & (sz-1)) != 0 ) return null;
		
		//We copy list to preserve the original list passed as argument
		ArrayList<Team> teams = new ArrayList<>();
		teams.addAll(teamList);
				
		//Simulate matches and remove losers. Do this until we have only one team (the champion).
		while(teams.size() > 1) {
			for(int i = 0; i < teams.size()-1; i++) {

				Team A = teams.get(i);
				Team B = teams.get(i+1);
				
				Team winner = MatchSimulator.match(A, B, nTimeSteps, false, verbose);
				
				if(winner.equals(A))
					teams.remove(B);
				else
					teams.remove(A);
				
				System.out.println("Match: " + A.getName() + " x " + B.getName() + " >> Winner: " + winner.getName());
			}
			
			System.out.println("Next round");
			
		}
		
		return teams.get(0);
	}

}
