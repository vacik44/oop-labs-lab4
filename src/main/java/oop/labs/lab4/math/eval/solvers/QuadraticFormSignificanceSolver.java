package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.math.eval.exceptions.MathEvaluationUnsupportedException;
import oop.labs.lab4.math.model.anynomials.Polynomial;
import oop.labs.lab4.math.model.matrix.NumMatrix;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.matrix.NumMatrixMutable;
import oop.labs.lab4.math.model.simplets.VariableDefinition;

import java.math.BigDecimal;
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
        var polynomial = (Polynomial) condition.task();

        if (polynomial.mononomials().stream().anyMatch(mononomial -> mononomial.power() != 2))
            throw new MathEvaluationUnsupportedException("Given polynomial does not have a quadratic form.");

        var solution = new SolutionNode("Check quadratic form significance for polynomial:", polynomial);

        var variables = new ArrayList<>(polynomial.variables());
        variables.sort(Comparator.comparing(VariableDefinition::index));

        var oddsOrigin = new NumMatrixMutable(variables.size(), variables.size(), BigDecimal.ZERO);
        for (var i = 1; i <= variables.size(); i++)
            for (var j = 1; j <= i; j++)
            {
                var odd = polynomial.oddOf(variables.get(i - 1), variables.get(j - 1));
                oddsOrigin.set(i, j, odd);
                oddsOrigin.set(j, i, odd);
            }

        var odds = NumMatrixImmutable.immutable(oddsOrigin);
        solution.newFinalNode("Build odds matrix for current polynomial:", odds);
        return null;
    }
}
