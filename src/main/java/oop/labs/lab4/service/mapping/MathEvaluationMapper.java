package oop.labs.lab4.service.mapping;

import oop.labs.lab4.data.configs.MappingConfiguration;
import oop.labs.lab4.math.eval.Solver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MathEvaluationMapper
{
    private final ApplicationContext solverBeans;


    public MathEvaluationMapper(@Qualifier("MathSolversMappingConfiguration") MappingConfiguration configuration) throws ClassNotFoundException
    {
        var context = new StaticApplicationContext();

        for (var clazz : configuration.getClasses())
            context.registerSingleton(clazz.getId(), clazz.getClazz());

        solverBeans = context;
    }


    public Solver getSolver(String id)
    {
        return solverBeans.getBean(id + "-solver", Solver.class);
    }
}
