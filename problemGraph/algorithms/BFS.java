package problemGraph.algorithms;

import problemGraph.Action;
import problemGraph.Problem;
import problemGraph.State;

import java.util.ArrayList;

public class BFS extends SearchAlgorithm {
    @Override
    protected State searchAFinal(Problem p) {
        super.searchAFinal(p);
        State start = p.getInitialState();
        if(p.isFinal(start)){
            return start;
        }
        ArrayList<State> openList /*f*/ = new ArrayList<>();
        openList.add(start);

        while(!openList.isEmpty()){
            State s = openList.remove(0);
            expandedStates.add(s);
            for(Action a : s.actionList()){
                State ns = a.nextState;
                if(expandedStates.contains(ns) || openList.contains(ns))
                    continue;
                if(p.isFinal(ns)){
                    return ns;
                }
                openList.add(ns);
            }
        }
        return null;
    }
}
