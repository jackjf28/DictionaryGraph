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
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    
	class GraphNode<E> {
		
		E nodeData;
		boolean visited;
		ArrayList<E> neighbors;
		//To easily find the shortest path from one
		//GraphNode to another.
		GraphNode<E> parent;
		//Stores all shortest paths from the GraphNode
		//to E(The goal word in the dictionary)
		HashMap<E, List<String>> shortestPaths;
		
		GraphNode(E data) {
			this.nodeData = data;
			this.visited = false;
			this.neighbors = new ArrayList<E>();
			this.shortestPaths = new HashMap<E, List<String>>();
		}
		
		public E getData() {
			return this.nodeData;
		}
		
		public void setData(E newData) {
			this.nodeData = newData;
		}
		
		@Override
		public String toString() {
			return this.nodeData.toString();
		}
	}
	
    /**
     * Instance variables and constructors
     */
	//List that holds all vertexes in the graph
	private ArrayList<E> vertexList;
	private HashMap<E, GraphNode<E>> adjacencyList;

	public Graph() {
		this.vertexList = new ArrayList<E>();
		this.adjacencyList = new HashMap<E, GraphNode<E>>();
		
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
    	if(vertex != null && !this.vertexList.contains(vertex)) {
    		this.vertexList.add(vertex);
    		this.adjacencyList.put(vertex, new GraphNode<E>(vertex));
    		return vertex;
    	}
    	else {
    		return null;
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        if(vertex == null || !this.vertexList.contains(vertex)) {
        	return null;
        }
        
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
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        
        else {
        	//Condition made so duplicates aren't added to a nodes list of neighbors
        	if(!this.adjacencyList.get(vertex1).neighbors.contains(vertex2) ||
        	    !this.adjacencyList.get(vertex2).neighbors.contains(vertex1)) {
        	this.adjacencyList.get(vertex2).neighbors.add(vertex1);
        	this.adjacencyList.get(vertex1).neighbors.add(vertex2);
        	}
        	return true;
        }
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
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
        if(!this.vertexList.contains(vertex1) || 
        		!this.vertexList.contains(vertex2) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        
        else {
        	if(this.adjacencyList.get(vertex1).neighbors.contains(vertex2)) {
        		return true;
        	}
        	return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
    	if(vertex != null && this.vertexList.contains(vertex)) {
    		return this.adjacencyList.get(vertex).neighbors;
    	}
    	else {
    		throw new IllegalArgumentException();
    	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
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
