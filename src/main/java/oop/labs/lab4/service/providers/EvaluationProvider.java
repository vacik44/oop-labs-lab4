package oop.labs.lab4.service.providers;

import oop.labs.lab4.math.MathematicalException;
import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.service.exceptions.EvaluationFailedException;
import oop.labs.lab4.service.exceptions.SolverNotFoundException;
import oop.labs.lab4.service.mapping.ApplicationMapper;
import oop.labs.lab4.service.mapping.MappingManagementException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EvaluationProvider
{
    private final ApplicationMapper solvers;


    EvaluationProvider(@Qualifier("MathSolversMapper") ApplicationMapper solvers)
    {
        this.solvers = solvers;
    }


    public EvalResults evaluate(String solverId, EvalCondition condition)
    {
        try
        {
            var solver = (Solver) solvers.getInstanceForName(solverId + "-solver");
            return solver.computeSolution(condition);
        }
        catch (MappingManagementException e)
        {
            throw SolverNotFoundException.forId(solverId);
        }
        catch (MathematicalException e)
        {
            throw new EvaluationFailedException(e);
        }
    }
}