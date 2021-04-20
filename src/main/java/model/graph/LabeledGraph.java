package model.graph;

public interface LabeledGraph<K, V> extends ReadOnlyGraph<K> {
    V get(K node);

    void put(K node, V label);
}
