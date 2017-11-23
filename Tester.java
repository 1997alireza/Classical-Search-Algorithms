import problemGraph.Problem;
import problemGraph.algorithms.BFS;
import problemGraph.algorithms.SearchAlgorithm;
import problemGraph.problems.Problem1;

public class Tester {
    public static void main(String[] args) {
        Problem p = new Problem1();
        SearchAlgorithm searchAlg = new BFS();
        searchAlg.run(p);
        System.out.println(searchAlg.getBestCost());
        System.out.println(searchAlg.getBestPath());
        System.out.println(searchAlg.getFinal());
    }
}
