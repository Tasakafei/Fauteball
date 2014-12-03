import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Group {

	//------------------------------------------
	private String name;
	private List<Team> teams;
	
	//------------------------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Team> getTeams() {
		return teams;
	}
	
	//------------------------------------------
	public Group(String name) {
		this.name = name;
		teams = new ArrayList<>();
	}
	
	public Group() {
		this("#defaultName");
	}
	
	//------------------------------------------
	public Team searchTeam(String teamName) {
		for(Team t: teams)
			if(t.getName().compareTo(teamName) == 0) return t;
		return null;
	}
	
	public boolean addTeam(Team t) {
		return teams.add(t);
	}
	
	public boolean removeTeam(String teamName) {
		Iterator<Team> it = teams.iterator();
		
		while(it.hasNext())
			if(it.next().getName().compareTo(teamName) == 0) {
				it.remove();
				return true;
			}
		
		return false;
	}
	
	/**
	 * Each team plays with all the others and then we return the N best teams.
	 * 
	 * @param nWinners How many teams we'll return.
	 * @param verbose Print each match score.
	 * @param nTimeSteps How many time steps each match in group stage we'll have.
	 * @return A List with the <Code>nWinners</Code> teams in the group.
	 */
	//play against the other
	public List<Team> simulateGroupStage(int nWinners, boolean verbose, int nTimeSteps) {
		
		if(teams.size() < 2) return null;
		
		//Get all 2 element combinations of all teams in group,
		//simulate match.
		for(int i = 0; i < teams.size()-1; i++)
			for(int j = i+1; j < teams.size(); j++) {
				
				Team A = teams.get(i);
				Team B = teams.get(j);
				
				Team winner = MatchSimulator.match(A, B, nTimeSteps, false, verbose);
				
				System.out.print("Match: " + A.getName() + " x " + B.getName());
				
				if(winner != null) {
					Team loser = (winner.equals(A)) ? B : A;
					winner.incWins();
					loser.incLosses();
					
					System.out.println(" >> Winner: " + winner.getName());
					
					continue;
				}
				
				A.incDraws();
				B.incDraws();
				
				System.out.println(" >> Draw");
			}
		
		//Get n best teams
		PriorityQueue<Team> pq = new PriorityQueue<>(teams.size(), new TeamComparator());
		List<Team> winners = new ArrayList<>();
		
		Iterator<Team> it = teams.iterator();
		while(it.hasNext())
			pq.add(it.next());
		
		for(int i = 0; i < nWinners; i++)
			winners.add(pq.poll());
			
		return winners;
	}
	
}
