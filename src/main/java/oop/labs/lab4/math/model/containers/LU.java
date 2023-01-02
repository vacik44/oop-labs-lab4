package oop.labs.lab4.math.model.containers;

import com.fasterxml.jackson.annotation.*;
import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.model.matrix.MatrixNumeric;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@JsonTypeName("decompositionLU")
@JsonIncludeProperties({"L", "U"})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public final class LU implements MathObject
{
    @Override public boolean isImmutable() { return true; }


    @JsonProperty("L")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
    private final MatrixNumeric lower;
    @JsonProperty("U")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
    private final MatrixNumeric upper;


    @Override
    public boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LU lu = (LU) o;
        return lower.equivalent(lu.lower) && upper.equivalent(lu.upper);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LU lu = (LU) o;
        return lower.equals(lu.lower) && upper.equals(lu.upper);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(lower, upper);
    }


    @JsonCreator public LU(@JsonProperty("L") MatrixNumeric lower, @JsonProperty("U") MatrixNumeric upper)
    {
        this.lower = lower;
        this.upper = upper;
    }


    public MatrixNumeric lower() { return lower; }
    public MatrixNumeric upper() { return upper; }


    public BigDecimal computeOriginDet() { return Arrays.stream(lower.diag()).reduce(BigDecimal.ONE, BigDecimal::multiply); }
}
