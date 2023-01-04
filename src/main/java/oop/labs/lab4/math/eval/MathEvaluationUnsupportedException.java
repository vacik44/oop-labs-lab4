package oop.labs.lab4.math.eval;

import oop.labs.lab4.math.model.anynomials.Polynomial;
import oop.labs.lab4.math.model.matrix.Matrix;

@SuppressWarnings("unused")
public class MathEvaluationUnsupportedException extends MathEvaluationException
{
    public MathEvaluationUnsupportedException() {}
    public MathEvaluationUnsupportedException(String msg) { super(msg); }
    public MathEvaluationUnsupportedException(Throwable cause) { super(cause); }


    public static <TMatrix extends Matrix<TElements>, TElements> TMatrix throwIfNotSquare(TMatrix matrix)
    {
        return throwIfNotSquare(matrix, "Operation supported for square matrices only");
    }
    public static <TMatrix extends Matrix<TElements>, TElements> TMatrix throwIfNotSquare(TMatrix matrix, String message)
    {
        if (!matrix.isSquare()) throw new UnsupportedOperationException(message);
        return matrix;
    }

    public static <TPolynomial extends Polynomial> TPolynomial throwIfNotOfExactPower(TPolynomial polynomial, int power)
    {
        return throwIfNotOfExactPower(polynomial, power, String.format("Operation supported only for polynomials with exact power of %d", power));
    }
    public static <TPolynomial extends Polynomial> TPolynomial throwIfNotOfExactPower(TPolynomial polynomial, int power, String message)
    {
        if (polynomial.mononomials().stream().anyMatch(mononomial -> mononomial.power() != power)) throw new MathEvaluationUnsupportedException(message);
        return polynomial;
    }
}
