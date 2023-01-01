package oop.labs.lab4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.model.anynomials.PPolynomial;
import oop.labs.lab4.math.model.matrix.NumMatrixImmutable;
import oop.labs.lab4.service.providers.SerializationProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class ApplicationTests
{
	@Autowired private SerializationProvider serializationProvider;

	@Test
	void manualTest() throws JsonProcessingException
	{
		var node = new SolutionNode("Hello from test", NumMatrixImmutable.eye(12));

		var serd = serializationProvider.mapper().writeValueAsString(node);
		System.out.println(serd);

		var desd = serializationProvider.mapper().readValue(serd, SolutionNode.class);
		var ser2 = serializationProvider.mapper().writeValueAsString(desd);
		System.out.println(ser2);
	}


	@Test
	void contextLoads()
	{

	}
}
