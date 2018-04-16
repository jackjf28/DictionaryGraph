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
    
    private GraphProcessor<String> graph;

    @Test
    public final void createGraph() {
        this.graph = new GraphProcessor<String>();
    }
    
    @Test
    public final void populateGraph() {
        int numberOfWords = 4;
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt").compareTo(numberOfWords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public final void testShortestPath() {
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        graph.getShortestPath("CAT", "SAM");
    }
    
    @Test
    public final void testShortestLength() {
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt");
            graph.shortestPathPrecomputation();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        graph.getShortestDistance("CAT", "SAM").compareTo(2);
    }
    
    @Test
    public final void testShortestLengthLong() {
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt");
            graph.shortestPathPrecomputation();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        graph.getShortestDistance("CAT", "REAM").compareTo(4);
    }
    
    @Test
    public final void testShortestPathLong() {
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        graph.getShortestPath("CAT", "BADGER");
    }
}
