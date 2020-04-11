package model;

import model.edge.Edge;
import model.graph.Graph;
import model.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface GraphTestItemGenerator<N, E> {
    N generateNodeKey();

    Graph generateGraph();

    Edge generateEdge(Node node1, Node node2);

    Node generateNode();

    default List<Node> generateNodes(int numToCreate) {
        return IntStream
                .range(0, numToCreate)
                .mapToObj(i -> generateNode())
                .collect(Collectors.toList());
    }

    default List<Edge> generateEdges(int numToCreate, List<Node> nodesToConnect) {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < numToCreate; i++) {
            Node node1 = nodesToConnect.get(i * 2);
            Node node2 = nodesToConnect.get(i * 2 + 1);
            result.add(generateEdge(node1, node2));
        }
        return result;
    }
}
