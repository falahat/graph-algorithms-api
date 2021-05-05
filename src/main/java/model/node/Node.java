package model.node;


import java.util.Objects;

/**
 *
 * @param <K> - The type of the key value which makes each node unique from one another
 */
public class Node<K> {
    private final K key;
    public Node(K key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Node) && Objects.equals(this.key, ((Node) obj).key);
    }

    @Override
    public String toString() {
        return String.format("Node[%s]", key.toString());
    }
}
