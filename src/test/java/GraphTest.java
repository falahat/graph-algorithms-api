import model.Edge;
import model.Graph;
import model.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public abstract class GraphTest<N extends Node, E extends Edge<N>> {
    Graph<N, E> graph;

    @Before
    public void setupGraph() {
        this.graph = itemGenerator().generateGraph();
    }

    public abstract GraphTestItemGenerator<N, E> itemGenerator();
}
