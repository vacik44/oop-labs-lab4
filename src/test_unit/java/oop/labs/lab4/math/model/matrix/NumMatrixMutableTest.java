package oop.labs.lab4.math.model.matrix;

import oop.labs.lab4.assertion.CustomAssertions;
import oop.labs.lab4.assertion.CustomHelpers;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;

import static oop.labs.lab4.assertion.CustomHelpers.num;
import static org.assertj.core.api.Assertions.assertThat;
import static oop.labs.lab4.assertion.CustomAssertions.assertThat;

public class NumMatrixMutableTest
{
    @Test
    public void testIsImmutable()
    {
        assertThat(new NumMatrixMutable(1, 1, null)).isNotDefinedAsImmutable();
    }

    @Test
    public void testConstructors()
    {
        CustomAssertions.assertThat(new NumMatrixMutable()).isEmpty();
        CustomAssertions.assertThat(new NumMatrixMutable(2, 2, CustomHelpers.num(1))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(1), CustomHelpers.num(1)}, {CustomHelpers.num(1), CustomHelpers.num(1)}});
        CustomAssertions.assertThat(new NumMatrixMutable(new BigDecimal[][] {{CustomHelpers.num(3), CustomHelpers.num(45)}, {CustomHelpers.num(123), CustomHelpers.num(34)}})).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(3), CustomHelpers.num(45)}, {CustomHelpers.num(123), CustomHelpers.num(34)}});
        CustomAssertions.assertThat(new NumMatrixMutable(List.of(List.of(CustomHelpers.num(12), CustomHelpers.num(33), CustomHelpers.num(56)), List.of(CustomHelpers.num(22), CustomHelpers.num(9), CustomHelpers.num(0))))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(12), CustomHelpers.num(33), CustomHelpers.num(56)}, {CustomHelpers.num(22), CustomHelpers.num(9), CustomHelpers.num(0)}});
        CustomAssertions.assertThat(new NumMatrixMutable(new NumMatrixImmutable(2, 3, CustomHelpers.num(7)))).isSameMatrixAs(new BigDecimal[][] {{CustomHelpers.num(7), CustomHelpers.num(7), CustomHelpers.num(7)}, {CustomHelpers.num(7), CustomHelpers.num(7), CustomHelpers.num(7)}});
    }

    @Test
    public void testEye()
    {
        CustomAssertions.assertThat(NumMatrixMutable.eye(5)).isSameMatrixAs(new BigDecimal[][]
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
        var matrix = new NumMatrixMutable(content);

        CustomAssertions.assertThat(matrix.angularMinorMatrix(1)).isSameMatrixAs(new BigDecimal[][] {{num(1)}});
        CustomAssertions.assertThat(matrix.angularMinorMatrix(2)).isSameMatrixAs(new BigDecimal[][] {{num(1), num(2)}, {num(4), num(5)}});
        CustomAssertions.assertThat(matrix.angularMinorMatrix(3)).isSameMatrixAs(content).isNotSameAs(matrix);
    }

    @Test
    public void testAccessibility()
    {
        var matrix = new NumMatrixMutable(new BigDecimal[][] {
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
    public void testMutability()
    {
        var matrix = new NumMatrixMutable(3, 3, null);

        CustomAssertions.assertThat(matrix.fill(new BigDecimal[][]{{CustomHelpers.num(1), CustomHelpers.num(2), CustomHelpers.num(3)}, {CustomHelpers.num(3), CustomHelpers.num(4), CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), CustomHelpers.num(8)}})).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(1), CustomHelpers.num(2), CustomHelpers.num(3)}, {CustomHelpers.num(3), CustomHelpers.num(4), CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), CustomHelpers.num(8)}});

        CustomAssertions.assertThat(matrix.set(1, 1, null)).isSameMatrixAs(new BigDecimal[][]{{null, CustomHelpers.num(2), CustomHelpers.num(3)}, {CustomHelpers.num(3), CustomHelpers.num(4), CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), CustomHelpers.num(8)}});
        CustomAssertions.assertThat(matrix.set(2, 2, null)).isSameMatrixAs(new BigDecimal[][]{{null, CustomHelpers.num(2), CustomHelpers.num(3)}, {CustomHelpers.num(3), null, CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), CustomHelpers.num(8)}});
        CustomAssertions.assertThat(matrix.set(3, 3, null)).isSameMatrixAs(new BigDecimal[][]{{null, CustomHelpers.num(2), CustomHelpers.num(3)}, {CustomHelpers.num(3), null, CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), null}});

        CustomAssertions.assertThat(matrix.fillRow(1, List.of(CustomHelpers.num(0), CustomHelpers.num(-1), CustomHelpers.num(-10)))).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(0), CustomHelpers.num(-1), CustomHelpers.num(-10)}, {CustomHelpers.num(3), null, CustomHelpers.num(5)}, {CustomHelpers.num(6), CustomHelpers.num(7), null}});
        CustomAssertions.assertThat(matrix.fillRow(3, new BigDecimal[] {CustomHelpers.num(12), CustomHelpers.num(16), CustomHelpers.num(1)})).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(0), CustomHelpers.num(-1), CustomHelpers.num(-10)}, {CustomHelpers.num(3), null, CustomHelpers.num(5)}, {CustomHelpers.num(12), CustomHelpers.num(16), CustomHelpers.num(1)}});

        CustomAssertions.assertThat(matrix.fillCol(1, List.of(CustomHelpers.num(12), CustomHelpers.num(-12), CustomHelpers.num(0)))).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(12), CustomHelpers.num(-1), CustomHelpers.num(-10)}, {CustomHelpers.num(-12), null, CustomHelpers.num(5)}, {CustomHelpers.num(0), CustomHelpers.num(16), CustomHelpers.num(1)}});
        CustomAssertions.assertThat(matrix.fillCol(3, new BigDecimal[] {CustomHelpers.num(122), CustomHelpers.num(-1), CustomHelpers.num(-23)})).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(12), CustomHelpers.num(-1), CustomHelpers.num(122)}, {CustomHelpers.num(-12), null, CustomHelpers.num(-1)}, {CustomHelpers.num(0), CustomHelpers.num(16), CustomHelpers.num(-23)}});

        CustomAssertions.assertThat(matrix.fill(List.of(List.of(CustomHelpers.num(11), CustomHelpers.num(-12), CustomHelpers.num(13)), List.of(CustomHelpers.num(-14), CustomHelpers.num(15), CustomHelpers.num(-16)), List.of(CustomHelpers.num(17), CustomHelpers.num(-18), CustomHelpers.num(19))))).isSameMatrixAs(new BigDecimal[][]{{CustomHelpers.num(11), CustomHelpers.num(-12), CustomHelpers.num(13)}, {CustomHelpers.num(-14), CustomHelpers.num(15), CustomHelpers.num(-16)}, {CustomHelpers.num(17), CustomHelpers.num(-18), CustomHelpers.num(19)}});
    }
}
