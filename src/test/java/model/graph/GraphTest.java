package model.graph;

import model.GraphTestItemGenerator;
import org.junit.jupiter.api.BeforeEach;

public abstract class GraphTest<N extends Cloneable> {
    Graph<N> graph;

    @BeforeEach
    public void setupGraph() {
        this.graph = itemGenerator().generateGraph();
    }

    public abstract GraphTestItemGenerator<N> itemGenerator();
}
