package imp;

import search.Edge;
import search.State;

import java.util.ArrayList;
import java.util.List;

public class GraphPath implements State {

    private final int[][] costmat;
    private final int target;

    private int self;


    public GraphPath(int[][] costmat, int self, int target) {

        this.costmat = costmat;
        this.self    = self;

        this.target  = target;
    }

    public int getSelf() {
        return self;
    }

    public boolean isGoal() {

      return (self == target);
    }

    @Override
    public boolean equals(Object v) {

        if(v instanceof State) {
            GraphPath gp = (GraphPath) v;
            return (gp.getSelf() == this.getSelf());
        }

        return false;
    }

    public final List getActions() {

        List<Edge> result = new ArrayList<Edge>();

        for(int i = 0; i < costmat[self - 1].length; i++) {

            if(costmat[self - 1][i] > -1) {

                Edge edge = new Edge(new GraphPath(costmat, i + 1, target), costmat[self - 1][i]);
                result.add(edge);
            }
        }

    	return result;
    }

	public void printState() {
        System.out.println(this);
	}


	public String toString() {
        return "" + this.self;
    }
	 
}
