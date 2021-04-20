package model.graph;

public interface Graph<K> extends ReadOnlyGraph<K> {
    void add(K key);
    void remove(K key);

    void connect(K source, K destination);
    void disconnect(K source, K destination);
}
