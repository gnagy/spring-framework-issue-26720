package hu.webhejj.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@AutoConfigureMockMvc
class SpringFrameworkIssue26720ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void error() {
		var restTemplateBuilder =  new RestTemplateBuilder()
				.requestFactory(() -> new MockMvcClientHttpRequestFactory(mockMvc));
		doTest(restTemplateBuilder);
	}

	@Test
	void success() {
		var restTemplateBuilder =  new RestTemplateBuilder()
				.requestFactory(() -> new FixedMockMvcClientHttpRequestFactory(mockMvc));
		doTest(restTemplateBuilder);
	}


	private void doTest(RestTemplateBuilder restTemplateBuilder) {
		var restTemplate = restTemplateBuilder.build();

		var request = RequestEntity.get("/test").build();

		var clientException = assertThrows(HttpClientErrorException.class, () -> {
			restTemplate.exchange(request, String.class);
		});
		var error = clientException.getResponseBodyAsString();

		assertEquals("error", error);
	}
}
