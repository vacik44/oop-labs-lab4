package oop.labs.lab4.math.model.matrix;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NumMatrixBaseTest.class,
        NumMatrixMutableTest.class,
        NumMatrixImmutableTest.class,
        NumMatrixSpecificTest.class
})
public class MatrixModelTests {}
