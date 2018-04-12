package search;

import java.util.*;

public class DFS implements Algorithm<State> {

    Stack<Node> frontier = new Stack<Node>();
    List<State> explored = new ArrayList<>();

    public Node search(State state) {

        Node node = new Node(null, state, 0);

        if(state.isGoal()) return node;

        frontier.add(node);

        while(!frontier.isEmpty()) {

            node = frontier.pop(); // Pop
            explored.add(node.getState()); // Explored

            List<Object> actions = node.getState().getActions();

            for(Object action : actions) {

                Node childnode = new Node(node, (State) action);

                if(!frontier.contains(childnode) && !explored.contains(childnode.getState())) {

                    if (childnode.getState().isGoal()) return childnode;
                    frontier.add(childnode);
                }
            }

        }

        return null;
    }

}
