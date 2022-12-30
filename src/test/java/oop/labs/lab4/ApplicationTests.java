package oop.labs.lab4;

import oop.labs.lab4.math.model.anynomials.PPolynomial;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class ApplicationTests
{
	public static void main(String[] args) throws ParseException
	{
		var variable = PPolynomial.parse("7x1x5^2+9x2^2x3");
		System.out.println(variable);
	}

	@Test
	void contextLoads()
	{

	}
}
