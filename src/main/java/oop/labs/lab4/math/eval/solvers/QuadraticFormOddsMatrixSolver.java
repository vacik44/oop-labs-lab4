package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.anynomials.Polynomial;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.matrix.NumMatrixMutable;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

@SuppressWarnings("unused")
public class QuadraticFormOddsMatrixSolver implements Solver
{
    @Override
    public EvalResults computeSolution(EvalCondition condition)
    {
        var polynomial = MathEvaluationUnsupportedException.throwIfNotOfExactPower((Polynomial) condition.task(), 2);
        var solution = new SolutionNode("Build odds matrix for quadratic form of polynomial:", polynomial, 1);

        var variables = new ArrayList<>(polynomial.variables());
        variables.sort(Comparator.comparing(VariableDefinition::index));

        var odds = new NumMatrixMutable(variables.size(), variables.size(), BigDecimal.ZERO);
        for (var i = 1; i <= variables.size(); i++)
            for (var j = 1; j <= i; j++)
            {
                var odd = polynomial.oddOf(variables.get(i - 1), variables.get(j - 1));

                if (i == j) odds.set(i, j, odd);
                else
                {
                    odd = odd.divide(BigDecimal.valueOf(2), condition.computingContext());
                    odds.set(i, j, odd);
                    odds.set(j, i, odd);
                }
            }

        var oddsMatrix = NumMatrixImmutable.immutable(odds);
        solution.newFinalNode("Build odds matrix for current polynomial:", oddsMatrix);
        return new EvalResults(oddsMatrix, solution);
    }
}
