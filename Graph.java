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
		//Whether or not node has been visited
		boolean visited;
		//Vertexes with which the node has an edge
		ArrayList<E> neighbors;
		
		/**
		 * Constructor initializes each of
		 * the fields. Node starts off as 
		 * unvisited.
		 * 
		 * @param data data to be stored in node
		 */
		GraphNode(E data) {
			this.nodeData = data;
			this.visited = false;
			this.neighbors = new ArrayList<E>();
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
        	for(E neighborVertex: this.adjacencyList.get(vertex).neighbors) {
        		this.adjacencyList.get(neighborVertex).neighbors.remove(vertex);
        	}
        	this.vertexList.remove(vertex);
        	return this.adjacencyList.remove(vertex).nodeData;
        }
    }

    /**
     * {@inheritDoc}
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
        	this.adjacencyList.get(vertex2).neighbors.add(vertex1);
        	this.adjacencyList.get(vertex1).neighbors.add(vertex2);
        	return true;
        }
    }    

    /**
     * {@inheritDoc}
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
        	this.adjacencyList.get(vertex2).neighbors.remove(vertex1);
        	this.adjacencyList.get(vertex1).neighbors.remove(vertex2);
        	return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
    	//Return false if preconditions not met
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        //Return whether or not vertex1's list of adjacent vertices contains vertex2
        else {
        		return this.adjacencyList.get(vertex1).neighbors.contains(vertex2);
        	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
    	//Return list of adjacent vertices if preconditions met
    	if(vertex != null && this.vertexList.contains(vertex)) {
    		return this.adjacencyList.get(vertex).neighbors;
    	}
    	//Throw exception if they are not
    	else {
    		throw new IllegalArgumentException();
    	}
    }

    /**
     * {@inheritDoc}
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
    public String graphToString() {
		return this.adjacencyList.toString();
    }
}
