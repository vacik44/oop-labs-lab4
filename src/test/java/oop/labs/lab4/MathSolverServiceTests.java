package oop.labs.lab4;

import oop.labs.lab4.service.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.service.math.model.anynomials.QPolynomialImmuatble;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MathSolverServiceTests {

	public static void main(String[] args)
	{
		var matrix = NumMatrixImmutable.eye(3);
		var polynomial = new QPolynomialImmuatble(matrix);

		System.out.println(polynomial.getExpression());

		var num = new BigDecimal(2);
	}

	@Test
	void contextLoads() {
	}

}
