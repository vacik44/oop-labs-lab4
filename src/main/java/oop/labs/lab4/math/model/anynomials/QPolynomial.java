package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.matrix.MatrixNumeric;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import oop.labs.lab4.math.model.simplets.VariableDfn;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.StreamSupport;

@SuppressWarnings("unused")
public final class QPolynomial implements PolynomialQuadratic
{
    @Override public boolean isImmutable() { return true; }


    private final MatrixNumeric odds;
    private final List<VariableDefinition> variables;
    private final Map<VariableDefinition, Integer> indexes;


    private QPolynomial(MatrixNumeric odds, List<VariableDefinition> variables, Map<VariableDefinition, Integer> indexes)
    {
        this.odds = odds;
        this.indexes = indexes;
        this.variables = variables;
    }


    public static QPolynomial of(MatrixNumeric odds, Iterable<VariableDefinition> variables) { return of(new NumMatrixImmutable(odds), variables); }
    public static QPolynomial of(NumMatrixImmutable odds, Iterable<VariableDefinition> variables) { return of(odds, StreamSupport.stream(variables.spliterator(), false).toList()); }
    private static QPolynomial of(NumMatrixImmutable odds, List<VariableDefinition> variables)
    {
        var indexes = new HashMap<VariableDefinition, Integer>();

        var i = 1;
        for (var variable: variables)
        {
            indexes.put(variable, i);
            i++;
        }

        return new QPolynomial(odds, variables, indexes);
    }

    public static QPolynomial of(MatrixNumeric odds, String variableName) { return of(new NumMatrixImmutable(odds), variableName); }
    public static QPolynomial of(NumMatrixImmutable odds, String variableName)
    {
        var variables = new ArrayList<VariableDefinition>();
        for (var i = 1; i <= odds.rows(); i++) variables.add(new VariableDfn(variableName, i));
        return of(odds, variables);
    }


    @Override public int variablesCount() { return variables.size(); }
    @Override public Set<VariableDefinition> variables() { return indexes.keySet(); }
    @Override public boolean containsVariable(VariableDefinition variable) { return variables.contains(variable); }

    @Override
    public int monomialsCount()
    {
        var count = 0;
        for (var i = 1; i <= odds.rows(); i++)
            for (var j= 1; j <= odds.cols(); j++)
                if (odds.get(i, j).signum() != 0)
                    count++;

        return count;
    }
    @Override
    public Set<Mononomial> mononomials()
    {
        var set = new HashSet<Mononomial>();
        for (var i = 1; i <= odds.rows(); i++)
            for (var j= 1; j <= odds.cols(); j++)
                if (odds.get(i, j).signum() != 0)
                    set.add(PMononomial.of(variables.get(i), variables.get(j)));

        return set;
    }
    @Override
    public boolean containsMononomial(Mononomial mononomial)
    {
        return oddOfContained(mononomial) != null;
    }

    @Override
    public Iterable<MononomialOdded> mononomialsOdded()
    {
        var set = new HashSet<MononomialOdded>();
        for (var i = 1; i <= odds.rows(); i++)
            for (var j= 1; j <= odds.cols(); j++)
                if (odds.get(i, j).signum() != 0)
                    set.add(new PMononomialOdded(odds.get(i, j), PMononomial.of(variables.get(i), variables.get(j))));

        return set;
    }

    @Override public MatrixNumeric odds() { return odds; }
    @Override public BigDecimal oddOf(Mononomial mononomial)
    {
        return Optional.ofNullable(oddOfContained(mononomial)).orElse(BigDecimal.ZERO);
    }
    @Override public BigDecimal oddOf(int index1, int index2) { return odds.get(index1, index2); }
    @Override public BigDecimal oddOfContained(Mononomial mononomial)
    {
        if (mononomial.power() != 2 || !indexes.keySet().containsAll(mononomial.variables())) return null;
        var pos = mononomial.variables().stream().map(indexes::get).toList();
        var odd = pos.size() == 1 ? odds.get(pos.get(0), pos.get(0)): odds.get(pos.get(0), pos.get(1));
        return odd.signum() == 0 ? null : odd;
    }


    @Override
    public String toExpression()
    {
        var builder = new StringBuilder();

        for (var i = 1; i <= odds.rows(); i++)
            for (var j = 1; j <= odds.cols(); j++)
            {
                var odd = odds.get(i, j);
                if (odd.equals(BigDecimal.ZERO)) continue;
                if (odd.signum() >= 0 && !builder.isEmpty()) builder.append('+');
                if (!odd.equals(BigDecimal.ONE)) builder.append(odd);
                builder.append('x').append(i).append(i == j ? "^2" : String.format("x%d", j));
            }

        return builder.toString();
    }

    @Override public String toString() { return toExpression(); }
}
