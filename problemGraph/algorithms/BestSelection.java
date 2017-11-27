package problemGraph.algorithms;

import problemGraph.Action;
import problemGraph.State;
import problemGraph.problems.Problem;

import java.util.ArrayList;

public abstract class BestSelection extends SearchAlgorithm {
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
            State s = getBestState(openList, p);
            openList.remove(s);
            if(p.isFinal(s)){
                return s;
            }
            expandedStates.add(s);
            for(Action a : s.actionList()){
                State ns = a.nextState;
                visitedStates.add(ns);
                if(cantBeAdded(ns, openList))
                    continue;
                openList.add(ns);
            }
        }
        return null;
    }

    private State getBestState(ArrayList<State> openList, Problem p){
        int minLoad = Integer.MAX_VALUE;
        State bestState = null;
        for(State s: openList){
            if(s.totalCost < minLoad){
                minLoad = getNodeLoad(s, p);
                bestState = s;
            }
        }
        return bestState;
    }

    /**
     *
     * @param s state
     * @param p problem, to have access to heuristic cost function
     * @return f function
     */
    protected abstract int getNodeLoad(State s, Problem p);
}
