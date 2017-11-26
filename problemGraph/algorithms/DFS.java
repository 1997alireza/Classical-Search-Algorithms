package problemGraph.algorithms;

import problemGraph.Action;
import problemGraph.State;
import problemGraph.problems.Problem;

import java.util.ArrayList;

public class DFS extends SearchAlgorithm {
    @Override
    protected State searchAFinal(Problem p) {
        State start = p.getInitialState();
        if(p.isFinal(start)){
            return start;
        }
        ArrayList<State> openList /*f*/ = new ArrayList<>();
        openList.add(start);

        while(!openList.isEmpty()){
            maxMemoryUsage = Math.max(maxMemoryUsage, openList.size());
            State s = openList.remove(0);
            expandedStates.add(s);
            for(Action a : s.actionList()){
                State ns = a.nextState;
                if(cantBeAdded(ns, openList))
                    continue;
                if(p.isFinal(ns)){
                    return ns;
                }
                openList.add(0, ns);
            }
        }
        return null;
    }
}
