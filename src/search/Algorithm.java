package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Algorithm<T> {

    Node search(T State);

    static void printPath(Node node, boolean reverse) {

        List<Node> nodes_temp = new ArrayList<>();

        Node temp = node;
        while(temp != null) {

            if(reverse) nodes_temp.add(temp);
            else temp.getState().printState();

            temp = temp.getParent();
        }

        if(reverse) {

            Collections.reverse(nodes_temp);

           for(Node n : nodes_temp) {
               n.getState().printState();
           }
        }
    }
}
