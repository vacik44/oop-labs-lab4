package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.MathContext;
import java.util.Optional;

@JsonTypeName("evalCondition")
@SuppressWarnings("unused")
public final class EvalCondition
{
    @JsonProperty("computingContext") private final EvalContext computingContext;
    @JsonProperty("presentationContext") private final EvalContext presentationContext;
    @JsonProperty("task")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
    private final Object mathTask;


    public EvalCondition(Object mathTask)
    {
        this(mathTask, null, null);
    }

    public EvalCondition(Object mathTask, EvalContext presentationContext)
    {
        this(mathTask, presentationContext, null);
    }

    @JsonCreator
    public EvalCondition(@JsonProperty("task") Object mathTask,
                         @JsonProperty("presentationContext") EvalContext presentationContext,
                         @JsonProperty("computingContext") EvalContext computingContext)
    {
        this.mathTask = mathTask;
        this.presentationContext = Optional.ofNullable(presentationContext).orElse(EvalContext.DEFAULT_PRESENTATION_CONTEXT);
        this.computingContext = Optional.ofNullable(computingContext).orElse(EvalContext.DEFAULT_COMPUTING_CONTEXT);
    }


    public EvalCondition sameContextCondition(Object newTask) { return new EvalCondition(newTask, presentationContext, computingContext); }
    public EvalCondition computingSubCondition(Object subTask) { return new EvalCondition(subTask, computingContext, computingContext); }


    public MathContext presentationMc() { return presentationContext.getMathContext(); }
    public MathContext computingMc() { return computingContext.getMathContext(); }

    public EvalContext presentationContext() { return presentationContext; }
    public EvalContext computingContext() { return computingContext; }

    public Object task() { return mathTask; }
}
