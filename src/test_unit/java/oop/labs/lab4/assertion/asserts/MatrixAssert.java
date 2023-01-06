package oop.labs.lab4.assertion.asserts;

import oop.labs.lab4.math.model.matrix.Matrix;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MatrixAssert<TMatrix extends Matrix<TElement>, TElement> extends MathObjectAssert<TMatrix>
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


    public MatrixAssert(TMatrix actual, Class<TElement> type)
    {
        super(actual);

        actualContent = extractContent(actual, type);
        actualContentType = type;
    }

    public MatrixAssert(AtomicReference<TMatrix> actual, Class<TElement> type)
    {
        super(actual);

        actualContent = extractContent(actual.get(), type);
        actualContentType = type;
    }


    public MatrixAssert<TMatrix, TElement> hasContent(TElement[][] expectedContent)
    {
        assertThat(actualContent).isDeepEqualTo(expectedContent);

        return this;
    }

    public MatrixAssert<TMatrix, TElement> hasSameContentAs(Matrix<TElement> expected)
    {
        assertThat(actualContent).isDeepEqualTo(extractContent(expected, actualContentType));

        return this;
    }


    public MatrixAssert<TMatrix, TElement> hasSize(int rows, int cols)
    {
        var actualSize = actual.size();

        assertThat(actualSize).containsExactly(rows, cols);
        assertThat(actual.rows()).isEqualTo(actualSize[0]);
        assertThat(actual.cols()).isEqualTo(actualSize[1]);

        return this;
    }

    public MatrixAssert<TMatrix, TElement> hasSameSizeAs(Matrix<TElement> expected) {
        var actualSize = actual.size();

        assertThat(actualSize).containsExactly(expected.size());
        assertThat(actual.rows()).isEqualTo(actualSize[0]).isEqualTo(expected.rows());
        assertThat(actual.cols()).isEqualTo(actualSize[1]).isEqualTo(expected.cols());

        return this;
    }


    public MatrixAssert<TMatrix, TElement> isSameMatrixAs(TElement[][] expectedContent)
    {
        return hasSize(expectedContent.length, expectedContent.length == 0 ? 0 : expectedContent[0].length).hasContent(expectedContent);
    }

    public MatrixAssert<TMatrix, TElement> isSameMatrixAs(Matrix<TElement> expected)
    {
        return hasSameSizeAs(expected).hasSameContentAs(expected);
    }


    @SuppressWarnings("unchecked")
    public MatrixAssert<TMatrix, TElement> isEmpty()
    {
        return hasSize(0, 0).hasContent((TElement[][]) Array.newInstance(actualContentType, 0, 0));
    }
}
