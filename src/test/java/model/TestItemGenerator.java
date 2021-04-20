package model;

import model.graph.Graph;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface TestItemGenerator<K, GRAPH extends Graph<K>> {

    GRAPH generateGraph();

    K generateNode();

    default List<K> generateNodes(int numToCreate) {
        return IntStream
                .range(0, numToCreate)
                .mapToObj(i -> generateNode())
                .collect(Collectors.toList());
    }
}
