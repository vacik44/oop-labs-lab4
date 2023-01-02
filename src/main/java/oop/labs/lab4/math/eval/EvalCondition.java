package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import oop.labs.lab4.service.mapping.JsonTypeInfoStandard;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

@JsonTypeName("evalCondition")
public final class EvalCondition
{
    @JsonProperty("context") private final MathContext context;
    @JsonProperty("task") @JsonTypeInfoStandard private final Object mathTask;


    @JsonCreator public EvalCondition(@JsonProperty("task") Object mathTask) { this(mathTask, null); }
    @JsonCreator public EvalCondition(@JsonProperty("task") Object mathTask, @JsonProperty("context") MathContext context)
    {
        this.mathTask = mathTask;
        this.context = Optional.ofNullable(context).orElse(new MathContext(4, RoundingMode.HALF_UP));
    }


    public MathContext context() { return context; }
    public Object task() { return mathTask; }
}
