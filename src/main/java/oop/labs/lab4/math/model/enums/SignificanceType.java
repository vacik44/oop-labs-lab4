package oop.labs.lab4.math.model.enums;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("significanceType")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public enum SignificanceType
{
    NEGATIVE,
    POSITIVE,
    UNDEFINED
}
