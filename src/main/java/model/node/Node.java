package model.node;

public class Node {
    private final String uniqueNodeId;

    public Node(String uniqueNodeId) {
        this.uniqueNodeId = uniqueNodeId;
    }

    public String getUniqueIdentifier() {
        return this.uniqueNodeId;
    }

    // TODO: remove this method and migrate away from using "N" anywhere
    public static <N> Node withNodeId(N nodeId) {
        return new Node(nodeId.toString());
    }
}
