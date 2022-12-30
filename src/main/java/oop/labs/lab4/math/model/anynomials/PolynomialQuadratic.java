package oop.labs.lab4.math.model.anynomials;

import oop.labs.lab4.math.model.matrix.MatrixNumeric;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public interface PolynomialQuadratic extends Polynomial
{
    MatrixNumeric odds();
    BigDecimal oddOf(int index1, int index2);
}
