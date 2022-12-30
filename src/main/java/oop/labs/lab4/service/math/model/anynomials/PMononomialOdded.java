package oop.labs.lab4.service.math.model.anynomials;

import oop.labs.lab4.service.math.model.MathObject;
import oop.labs.lab4.service.math.model.simplets.VariableDefinition;
import oop.labs.lab4.service.math.parse.MathParser;
import oop.labs.lab4.service.math.parse.ParsingSource;
import oop.labs.lab4.service.math.parse.ParsingSourceIterator;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
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


    @Override public BigDecimal getOdd() { return odd; }
    @Override public Mononomial getMononomial() { return mononomial; }


    @Override public int getVariablesCount() { return mononomial.getVariablesCount(); }
    @Override public Set<VariableDefinition> getVariablesSet() { return mononomial.getVariablesSet(); }
    @Override public boolean containsVariable(VariableDefinition variable) { return mononomial.containsVariable(variable); }

    @Override public Integer getPowerOfContained(VariableDefinition variable) { return mononomial.getPowerOfContained(variable); }
    @Override public Integer getPowerOf(VariableDefinition variable) { return mononomial.getPowerOf(variable); }


    @Override
    public String getExpression()
    {
        var builder = new StringBuilder();
        if (odd.signum() >= 0) builder.append('+');
        builder.append(odd).append(mononomial.getExpression());
        return builder.toString();
    }

    @Override public String toString() { return getExpression(); }
}
