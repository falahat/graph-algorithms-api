package model;

import model.graph.Graph;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface TestItemGenerator<K, GRAPH extends Graph<K>> {

    GRAPH generateGraph();

    K generateNode(long seedValue);

    default K generateNode() {
        return generateNode(UUID.randomUUID().getLeastSignificantBits());
    }

    default List<K> generateNodes(int numToCreate) {
        return IntStream.range(0, numToCreate).boxed()
                .map(i -> generateNode())
                .collect(Collectors.toList());
    }
}
