package search;

import java.util.*;

public class AS implements Algorithm<State> {
	
    PriorityQueue<Node> frontier = new PriorityQueue<Node>((Node n1, Node n2) -> {

                if(n1.getPathcost() > n2.getPathcost())
                    return 1;
                else if(n1.getPathcost() < n2.getPathcost())
                    return -1;

                return 0;
    });

    List<State> explored = new ArrayList<>();

    public Node search(State state) {

        Node node = new Node(null, state);

        frontier.add(node);

        while(!frontier.isEmpty()) {

            node = frontier.poll(); //Retrieves and removes head

            if(node.getState().isGoal()) return node;

            explored.add(node.getState()); // Explored

            List<Node> childnodes = node.getSuccessors();

            for(Node childnode : childnodes) {

                 if(!frontier.contains(childnode) && !explored.contains(childnode.getState())) {
                     frontier.add(childnode);
                 } else {

                     // not sure..
                     for (Node n : frontier) {
                         if(n == childnode && n.getPathcost() > childnode.getPathcost())  {
                             frontier.remove(n);
                             frontier.add(childnode);
                             System.out.println("replaced ?");
                             break;
                         }
                     }
                     // ----

                 }
                 
            }

        }

     return null;
    }


}
