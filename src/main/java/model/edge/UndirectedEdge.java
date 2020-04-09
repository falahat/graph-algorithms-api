package model.edge;

import model.node.Node;

public interface UndirectedEdge<N extends Node> extends Edge<N> {

    @Override
    default boolean isDirected() {
        return false;
    }
}
