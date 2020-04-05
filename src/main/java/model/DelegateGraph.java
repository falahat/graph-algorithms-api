package model;

import java.util.Collection;

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
 * @param <E>
 */
public class DelegateGraph<N extends Node, E extends Edge<N>> implements Graph<N, E> {
    private final Graph<N, E> innerGraph;

    public DelegateGraph(Graph<N, E> innerGraph) {
        this.innerGraph = innerGraph;
    }

    @Override
    public boolean contains(N node) {
        return innerGraph.contains(node);
    }

    @Override
    public void add(N node) {
        innerGraph.add(node);
    }

    @Override
    public void add(E edge) {
        innerGraph.add(edge);
    }

    @Override
    public void remove(N node) {
        innerGraph.remove(node);
    }

    @Override
    public void remove(E edge) {
        innerGraph.remove(edge);
    }

    @Override
    public Collection<N> nodes() {
        return innerGraph.nodes();
    }

    @Override
    public Collection<E> edges() {
        return innerGraph.edges();
    }

    @Override
    public Collection<E> edges(N node) {
        return innerGraph.edges(node);
    }
}
