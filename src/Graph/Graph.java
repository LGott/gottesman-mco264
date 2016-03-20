package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Graph {
	private ArrayList<LinkedList<City>> vertices;
	private int numVertex; //number of vertices

	public Graph() {
		numVertex = 0;
		vertices = new ArrayList<LinkedList<City>>();
	}

	public void resetVisited() {
		ListIterator<LinkedList<City>> iter = vertices.listIterator();
		ListIterator<City> iterEdges;
		while (iter.hasNext()) {
			iterEdges = iter.next().listIterator();
			//now make your way across one linked list of edges
			while (iterEdges.hasNext()) {
				iterEdges.next().resetVisit();
			}
		}
	}

	public int findVertex(String name) {
		ListIterator<LinkedList<City>> iter = vertices.listIterator();
		int i = 0;
		while (iter.hasNext()) {
			//iterate through the ArrayList checking the name of the city
			//in the first Node of each of the LinkedLists that it contains
			if (iter.next().getFirst().getName().compareTo(name) == 0) {
				//iter.next() returns the next LinkedList
				//getFirst() returns the first Node in that LinkedList
				//getName() returns the name of the City since that is the data in the Node
				return i;
			} else {
					i++;
				}

		}
		return -1; //didn't find that City name as a vertex
	}

	public void addVertex(String n) {
		LinkedList<City> nextVertexList;
		City nextVertexCity;
		boolean found = false; //indicates whether city was found in the graph
		int i = 0;
		//loop through the vertices array to see if the city you are looking for is there
		ListIterator<LinkedList<City>> iter = vertices.listIterator();
		while (iter.hasNext()) {
			//iterate through the ArrayList checking the name of the city
			//in the first Node of each of the LinkedLists that it contains
			if (iter.next().getFirst().getName().compareTo(n) == 0) {
				//iter.next() returns the next LinkedList
				//getFirst() returns the first Node in that LinkedList
				//getName() returns the name of the City since that is the data in the Node

				//found the city, it is a duplicate!
				throw new RuntimeException("Duplicate Vertex");

			}
		}//end while 
			//can add the city, it is not a duplicate
		nextVertexCity = new City(n); //set up a new city object
		nextVertexList = new LinkedList<City>(); //set up a new LinkedList for this vertex
		nextVertexList.add(nextVertexCity); //add the city to the LinkedList
		vertices.add(nextVertexList); //add this new LinkedList to the Graph

	}

	public boolean addEdge(String startCity, String endCity) {
		//first determine if start city is in the graph.  It must be a vertex
		int positionSt, positionEnd;
		City edgeCity;
		positionSt = findVertex(startCity);
		if (positionSt == -1) {
			return false; //could not add edge, vertex doesn't exist
		} else {
			positionEnd = findVertex(endCity);
			if (positionEnd == -1) {
				return false; //no such city in the graph
			} else {
				edgeCity = vertices.get(positionEnd).getFirst();
				vertices.get(positionSt).add(edgeCity);
				//vertices.get(position) returns the LinkedList of that vertex
				//.add - add the nextEdge to the LinkedList of that vertex
				//the LinkedList contains the list of edges that can be reached by one
				//hop from that vertex
				return true;
			}
		}
	}

	@Override
	public String toString() {//display vertices and its edge
		StringBuffer graphNodes = new StringBuffer();
		String result;
		String city;
		final String BLANK = " ";
		ListIterator<LinkedList<City>> iter = vertices.listIterator();
		ListIterator<City> iterEdges;
		while (iter.hasNext()) {
			graphNodes.append("\n");
			iterEdges = iter.next().listIterator();
			//now make your way across one linked list of edges
			while (iterEdges.hasNext()) {
				city = iterEdges.next().getName();
				graphNodes.append(city);
				graphNodes.append(BLANK);
			}

		}
		result = new String(graphNodes);
		return result;
	}

	public String displayAdjNodes(String startCity) {
		StringBuffer adjNodes = new StringBuffer();
		String result;
		final String BLANK = " ";
		int position;
		position = findVertex(startCity);
		LinkedList<City> edges;
		ListIterator<City> iter;
		if (position == -1) {
			throw new RuntimeException("Vertex " + startCity + " does not exist");
		} else {
			edges = vertices.get(position);
			iter = edges.listIterator(); //get an iterator to the list from that vertex
			while (iter.hasNext()) {
				adjNodes.append(iter.next().getName());
				adjNodes.append(BLANK);
			}
		}
		result = new String(adjNodes);
		return result;
	}

	public boolean isConnected(String startCity, String destination) {
		//To be completed as a homework exercise using a Stack
		LinkedList<City> vertex;
		ListIterator<City> iter;
		String cityName;
		City nextCity;
		int position;
		boolean done;
		position = findVertex(startCity);
		if (position == -1) {
			throw new RuntimeException("can not find a vertex");
		} else {
			cityName = vertices.get(position).getFirst().getName();
			System.out.println("visited " + cityName);
			if (cityName.compareTo(destination) == 0) {
				System.out.println("visited " + cityName);
				return true; //found the destination

			} else {
				//visit the city
				done = false;
				vertices.get(position).getFirst().visitCity();
				iter = vertices.get(position).listIterator();
				while (iter.hasNext() && !done) {
					nextCity = iter.next();
					if (!nextCity.wasVisited()) {//city was not yet visited
													//search from that point onwards
						done = isConnected(nextCity.getName(), destination);

					}
				}

			}
		}
		return done;

	}
}


