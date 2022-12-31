package oop.labs.lab4.math.model.anynomials;

import com.fasterxml.jackson.annotation.*;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import oop.labs.lab4.math.parse.ParsingSource;
import oop.labs.lab4.math.parse.ParsingSourceIterator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@JsonRootName("polynomial")
@JsonIncludeProperties("expression")
@SuppressWarnings("unused")
public final class PPolynomial implements Polynomial
{
    @Override public boolean isImmutable() { return true; }


    private final Map<Mononomial, BigDecimal> mononomials;
    private final Set<VariableDefinition> variables;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PPolynomial that = (PPolynomial) o;

        return mononomials.equals(that.mononomials);
    }

    @Override
    public int hashCode()
    {
        return mononomials.hashCode();
    }


    private PPolynomial(Map<Mononomial, BigDecimal> mononomials, Set<VariableDefinition> variables)
    {
        this.mononomials = mononomials;
        this.variables = variables;
    }

    private static PPolynomial of(Map<Mononomial, BigDecimal> mononomials, boolean copy)
    {
        var variablesSet = new HashSet<VariableDefinition>();
        for (var mononomial: mononomials.keySet()) variablesSet.addAll(mononomial.variables());
        return new PPolynomial(copy ? new HashMap<>(mononomials) : mononomials, variablesSet);
    }

    public static PPolynomial of(Iterable<MononomialOdded> mononomials)
    {
        var mononomialsMap = new HashMap<Mononomial, BigDecimal>();
        for (var mononomial: mononomials) mononomialsMap.put(mononomial.mononomial(), mononomial.odd());
        return PPolynomial.of(mononomialsMap, false);
    }

    public static PPolynomial of(Map<Mononomial, BigDecimal> mononomials)
    {
        return PPolynomial.of(mononomials, true);
    }


    @JsonCreator public static PPolynomial parse(@JsonProperty("expression") String source) throws ParseException { return parse(new ParsingSource(source)); }
    public static PPolynomial parse(ParsingSourceIterator source) throws ParseException
    {
        var map = new HashMap<Mononomial, BigDecimal>();

        while (source.hasCurrent())
        {
            var mononomial = PMononomialOdded.parse(source);
            if (mononomial == null) throw source.createException();

            if (map.containsKey(mononomial.mononomial()))
                map.put(mononomial.mononomial(), map.get(mononomial.mononomial()).add(mononomial.odd()));
            else
                map.put(mononomial.mononomial(), mononomial.odd());
        }

        return PPolynomial.of(map, false);
    }


    @Override public int variablesCount() { return variables.size(); }
    @Override public Set<VariableDefinition> variables() { return Collections.unmodifiableSet(variables); }
    @Override public boolean containsVariable(VariableDefinition variable) { return variables.contains(variable); }

    @Override public int monomialsCount() { return mononomials.size(); }
    @Override public Set<Mononomial> mononomials() { return mononomials.keySet(); }
    @Override public boolean containsMononomial(Mononomial mononomial) { return mononomials.containsKey(mononomial); }

    @Override
    public Iterable<MononomialOdded> mononomialsOdded()
    {
        return mononomials.keySet()
                .stream()
                .map(mononomial -> (MononomialOdded) new PMononomialOdded(mononomials.get(mononomial), mononomial))
                .toList();
    }

    @Override public BigDecimal oddOf(Mononomial mononomial) { return mononomials.getOrDefault(mononomial, BigDecimal.ONE); }
    @Override public BigDecimal oddOfContained(Mononomial mononomial) { return mononomials.get(mononomial); }


    @Override
    @JsonGetter("expression")
    public String toExpression()
    {
        var builder = new StringBuilder();
        for (var mononmial: mononomialsOdded()) builder.append(mononmial);
        return builder.toString();
    }

    @Override public String toString() { return toExpression(); }
}
