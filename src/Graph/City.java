package Graph;

public class City {
	private String name;
	private boolean visited;

	public City() {
		visited = false;
	}

	public City(String n) {
		name = new String(n);
		visited = false;
	}

	String getName() {
		return new String(name);
	}

	void visitCity() {
		visited = true;
	}

	boolean wasVisited() {
		return visited;
	}

	void resetVisit() {
		visited = false;
	}

}


