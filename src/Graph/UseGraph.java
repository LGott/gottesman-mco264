package Graph;

public class UseGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph cities = new Graph();
		cities.addVertex("A");
		cities.addVertex("B");
		cities.addVertex("C");
		cities.addVertex("D");
		cities.addVertex("F");
		cities.addVertex("E");
		cities.addVertex("G");
		System.out.println("Graph: " + cities.toString());
		cities.addEdge("A", "B");
		cities.addEdge("B", "C");
		cities.addEdge("C", "A");
		cities.addEdge("C", "D");
		cities.addEdge("D", "C");
		cities.addEdge("D", "E");
		cities.addEdge("F", "E");
		cities.addEdge("A", "F");
		System.out.println("Graph: " + cities.toString());
		System.out.println("Edges from " + cities.displayAdjNodes("D"));
		System.out.println(" A connected E " + cities.isConnected("A", "E"));
		cities.resetVisited();
		System.out.println(" A connected F " + cities.isConnected("C", "F"));
	}

}

