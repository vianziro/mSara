package ch.vorburger.saraswathi.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ch.vorburger.saraswathi.tests.config.WebTestApplication;
import ch.vorburger.saraswathi.web.IncomingRestController;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest({ "server.port=0", "management.port=0" })
@SpringApplicationConfiguration(classes = WebTestApplication.class)
public class IncomingRestControllerIntegrationTest {

	@Value("${local.server.port}")
	int port;

	@Test
	public void testTwilio() throws Exception {
		assertPOST(baseURL() + IncomingRestController.TwilioPath,
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>yo hello, world</Message></Response>",
				"From", "+911234567890",
				"To", "+910987654321",
				"Body", "hello, world");
	}

	@Test
	public void testUshahidiSMSSync() throws Exception {
		assertPOST(baseURL() + IncomingRestController.UshahidiPath,
				"{\"payload\":{\"success\":true,\"task\":\"send\",\"messages\":[{\"to\":\"+911234567890\",\"message\":\"yo hello, world\"}]}}",
				"from", "+911234567890",
				"message", "hello, world",
				"message_id", "kawne9723bvn27",
				"sent_to", "+910987654321",
				"secret", "abracadabra",
				"device_id", "gatewayPhone1",
				"sent_timestamp", "exampleOfUnixTS");		
	}

	private void assertPOST(String url, String expected, String... paramPairs) {
		RestTemplate restTemplate = new TestRestTemplate(); // NOT just RestTemplate
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>(7); // NOT just Map<String, String>
		for (int i = 0; i < paramPairs.length; i+=2) {
			String key = paramPairs[i];
			String value = paramPairs[i+1];
			map.add(key, value);
		}
		ResponseEntity<String> r = restTemplate.postForEntity(url, map, String.class);
		assertEquals(HttpStatus.OK, r.getStatusCode());
		assertEquals(expected, r.getBody());
	}

	protected String baseURL() {
		return "http://localhost:" + port + "/";
	}
}
