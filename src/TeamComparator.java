import java.util.Comparator;

/* This code and the one that deals with PriorityQueue in Group.java were based on the article: 
 * 
 * 		http://www.programcreek.com/2009/02/using-the-priorityqueue-class-example/
 * 
 * With help of the official documentation:
 * 
 * 		http://docs.oracle.com/javase/6/docs/api/java/util/Comparator.html
 *		http://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
 */

public class TeamComparator implements Comparator<Team> {

	/**
	 * This is a quite of a complicated code, so let's explain it.
	 * Team A is "better" then Team B when: 
	 * 1) Team A has more wins then Team B, OR 
	 * 2) Team A has more draws then Team B, OR 
	 * 3) Team A has less losses then Team B. 
	 * Otherwise, they are incomparable. 
	 * 
	 * NOTICE THAT WE INVERT THE RESULTS. This is because priorityQueue's 
	 * head is the LEAST element, so we invert the comparison results to 
	 * have the reverse order.
	 */
	public int compare(Team A, Team B) {
	
		if(A.getWins() == B.getWins()) {
			
			if(A.getDraws() == B.getDraws()) {
				if(A.getLosses() == B.getLosses())
					return 0;
				else
					return -(B.getLosses() - A.getLosses());
			}
			
			return -(A.getDraws() - B.getDraws());
		}
		
		return -(A.getWins() - B.getWins());
	}
	
}
