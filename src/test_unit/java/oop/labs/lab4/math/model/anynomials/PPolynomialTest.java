package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.simplets.VariableDfn;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import java.text.ParseException;

import static oop.labs.lab4.assertion.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PPolynomialTest
{
    @Test
    public void testIsImmutable() throws ParseException
    {
        assertThat(PPolynomial.parse("x#test0")).isDefinedAsImmutable();
    }

    @Test
    public void testParse()
    {
        Assertions.assertThat(catchThrowable(() -> PPolynomial.parse("2x1x2+x1**"))).isInstanceOf(ParseException.class);
        Assertions.assertThat(catchThrowable(() -> PPolynomial.parse("x*1*2+10x3"))).isInstanceOf(ParseException.class);
        Assertions.assertThat(catchThrowable(() -> PPolynomial.parse("x3+x4&x5"))).isInstanceOf(ParseException.class);
    }

    @Test
    public void testAnynomialMethods() throws ParseException
    {
        assertThat(PPolynomial.parse("2x1x2+3x1^2x3")).hasPowerOf(3)
                .containsAllVariables(VariableDfn.parse("x1"), VariableDfn.parse("x2"), VariableDfn.parse("x3"));
    }
}
