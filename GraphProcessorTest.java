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
        System.out.print(graph.getShortestPath("CAT", "SAM"));
    }
    
    @Test
    public final void testShortestLength() {
        this.graph = new GraphProcessor<String>();
        try {
            graph.populateGraph("TestWords.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        graph.getShortestDistance("CAT", "SAM").compareTo(3);
    }
}
