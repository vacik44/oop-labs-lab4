package oop.labs.lab4.math.eval.solvers;

import oop.labs.lab4.math.eval.EvaluationResult;
import oop.labs.lab4.math.eval.Solver;

@SuppressWarnings("unused")
public class QuadraticFormSignificanceSolver implements Solver
{
    private final Solver matrixDeterminantSolver;


    public QuadraticFormSignificanceSolver(Solver matrixDeterminantSolver)
    {
        this.matrixDeterminantSolver = matrixDeterminantSolver;
    }


    @Override
    public EvaluationResult GetSolution(Object condition)
    {
        return null;
    }
}
