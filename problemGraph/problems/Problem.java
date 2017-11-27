package problemGraph.problems;

import problemGraph.State;
import java.util.ArrayList;

public abstract class Problem{
    public Problem(){
    }
    public abstract State getInitialState();
    public abstract boolean isFinal(State s);
    public int hCost(State s) {// heuristic cost to final
        return 0;
    }

    public abstract ArrayList<State> getFinalStates();
}
