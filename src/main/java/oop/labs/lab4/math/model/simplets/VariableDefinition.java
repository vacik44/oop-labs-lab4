package oop.labs.lab4.math.model.simplets;

import oop.labs.lab4.math.model.MathExpressionable;
import oop.labs.lab4.math.model.MathObject;

@SuppressWarnings("unused")
public interface VariableDefinition extends MathObject, MathExpressionable
{
    String name();
    Integer index();
    String subName();
}
