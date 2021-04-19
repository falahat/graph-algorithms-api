package model;

import model.graph.Graph;
import model.node.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface GraphTestItemGenerator<N extends Cloneable> {
    N generateNodeKey();

    Graph<N> generateGraph();

    Node generateNode();

    default List<Node> generateNodes(int numToCreate) {
        return IntStream
                .range(0, numToCreate)
                .mapToObj(i -> generateNode())
                .collect(Collectors.toList());
    }
}
