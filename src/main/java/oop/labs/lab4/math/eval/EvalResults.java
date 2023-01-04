package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonRootName("evalResults")
@SuppressWarnings("unused")
public final class EvalResults
{
    @JsonProperty("result")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
    private final Object result;
    @JsonProperty("solution") private final SolutionNode solution;


    @JsonCreator public EvalResults(@JsonProperty("result") Object result,
                                    @JsonProperty("solution") SolutionNode solution)
    {
        this.result = result;
        this.solution = solution;
    }


    public Object result() { return result; }
    public SolutionNode solution() { return solution; }
}
