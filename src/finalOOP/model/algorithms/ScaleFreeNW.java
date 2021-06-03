package finalOOP.model.algorithms;

import finalOOP.model.network.Edge;
import finalOOP.model.network.Network;
import finalOOP.model.network.Node;

import java.util.ArrayList;
import java.util.Random;

public class ScaleFreeNW extends Algorithms {

    private int initialNode;
    private int numEdges; // number of links that every new node must gain

    public ScaleFreeNW(){}
    public ScaleFreeNW(Network network, int totalNodes, int initialNode, int numEdges) {
        super(network, totalNodes);
        this.initialNode = initialNode;
        this.numEdges = numEdges;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public int getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(int initialNode) {
        this.initialNode = initialNode;
    }

    @Override
    public void createEdge() {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.initialNode; i++) {
            if (initialNode == 2) {
                this.getNetwork().addEdge(new Edge(this.getNetwork().findById(0), this.getNetwork().findById(1)));
                list.add(0);
                list.add(1);
                this.getGraphEdges().get(1).add(0);
                break;
            }
            int total = new Random().nextInt(initialNode - 2) + 1;
            while (this.getNetwork().countEdgesofNode(this.getNetwork().findById(i)) <= total) {
                int index;
                do {
                    index = new Random().nextInt(initialNode);
                } while (index == i || this.getNetwork().findEdgebyId(i, index) != null);
                this.getNetwork().addEdge(new Edge(this.getNetwork().findById(index), this.getNetwork().findById(i)));
                list.add(i);
                list.add(index);
                if (index < i){
                    this.getGraphEdges().get(i).add(index);
                }
                else{
                    this.getGraphEdges().get(index).add(i);
                }
            }
        }
//        for (int i=0;i<this.initialNode;i++){
//            for (int j = 0; j < ; j++) {
//
//            }
//        }
//        getNetwork().addEdge(new Edge(this.getNetwork().findById(0),this.getNetwork().findById(1)));
//        getNetwork().addEdge(new Edge(this.getNetwork().findById(0),this.getNetwork().findById(2)));
//        list.add(0);list.add(0);list.add(1);list.add(2);

        for (int i = initialNode; i < getTotalNodes(); i++) {
            this.getNetwork().addNode(new Node(i));
            int check = 1;
//            for (int j = i-1 ; j >= 0;j--){
//                Random random = new Random();
//                int prob = random.nextInt(list.size());
//                double prob_temp = (double) this.network.countEdgesofNode(this.network.findById(j))/(double)this.network.totalEdges();
//                System.out.println(prob + "-" + prob_temp);
//                if (prob < prob_temp){
//                    this.network.addEdge(new Edge(this.network.findById(i),this.network.findById(j)));
//                }
            do {
                int index;
                do {
//                    System.out.println("11");
                    Random random = new Random();
                    index = random.nextInt(list.size());
                } while (this.getNetwork().findEdgebyId(i, list.get(index)) != null || list.get(index) == i);
                this.getNetwork().addEdge(new Edge(getNetwork().findById(i), getNetwork().findById(list.get(index))));
                list.add(i);
                list.add(list.get(index));
                if (list.get(index) < i){
                    this.getGraphEdges().get(i).add(list.get(index));
                }
                else{
                    System.out.println("graph edges add "+ list.get(index) +" -- " +i);
                    this.getGraphEdges().get(index).add(i);
                }
                for (int j = 0; j < list.size(); j++) {
                    System.out.print(list.get(j) + " - ");
                }
                check++;
            } while (check <= this.numEdges);
        }
    }

    @Override
    public void buildNetwork() {
        for (int i = 0; i < initialNode; i++) {
            this.getNetwork().addNode(new Node(i));
        }
        for (int i=0;i<this.getTotalNodes();i++){
            this.getGraphEdges().add(new ArrayList<>());
        }
        createEdge();
    }

}

