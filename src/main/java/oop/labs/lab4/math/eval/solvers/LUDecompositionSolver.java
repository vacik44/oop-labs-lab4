package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.containers.LU;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.matrix.NumMatrixMutable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class LUDecompositionSolver implements Solver
{
    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var data = SolverData.init(condition);

        for (var i = 1; i <= data.origin.rows(); i++) data.lower.set(i, 1, data.origin.get(i, 1));
        data.solution.newFinalNode("Filling first column of L matrix with values from first column of origin matrix:", new NumMatrixImmutable(data.origin));

        fillUpperLine(data, 1);

        if (data.origin.rows() > 1)
        {
            for (var i = 2; i < data.origin.rows(); i++)
            {
                fillLowerColumn(data, i);
                fillUpperLine(data, i);
            }

            fillLowerColumn(data, data.origin.cols());
        }

        var resultLU = new LU(NumMatrixImmutable.immutable(data.lower), NumMatrixImmutable.immutable(data.upper));
        data.solution.newFinalNode("Resulting LU-factorization:", resultLU);

        return new EvalResults(resultLU, data.solution);
    }

    private static void fillLowerColumn(SolverData data, int col)
    {
        for (var row = col; row <= data.origin.rows(); row++)
        {
            var sum = BigDecimal.ZERO;
            for (var i = 1; i <= col; i++) sum = sum.add(data.lower.get(row, i).multiply(data.upper.get(i, col)));
            data.lower.set(row, col, data.origin.get(row, col).subtract(sum));
        }

        data.solution.newFinalNode(String.format("Filling column %d of L matrix:", col), data.lower.rounded(data.condition.presentationContext()));
    }

    private static void fillUpperLine(SolverData data, int row)
    {
        for (var col = row + 1; col <= data.origin.cols(); col++)
        {
            var sum = BigDecimal.ZERO;
            for (var i = 1; i <= row; i++) sum = sum.add(data.lower.get(row, i).multiply(data.upper.get(i, col)));
            data.upper.set(row, col, data.origin.get(row, col).subtract(sum).divide(data.lower.get(row, row), data.condition.computingContext()));
        }

        data.solution.newFinalNode(String.format("Filling line %d of U matrix:", row), data.upper.rounded(data.condition.presentationContext()));
    }


    private static class SolverData
    {
        private final EvalCondition condition;
        private final MatrixNumeric origin;
        private final SolutionNode solution;
        private final NumMatrixMutable lower;
        private final NumMatrixMutable upper;


        private SolverData(EvalCondition condition, MatrixNumeric origin, SolutionNode solution, NumMatrixMutable lower, NumMatrixMutable upper)
        {
            this.condition = condition;
            this.origin = origin;
            this.solution = solution;
            this.lower = lower;
            this.upper = upper;
        }

        public static SolverData init(EvalCondition condition)
        {
            var origin = MathEvaluationUnsupportedException.throwIfNotSquare((MatrixNumeric)(condition.task()));
            var solution = new SolutionNode("Perform LU-factorization for matrix:", origin);

            var upper = NumMatrixMutable.eye(origin.rows());
            solution.newFinalNode("Create empty upper matrix", new NumMatrixImmutable(upper));

            var lower = new NumMatrixMutable(origin.rows(), origin.cols(), BigDecimal.ZERO);
            solution.newFinalNode("Create empty lower matrix", new NumMatrixImmutable(lower));

            return new SolverData(condition, origin, solution, lower, upper);
        }
    }
}
