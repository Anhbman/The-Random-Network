package finalOOP.model.network;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> listNodes;
    private List<List<Edge>> listEdges;

    public Network() {
        this.listNodes = new ArrayList<>();
        this.listEdges = new ArrayList<>();
    }

    public Network(List<Node> listNodes, List<List<Edge>> listEdges) {
        this.listNodes = listNodes;
        this.listEdges = listEdges;
    }

    public List<Node> getListNodes() {
        return listNodes;
    }

    public void setListNodes(List<Node> listNodes) {
        this.listNodes = listNodes;
    }

    public List<List<Edge>> getListEdges() {
        return listEdges;
    }

    public void setListEdges(List<List<Edge>> listEdges) {
        this.listEdges = listEdges;
    }

    public void addNode(Node node) {
        this.listNodes.add(node);
        this.listEdges.add(new ArrayList<>());
    }

    public boolean hasEdge(Edge edge) {
        for (Edge edge1 : this.listEdges.get(edge.getStart().getId())) {
            if (edge.compareTo(edge1)) {
                return true;
            }
        }

        return false;
    }

    public Edge findEdgebyId(int startID, int destinationID) {
        for (Edge edge : this.listEdges.get(startID)) {
            if (edge.getDestination().getId() == destinationID) {
                return edge;
            }
        }
//        System.out.println(startID + "--" + destinationID);
//        System.out.println("Not existed edge");
        return null;
    }

    public void addEdge(Edge edge) {
        if (edge.getDestination().getId() == edge.getStart().getId()) {
            return;
        } else if (this.hasEdge(edge)) {
            return;
        }
        {
            this.listEdges.get(edge.getStart().getId()).add(edge);
            if (edge.getProbality() != 0.0) {
                this.listEdges.get(edge.getDestination().getId()).add(new Edge(edge.getDestination(), edge.getStart(), edge.getProbality()));
            } else {
                this.listEdges.get(edge.getDestination().getId()).add(new Edge(edge.getDestination(), edge.getStart()));
            }

        }
    }

    public Node findById(int id) {
        for (Node node : listNodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }

    public void removeEdge(Edge edge) {
        for (Edge edge1 : listEdges.get(edge.getStart().getId())) {
            if (edge1.compareTo(edge)) {
                listEdges.get(edge.getStart().getId()).remove(edge1);
                break;
            }
        }
        for (Edge edge2 : listEdges.get(edge.getDestination().getId())) {
            if (edge2.compareTo(new Edge(edge.getDestination(), edge.getStart()))) {
                listEdges.get(edge.getDestination().getId()).remove(edge2);
                return;
            }
        }
    }

    public int countEdgesofNode(Node node) {
        return this.listEdges.get(node.getId()).size();
    }

    public int totalEdges() {
        int total = 0;
        for (int i = 0; i < this.listNodes.size(); i++) {
            total += countEdgesofNode(findById(i));
        }
        return total;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : this.listNodes) {
            stringBuilder.append(node.toString()).append(":");
            for (Edge edge : listEdges.get(node.getId())) {
                stringBuilder.append(edge.getDestination().toString()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}