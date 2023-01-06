package oop.labs.lab4;

import oop.labs.lab4.api.LoadRequestOptions;
import oop.labs.lab4.math.eval.EvalCondition;
import oop.labs.lab4.math.eval.EvalResults;
import oop.labs.lab4.math.eval.SolutionNode;
import oop.labs.lab4.math.model.anynomials.PPolynomial;
import oop.labs.lab4.math.model.enums.SignificanceType;
import oop.labs.lab4.service.providers.SerializationProvider;
import oop.labs.lab4.service.providers.StorageProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
public class ApplicationIntegrationTests
{
	@Autowired private MockMvc mvc;

	@Autowired private SerializationProvider serializationProvider;
	@Autowired private StorageProvider storageProvider;

	@Test
	void contextLoads()
	{
		assertThat(mvc).isNotNull();

		assertThat(serializationProvider).isNotNull();
		assertThat(storageProvider).isNotNull();
	}

	@BeforeEach
	void clearRepository()
	{
		storageProvider.clearValues();
	}

	@Test
	void testCase1() throws Exception
	{
		mvc.perform(post("/calculations/quadratic-form-significance").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testCase2() throws Exception
	{
		var payload = serializationProvider.mapper().writeValueAsString(new EvalCondition("Hello, world!"));

		mvc.perform(post("/calculations/unknown").contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isNotFound());
	}



	@Test
	void testCase3() throws Exception
	{
		var payload = "incorrect payload format";

		mvc.perform(post("/calculations/quadratic-form-significance").contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isBadRequest());
	}

	@Test
	void testCase4() throws Exception
	{
		var condition = new EvalCondition(PPolynomial.parse("-11x1^2x3-6x2^2-6x3^2+12x1x2-12x1x3+6x2x3"));
		var payload = serializationProvider.mapper().writeValueAsString(condition);

		mvc.perform(post("/calculations/quadratic-form-significance").contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isConflict());
	}

	@Test
	void testCase5() throws Exception
	{
		var calculationId = BigInteger.valueOf(666);
		var payload = "incorrect payload format";

		mvc.perform(get(String.format("/calculations/repository/%s", calculationId)).contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isBadRequest());
	}

	@Test
	void testCase6() throws Exception
	{
		var calculationId = BigInteger.valueOf(666);
		var payload = serializationProvider.mapper().writeValueAsString(LoadRequestOptions.REQUEST_RESULT_ONLY);

		mvc.perform(get(String.format("/calculations/repository/%s", calculationId)).contentType(MediaType.APPLICATION_JSON)
				.content(payload)).andExpect(status().isNotFound());
	}

	@Test
	void testCase7() throws Exception
	{
		var condition = new EvalCondition(PPolynomial.parse("-11x1^2-6x2^2-6x3^2+12x1x2-12x1x3+6x2x3"));
		var answer = SignificanceType.NEGATIVE;

		var submitCalculationPayload = serializationProvider.mapper().writeValueAsString(condition);
		var calculationId = new BigInteger(mvc.perform(post("/calculations/quadratic-form-significance")
						.contentType(MediaType.APPLICATION_JSON).content(submitCalculationPayload))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString());

		var loadCalculationResultOnlyPayload = serializationProvider.mapper().writeValueAsString(LoadRequestOptions.REQUEST_RESULT_ONLY);
		var calculationResultsOnly = serializationProvider.mapper().readValue(
				mvc.perform(get(String.format("/calculations/repository/%s", calculationId))
								.contentType(MediaType.APPLICATION_JSON).content(loadCalculationResultOnlyPayload))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
				SignificanceType.class);

		var loadCalculationResultSolutionPayload = serializationProvider.mapper().writeValueAsString(LoadRequestOptions.REQUEST_RESULT_SOLUTION);
		var calculationResultsSolution = serializationProvider.mapper().readValue(
				mvc.perform(get(String.format("/calculations/repository/%s", calculationId))
								.contentType(MediaType.APPLICATION_JSON).content(loadCalculationResultSolutionPayload))
						.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
				EvalResults.class);

		assertThat(calculationResultsSolution.result()).isEqualTo(calculationResultsOnly).isEqualTo(answer);
		assertThat(calculationResultsSolution.solution()).isInstanceOf(SolutionNode.class);
	}
}
