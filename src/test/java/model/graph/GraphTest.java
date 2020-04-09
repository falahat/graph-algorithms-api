package model.graph;

import model.GraphTestItemGenerator;
import model.edge.Edge;
import model.graph.Graph;
import model.node.Node;
import org.junit.Before;

public abstract class GraphTest<N extends Node, E extends Edge<N>> {
    Graph<N, E> graph;

    @Before
    public void setupGraph() {
        this.graph = itemGenerator().generateGraph();
    }

    public abstract GraphTestItemGenerator<N, E> itemGenerator();
}
