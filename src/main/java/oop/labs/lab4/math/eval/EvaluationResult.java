package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("evalResults")
@SuppressWarnings("unused")
public final class EvaluationResult
{
    @JsonProperty("result") private final Object result;
    @JsonProperty("solution") private final SolutionNode solution;


    @JsonCreator public EvaluationResult(@JsonProperty("result") Object result,
                                         @JsonProperty("solution") SolutionNode solution)
    {
        this.result = result;
        this.solution = solution;
    }


    public Object result() { return result; }
    public SolutionNode solution() { return solution; }
}
