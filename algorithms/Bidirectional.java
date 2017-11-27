package algorithms;

import problemGraph.Action;
import problemGraph.State;
import problemGraph.problems.Problem;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Bidirectional extends SearchAlgorithm{
    private ArrayList <SingleSearch> singleSearches;
    @Override
    protected State searchAFinal(Problem p) {
        singleSearches = new ArrayList<>();
        singleSearches.add(new SingleSearch(p.getInitialState())); // singleSearch[0] is for start state
        singleSearches.addAll(p.getFinalStates().stream().map(SingleSearch::new).collect(Collectors.toList()));

        while(!isFinished()){
            {
                int memUsage = 0;
                for (SingleSearch ss : singleSearches)
                    memUsage += ss.openList.size() + ss.expandedStates.size();
                maxMemoryUsage‌ = Math.max(maxMemoryUsage‌, memUsage);
            }
            State commonState = getCommonState();
            if(commonState != null) {
                return commonState;
            }
            for(int i = 0; i < singleSearches.size(); i++){
                SingleSearch ss = singleSearches.get(i);
                if(ss.openList.isEmpty()){
                    singleSearches.remove(i);
                    i--;
                }
                else{
                    State s = ss.openList.remove(0);
                    expandedStates.add(s);
                    ss.expandedStates.add(s);
                    for(Action a : (i == 0)? s.actionList() : s.actionInverseList()){
                        State ns = a.nextState;
                        visitedStates.add(ns);
                        if(ss.cantBeAdded(ns))
                            continue;
                        ss.openList.add(ns);
                    }
                }
            }
        }
        return null;
    }

    private boolean isFinished(){
        if(singleSearches.get(0).openList.isEmpty())
            return true;
        for(int i = 1; i < singleSearches.size(); i++){
            if(!singleSearches.get(i).openList.isEmpty())
                return false;
        }
        return true;
    }

    /**
     *
     * @return common state between initial state DFS and a final state DFS that has all the path from initial to a final
     */
    private State getCommonState(){
        for(State s : singleSearches.get(0).openList){
            State commonState = getCommonState(s);
            if(commonState != null) {
                return connectPaths(s, commonState);
            }
        }
        for(State s : singleSearches.get(0).expandedStates){
            State commonState = getCommonState(s);
            if(commonState != null)
                return connectPaths(s, commonState);
        }
        return null;
    }

    /**
     *
     * @param s in initial state path
     * @return common state with s that is in a final state path
     */
    private State getCommonState(State s){
        for(int i = 1; i < singleSearches.size(); i++){
            for(State fs : singleSearches.get(i).openList){
                if(s.equals(fs)){
                    return fs;
                }
            }
            for(State fs : singleSearches.get(i).expandedStates){
                if(s.equals(fs)){
                    return fs;
                }
            }
        }
        return null;
    }

    private State connectPaths(State fromInit, State fromFinal){
        ArrayList<State> FPathStates = new ArrayList<>();
        State stateFPath = fromFinal;
        while(stateFPath != null){
            FPathStates.add(stateFPath);
            stateFPath = stateFPath.parent;
        }
        for(int i = FPathStates.size()-1; i > 1; i--){
            State fsInFPath= FPathStates.get(i);
            fsInFPath.parent = FPathStates.get(i-1);
            fsInFPath.actionStr = FPathStates.get(i-1).actionStr;
        }
        FPathStates.get(1).parent = fromInit;
        FPathStates.get(1).actionStr = FPathStates.get(0).actionStr;

        State finalState = FPathStates.get(FPathStates.size()-1);
        finalState.totalCost = fromInit.totalCost + fromFinal.totalCost;
        return FPathStates.get(FPathStates.size()-1);
    }

    private class SingleSearch{
        ArrayList<State> openList, expandedStates;
        public SingleSearch(State init){
            openList = new ArrayList<>();
            expandedStates = new ArrayList<>();
            openList.add(init);
            visitedStates.add(init);
        }
        boolean cantBeAdded(State s){
            for(State aState: openList) {
                if (s.equals(aState))
                    return true;
            }
            if(isGraphSearch){
                for(State aState : expandedStates) {
                    if (s.equals(aState))
                        return true;
                }
            }
            return false;
        }
    }
}
