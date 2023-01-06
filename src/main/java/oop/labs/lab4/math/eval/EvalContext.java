package oop.labs.lab4.math.eval;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.MathContext;
import java.math.RoundingMode;

@SuppressWarnings("unused")
public class EvalContext
{
    public static final EvalContext DEFAULT_COMPUTING_CONTEXT = new EvalContext(50, RoundingMode.HALF_UP);
    public static final EvalContext DEFAULT_PRESENTATION_CONTEXT = new EvalContext(25, RoundingMode.HALF_UP);


    @JsonIgnore private final MathContext mathContext;


    public EvalContext(MathContext mc) { this.mathContext = mc; }

    @JsonCreator
    public EvalContext(@JsonProperty("roundDigits") int roundDigits, @JsonProperty("roundMode") RoundingMode roundMode)
    {
        this(new MathContext(roundDigits, roundMode));
    }


    public MathContext getMathContext() { return mathContext; }

    @JsonGetter("roundDigits") public int roundDigits() { return mathContext.getPrecision(); }
    @JsonGetter("roundMode") public RoundingMode roundMode() { return mathContext.getRoundingMode(); }
}
