import model.Edge;
import model.Graph;
import model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface GraphTestItemGenerator<N extends Node, E extends Edge<N>> {
    Graph<N, E> generateGraph();
    E generateEdge(N node1, N node2);
    N generateNode();

    default List<N> generateNodes(int numToCreate) {
        return IntStream
                .range(0, numToCreate)
                .mapToObj(i -> generateNode())
                .collect(Collectors.toList());
    }

    default List<E> generateEdges(int numToCreate, List<N> nodesToConnect) {
        List<E> result = new ArrayList<>();
        for (int i = 0; i < numToCreate; i++) {
            N node1 = nodesToConnect.get(i*2);
            N node2 = nodesToConnect.get(i*2 + 1);
            result.add(generateEdge(node1, node2));
        }
        return result;
    }
}
