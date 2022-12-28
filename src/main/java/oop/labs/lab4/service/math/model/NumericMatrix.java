package oop.labs.lab4.service.math.model;

import java.math.BigDecimal;

public interface NumericMatrix extends Matrix<BigDecimal>
{
    NumericMatrix dot(NumericMatrix other);
}
