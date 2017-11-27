import algorithms.*;
import problemGraph.problems.*;

public class Tester {
    public static void main(String[] args) {
        Problem1 p1 = new Problem1();
        Problem p2 = new Problem2(1, 4, 2, 3, 0, 7, 6, 8, 5);
        Problem p3 = new Problem3(4, 3, new boolean[][]{{true, true, false}, {true, true, true}, {true, false, true}, {true, true, true}});
        SearchAlgorithm searchAlg = new AStar().graphSearch();
        searchAlg.run(p3);
        System.out.println("Visited states number: " + searchAlg.visitedStatesNumber());
        System.out.println("Expanded states number: " + searchAlg.expandedStatesNumber());
        System.out.println("Best path: " + searchAlg.getBestPath());
        System.out.println("Best path cost: " + searchAlg.getBestCost());
        System.out.println("Max memory usage (states number): " + searchAlg.getMaxMemoryUsage‌() );
        System.out.println("Final state: [" + searchAlg.getFinal() + "]");
    }
}