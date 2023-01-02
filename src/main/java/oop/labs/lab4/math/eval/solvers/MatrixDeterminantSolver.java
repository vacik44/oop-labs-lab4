package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.containers.LU;
import oop.labs.lab4.math.model.matrix.Matrix;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;

@SuppressWarnings("unused")
public class MatrixDeterminantSolver implements Solver
{
    private final Solver luDecompositionSolver;


    private MatrixDeterminantSolver(Solver luDecompositionSolver)
    {
        this.luDecompositionSolver = luDecompositionSolver;
    }


    @Override
    public EvalResults GetSolution(EvalCondition condition)
    {
        var matrix = (MatrixNumeric)((Matrix<?>) condition.task()).throwUnsupportedIfNotSquare();
        var solution = new SolutionNode("Evaluate determinant for matrix:", matrix);

        var resultLu = luDecompositionSolver.GetSolution(new EvalCondition(matrix, condition.context()));
        solution.addNode(resultLu.solution());

        var result = ((LU) resultLu.result()).computeOriginDet();
        solution.newFinalNode("Calculate the determinant of the matrix from the resulting lu decomposition " +
                              "by multiplying the determinants of the resulting triangular matrices", result);

        return new EvalResults(result, solution);
    }
}
