package model.graph;

import model.TestItemGenerator;
import org.junit.jupiter.api.BeforeEach;

public abstract class GraphTest<K, GRAPH extends Graph<K>> {
    GRAPH graph;

    @BeforeEach
    public void setupGraph() {
        this.graph = itemGenerator().generateGraph();
    }

    public abstract TestItemGenerator<K, GRAPH> itemGenerator();
}
