

public final class View {
	
	public static void standardOut(String s) {
		System.out.println(s);
		return;
	}
	
	public static void errorOut(String s) {
		System.err.println(s);
		System.err.println();
		return;
	}
	
}
