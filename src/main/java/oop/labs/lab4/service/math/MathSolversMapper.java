package oop.labs.lab4.service.math;

import oop.labs.lab4.service.math.solvers.Solver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MathSolversMapper
{
    private final ApplicationContext solverBeans;


    public MathSolversMapper(@Value("${app.service.math.solvers.mapper-cfg-path}") String cfgPath)
    {
        solverBeans = new FileSystemXmlApplicationContext(cfgPath);
    }


    public Solver getSolver(String id)
    {
        return solverBeans.getBean(id + "-solver", Solver.class);
    }
}
