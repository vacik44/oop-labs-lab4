package oop.labs.lab4.assertion.asserts;

import oop.labs.lab4.math.model.anynomials.Anynomial;
import oop.labs.lab4.math.model.simplets.VariableDefinition;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class AnynomialAssert<TAnynomial extends Anynomial> extends MathObjectAssert<TAnynomial>
{
    public AnynomialAssert(TAnynomial tAnynomial) { super(tAnynomial); }
    public AnynomialAssert(AtomicReference<TAnynomial> actual) { super(actual); }


    public AnynomialAssert<TAnynomial> hasPowerOf(Integer power)
    {
        assertThat(actual.power()).isEqualTo(power);
        return this;
    }

    public AnynomialAssert<TAnynomial> hasVariablesCountOf(int count)
    {
        assertThat(actual.variablesCount()).isEqualTo(count);
        return this;
    }


    public AnynomialAssert<TAnynomial> containsVariable(VariableDefinition variable)
    {
        assertThat(actual.containsVariable(variable)).isTrue();
        return this;
    }

    public AnynomialAssert<TAnynomial> doesNotContainsVariable(VariableDefinition variable)
    {
        assertThat(actual.containsVariable(variable)).isFalse();
        return this;
    }


    public AnynomialAssert<TAnynomial> containsAllVariables(VariableDefinition... variables)
    {
       Arrays.stream(variables).forEach(this::containsVariable);
       return this;
    }

    public AnynomialAssert<TAnynomial> containsAllVariables(Collection<VariableDefinition> variables)
    {
        variables.forEach(this::containsVariable);
        return this;
    }

    public AnynomialAssert<TAnynomial> containsNoneOfVariables(VariableDefinition... variables)
    {
        Arrays.stream(variables).forEach(this::doesNotContainsVariable);
        return this;
    }

    public AnynomialAssert<TAnynomial> containsNoneOfVariables(Collection<VariableDefinition> variables)
    {
        variables.forEach(this::doesNotContainsVariable);
        return this;
    }


    public AnynomialAssert<TAnynomial> isEquivalentByVariablesTo(Anynomial other)
    {
        assertThat(actual.equivalentByVariables(other)).isTrue();
        return this;
    }

    public AnynomialAssert<TAnynomial> isENotEquivalentByVariablesTo(Anynomial other)
    {
        assertThat(actual.equivalentByVariables(other)).isFalse();
        return this;
    }
}
