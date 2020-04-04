import model.Edge;
import model.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public abstract class NodeTest<N extends Node, E extends Edge<N>> extends GraphTest<N, E> {

    @Test
    public void testGettingZeroNodes() {
        Collection<N> nodes = graph.nodes();
        Assert.assertNotNull("Must not be null", nodes);
        Assert.assertTrue("Must be empty list", nodes.isEmpty());
    }

    @Test
    public void testGettingSingleNodes() {
        N node1 = itemGenerator().generateNode();

        graph.add(node1);

        Collection<N> nodes = graph.nodes();
        Assert.assertTrue("Must contain node1", nodes.contains(node1));
    }

    @Test
    public void testGettingMultipleNodes() {
        N node1 = itemGenerator().generateNode();
        N node2 = itemGenerator().generateNode();

        graph.add(node1);
        graph.add(node2);

        Collection<N> nodes = graph.nodes();
        Assert.assertTrue("Must contain node1", nodes.contains(node1));
        Assert.assertTrue("Must contain node2", nodes.contains(node2));
    }
}
