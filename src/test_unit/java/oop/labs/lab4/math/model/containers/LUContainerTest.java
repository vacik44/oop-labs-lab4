package oop.labs.lab4.math.model.containers;

import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.math.model.matrix.NumMatrixMutable;
import org.junit.Test;

import java.math.BigDecimal;

import static oop.labs.lab4.assertion.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static oop.labs.lab4.assertion.CustomHelpers.num;

public class LUContainerTest
{
    @Test
    public void testIsImmutable()
    {
        assertThat(new LU(null, null)).isDefinedAsImmutable();
    }

    @Test
    public void testEqualsHashCodeEquivalent()
    {
        var content1 = new BigDecimal[][]{{num(1), num(33)}, {num(0), num(1)}};
        var content2 = new BigDecimal[][]{{num(15), num(0)}, {num(22), num(34)}};
        var content3 = new BigDecimal[][]{{num(27), num(0)}, {num(22), num(34)}};

        var matrix1 = new NumMatrixMutable(content1);
        var matrix2 = new NumMatrixImmutable(content1);
        var matrix3 = new NumMatrixImmutable(content2);
        var matrix4 = new NumMatrixImmutable(content3);

        var lu1 = new LU(matrix3, matrix1);
        var lu2 = new LU(matrix3, matrix1);
        var lu3 = new LU(matrix3, matrix2);
        var lu4 = new LU(matrix4, matrix1);

        assertThat(lu1).isEquivalentOf(lu2).isEquivalentOf(lu3).isNotEquivalentOf(lu4);
        assertThat(lu3).isNotEquivalentOf(lu4);

        assertThat(lu1).isEqualTo(lu2).hasSameHashCodeAs(lu2).isNotEqualTo(lu3).hasSameHashCodeAs(lu3).isNotEqualTo(lu4).doesNotHaveSameHashCodeAs(lu4);
        assertThat(lu2).isNotEqualTo(lu3).hasSameHashCodeAs(lu2).isNotEqualTo(lu4).doesNotHaveSameHashCodeAs(lu4);
        assertThat(lu3).isNotEqualTo(lu4).doesNotHaveSameHashCodeAs(lu4);
    }

    @Test
    public void testGetters()
    {
        var lower = new NumMatrixMutable(new BigDecimal[][]{{num(15), num(0)}, {num(22), num(34)}});
        var upper = new NumMatrixImmutable(new BigDecimal[][]{{num(1), num(33)}, {num(0), num(1)}});

        var lu = new LU(lower, upper);

        assertThat(lu.lower()).isEqualTo(lower).hasSameHashCodeAs(lower);
        assertThat(lu.upper()).isEqualTo(upper).hasSameHashCodeAs(upper);
    }

    @Test
    public void testComputeOriginDet()
    {
        var lower = new NumMatrixMutable(new BigDecimal[][]{{num(15), num(0)}, {num(22), num(34)}});
        var upper = new NumMatrixImmutable(new BigDecimal[][]{{num(1), num(33)}, {num(0), num(1)}});

        var lu = new LU(lower, upper);

        assertThat(lu.computeOriginDet()).isEqualTo(num(510));
    }
}
