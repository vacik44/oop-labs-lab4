package oop.labs.lab4;

import oop.labs.lab4.service.math.model.anynomials.PMononomial;
import oop.labs.lab4.service.math.model.anynomials.PMononomialOdded;
import oop.labs.lab4.service.math.model.anynomials.PPolynomial;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class MathSolverServiceTests {

	public static void main(String[] args) throws ParseException
	{
		var variable = PPolynomial.parse("7x1x5+9x2x3");
		System.out.println(variable);
	}

	@Test
	void contextLoads()
	{
	}

}
