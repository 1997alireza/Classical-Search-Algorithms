package algorithms;

import problemGraph.Action;
import problemGraph.problems.Problem;
import problemGraph.State;

import java.util.ArrayList;

public class BFS extends SearchAlgorithm {
    @Override
    protected State searchAFinal(Problem p) {
        State start = p.getInitialState();
        if(p.isFinal(start)){
            return start;
        }
        ArrayList<State> openList /*f*/ = new ArrayList<>();
        openList.add(start);
        visitedStates.add(start);

        while(!openList.isEmpty()){
            maxMemoryUsage‌ = Math.max(maxMemoryUsage‌, openList.size() + expandedStates.size());
            State s = openList.remove(0);
            expandedStates.add(s);
            for(Action a : s.actionList()){
                State ns = a.nextState;
                if(cantBeAdded(ns, openList))
                    continue;
                visitedStates.add(ns);
                if(p.isFinal(ns)){
                    return ns;
                }
                openList.add(ns);
            }
        }
        return null;
    }
}
