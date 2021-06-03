package finalOOP.model.algorithms;

import finalOOP.model.network.Edge;
import finalOOP.model.network.Network;
import finalOOP.model.network.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmallWorldNW extends Algorithms {

    private double rewireProbality;
    private int neighborNodes;

    public SmallWorldNW(){}

    public SmallWorldNW(Network network, int totalNodes, double rewireProbality, int neighborNodes) {
        super(network, totalNodes);
        this.rewireProbality = rewireProbality;
        this.neighborNodes = neighborNodes;
    }


    public double getRewireProbality() {
        return rewireProbality;
    }

    public void setRewireProbality(double rewireProbality) {
        this.rewireProbality = rewireProbality;
    }

    public int getNeighborNodes() {
        return neighborNodes;
    }

    public void setNeighborNodes(int neighborNodes) {
        this.neighborNodes = neighborNodes;
    }


    @Override
    public void createEdge() {
        for (int i = 0; i < getTotalNodes(); i++) {
            for (int j = 0 - this.neighborNodes; j < this.neighborNodes + 1; j++) {
                int index = i + j;
                if (index < 0) {
                    index = this.getTotalNodes() + index;
                } else if (index >= getTotalNodes()) {
                    index = index - this.getTotalNodes();
                }
                System.out.println(i + "-" + index);
                this.getNetwork().addEdge(new Edge(getNetwork().findById(i), this.getNetwork().findById(index), 0.0));
            }
        }
    }

    @Override
    public void buildNetwork() {
        for (int i = 0; i < this.getTotalNodes(); i++) {
            this.getNetwork().addNode(new Node(i));
        }
        createEdge();
        rewireEdge();
    }

    public void rewireEdge() {
        for (int i = 0; i < this.getTotalNodes(); i++) {

            this.getGraphEdges().add(new ArrayList<>());
            for (int j = i + 1; j < (this.neighborNodes + i + 1); j++) {
//                System.out.println("kiki: " + i +"---" + num);
//                System.out.println("kaka: " + num +"---" + (this.neighborNodes + i +1));
                int num = j;
                if (num >= this.getTotalNodes()) {
                    num = num - this.getTotalNodes();
                }
                this.getGraphEdges().get(i).add(num);
                Random random = new Random();
                double prob = random.nextDouble();
                if (rewireProbality == 1 && this.getNetwork().findEdgebyId(i, num).getProbality() != 1) {
                    if (this.getNetwork().countEdgesofNode(getNetwork().findById(i)) < this.getTotalNodes() - 1) {
                        this.getNetwork().removeEdge(this.getNetwork().findEdgebyId(i, num));
                        int index;
                        do {
                            index = random.nextInt(this.getTotalNodes());
                        } while (index == i || index == num || this.getNetwork().findEdgebyId(i, index) != null);
                        this.getNetwork().addEdge(new Edge(new Node(i), new Node(index), 1.0));
                        this.getGraphEdges().get(i).set((this.getGraphEdges().get(i).size() - 1), index);
                    }
                } else if (prob < this.rewireProbality && this.getNetwork().findEdgebyId(i, num).getProbality() != 1) {
                    if (this.getNetwork().countEdgesofNode(getNetwork().findById(i)) < this.getTotalNodes() - 2) {
                        this.getNetwork().removeEdge(this.getNetwork().findEdgebyId(i, num));
                        int index;
                        do {
                            index = random.nextInt(this.getTotalNodes());
                        } while (index == i || index == num || this.getNetwork().findEdgebyId(i, index) != null);
                        this.getNetwork().addEdge(new Edge(new Node(i), new Node(index), 1.0));
                        this.getGraphEdges().get(i).set((this.getGraphEdges().get(i).size() - 1), index);
                    }
                }
            }
        }


//        this.getNetwork().setListEdges(list);
    }
}

