package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.parse.MathParser;
import oop.labs.lab4.math.parse.ParsingSourceIterator;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import oop.labs.lab4.math.model.simplets.VariableDfn;
import oop.labs.lab4.math.parse.ParsingSource;

import java.text.ParseException;
import java.util.*;

public final class PMononomial implements Mononomial
{
    @Override public boolean isImmutable() { return true; }


    private final Map<VariableDefinition, Integer> variables;


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PMononomial that = (PMononomial) o;

        return variables.equals(that.variables);
    }

    @Override
    public int hashCode()
    {
        return variables.hashCode();
    }


    private static PMononomial instance(Map<VariableDefinition, Integer> variables) { return new PMononomial(variables, false); }
    private PMononomial(Map<VariableDefinition, Integer> variables, boolean ignored)  { this.variables = variables; }

    public static PMononomial empty() { return instance(new HashMap<>()); }
    public static PMononomial of(Map<VariableDefinition, Integer> variables) { return instance(new HashMap<>(variables)); }

    public static PMononomial of(Iterable<VariableDefinition> variables)
    {
        var map = new HashMap<VariableDefinition, Integer>();

        for (var variable: variables)
            if (map.containsKey(variable)) map.put(variable, map.get(variable) + 1);
            else map.put(variable, 1);

        return instance(map);
    }
    public static PMononomial of(VariableDefinition... variables)
    {
        var map = new HashMap<VariableDefinition, Integer>();

        for (var variable: variables)
            if (map.containsKey(variable)) map.put(variable, map.get(variable) + 1);
            else map.put(variable, 1);

        return instance(map);
    }


    public static PMononomial parse(String source) throws ParseException { return parse(new ParsingSource(source)); }
    public static PMononomial parse(ParsingSourceIterator source) throws ParseException
    {
        var variables = new HashMap<VariableDefinition, Integer>();

        var memberExpected = false;
        while (source.hasCurrent())
        {
            if (source.current() == '*')
                if (memberExpected) throw source.createException();
                else
                {
                    memberExpected = true;
                    source.move();
                    continue;
                }

            VariableDefinition def;
            int pow;

            if ((def = VariableDfn.parse(source)) == null)
                if (memberExpected) throw source.createException();
                else break;

            memberExpected = false;

            if (!source.hasCurrent() || source.current() != '^') pow = 1;
            else
            {
                source.move();
                var buffer = MathParser.parseInt(source);
                if (buffer == null) throw source.createException();
                pow = buffer;
            }

            variables.put(def, pow);
        }

        return variables.size() == 0 ? null : instance(variables);
    }


    @Override public int variablesCount() { return variables.size(); }
    @Override public Set<VariableDefinition> variables() { return variables.keySet(); }
    @Override public boolean containsVariable(VariableDefinition variable) { return variables.containsKey(variable); }

    @Override public Integer power() { return variables.values().stream().mapToInt(Integer::intValue).sum(); }
    @Override public Integer powerOfContained(VariableDefinition variable) { return variables.get(variable); }
    @Override public Integer powerOf(VariableDefinition variable) { return variables.getOrDefault(variable, 0); }


    @Override
    public String toExpression()
    {
        var builder = new StringBuilder();

        for (var memebr : variables())
        {
            builder.append(memebr);
            var pow = powerOfContained(memebr);
            if (pow > 1) builder.append('^').append(pow);
        }

        return builder.toString();
    }

    @Override public String toString() { return toExpression(); }
}
