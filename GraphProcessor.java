import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * @param <E>
 * 
 */
public class GraphProcessor<E> {

    /**
     * Graph which stores the dictionary words and their associated connections
     */
    private GraphADT<String> graph;
    private ArrayList<String> shortestPath = new ArrayList<String>();
	private Queue<String> bfsQueue = new LinkedList<String>();
	private ArrayList<String> exploredWords = new ArrayList<String>();
	private ArrayList<String> inFrontier = new ArrayList<String>();

    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     * @throws IOException 
     */
    public Integer populateGraph(String filepath) throws IOException {
        Stream<String> streamOfLines = WordProcessor.getWordStream(filepath);
        List<String> listOfStrings = streamOfLines.collect(Collectors.toList());
        int numVertices = 0;
        for (String s : listOfStrings) {
        	//Add words to the graph
        	//Duplicate taken care of in Graph.java(?)
        	graph.addVertex(s);
        	numVertices++;
        }
        //Adds edges to all adjacent words
        findNeighbors();
        return numVertices;  
    }
    /**
     * Helper method to add edges for all
     * adjacent words in the graph.
     */
    private void findNeighbors() {
    	//Nested for-each loop to compare all vertices,
    	//If anyone can find a more efficient way feel free to edit.
    	for(String g : graph.getAllVertices()) {
    		for(String n : graph.getAllVertices()) {
    			if(WordProcessor.isAdjacent(g, n)) {
    				graph.addEdge(g, n);
    			}
    		}
    	}
    }
  
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     * 
     * @param word1 first word, lets make this the 'starting' state
     * @param word2 second word, this will be the 'goal' state.
     * @return List<String> list of the words
     */
    public List<String> getShortestPath(String word1, String word2) {
    	//resets shortestPath to make sure no nodes are left inside
    	shortestPath = new ArrayList<String>();
    	//Execute bfsSearch if the start node and goal node aren't 
    	//the same.
    	if(word1 != word2) {
        	bfsSearch(word1, word2);   	
    	}
    	//Return a list only containing one String (word1)
    	else {
    		shortestPath.add(word1);
    	}
        return shortestPath;  
    } 
    
    /**
     * This helper method implements the Breadth First Search algorithm (an unweighted
     * 	version of Dijkstra's Algorithm) to find the shortest path between two words
     * 	in the dictionary.
     * 
     * @param currNode currently expanded node, looks at it's children(adjacent nodes)
     * @param end the goal node we are trying to reach
     */
    private void bfsSearch(String currNode, String end) {
    	bfsQueue.add(currNode);
    	exploredWords.add(currNode);

    	while(!bfsQueue.isEmpty()){
    		
    		currNode = bfsQueue.remove();
    		exploredWords.add(currNode);
    		//Goal Check
    		if(currNode == end) {
    			getPath(currNode);
    			break;
    		}
    		//Prints out expanded nodes
    		System.out.println(currNode+ " ");
	    	//Iterates through adjacent vertices of the current node.
	    	for(String word : graph.getNeighbors(currNode)) {
	    		//Checks to make sure the node hasn't already been
	    		//expanded AND isn't already in the bfsQueue
	    		if(!exploredWords.contains(word) && !bfsQueue.contains(word)) {
	    			bfsQueue.add(word);
	    			//Assigns the currNode as word's parent.
	    			//Had to typecast to Graph<String> in order to access the 
	    			// getGraphNode method.
	    			((Graph<String>) graph).getGraphNode(word).parent = 
	    								((Graph<String>) graph).getGraphNode(currNode);
	    		}
	    	}   
    	}
    	//If the bfs Queue is empty that means that
    	//the goal node wasn't found.
    	if(bfsQueue.isEmpty()) {
    		System.out.println("Path to " + end + " was not found.");
    	}
    	else {
    		System.out.println("Goal state found!");
    	}
    }
    
    /**
     * This method recursively moves from the goal node to the start node, and then
     *  adds each node in order to List<String> shortestPath.
     *  
     * @param graphNodeVal the current node we are at in the Graph
     */
    private void getPath(String graphNodeVal) {
    	//If this condition is true, that means we have reached the starting node.
    	if( graphNodeVal != null) {
    		//Recursively calls up the parent hierarchy of nodes until it reaches the start
    		//node. 
    		getPath(((Graph<String>) graph).getGraphNode(graphNodeVal).parent.nodeData);
    		//Then adds each node on the path to shortestPath in order.
    		shortestPath.add(graphNodeVal);
    	}
    	return;
    }
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     *   = 3 (the number of edges in the shortest path)
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
    public Integer getShortestDistance(String word1, String word2) {
    	//Subtract 1 to account for the starting node
    	if(shortestPath != null) {
    		//Accesses the graphNode word1's HashMap of shortestPaths and returns the size of the
    		// shortest path to word2
    		//Also subtracts 1 off of the size to get the correct number of edges in the shortest path.
    		Integer edgeNum = ((Graph<String>) graph).getGraphNode(word1).shortestPaths.get(word2).size() - 1;
	        return edgeNum;
    	}
    	//Returns -1 when there is no path from word1 to word2
    	return -1;
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {
    	//Iterates through all possible 'starting' nodes for a given path
    	for(String s : graph.getAllVertices()) {
    		//Iterates through all potential 'goal' nodes for a given path
    		for(String g : graph.getAllVertices()) {
    			//Assigns a list of the words of a shortest path from word
    			// s to word g.
    			((Graph<String>) graph).getGraphNode(s).shortestPaths.put(g, getShortestPath(s,g));
    		}
    	}
    }
}
