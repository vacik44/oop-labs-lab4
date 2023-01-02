package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.anynomials.Polynomial;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.matrix.NumMatrixMutable;
import oop.labs.lab4.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings("unused")
public class QuadraticFormSignificanceSolver implements Solver
{
    private final Solver matrixDeterminantSolver;


    public QuadraticFormSignificanceSolver(Solver matrixDeterminantSolver)
    {
        this.matrixDeterminantSolver = matrixDeterminantSolver;
    }


    @Override
    public EvalResults GetSolution(EvalCondition condition)
    {
        var data = SolverData.init(condition);
        buildOddsMatrix(data);
        return null;
    }

    private static void buildOddsMatrix(SolverData data)
    {
        var variables = new ArrayList<>(data.plynomial.variables());
        variables.sort(Comparator.comparing(VariableDefinition::index));

        var odds = new NumMatrixMutable(variables.size(), variables.size(), BigDecimal.ZERO);
        for (var i = 1; i <= variables.size(); i++)
            for (var j = 1; j <= i; j++)
            {
                var odd = data.plynomial.oddOf(variables.get(i - 1), variables.get(j - 1));
                odds.set(i, j, odd);
                odds.set(j, i, odd);
            }

        data.oddsMatrix = NumMatrixImmutable.immutable(odds);
        data.solution.newFinalNode("Build odds matrix for current polynomial:", data.oddsMatrix);
    }

    private static boolean checkSignificance(SolverData data)
    {
        return false;
    }


    private static class SolverData
    {
        private final MathContext context;
        private final Polynomial plynomial;
        private final SolutionNode solution;

        private MatrixNumeric oddsMatrix;


        private SolverData(MathContext context, Polynomial plynomial, SolutionNode solution)
        {
            this.context = context;
            this.plynomial = plynomial;
            this.solution = solution;
        }

        public static SolverData init(EvalCondition condition)
        {
            var polynomial = (Polynomial) condition.task();

            if (polynomial.mononomials().stream().anyMatch(mononomial -> mononomial.power() != 2))
                throw new MathEvaluationUnsupportedException("Given polynomial does not have a quadratic form.");

            var solution = new SolutionNode("Check quadratic form significance for polynomial:", polynomial);

            return new SolverData(condition.context(), polynomial, solution);
        }
    }
}
