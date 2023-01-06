package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.enums.SignificanceType;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;

import java.math.BigDecimal;

public class SymmetricMatrixSignificanceSilvesterSolver implements Solver
{
    private final Solver matrixDeterminantSolver;


    private SymmetricMatrixSignificanceSilvesterSolver(Solver matrixDeterminantSolver)
    {
        this.matrixDeterminantSolver = matrixDeterminantSolver;
    }


    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var matrix = MathEvaluationUnsupportedException.throwIfNotSquare((MatrixNumeric) condition.task());
        var solution = new SolutionNode("Check significance of matrix by Silvester's criterion", matrix, 2);

        var solutionMinors = new SolutionNode("Find signs of angular minors");
        solution.addNode(solutionMinors);

        var expectedSignForNegative = -1;
        var negativeConfirmed = true;
        var positiveConfirmed = true;
        var power = 1;

        while (power <= matrix.rows() && (negativeConfirmed || positiveConfirmed))
        {
            var minor = matrix.angularMinorMatrix(power);
            var detResults = matrixDeterminantSolver.computeSolution(condition.sameContextCondition(minor));

            var sign = ((BigDecimal) detResults.result()).signum();

            var minorSolution = new SolutionNode(String.format("Angular minor %d", power), minor, 1);
            minorSolution.addNode(detResults.solution());
            minorSolution.newFinalNode("The sign of minor is" + (sign > 0 ? "+" : sign == 0 ? "undefined" : "-"));
            solutionMinors.addNode(minorSolution);

            if (positiveConfirmed && sign <= 0) positiveConfirmed = false;
            if (negativeConfirmed && sign != expectedSignForNegative) negativeConfirmed = false;
            else expectedSignForNegative = -expectedSignForNegative;

            power++;
        }

        var result = negativeConfirmed ? SignificanceType.NEGATIVE : positiveConfirmed ? SignificanceType.POSITIVE : SignificanceType.UNDEFINED;
        solution.newFinalNode("Resolved significance type:", result);

        return new EvalResults(result, solution);
    }
}
