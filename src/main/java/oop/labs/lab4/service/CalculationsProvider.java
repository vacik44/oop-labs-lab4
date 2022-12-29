package oop.labs.lab4.service;

import oop.labs.lab4.service.math.MathSolversMapper;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

@Service
public class CalculationsProvider
{
    private final MathSolversMapper solvers;


    CalculationsProvider(MathSolversMapper solvers)
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
