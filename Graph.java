import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
		
		GraphNode(E data) {
			this.nodeData = data;
			this.visited = false;
		}
		
		public E getData() {
			return this.nodeData;
		}
		
		public void setData(E newData) {
			this.nodeData = newData;
		}
		
		@Override
		public boolean equals(Object graphNode) {
	        if (((GraphNode<E>)graphNode).getData().equals(this.nodeData)){
	        	return true;
	        }
	        return false;
		}
		
		@Override
		public int hashCode() {
			return this.nodeData.hashCode();
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
	private HashSet<GraphNode<E>> vertexList;
	
	//List that holds all edges in the graph
	private HashMap<GraphNode<E>,ArrayList<GraphNode<E>>> adjacencyList;

	public Graph() {
		this.vertexList = new HashSet<GraphNode<E>>();
		this.adjacencyList = new HashMap<GraphNode<E>,ArrayList<GraphNode<E>>>();
		
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
    	if(vertex != null && !this.vertexList.contains(new GraphNode<E>(vertex))) {
    		this.vertexList.add(new GraphNode<E>(vertex));
    		this.adjacencyList.put(new GraphNode<E>(vertex), new ArrayList<GraphNode<E>>());
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
        if(vertex == null || !this.vertexList.contains(new GraphNode<E>(vertex))) {
        	return null;
        }
        
        else {
        	this.vertexList.remove(new GraphNode<E>(vertex));
        	this.adjacencyList.remove(new GraphNode<E>(vertex));
        	return vertex;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        if(!this.vertexList.contains(new GraphNode<E>(vertex1)) || 
        		!this.vertexList.contains(new GraphNode<E>(vertex2)) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        
        else {
        	this.adjacencyList.get(new GraphNode<E>(vertex1)).add(new GraphNode<E>(vertex2));
        	this.adjacencyList.get(new GraphNode<E>(vertex2)).add(new GraphNode<E>(vertex1));
        	return true;
        }
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if(!this.vertexList.contains(new GraphNode<E>(vertex1)) || 
        		!this.vertexList.contains(new GraphNode<E>(vertex2)) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        
        else {
        	this.adjacencyList.get(new GraphNode<E>(vertex1)).remove(new GraphNode<E>(vertex2));
        	this.adjacencyList.get(new GraphNode<E>(vertex2)).remove(new GraphNode<E>(vertex1));
        	return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        if(!this.vertexList.contains(new GraphNode<E>(vertex1)) || 
        		!this.vertexList.contains(new GraphNode<E>(vertex2)) || 
        		vertex1.equals(vertex2)) {
        	return false;
        }
        
        else {
        	if(this.adjacencyList.get(new GraphNode<E>(vertex1)).contains(new GraphNode<E>(vertex2))) {
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
        return (Iterable<E>) this.adjacencyList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        return (Iterable<E>) this.vertexList;
    }
    
    public String hashSetToString() {
    	System.out.println(this.adjacencyList.toString());
		return this.vertexList.toString();
    }
}
