package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.simplets.VariableDfn;
import org.junit.Test;
import java.text.ParseException;

import static oop.labs.lab4.assertion.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

public class PMononomialOddedTest
{
    @Test
    public void testIsImmutable() throws ParseException
    {
        assertThat(PMononomialOdded.parse("x#test0")).isDefinedAsImmutable();
    }

    @Test
    public void testParse()
    {
        assertThat(catchThrowable(() -> PMononomialOdded.parse("x1**"))).isInstanceOf(ParseException.class);
        assertThat(catchThrowable(() -> PMononomialOdded.parse("x*1*2"))).isInstanceOf(ParseException.class);
        assertThat(catchThrowable(() -> PMononomialOdded.parse("x#1**"))).isInstanceOf(ParseException.class);
        assertThat(catchThrowable(() -> PMononomialOdded.parse("x1^x3"))).isInstanceOf(ParseException.class);
    }

    @Test
    public void testAnynomialMethods() throws ParseException
    {
        var mononomial1 = PMononomialOdded.parse("3x1^2x3x2");
        var mononomial2 = PMononomialOdded.parse("3x1^2x3x2");
        var mononomial3 = PMononomialOdded.parse("x1x2x3");

        assertThat(mononomial1)
                .hasPowerOf(4)
                .hasVariablesCountOf(3)
                .containsAllVariables(VariableDfn.parse("x1"), VariableDfn.parse("x2"), VariableDfn.parse("x3"))
                .containsAllVariables(mononomial3.variables())
                .isEquivalentByVariablesTo(mononomial3).isEquivalentByVariablesTo(mononomial2)
                .isEqualTo(mononomial2).hasSameHashCodeAs(mononomial2)
                .isNotEqualTo(mononomial3).doesNotHaveSameHashCodeAs(mononomial3);
    }

    @Test
    public void testToExpressionToString() throws ParseException
    {
        var mononomial1 = PMononomialOdded.parse("3x1^2x3x2");
        var mononomial2 = PMononomialOdded.parse("-4x1x2x3");

        assertThat(mononomial1.toExpression()).isEqualTo(mononomial1.toString());
        assertThat(mononomial2.toExpression()).isEqualTo(mononomial2.toString());
    }
}
