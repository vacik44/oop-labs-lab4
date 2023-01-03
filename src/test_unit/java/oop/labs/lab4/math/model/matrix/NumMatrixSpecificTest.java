package oop.labs.lab4.math.model.matrix;

import org.junit.Test;
import org.assertj.core.api.Assertions;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static oop.labs.lab4.asserts.CustomAssertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import static oop.labs.lab4.asserts.CustomHelpers.num;

public class NumMatrixSpecificTest
{
    @Test
    public void testDiag()
    {
        var content = new BigDecimal[][] {{num(1), num(2), num(3)}, {num(4), num(5), num(6)}, {num(7), num(8), num(9)}};
        var diag = new BigDecimal[] {num(1), num(5), num(9)};

        var matrixImmutable = new NumMatrixImmutable(content);
        var matrixMutable = new NumMatrixMutable(content);

        assertThat(matrixImmutable.diag()).isEqualTo(matrixMutable.diag()).isEqualTo(diag);
    }

    @Test
    public void testRounding()
    {
        var rawContent = new BigDecimal[][] {{num(2.345), num(3.344)}, {num(15.664), num(-3.012)}};
        var roundedContent = new BigDecimal[][] {{num(2.35), num(3.34)}, {num(15.7), num(-3.01)}};
        var mc = new MathContext(3, RoundingMode.HALF_UP);

        var immutableMatrix = new NumMatrixImmutable(rawContent);
        var mutableMatrix = new NumMatrixMutable(rawContent);

        var roundedMutableMatrix = mutableMatrix.rounded(mc);
        var roundedImmutableMatrix = immutableMatrix.rounded(mc);

        assertThat(roundedMutableMatrix).isSameMatrixAs(roundedContent).isEquivalentOf(roundedImmutableMatrix).isNotEquivalentOf(mutableMatrix);
        assertThat(immutableMatrix.rounded(mc.getPrecision(), mc.getRoundingMode())).isSameMatrixAs(roundedImmutableMatrix);

        var matrix5 = new NumMatrixMutable(rawContent);
        assertThat(mutableMatrix).isSameMatrixAs(rawContent).isEqualTo(matrix5);

        mutableMatrix.round(mc);
        matrix5.round(mc.getPrecision(), mc.getRoundingMode());
        assertThat(mutableMatrix).isSameMatrixAs(roundedContent).isEqualTo(matrix5);

        Assertions.assertThat(catchThrowable(() -> immutableMatrix.round(mc))).isInstanceOf(UnsupportedOperationException.class);
    }
}
