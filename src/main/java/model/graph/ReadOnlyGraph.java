package model.graph;

import model.node.Node;

import java.util.Collection;

/**
 *
 * @param <K> The key values for this graph. Every node will be uniquely identified by this.
 */
public interface ReadOnlyGraph<K> {
    Collection<Node> nodes();

    boolean contains(Node node);

    boolean isConnected(Node src, Node dst);

    /**
     * @return All nodes such that isConnected(source, node) == true;
     */
    Collection<Node> edges(Node source);
}
