package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;

public class QuadraticFormSignificanceSolver implements Solver
{
    private final Solver symmetricMatrixSignificanceSolver;
    private final Solver quadraticFormOddsMatrixSolver;


    public QuadraticFormSignificanceSolver(Solver symmetricMatrixSignificanceSolver, Solver quadraticFormOddsMatrixSolver)
    {
        this.symmetricMatrixSignificanceSolver = symmetricMatrixSignificanceSolver;
        this.quadraticFormOddsMatrixSolver = quadraticFormOddsMatrixSolver;
    }


    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var solution = new SolutionNode("Check quadratic form significance for polynomial:", condition.task());

        var oddsExtractionResults = quadraticFormOddsMatrixSolver.computeSolution(condition);
        solution.addNode(oddsExtractionResults.solution());

        var significanceCheckingResults = symmetricMatrixSignificanceSolver.computeSolution(condition.sameContextCondition(oddsExtractionResults.result()));
        solution.addNode(significanceCheckingResults.solution());

        var result = significanceCheckingResults.result();
        solution.newFinalNode("Resolved significance type:", result);
        return new EvalResults(result, solution);
    }
}
