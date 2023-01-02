package oop.labs.lab4.math.model.enums;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("significanceType")
public enum SignificanceType
{
    NEGATIVE,
    POSITIVE,
    UNDEFINED
}
