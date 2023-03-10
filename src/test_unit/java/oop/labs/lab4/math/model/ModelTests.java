package oop.labs.lab4.math.model;

import oop.labs.lab4.math.model.anynomials.AnynomialsModelTests;
import oop.labs.lab4.math.model.containers.ContainersModelTests;
import oop.labs.lab4.math.model.matrix.MatrixModelTests;
import oop.labs.lab4.math.model.simplets.SimpletsModelTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MatrixModelTests.class,
        SimpletsModelTests.class,
        ContainersModelTests.class,
        AnynomialsModelTests.class
})
public class ModelTests {}
