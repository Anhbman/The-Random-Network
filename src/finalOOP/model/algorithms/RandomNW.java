package finalOOP.model.algorithms;

import finalOOP.model.network.Edge;
import finalOOP.model.network.Network;
import finalOOP.model.network.Node;

import java.util.ArrayList;
import java.util.Random;

public class RandomNW extends Algorithms {

    private double probality;

    public  RandomNW(){

    }

    public RandomNW(Network network, int totalNodes, double probality) {
        super(network,totalNodes);
        this.probality = probality;
    }

    public double getProbality() {
        return probality;
    }

    public void setProbality(double probality) {
        this.probality = probality;
    }

    @Override
    public void createEdge() {
        if (this.probality == 0){
            System.out.println("connect's probality = 0");
            return;
        }
        for (int i=0;i<this.getTotalNodes();i++){
            this.getGraphEdges().add(new ArrayList<>());
            for (int j=0;j<this.getTotalNodes();j++){
                if (this.probality == 1){
                    if (i != j && this.getNetwork().findEdgebyId(j,i) == null){
                        this.getGraphEdges().get(i).add(j);
                    }
                    this.getNetwork().addEdge(new Edge(this.getNetwork().findById(i),this.getNetwork().findById(j)));
                }
                else if(this.probality >0 && this.probality < 1){
                    Random random = new Random();
                    if(random.nextDouble() < this.probality){
                        if (i != j && this.getNetwork().findEdgebyId(j,i) == null){
                            this.getGraphEdges().get(i).add(j);
                        }
                        this.getNetwork().addEdge(new Edge(this.getNetwork().findById(i),this.getNetwork().findById(j)));
                    }
                }
            }
        }
    }

    @Override
    public void buildNetwork() {
        for (int i=0;i<this.getTotalNodes();i++){
            this.getNetwork().addNode(new Node(i));
        }
        createEdge();
        System.out.println("Build Success");
    }
}
