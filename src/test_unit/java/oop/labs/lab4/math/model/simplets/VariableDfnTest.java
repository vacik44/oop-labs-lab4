package oop.labs.lab4.math.model.simplets;

import org.junit.Test;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static oop.labs.lab4.asserts.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class VariableDfnTest
{
    @Test
    public void testIsImmutable()
    {
        assertThat(new VariableDfn(null).isImmutable()).isTrue();
    }

    @Test
    public void testConstructorsGetters()
    {
        var v1 = new VariableDfn("x");
        var v2 = new VariableDfn("x", 1);
        var v3 = new VariableDfn("x", "init");
        var v4 = new VariableDfn("x", "init", 1);

        assertThat(v1.index()).isNull();
        assertThat(v1.subName()).isNull();
        assertThat(v2.subName()).isNull();
        assertThat(v3.index()).isNull();

        assertThat(v1.name()).isEqualTo(v2.name()).isEqualTo(v3.name()).isEqualTo(v4.name()).isEqualTo("x");
        assertThat(v2.index()).isEqualTo(v4.index()).isEqualTo(1);
        assertThat(v3.subName()).isEqualTo(v4.subName()).isEqualTo("init");
    }

    @Test
    public void testEqualsHashCodeEquivalent()
    {
        var v1 = new VariableDfn("x");
        var v2 = new VariableDfn("x");
        var v3 = new VariableDfn("x", "init", 1);
        var v4 = new VariableDfn("x", "init", 1);
        var v5 = new VariableDfn("x", "test", 1);

        assertThat(v1).isEquivalentOf(v2).isNotEquivalentOf(v3).isNotEquivalentOf(v4).isNotEquivalentOf(v5);
        assertThat(v3).isEquivalentOf(v4).isNotEquivalentOf(v5);

        assertThat(v1).isEqualTo(v2).hasSameHashCodeAs(v2).isNotEqualTo(v3).doesNotHaveSameHashCodeAs(v3).isNotEqualTo(v4).doesNotHaveSameHashCodeAs(v4).isNotEqualTo(v5).doesNotHaveSameHashCodeAs(v5);
        assertThat(v3).isEqualTo(v4).hasSameHashCodeAs(v4).isNotEqualTo(v5).doesNotHaveSameHashCodeAs(v5);
    }

    @Test
    public void testParse() throws ParseException
    {
        assertThat(catchThrowable(() -> VariableDfn.parse("1x"))).isInstanceOf(ParseException.class);
        assertThat((catchThrowable(() -> VariableDfn.parse("x2init")))).isInstanceOf(ParseException.class);

        assertThat(VariableDfn.parse("x")).isEqualTo(new VariableDfn("x"));
        assertThat(VariableDfn.parse("x1")).isEqualTo(new VariableDfn("x", 1));
        assertThat(VariableDfn.parse("x#init")).isEqualTo(new VariableDfn("x", "init"));
        assertThat(VariableDfn.parse("x#init1")).isEqualTo(new VariableDfn("x", "init", 1));
    }

    @Test
    public void testToExpressionToString()
    {
        var v1 = new VariableDfn("x");
        var v2 = new VariableDfn("x", 1);
        var v3 = new VariableDfn("x", "init");
        var v4 = new VariableDfn("x", "init", 1);

        assertThat(v1.toString()).isEqualTo(v1.toExpression()).isEqualTo("x");
        assertThat(v2.toString()).isEqualTo(v2.toExpression()).isEqualTo("x1");
        assertThat(v3.toString()).isEqualTo(v3.toExpression()).isEqualTo("x#init");
        assertThat(v4.toString()).isEqualTo(v4.toExpression()).isEqualTo("x#init1");
    }

}
