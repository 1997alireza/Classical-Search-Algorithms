import problemGraph.algorithms.*;
import problemGraph.problems.*;

import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Problem p = new Problem1();
        SearchAlgorithm searchAlg = new BFS().graphSearch();
        searchAlg.run(p);
        System.out.println(searchAlg.getBestCost());
        System.out.println(searchAlg.getBestPath());
        System.out.println(searchAlg.getFinal());
        System.out.println(searchAlg.getMaxMemoryUsage());
    }
}
