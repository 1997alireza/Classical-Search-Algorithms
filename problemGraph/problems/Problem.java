package problemGraph.problems;

import problemGraph.State;

public abstract class Problem{
    public Problem(){
    }
    public abstract State getInitialState();
    public abstract boolean isFinal(State s);
    public abstract int hCost(State s); // heuristic cost to final
}
