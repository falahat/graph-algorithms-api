package model.graph;

import model.edge.Edge;
import model.node.Node;

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
 * @param <N>
 */
public class DelegateGraph<N> implements Graph<N> {
    private final Graph<N> innerGraph;

    public DelegateGraph(Graph<N> innerGraph) {
        this.innerGraph = innerGraph;
    }

    @Override
    public Node add(N node) {
        return innerGraph.add(node);
    }

    @Override
    public Edge connect(Node node1, Node node2) {
        return innerGraph.connect(node1, node2);
    }

    @Override
    public Edge connect(N nodeValue1, N nodeValue2) {
        return innerGraph.connect(nodeValue1, nodeValue2);
    }

    @Override
    public void remove(Node node) {
        innerGraph.remove(node);
    }

    @Override
    public void disconnect(Node node1, Node node2) {
        innerGraph.disconnect(node1, node2);
    }

    @Override
    public void disconnect(N nodeValue1, N nodeValue2) {
        innerGraph.disconnect(nodeValue1, nodeValue2);
    }

    @Override
    public List<Node> nodes() {
        return innerGraph.nodes();
    }

    @Override
    public List<Node> nodes(N targetValue) {
        return innerGraph.nodes(targetValue);
    }

    @Override
    public Node node(N targetValue) {
        return innerGraph.node(targetValue);
    }

    @Override
    public Edge edge(Node node1, Node node2) {
        return innerGraph.edge(node1, node2);
    }

    @Override
    public List<Edge> edges() {
        return innerGraph.edges();
    }

    @Override
    public List<Edge> edges(Node node) {
        return innerGraph.edges(node);
    }

    @Override
    public boolean contains(Node node) {
        return innerGraph.contains(node);
    }

    @Override
    public List<Node> neighbors(Node node) {
        return innerGraph.neighbors(node);
    }
}
