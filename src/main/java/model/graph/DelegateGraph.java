package model.graph;

import model.node.Node;

import java.util.Collection;
import java.util.List;

/**
 * This serves as a view of an existing graph. If nodes are added or removed from either graph,
 * the changes will be reflected in the other graph automatically.
 *
 * This can be used to prevent the use of certain methods. For example, we can implement a DelegateGraph class which
 * throws an "UnsupportedOperationException" when a programmer calls {@code remove()}.
 *
 * This can be used as a labeled overview of an existing graph to attach arbitrary data to each node
 * or edge. See {@link LabeledGraph}.
 *
 * This can be a useful abstraction for extracting multiple types of information from a graph network without
 * duplicating the underlying data.
 *
 * For example, assume we have a graph network that represents a 2D gameboard where each node is a tile in the game.
 * To represent this game, we can use multiple delegated layers:
 *    1. Labeled layer which is a labeled overlay of the existing graph, representing
 *    2. Labeled layer layer which holds a list for each node of which players are standing on that tile.
 *    3. Read-Only board which is passed to players. This is read-only so they can decide their next move but not
 *       mutate the game.
 *
 * By creating these layers as delegated graphs, we know that labels will automatically be removed if a node is removed.
 * We can store labeled information about the graph and not need to worry about the exact data types of the nodes or edges.
 *
 */
public class DelegateGraph implements Graph {
    private final Graph innerGraph;

    public DelegateGraph(Graph innerGraph) {
        this.innerGraph = innerGraph;
    }

    @Override
    public void add(Node node) {
        innerGraph.add(node);
    }

    @Override
    public void remove(Node node) {
        innerGraph.remove(node);
    }

    @Override
    public void connect(Node node1, Node node2) {
        innerGraph.connect(node1, node2);
    }

    @Override
    public void disconnect(Node node1, Node node2) {
        innerGraph.disconnect(node1, node2);
    }

    @Override
    public Collection<Node> nodes() {
        return innerGraph.nodes();
    }

    @Override
    public boolean contains(Node node) {
        return innerGraph.contains(node);
    }

    @Override
    public boolean isConnected(Node src, Node dst) {
        return innerGraph.isConnected(src, dst);
    }

    @Override
    public Collection<Node> edges(Node source) {
        return innerGraph.edges(source);
    }
}
