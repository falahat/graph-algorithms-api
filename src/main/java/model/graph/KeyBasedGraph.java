package model.graph;

import model.node.Node;

public class KeyBasedGraph<K> extends DelegateGraph {

    public KeyBasedGraph(Graph innerGraph) {
        super(innerGraph);
    }

    public void addNodeWithKey(K key, Node toAdd) {
        this.add(toAdd);
    }

    public void removeNodeWithKey(K toRemove) {
        this.remove(getNodeWithKey(toRemove));
    }

    public Node getNodeWithKey(K key) {
        return null; // TODO: implement
    }

    public void connectNodesWithKeys(K source, K destination) {
        this.connect(getNodeWithKey(source), getNodeWithKey(destination));
    }
}
