package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import oop.labs.lab4.service.mapping.JsonTypeInfoStandard;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

@JsonTypeName("evalCondition")
@SuppressWarnings("unused")
public final class EvalCondition
{
    @JsonProperty("computingContext") private final MathContext computingContext;
    @JsonProperty("presentationContext") private final MathContext presentationContext;
    @JsonProperty("task") @JsonTypeInfoStandard private final Object mathTask;


    @JsonCreator
    public EvalCondition(@JsonProperty("task") Object mathTask)
    {
        this(mathTask, null, null);
    }

    @JsonCreator
    public EvalCondition(@JsonProperty("task") Object mathTask,
                         @JsonProperty("presentationContext") MathContext presentationContext)
    {
        this(mathTask, presentationContext, null);
    }

    @JsonCreator
    public EvalCondition(@JsonProperty("task") Object mathTask,
                         @JsonProperty("presentationContext") MathContext presentationContext,
                         @JsonProperty("computingContext") MathContext computingContext)
    {
        this.mathTask = mathTask;
        this.presentationContext = Optional.ofNullable(presentationContext).orElse(new MathContext(4, RoundingMode.HALF_UP));
        this.computingContext = Optional.ofNullable(computingContext).orElse(new MathContext(25, RoundingMode.HALF_UP));
    }


    public EvalCondition sameContextCondition(Object newTask) { return new EvalCondition(newTask, presentationContext, computingContext); }
    public EvalCondition computingSubCondition(Object subTask) { return new EvalCondition(subTask, computingContext, computingContext); }


    public MathContext presentationContext() { return presentationContext; }
    public MathContext computingContext() { return computingContext; }
    public Object task() { return mathTask; }
}
