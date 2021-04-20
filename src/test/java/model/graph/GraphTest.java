package model.graph;

import model.GraphTestUtil;
import org.junit.jupiter.api.BeforeEach;

public abstract class GraphTest {
    Graph graph;

    @BeforeEach
    public void setupGraph() {
        this.graph = testUtil().generateGraph();
    }

    public abstract GraphTestUtil testUtil();
}
