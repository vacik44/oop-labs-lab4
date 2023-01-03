package oop.labs.lab4.math.model.matrix;

import java.util.List;
import java.math.BigDecimal;

import oop.labs.lab4.asserts.CustomAssertions;
import oop.labs.lab4.asserts.CustomHelpers;
import org.junit.Test;

import static oop.labs.lab4.asserts.CustomHelpers.num;
import static oop.labs.lab4.asserts.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class NumMatrixImmutableTest
{
    @Test
    public void testIsImmutable()
    {
        assertThat(new NumMatrixImmutable(1, 1, null).isImmutable()).isTrue();
    }

    @Test
    public void testConstructors()
    {
        CustomAssertions.assertThat(new NumMatrixImmutable()).isEmpty();
        CustomAssertions.assertThat(new NumMatrixImmutable(2, 2, CustomHelpers.num(1))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(1), CustomHelpers.num(1)}, {CustomHelpers.num(1), CustomHelpers.num(1)}});
        CustomAssertions.assertThat(new NumMatrixImmutable(new BigDecimal[][] {{CustomHelpers.num(3), CustomHelpers.num(45)}, {CustomHelpers.num(123), CustomHelpers.num(34)}})).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(3), CustomHelpers.num(45)}, {CustomHelpers.num(123), CustomHelpers.num(34)}});
        CustomAssertions.assertThat(new NumMatrixImmutable(List.of(List.of(CustomHelpers.num(12), CustomHelpers.num(33), CustomHelpers.num(56)), List.of(CustomHelpers.num(22), CustomHelpers.num(9), CustomHelpers.num(0))))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(12), CustomHelpers.num(33), CustomHelpers.num(56)}, {CustomHelpers.num(22), CustomHelpers.num(9), CustomHelpers.num(0)}});
        CustomAssertions.assertThat(new NumMatrixImmutable(new NumMatrixImmutable(2, 3, CustomHelpers.num(7)))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(7), CustomHelpers.num(7), CustomHelpers.num(7)}, {CustomHelpers.num(7), CustomHelpers.num(7), CustomHelpers.num(7)}});
    }

    @Test
    public void testEye()
    {
        CustomAssertions.assertThat(NumMatrixImmutable.eye(5)).isSameMatrixAs(new BigDecimal[][]
                {
                        {CustomHelpers.num(1), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0)},
                        {CustomHelpers.num(0), CustomHelpers.num(1), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0)},
                        {CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(1), CustomHelpers.num(0), CustomHelpers.num(0)},
                        {CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(1), CustomHelpers.num(0)},
                        {CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(0), CustomHelpers.num(1)}
                });
    }

    @Test
    public void testAngularMinor()
    {
        var content = new BigDecimal[][] {{num(1), num(2), num(3)}, {num(4), num(5), num(6)}, {num(7), num(8), num(9)}};
        var matrix = new NumMatrixImmutable(content);

        assertThat(matrix.angularMinorMatrix(1)).isSameMatrixAs(new BigDecimal[][] {{num(1)}});
        assertThat(matrix.angularMinorMatrix(2)).isSameMatrixAs(new BigDecimal[][] {{num(1), num(2)}, {num(4), num(5)}});
        assertThat(matrix.angularMinorMatrix(3)).isSameMatrixAs(content).isNotSameAs(matrix);
    }

    @Test
    public void testAccessibility()
    {
        var matrix = new NumMatrixImmutable(new BigDecimal[][] {
                {  CustomHelpers.num(12),  CustomHelpers.num(34),   CustomHelpers.num(6)},
                {   CustomHelpers.num(3),  CustomHelpers.num(-7),  CustomHelpers.num(15)},
                {CustomHelpers.num(-888),  CustomHelpers.num(19),   CustomHelpers.num(0)}
        });

        assertThat(matrix.get(1, 1)).isEqualTo(CustomHelpers.num(12));
        assertThat(matrix.get(2, 2)).isEqualTo(CustomHelpers.num(-7));
        assertThat(matrix.get(3, 3)).isEqualTo(CustomHelpers.num(0));

        assertThat(matrix.row(1)).containsExactly(CustomHelpers.num(12), CustomHelpers.num(34), CustomHelpers.num(6));
        assertThat(matrix.row(2)).containsExactly( CustomHelpers.num(3), CustomHelpers.num(-7), CustomHelpers.num(15));
        assertThat(matrix.row(3)).containsExactly(CustomHelpers.num(-888), CustomHelpers.num(19), CustomHelpers.num(0));

        assertThat(matrix.col(1)).containsExactly(CustomHelpers.num(12), CustomHelpers.num(3), CustomHelpers.num(-888));
        assertThat(matrix.col(2)).containsExactly( CustomHelpers.num(34), CustomHelpers.num(-7), CustomHelpers.num(19));
        assertThat(matrix.col(3)).containsExactly(CustomHelpers.num(6), CustomHelpers.num(15), CustomHelpers.num(0));
    }

    @Test
    public void testImmutability()
    {
        var matrix = new NumMatrixImmutable(1, 1, CustomHelpers.num(1));

        assertThat(catchThrowable(() -> matrix.set(1, 1, CustomHelpers.num(2)))).isInstanceOf(UnsupportedOperationException.class);

        assertThat(catchThrowable(() -> matrix.fill(new BigDecimal[][]{{CustomHelpers.num(1)}}))).isInstanceOf(UnsupportedOperationException.class);
        assertThat(catchThrowable(() -> matrix.fill(List.of(List.of(CustomHelpers.num(1)))))).isInstanceOf(UnsupportedOperationException.class);

        assertThat(catchThrowable(() -> matrix.fillRow(1, new BigDecimal[] {CustomHelpers.num(2)}))).isInstanceOf(UnsupportedOperationException.class);
        assertThat(catchThrowable(() -> matrix.fillCol(1, new BigDecimal[] {CustomHelpers.num(2)}))).isInstanceOf(UnsupportedOperationException.class);

        assertThat(catchThrowable(() -> matrix.fillRow(1, List.of(CustomHelpers.num(2))))).isInstanceOf(UnsupportedOperationException.class);
        assertThat(catchThrowable(() -> matrix.fillCol(1, List.of(CustomHelpers.num(2))))).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testImmutableCast()
    {
        var mutable = new NumMatrixMutable(List.of(List.of(CustomHelpers.num(2), CustomHelpers.num(3), CustomHelpers.num(4))));
        var immutable = NumMatrixImmutable.immutable(mutable);

        CustomAssertions.assertThat(mutable).isSameMatrixAs(immutable).isNotEqualTo(immutable).hasSameHashCodeAs(immutable);
        assertThat(catchThrowable(() -> immutable.set(1, 2, CustomHelpers.num(18)))).isInstanceOf(UnsupportedOperationException.class);
    }
}
