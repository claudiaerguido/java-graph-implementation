package org.pr2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    private Graph<Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        assertTrue(graph.addVertex(1));
        assertFalse(graph.addVertex(1));
    }

    @Test
    public void testAddEdge() {
        assertTrue(graph.addEdge(1, 2));
        assertFalse(graph.addEdge(1, 2));
    }

    @Test
    public void testContainsVertex() {
        graph.addVertex(1);
        assertTrue(graph.containsVertex(1));
        assertFalse(graph.containsVertex(2));
    }

    @Test
    public void testObtainAdjacents() {
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        try {
            assertTrue(graph.obtainAdjacents(1).contains(2));
            assertTrue(graph.obtainAdjacents(1).contains(3));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test(expected = Exception.class)
    public void testObtainAdjacentsException() throws Exception {
        graph.obtainAdjacents(1);
    }

    @Test
    public void testOnePath() {
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(1, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);

        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);

        assertEquals(expectedPath, graph.onePath(1, 4));
    }

    @Test
    public void testOnePathNotFound() {
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(1, 5);

        assertNull(graph.onePath(1, 4));
    }
}

