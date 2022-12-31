package oop.labs.lab4.service.mapping;

import oop.labs.lab4.math.eval.Solver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MathEvaluationMapper
{
    private final ApplicationContext evaluationContext;


    public MathEvaluationMapper(@Value("${app.service.mapping.math-solvers-beans-xml-path}") String xmlConfigPath)
    {
        evaluationContext = new FileSystemXmlApplicationContext(xmlConfigPath);
    }


    public Solver getSolver(String id)
    {
        return evaluationContext.getBean(id + "-solver", Solver.class);
    }
}
