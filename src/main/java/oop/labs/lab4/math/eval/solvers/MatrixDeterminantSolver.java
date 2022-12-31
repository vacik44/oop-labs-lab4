package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvaluationResult;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.containers.LU;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unused")
public class MatrixDeterminantSolver implements Solver
{
    private final Solver luDecompositionSolver;


    private MatrixDeterminantSolver(Solver luDecompositionSolver)
    {
        this.luDecompositionSolver = luDecompositionSolver;
    }


    @Override
    public EvaluationResult GetSolution(Object condition)
    {
        var matrix = (MatrixNumeric) condition;
        if (matrix.rows() != matrix.cols()) throw new MathEvaluationUnsupportedException("Determinant defined for quadratic matrices only");

        var solution = new SolutionNode("Evaluate determinant for matrix:", matrix);

        var resultLu = luDecompositionSolver.GetSolution(matrix);
        solution.addNode(resultLu.solution());

        var result = ((LU) resultLu.result()).computeOriginDet();
        solution.newFinalNode("Calculate the determinant of the matrix from the resulting lu decomposition " +
                              "by multiplying the determinants of the resulting triangular matrices", result);

        return new EvaluationResult(result, solution);
    }
}
