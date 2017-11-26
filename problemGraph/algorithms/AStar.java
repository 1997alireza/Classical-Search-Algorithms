package problemGraph.algorithms;

import problemGraph.State;
import problemGraph.problems.Problem;

public class AStar extends BestSelection {

    @Override
    protected int getNodeLoad(State s, Problem p) {
        return s.totalCost + p.hCost(s);
    }
}
