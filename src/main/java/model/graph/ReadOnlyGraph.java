package model.graph;

import java.util.Collection;

/**
 *
 * @param <K> The key types for this graph. Every node will be uniquely identified by this.
 */
public interface ReadOnlyGraph<K> {
    Collection<K> nodes();

//    Node<K> getNode(K key);

    boolean contains(K node);

    boolean isConnected(K src, K dst);

    /**
     * @return All nodes such that isConnected(source, node) == true;
     */
    Collection<K> edges(K source);
}
