package oop.labs.lab4.service.math.model.simplets;

import oop.labs.lab4.service.math.model.MathExpressionable;
import oop.labs.lab4.service.math.model.MathObject;

public interface VariableDefinition extends MathObject, MathExpressionable
{
    String getName();
    Integer getIndex();
    String getSubName();
}
