package problemGraph.algorithms;

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
        /*for(State fs : p.getFinalStates()){
            singleSearches.add(new SingleSearch(fs));
        }*/ //TODO check
        singleSearches.addAll(p.getFinalStates().stream().map(SingleSearch::new).collect(Collectors.toList()));

        while(!isFinished()){
            {
                int memUsage = 0;
                for (SingleSearch ss : singleSearches)
                    memUsage += ss.openList.size();
                maxMemoryUsage = Math.max(maxMemoryUsage, memUsage);
            }
            State commonState = getCommonState();
            if(commonState != null)
                return commonState;
            for(int i = 0; i < singleSearches.size(); i++){
                SingleSearch ss = singleSearches.get(i);
                if(ss.openList.isEmpty()){
                    singleSearches.remove(i);
                    i--;
                }
                else{
                    State s = ss.openList.remove(0);
                    ss.expandedStates.add(s);
                    for(Action a : (i == 0)? s.actionList() : s.actionInverseList()){
                        State ns = a.nextState;
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

    private State getCommonState(){
        for(State s : singleSearches.get(0).openList){
            State commonState = getCommonState(s);
            if(commonState != null)
                return commonState;
        }
        for(State s : singleSearches.get(0).expandedStates){
            State commonState = getCommonState(s);
            if(commonState != null)
                return commonState;
        }
        return null;
    }
    private State getCommonState(State s){
        for(int i = 1; i < singleSearches.size(); i++){
            for(State fs : singleSearches.get(i).openList){
                if(s.equals(fs)){
                    return s;
                }
            }
            for(State fs : singleSearches.get(i).expandedStates){
                if(s.equals(fs)){
                    return s;
                }
            }
        }
        return null;
    }

    private class SingleSearch{
        ArrayList<State> openList, expandedStates;
        public SingleSearch(State init){
            openList = new ArrayList<>();
            expandedStates = new ArrayList<>();
            openList.add(init);
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
