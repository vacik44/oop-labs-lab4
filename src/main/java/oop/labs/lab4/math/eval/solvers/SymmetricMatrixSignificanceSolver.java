package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;

@SuppressWarnings("unused")
public class SymmetricMatrixSignificanceSolver implements Solver
{
    private final Solver symmetricMatrixSignificanceSilvesterSolver;


    private SymmetricMatrixSignificanceSolver(Solver symmetricMatrixSignificanceSilvesterSolver)
    {
        this.symmetricMatrixSignificanceSilvesterSolver = symmetricMatrixSignificanceSilvesterSolver;
    }


    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var solution = new SolutionNode("Check significance of matrix:", condition.task(), 2);

        var silvesterResults = symmetricMatrixSignificanceSilvesterSolver.computeSolution(condition);
        solution.addNode(silvesterResults.solution());

        solution.newFinalNode("Resolved significance type:", silvesterResults.result());
        return new EvalResults(silvesterResults.result(), solution);
    }
}
