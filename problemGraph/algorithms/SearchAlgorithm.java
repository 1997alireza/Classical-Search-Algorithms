package problemGraph.algorithms;
import problemGraph.State;
import problemGraph.Problem;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class SearchAlgorithm {
    protected Problem p;
    protected State finalState;
    public int maxMemoryUsage; // max number of kept states
    public SearchAlgorithm() {
        visitedStates = new HashSet<>();
        expandedStates = new HashSet<>();
    }

    /**
     *
     * @return finalState, if it's null so we can't access to a final state
     */
    protected State searchAFinal(Problem p){
        this.p = p;
        maxMemoryUsage = 0;
        visitedStates.clear();
        expandedStates.clear();
        return null;
    }
    public void run(Problem p){
        finalState = searchAFinal(p);
    }
    public HashSet<State> visitedStates, expandedStates/*closed list (e)*/;
    public ArrayList<String> getBestPath(){
        if(finalState == null)
            return null;
        ArrayList<String> actionStrs = new ArrayList<>();
        State s = finalState;
        while(s.parent != null){
            actionStrs.add(0, s.actionStr);
            s = s.parent;
        }
        return actionStrs;
    }
    public int getBestCost(){
        if(finalState == null)
            return -1;
        return finalState.totalCost;
    }
    public State getFinal(){
        return finalState;
    }
}
