import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    
    /**
     * Instance variables and constructors
     */	
		/**
	 * Inner class that defines graph nodes which will
	 * be the objects associated with each vertex.
	 * 
	 * @author XTeam 56
	 *
	 * @param <E> generic data type to be used for graph
	 */
	class GraphNode<E> {
		
		E nodeData;
		boolean visited;
		HashMap<E,E> neighbors;
		
		GraphNode(E data) {
			this.nodeData = data;
			this.visited = false;
			this.neighbors = new HashMap<E,E>();
		}
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        
    	return vertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        
    	return vertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        return false;
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        return (Iterable<E>) vertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        E itr = null;
		return (Iterable<E>) itr;
    }

}
