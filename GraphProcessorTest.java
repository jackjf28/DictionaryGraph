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
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit test class to test class @see GraphProcessor
 *
 */
public class GraphProcessorTest {
	private GraphProcessor<String> graph; // Runs tests with TestWords.txt
	private GraphProcessor<String> wlGraph; // Runs tests with word_list.txt
	private GraphProcessor<String> popGraph; // Empty so runs populateGraph tests
	String expected = null;
	String actual = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		graph = new GraphProcessor<String>();
		graph.populateGraph("TestWords.txt");
		wlGraph = new GraphProcessor<String>();
		wlGraph.populateGraph("word_list.txt");
		popGraph = new GraphProcessor<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		graph = null;
		wlGraph = null;
		popGraph = null;
	}
	
// File1 tests using TestWords.txt
	
    @Test
    public final void populateGraph() {
        Integer numberOfWords = 8;
        try {
            int number = popGraph.populateGraph("TestWords.txt").compareTo(numberOfWords);
            System.out.println(number);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            fail("Some error message");
        }
    }
    
    @Test
    public final void testShortestPath() {
        graph.getShortestPath("CAT", "SAM");
    }
    
    @Test
    public final void testShortestLength() {
    	graph.shortestPathPrecomputation();
        graph.getShortestDistance("CAT", "SAM").compareTo(2);
    }
    
    @Test
    public final void testShortestPathLong() {
        graph.getShortestPath("CAT", "BADGER");
    }
    
    @Test
    public final void testShortestLengthLong() {
        graph.shortestPathPrecomputation();
        graph.getShortestDistance("CAT", "REAM").compareTo(4);
    }
    
// File2 tests using word_list.txt
    
    @Test
    public final void populateGraph_word_list() {
        int numberOfWords = 441;
        try {
            int number = popGraph.populateGraph("word_list.txt").compareTo(numberOfWords);
            expected = "0";
	    actual = "" + number;
    		if (!expected.equals(actual))
    		    fail("expected: "+expected+ " actual: "+actual);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            fail("IOException was thrown");
        }
    }
    
    @Test
    public final void testShortestPath_word_list() {
        expected = "[BELLIES, JELLIES, JOLLIES]";
        actual = "" + wlGraph.getShortestPath("BELLIES", "JOLLIES");
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    public final void testShortestLength_word_list() {
	expected = "49";
	wlGraph.shortestPathPrecomputation();
        actual = "" + wlGraph.getShortestDistance("COMEDO", "CHARGE");
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    public final void testShortestPathLong_word_list() {
        expected = "[DEFINE, DEFILE, DECILE, DECKLE, HECKLE, HACKLE, HACKEE, HACKER, HANKER, "
        		+ "RANKER, RANTER, RENTER, RENDER, READER, HEADER, HEALER, SEALER, SCALER, SCARER, "
        		+ "SHARER, SHAVER, SHIVER, SHINER, WHINER, WHINEY, WHINNY, SHINNY]";
        actual = "" + wlGraph.getShortestPath("DEFINE", "SHINNY");
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    public final void testShortestLengthLong_word_list() {
	expected = "78";
	wlGraph.shortestPathPrecomputation();
        actual = "" + wlGraph.getShortestDistance("CHARGE", "GIMLETS");
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
}
