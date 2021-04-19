package model.node;

public class Node implements Cloneable {
    private final String uniqueNodeId;

    private Node(String uniqueNodeId) {
        this.uniqueNodeId = uniqueNodeId;
    }

    public String getUniqueIdentifier() {
        return this.uniqueNodeId;
    }

    public static <N> Node withNodeId(N nodeId) {
        return new Node(nodeId.toString());
    }
}
