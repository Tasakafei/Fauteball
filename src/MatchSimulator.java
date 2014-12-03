import java.util.Random;

public class MatchSimulator {
	
	/**
	 * Simulates a match between two teams. This will occur doing a somewhat "discrete-time simulation":
	 * the strength parameter of the team informs what's the probability of doing a goal at each time step;
	 * we can then simulate it for a fixed number of steps and get a final score. This is certainly not the
	 * easiest way of simulating it, but it gives kind of a more "realistic" and cooler feel to the simulation.
	 * 
	 * @param A
	 * @param B
	 * @param nTimeSteps How many time steps the simulation will have.
	 * @param draw If it's true, a match can end in a draw.
	 * @param verbose If it's true, we print the match score at each time step
	 * @return NULL if the match ends in a draw; otherwise, it will return the winner's reference.
	 */
	public static Team match(Team A, Team B, int nTimeSteps, boolean draw, boolean verbose) {

		int goalsA = 0, goalsB = 0;
		for(int i = 0; i < nTimeSteps; i++) {
			
			if( randomGoal(A.getStrength()) ) goalsA++;
			if( randomGoal(B.getStrength()) ) goalsB++;
			
			if(verbose)
				System.out.println(i + ". " + A.getName() + " " + goalsA + " x " + B.getName() + " " + goalsB);
		}
		
		if(goalsA == goalsB)
			if(!draw) {
				if(verbose) 
					View.standardOut("DRAW! Let's go for another match...");
				
				return match(A, B, nTimeSteps, draw, verbose);
			}
			else return null;
		
		return (goalsA > goalsB) ? A : B;
	}
	
	/**
	 * This method returns whether a team with specific strength has made a goal or not;
	 * the intent is: as Strength value goes smaller, chances of doing a goal get larger.
	 * So, for small values of Strength, it is expected that this method returns more TRUE
	 * values then FALSE, when calling it many times.
	 * Values between 50 and 150 will usually give reasonable scores.
	 * 
	 * @param strength As strength goes smaller, the chance of doing a goal increases.
	 * @return TRUE for if we made a goal, false otherwise.
	 */
	private static boolean randomGoal(int strength) {
		
		Random r = new Random();
		
		double success = 0;
		if(strength > 0) success = 1.0d/strength;
		
		double outcome = r.nextDouble();
		
		return (outcome <= success);
	}
	
	/**
	 * Prevent it from being instantiated by making the default constructor private.
	 */
	private MatchSimulator() {}

}
