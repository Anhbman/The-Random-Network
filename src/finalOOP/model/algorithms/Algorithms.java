package finalOOP.model.algorithms;

import finalOOP.model.network.Network;

import java.util.ArrayList;
import java.util.List;

public abstract class Algorithms {
    protected Network network;
    private int totalNodes;
    private List<List<Integer>> graphEdges;

    public Algorithms(){}

    public Algorithms(Network network, int totalNodes) {
        this.network = network;
        this.totalNodes = totalNodes;
        this.graphEdges = new ArrayList<>();
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public void setTotalNodes(int totalNodes) {
        this.totalNodes = totalNodes;
    }

    public List<List<Integer>> getGraphEdges() {
        return graphEdges;
    }

    public void setGraphEdges(List<List<Integer>> graphEdges) {
        this.graphEdges = graphEdges;
    }

    public void createEdge(){};

    public void buildNetwork(){};
}
