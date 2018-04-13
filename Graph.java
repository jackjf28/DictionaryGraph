import java.util.HashMap;
import java.util.HashSet;
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
		
		GraphNode(E data) {
			this.nodeData = data;
			this.visited = false;
			this.neighbors = new ArrayList<E>();
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
    
    public String graphToString() {
		return this.adjacencyList.toString();
    }
}
