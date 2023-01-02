package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.model.containers.LU;

@SuppressWarnings("unused")
public class MatrixDeterminantSolver implements Solver
{
    private final Solver luDecompositionSolver;


    private MatrixDeterminantSolver(Solver luDecompositionSolver)
    {
        this.luDecompositionSolver = luDecompositionSolver;
    }


    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var solution = new SolutionNode("Evaluate determinant for matrix:", condition.task());

        var resultLu = luDecompositionSolver.computeSolution(condition.computingSubCondition(condition.task()));
        solution.addNode(resultLu.solution());

        var result = ((LU) resultLu.result()).computeOriginDet().round(condition.presentationContext());
        solution.newFinalNode("Calculate the determinant of the matrix from the resulting lu decomposition " +
                              "by multiplying the determinants of the resulting triangular matrices", result);

        return new EvalResults(result, solution);
    }
}
