package oop.labs.lab4.asserts;

import oop.labs.lab4.math.model.matrix.Matrix;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixAssert<TElement> extends MathObjectAssert<Matrix<TElement>>
{
    TElement[][] actualContent;
    Class<TElement> actualContentType;


    @SuppressWarnings("unchecked")
    private static <T> T[][] extractContent(Matrix<T> matrix, Class<T> type)
    {
        var content = (T[][]) Array.newInstance(type, matrix.rows(), matrix.cols());

        for (var i = 0; i < matrix.rows(); i++)
            for (var j = 0; j < matrix.cols(); j++)
                content[i][j] = matrix.get(i + 1, j + 1);

        return content;
    }


    public MatrixAssert(Matrix<TElement> actual, Class<TElement> type)
    {
        super(actual);

        actualContent = extractContent(actual, type);
        actualContentType = type;
    }

    public MatrixAssert(AtomicReference<Matrix<TElement>> actual, Class<TElement> type)
    {
        super(actual);

        actualContent = extractContent(actual.get(), type);
        actualContentType = type;
    }


    public MatrixAssert<TElement> hasContent(TElement[][] expectedContent)
    {
        assertThat(actualContent).isDeepEqualTo(expectedContent);

        return this;
    }

    public MatrixAssert<TElement> hasSameContentAs(Matrix<TElement> expected)
    {
        assertThat(actualContent).isDeepEqualTo(extractContent(expected, actualContentType));

        return this;
    }


    public MatrixAssert<TElement> hasSize(int rows, int cols)
    {
        var actualSize = actual.size();

        assertThat(actualSize).containsExactly(rows, cols);
        assertThat(actual.rows()).isEqualTo(actualSize[0]);
        assertThat(actual.cols()).isEqualTo(actualSize[1]);

        return this;
    }

    public MatrixAssert<TElement> hasSameSizeAs(Matrix<TElement> expected) {
        var actualSize = actual.size();

        assertThat(actualSize).containsExactly(expected.size());
        assertThat(actual.rows()).isEqualTo(actualSize[0]).isEqualTo(expected.rows());
        assertThat(actual.cols()).isEqualTo(actualSize[1]).isEqualTo(expected.cols());

        return this;
    }


    public MatrixAssert<TElement> isSameMatrixAs(TElement[][] expectedContent)
    {
        return hasSize(expectedContent.length, expectedContent.length == 0 ? 0 : expectedContent[0].length).hasContent(expectedContent);
    }

    public MatrixAssert<TElement> isSameMatrixAs(Matrix<TElement> expected)
    {
        return hasSameSizeAs(expected).hasSameContentAs(expected);
    }


    @SuppressWarnings("unchecked")
    public MatrixAssert<TElement> isEmpty()
    {
        return hasSize(0, 0).hasContent((TElement[][]) Array.newInstance(actualContentType, 0, 0));
    }
}
