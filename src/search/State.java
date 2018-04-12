package search;

import java.util.List;

public interface State {

    List getActions();
     
    boolean isGoal();

    void printState();

    boolean equals(Object v);
}
