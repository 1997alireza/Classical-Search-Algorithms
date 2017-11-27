package algorithms;

import problemGraph.Action;
import problemGraph.State;
import problemGraph.problems.Problem;

import java.util.ArrayList;

/**
 * ID_DFS: Iterative deepening DFS
 */
public class ID_DFS extends SearchAlgorithm {
    @Override
    protected State searchAFinal(Problem p) {
        State start = p.getInitialState();
        if(p.isFinal(start)){
            return start;
        }
        ArrayList<State> openList /*f*/ = new ArrayList<>();
        State LGSLastRound = null, LGSCurrentRound; // LGS = Last Generated State
        for(int depthLimit = 0; ; depthLimit++) {
            openList.clear();
            openList.add(start);
            visitedStates.add(start);
            LGSCurrentRound = start;
            while (!openList.isEmpty()) {
                maxMemoryUsage‌ = Math.max(maxMemoryUsage‌, openList.size() + expandedStates.size());
                State s = openList.remove(0);
                if (s.height >= depthLimit) continue;
                expandedStates.add(s);
                for (Action a : s.actionList()) {
                    State ns = a.nextState;
                    if (cantBeAdded(ns, openList))
                        continue;
                    visitedStates.add(ns);
                    if (p.isFinal(ns)) {
                        return ns;
                    }
                    openList.add(0, ns);
                    LGSCurrentRound = ns;
                }
            }
            if(LGSCurrentRound == LGSLastRound)
                return null;
            else{
                LGSLastRound = LGSCurrentRound;
            }
        }
    }
}
