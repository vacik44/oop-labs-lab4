package oop.labs.lab4.service.math.model;

import java.math.BigDecimal;

public interface MatrixNumeric extends Matrix<BigDecimal>
{
    MatrixNumeric dot(MatrixNumeric other);
}
