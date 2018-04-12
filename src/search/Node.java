package search;

import java.util.ArrayList;
import java.util.List;

public class Node {

    Node parent;
    State state;
    int pathcost;

    public Node(Node parent, State state) {

        this.parent = parent;
        this.state  = state;
    }

    public Node(Node parent, State state, int cost) {

        this.parent   = parent;
        this.state    = state;

        if(parent != null) this.pathcost = parent.getPathcost() + cost;
    }
    
    public Node(Node parent, Edge edge) {

        this.parent   = parent;
        this.state    = edge.getState();

        if(parent != null) this.pathcost = parent.getPathcost() + edge.getCost();
    }
   
    public List<Node> getSuccessors() {
    	    	
    	List<Edge> edges    =  state.getActions();
        List<Node> sucnodes = new ArrayList<>();
    	
    	for(Edge edge : edges) {
    		sucnodes.add(new Node(this, edge));
        }
    	
    	return sucnodes;
    }

    public int getPathcost() {
        return pathcost;
    }

    public void setPathcost(int pathcost) {
        this.pathcost = pathcost;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    /*
    @Override
    public boolean equals(Object v) {

        if(v instanceof Node) {

            return state.equals(v);
        }

        return false;
    }
        */

}
