package oop.labs.lab4.service;

import oop.labs.lab4.data.repos.CalculationsRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CalculationsProvider
{
    private final ApplicationContext solvers;
    private final CalculationsRepository repository;


    CalculationsProvider(CalculationsRepository repository)
    {
        this.repository = repository;
        this.solvers = new ClassPathXmlApplicationContext("math.solvers.xml");
    }

    public String submitCalculation(String solverId, Object condition)
    {
        try
        {
            System.out.println("Hello from CalculationsProvider");
            var solver = solvers.getBean(solverId);
        }
        catch (BeansException exception)
        {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}
