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
	private GraphProcessor<String> graphProcessor; // Runs tests with TestWords.txt
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
		graphProcessor = new GraphProcessor<String>();
		graphProcessor.populateGraph("TestWords.txt");
		wlGraph = new GraphProcessor<String>();
		wlGraph.populateGraph("word_list.txt");
		popGraph = new GraphProcessor<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		graphProcessor = null;
		wlGraph = null;
		popGraph = null;
	}
	
// File1 tests using TestWords.txt
	
    @Test
    public final void populateGraph() {
        Integer numberOfWords = 8;
        try {
            int number = popGraph.populateGraph("TestWords.txt").compareTo(numberOfWords);
            if (number != 0)
                throw new IOException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            fail("Expected:" + numberOfWords + "but did not get that value.");
        }
    }
    
    @Test   
    public final void fileTest() {
        try {
            Integer numberOfWords = 8;
            int number = popGraph.populateGraph("Stuff").compareTo(numberOfWords);
            fail("Expected an IOException but did not get one.");
        } catch (IOException e) {
        }
    }
    
    
    @Test
    public final void testShortestPath() {
    	graphProcessor.shortestPathPrecomputation();
        graphProcessor.getShortestPath("CAT", "SAM");
    }
    
    @Test
    public final void testShortestLength() {
    	graphProcessor.shortestPathPrecomputation();
        graphProcessor.getShortestDistance("CAT", "SAM").compareTo(2);
    }
    
    @Test
    public final void testShortestPathLong() {
    	graphProcessor.shortestPathPrecomputation();
        graphProcessor.getShortestPath("CAT", "BADGER");
    }
    
    @Test
    public final void testShortestLengthLong() {
        graphProcessor.shortestPathPrecomputation();
        graphProcessor.getShortestDistance("CAT", "REAM").compareTo(4);
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
    	wlGraph.shortestPathPrecomputation();
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
    	wlGraph.shortestPathPrecomputation();
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
    
    //Tests for WordProcessor
    //Each test checks both directions and different word permutations
    
    //This tests that two words are adjacent by deletion
    @Test
    public void testisAdjacent_deletion_true() {
    	String[] testStrings = {"catt", "cat", "att", "ctt"};
    	expected = "true";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    //This tests that two words are adjacent by addition
    @Test
    public void testisAdjacent_addition_true() {
    	String[] testStrings = {"cat", "at", "ct", "ca"};
    	expected = "true";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    //This tests that two words are adjacent by substitution
    @Test
    public void testisAdjacent_substitution_true() {
    	String[] testStrings = {"cat", "sat", "cst", "cas"};
    	
    	expected = "true";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);

    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    //This tests that two words are not adjacent by deletion
    @Test
    public void testisAdjacent_deletion_false() {
    	String[] testStrings = {"catt", "baj", "atr", "ckt"};
    	expected = "false";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    //This tests that two words are not adjacent by addition
    @Test
    public void testisAdjacent_addition_false() {
    	String[] testStrings = {"cat", "ft", "cl", "ja"};
    	expected = "false";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
    
    //This tests that two words are not adjacent by substitution
    @Test
    public void testisAdjacent_substitution_false() {
    	String[] testStrings = {"cat", "sbt", "csh", "zas"};
    	
    	expected = "false";
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[1]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);

    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[2]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
    	actual = "" + WordProcessor.isAdjacent(testStrings[0], testStrings[3]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[1], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[2], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
        
        actual = "" + WordProcessor.isAdjacent(testStrings[3], testStrings[0]);
        if (!expected.equals(actual))
			fail("expected: "+expected+ " actual: "+actual);
    }
 
}
