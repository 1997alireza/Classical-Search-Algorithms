package algorithms;

import problemGraph.State;
import problemGraph.problems.Problem;

public class UniformCost extends BestSelection{

    @Override
    protected int getNodeLoad(State s, Problem p) {
        return s.totalCost;
    }
}
