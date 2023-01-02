package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.parse.MathParser;
import oop.labs.lab4.math.parse.ParsingSourceIterator;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import oop.labs.lab4.math.parse.ParsingSource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

public class PMononomialOdded implements MononomialOdded
{
    @Override public boolean isImmutable() { return true; }


    private final BigDecimal odd;
    private final Mononomial mononomial;


    @Override
    public boolean equivalentByVariables(Anynomial o)
    {
        return mononomial.equivalentByVariables(o);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PMononomialOdded that = (PMononomialOdded) o;

        if (!odd.equals(that.odd)) return false;
        return mononomial.equals(that.mononomial);
    }

    @Override
    public int hashCode()
    {
        int result = odd.hashCode();
        result = 31 * result + mononomial.hashCode();
        return result;
    }


    public PMononomialOdded(BigDecimal odd, Mononomial mononomial)
    {
        this.odd = odd;
        this.mononomial = mononomial;
    }



    public static PMononomialOdded parse(String source) throws ParseException { return parse(new ParsingSource(source)); }
    public static PMononomialOdded parse(ParsingSourceIterator source) throws ParseException
    {
        var odd = MathParser.parseNum(source);
        var mononomial = PMononomial.parse(source);
        return odd == null && mononomial == null ? null :
                new PMononomialOdded(Optional.ofNullable(odd).orElse(BigDecimal.ONE),
                        Optional.ofNullable(mononomial).orElse(PMononomial.empty()));
    }


    @Override public BigDecimal odd() { return odd; }
    @Override public Mononomial mononomial() { return mononomial; }


    @Override public int variablesCount() { return mononomial.variablesCount(); }
    @Override public Set<VariableDefinition> variables() { return mononomial.variables(); }
    @Override public boolean containsVariable(VariableDefinition variable) { return mononomial.containsVariable(variable); }

    @Override public Integer power() { return mononomial.power(); }
    @Override public Integer powerOfContained(VariableDefinition variable) { return mononomial.powerOfContained(variable); }
    @Override public Integer powerOf(VariableDefinition variable) { return mononomial.powerOf(variable); }


    @Override
    public String toExpression()
    {
        var builder = new StringBuilder();
        if (odd.signum() >= 0) builder.append('+');
        builder.append(odd).append(mononomial.toExpression());
        return builder.toString();
    }

    @Override public String toString() { return toExpression(); }
}
