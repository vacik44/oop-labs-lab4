package oop.labs.lab4;

import com.fasterxml.jackson.core.JsonProcessingException;
import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.model.anynomials.PPolynomial;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.service.providers.CalculationsProvider;
import oop.labs.lab4.service.providers.SerializationProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;

@SpringBootTest
class ApplicationTests
{
	@Autowired private SerializationProvider serializationProvider;
	@Autowired private CalculationsProvider calculationsProvider;

	@Test
	void manualTest() throws JsonProcessingException, ParseException
	{
		var unsered = PPolynomial.parse("2x1x2+3x1+4x2");


		var sered = serializationProvider.mapper().writeValueAsString(unsered);
		var desered = serializationProvider.mapper().readValue(sered, PPolynomial.class);
		var resered = serializationProvider.mapper().writeValueAsString(desered);

		System.out.println(unsered);
		System.out.println(sered);
		System.out.println(desered);
		System.out.println(resered);
	}

	@Test
	void manualTest2() throws JsonProcessingException, ParseException
	{
		var task = new NumMatrixImmutable(new BigDecimal[][] {{BigDecimal.valueOf(15), BigDecimal.valueOf(34)},
				                                              {BigDecimal.valueOf(32), BigDecimal.valueOf(41)}});

		var result = calculationsProvider.submitCalculation("matrix-determinant-solver", new EvalCondition(task));
		System.out.println(result.result());
	}


	@Test
	void contextLoads()
	{

	}
}
