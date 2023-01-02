package oop.labs.lab4;

import com.fasterxml.jackson.core.JsonProcessingException;
import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.model.anynomials.PPolynomial;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.service.providers.EvaluationProvider;
import oop.labs.lab4.service.providers.SerializationProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;

@SpringBootTest
class ApplicationTests
{
	@Autowired private SerializationProvider serializationProvider;
	@Autowired private EvaluationProvider evaluationProvider;

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
	void manualTest2() throws JsonProcessingException
	{
		var task = new NumMatrixImmutable(new BigDecimal[][] {{BigDecimal.valueOf(15), BigDecimal.valueOf(34)},
				                                              {BigDecimal.valueOf(32), BigDecimal.valueOf(41)}});

		var result = evaluationProvider.evaluate("matrix-determinant", new EvalCondition(task));
		System.out.println(result.result());
		System.out.println(serializationProvider.mapper().writeValueAsString(result.solution()));
	}

	@Test
	void manualTest3() throws JsonProcessingException, ParseException
	{
		var task = PPolynomial.parse("2x1^2+4x1x2+3x2^2");
		var condition = new EvalCondition(task);
		var results = evaluationProvider.evaluate("quadratic-form-significance", condition);
		System.out.println(results.result());
		System.out.println(serializationProvider.mapper().writeValueAsString(results.solution()));
		System.out.println(serializationProvider.mapper().writeValueAsString(results));
		System.out.println("Complete.");
	}


	@Test
	void contextLoads()
	{

	}
}
