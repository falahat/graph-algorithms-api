package model;

import model.graph.Graph;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class GraphTestUtil<K, GRAPH extends Graph<K>> {

    public abstract GRAPH generateGraph();

    public abstract K generateNode(long seedValue);

    public K generateNode() {
        return generateNode(UUID.randomUUID().getLeastSignificantBits());
    }

    public List<K> generateNodes(int numToCreate) {
        return IntStream.range(0, numToCreate).boxed()
                .map(i -> generateNode())
                .collect(Collectors.toList());
    }

}
