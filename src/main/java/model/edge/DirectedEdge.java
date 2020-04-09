package model.edge;

import model.node.Node;

public interface DirectedEdge<N extends Node> extends Edge<N> {

    @Override
    default boolean isDirected() {
        return true;
    }
}
