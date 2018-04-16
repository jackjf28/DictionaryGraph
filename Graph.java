//
// Title:           p4 Dictionary Graph 
// Due Date:        April 16th, 2018
// Files:           Graph.java, GraphTest.java, WordProcessor.java, GraohProcessor.java, GraphProcessorTest.java
// Course:          CS 400, Spring 2018
//
// Authors:          Brady Erdman, Jay Desai, Sam Fetherstorm, Megan Fischer, Jack Farrell
// Email:           erdman3@wisc.edu, jdesai2@wisc.edu, sfetherston@wisc.edu, mfischer9@wisc.edu, jfarrell3@wisc.edu 
// Lecturer's Name: Deb Depeller
//
// No known bugs
//
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Undirected and unweighted graph implementation. Contains an
 * inner class GraphNode<E> which defines the object type that 
 * will be used for the vertexes in the graph itself.
 * 
 * @param <E> type of a vertex
 * 
 * @author X-Team 56
 * 
 */
public class Graph<E> implements GraphADT<E> {
    
	/**
	 * This inner class defines a GraphNode<E> which will
	 * store the information associated with each vertex. This includes
	 * the vertex values with which the node is adjacent. 
	 * 
	 * @author X-Team 56
	 *
	 * @param <E> generic type of vertex
	 */
	class GraphNode<E> {
		//Data contained in node
		E nodeData;
		//Vertices with which the node has an edge
		ArrayList<E> neighborNodes;
		//Node prior to this one in a path
		GraphNode<E> parent;
		//List of shortest paths to other vertices
		HashMap<E, List<String>> shortestPaths;
		
		/**
		 * Constructor initializes each of
		 * the fields. Node starts off as 
		 * unvisited.
		 * 
		 * @param data data to be stored in node
		 */
		GraphNode(E data) {
			this.nodeData = data;
			this.neighborNodes = new ArrayList<E>();
			this.parent = null;
			this.shortestPaths = new HashMap<E, List<String>>();
		}
		
		/**
		 * Returns the node's data
		 * @return the data in the node
		 */
		public E getData() {
			return this.nodeData;
		}
		
		/**
		 * Sets the node's data
		 * @param newData data to be stored
		 */
		public void setData(E newData) {
			this.nodeData = newData;
		}
		
		/**
		 * Overridden toString method to make
		 * testing easier. Uses toString method
		 * of the data type in node.
		 */
		@Override
		public String toString() {
			return this.nodeData.toString();
		}
	}
	
	//Fields
	/* List that holds all vertexes in the graph.
	 * Wanted this to hold GraphNode<E>s, but it made more
	 * sense to just return the data values associated with
	 * a vertex instead for testing.
	 */
	private ArrayList<E> vertexList;
	//HashMap that maps each to each GraphNode<E> using data value in that node
	//Functions as an adjacency list since nodes contain list of adjacent vertices
	private HashMap<E, GraphNode<E>> adjacencyList;

	/**
	 * Constructor initializes two primary data structures.
	 */
	public Graph() {
		this.vertexList = new ArrayList<E>();
		this.adjacencyList = new HashMap<E, GraphNode<E>>();
		
	}
	
	/**
	 * Add new vertex to the graph
	 * 
	 * Valid argument conditions:
	 * 1. vertex should be non-null
	 * 2. vertex should not already exist in the graph 
	 * 
	 * @param vertex the vertex to be added
	 * @return vertex if vertex added, else return null if vertex can not be added (also if valid conditions are violated)
	 */
    @Override
    public E addVertex(E vertex) {
    	//Add vertex if preconditions are met
    	if(vertex != null && !this.vertexList.contains(vertex)) {
    		//Add vertex to both primary data structures
    		this.vertexList.add(vertex);
    		this.adjacencyList.put(vertex, new GraphNode<E>(vertex));
    		//Return value of newly added vertex
    		return vertex;
    	}
    	//Return null if conditions not met
    	else {
    		return null;
    	}
    }

	/**
	 * Remove the vertex and associated edge associations from the graph
	 * 
	 * Valid argument conditions:
	 * 1. vertex should be non-null
	 * 2. vertex should exist in the graph 
	 *  
	 * @param vertex the vertex to be removed
	 * @return vertex if vertex removed, else return null if vertex and associated edges can not be removed (also if valid conditions are violated)
	 */
    @Override
    public E removeVertex(E vertex) {
    	//Return null if preconditions not met
        if(vertex == null || !this.vertexList.contains(vertex)) {
        	return null;
        }
        /*
         * Iterate through all vertices with which this vertex has an edge, remove them
           from the adjacency list. Remove vertex from vertex list and adjacency list whilst
           returning the data that was stored in it.
         */
        else {
        	for(E neighborVertex: this.adjacencyList.get(vertex).neighborNodes) {
        		this.adjacencyList.get(neighborVertex).neighborNodes.remove(vertex);
        	}
        	this.vertexList.remove(vertex);
        	return this.adjacencyList.remove(vertex).nodeData;
        }
    }

	/**
	 * Add an edge between two vertices (edge is undirected and unweighted)
	 * 
	 * Valid argument conditions:
	 * 1. both the vertices should exist in the graph
	 * 2. vertex1 should not equal vertex2
	 *  
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return true if edge added, else return false if edge can not be added (also if valid conditions are violated)
	 */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        //Return false if preconditions not met
    	if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        //Add each vertex to the others list of adjacent vertices
    	//Return true
        else {
        	//Condition made so duplicates aren't added to anodes list of neighbors
        	if(!this.adjacencyList.get(vertex1).neighborNodes.contains(vertex2) ||
        		!this.adjacencyList.get(vertex2).neighborNodes.contains(vertex1)) {	
        	this.adjacencyList.get(vertex2).neighborNodes.add(vertex1);
        	this.adjacencyList.get(vertex1).neighborNodes.add(vertex2);
        	return true;
        	}
        	return false;
        }
    }    

	/**
	 * Remove the edge between two vertices (edge is undirected and unweighted)
	 * 
	 * Valid argument conditions:
	 * 1. both the vertices should exist in the graph
	 * 2. vertex1 should not equal vertex2
	 *  
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return true if edge removed, else return false if edge can not be removed (also if valid conditions are violated)
	 */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
    	//Return false if preconditions not met
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        //Remove each vertex from the others list of adjacent vertices
    	//Return true
        else {
        	this.adjacencyList.get(vertex2).neighborNodes.remove(vertex1);
        	this.adjacencyList.get(vertex1).neighborNodes.remove(vertex2);
        	return true;
        }
    }

	/**
	 * Check whether the two vertices are adjacent
	 * 
	 * Valid argument conditions:
	 * 1. both the vertices should exist in the graph
	 * 2. vertex1 should not equal vertex2
	 *  
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 * @return true if both the vertices have an edge with each other, else return false if vertex1 and vertex2 are not connected (also if valid conditions are violated)
	 */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
    	//Return false if preconditions not met
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        //Return whether or not each vertice's list of adjacent vertices contains the other
        else {
        	if(this.adjacencyList.get(vertex1).neighborNodes.contains(vertex2) && 
        			this.adjacencyList.get(vertex2).neighborNodes.contains(vertex1)) {
        		return true;
        	}
        		
        	else {
        		return false;
        	}
        }
    }

	/**
	 * Get all the neighbor vertices of a vertex
	 * 
	 * Valid argument conditions:
	 * 1. vertex is not null
	 * 2. vertex exists
	 * 
	 * @param vertex the vertex
	 * @return an iterable for all the immediate connected neighbor vertices
	 */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
    	//Return list of adjacent vertices if preconditions met
    	if(vertex != null && this.vertexList.contains(vertex)) {
    		return this.adjacencyList.get(vertex).neighborNodes;
    	}
    	//Throw exception if they are not
    	else {
    		throw new IllegalArgumentException();
    	}
    }

	/**
	 * Get all the vertices in the graph
	 * 
	 * @return an iterable for all the vertices
	 */
    @Override
    public Iterable<E> getAllVertices() {
    	//Return list of all vertex values in graph
        return this.vertexList;
    }
    /**
     * Returns the graphNode containing data 'vertex'
     * 
     * @param vertex the data inside nodeData in the GraphNode class
     * @return the graphNode with nodeData == vertex.
     */
    public GraphNode<E> getGraphNode(E vertex){
    	return adjacencyList.get(vertex);
    }
}
