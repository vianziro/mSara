package ch.vorburger.saraswathi.tests.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

import ch.vorburger.saraswathi.web.AbstractWebApplication;

@Import({ SaraswathiTestConfiguration.class })
public class WebTestApplication extends AbstractWebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebTestApplication.class, args);
	}

}
