package oop.labs.lab4.math.model.matrix;

import oop.labs.lab4.assertion.CustomAssertions;
import oop.labs.lab4.assertion.CustomHelpers;
import org.junit.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NumMatrixBaseTest
{
    @Test
    public void testEqualsHashCodeEquivalent()
    {
        var content1 = new BigDecimal[][] {{CustomHelpers.num(1), CustomHelpers.num(2)}, {CustomHelpers.num(3), CustomHelpers.num(4)}};
        var content2 = new BigDecimal[][] {{CustomHelpers.num(5), CustomHelpers.num(6)}, {CustomHelpers.num(7), CustomHelpers.num(8)}};

        var m1 = new NumMatrixMutable(content1);
        var m2 = new NumMatrixMutable(content2);

        var i1 = new NumMatrixImmutable(content1);
        var i2 = new NumMatrixImmutable(content2);


        CustomAssertions.assertThat(new NumMatrixMutable(content1)).isEquivalentOf(m1).isNotEquivalentOf(m2).isEquivalentOf(i1).isNotEquivalentOf(i2);
        CustomAssertions.assertThat(new NumMatrixImmutable(content1)).isEquivalentOf(i1).isNotEquivalentOf(i2);


        CustomAssertions.assertThat(new NumMatrixMutable(content1))
                .isEqualTo(m1).hasSameHashCodeAs(m1)
                .isNotEqualTo(m2).doesNotHaveSameHashCodeAs(m2)
                .isNotEqualTo(i1).hasSameHashCodeAs(i1)
                .isNotEqualTo(i2).doesNotHaveSameHashCodeAs(i2);

        CustomAssertions.assertThat(new NumMatrixImmutable(content1))
                .isEqualTo(i1).hasSameHashCodeAs(i1)
                .isNotEqualTo(i2).doesNotHaveSameHashCodeAs(i2);
    }

    @Test
    public void testToString()
    {
        var content = new BigDecimal[][] {{CustomHelpers.num(1), CustomHelpers.num(2)}, {CustomHelpers.num(3), CustomHelpers.num(4)}};

        assertThat(new NumMatrixImmutable(content).toString()).isEqualTo("[[1, 2], [3, 4]]");
    }

    @Test
    public void testIsSquare()
    {
        var squareContent = new BigDecimal[][] {{null, null}, {null, null}};
        var nonSquareContent = new BigDecimal[][] {{null, null}, {null, null}, {null, null}};

        assertThat(new NumMatrixMutable(squareContent).isSquare()).isEqualTo(new NumMatrixImmutable(squareContent).isSquare()).isTrue();
        assertThat(new NumMatrixMutable(nonSquareContent).isSquare()).isEqualTo(new NumMatrixImmutable(nonSquareContent).isSquare()).isFalse();
    }
}
