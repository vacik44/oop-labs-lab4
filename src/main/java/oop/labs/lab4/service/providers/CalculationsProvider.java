package oop.labs.lab4.service.providers;

import oop.labs.lab4.math.eval.Solver;
import oop.labs.lab4.service.mapping.ApplicationMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CalculationsProvider
{
    private final ApplicationMapper solvers;


    CalculationsProvider(@Qualifier("MathSolversMapper") ApplicationMapper solvers)
    {
        this.solvers = solvers;
    }


    public Object submitCalculation(String solverId, Object condition)
    {
        try
        {
            System.out.println("Hello from CalculationsProvider");
            var solver = (Solver) solvers.getInstanceForName(solverId);
            return solver.GetSolution(condition);
        }
        catch (BeansException exception)
        {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}