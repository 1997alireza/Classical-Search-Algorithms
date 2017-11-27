import problemGraph.algorithms.*;
import problemGraph.problems.*;

public class Tester {
    public static void main(String[] args) {
        Problem p = new Problem1();
        SearchAlgorithm searchAlg = new Bidirectional().graphSearch();
        searchAlg.run(p);
        System.out.println("Visited states number: " + searchAlg.visitedStatesNumber());
        System.out.println("Expanded states number: " + searchAlg.expandedStatesNumber());
        System.out.println("Best path: " + searchAlg.getBestPath());
        System.out.println("Best path cost: " + searchAlg.getBestCost());
        System.out.println("Max memory usage (states number): " + searchAlg.getMaxMemoryUsage‌() );
        System.out.println("Final state: [" + searchAlg.getFinal() + "]");
    }
}
