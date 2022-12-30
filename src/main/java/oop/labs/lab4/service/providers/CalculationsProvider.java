package oop.labs.lab4.service.providers;

import oop.labs.lab4.service.mapping.MathEvaluationMapper;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

@Service
public class CalculationsProvider
{
    private final MathEvaluationMapper solvers;


    CalculationsProvider(MathEvaluationMapper solvers)
    {
        this.solvers = solvers;
    }


    public Object submitCalculation(String solverId, Object condition)
    {
        try
        {
            System.out.println("Hello from CalculationsProvider");
            var solver = solvers.getSolver(solverId);
            return solver.GetSolution(condition);
        }
        catch (BeansException exception)
        {
            System.out.println(exception.getMessage());
        }
        return null;
    }
}