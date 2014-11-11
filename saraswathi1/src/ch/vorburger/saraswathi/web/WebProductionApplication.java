package ch.vorburger.saraswathi.web;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

import ch.vorburger.saraswathi.config.SaraswathiProductionConfiguration;

@Import({ SaraswathiProductionConfiguration.class })
public class WebProductionApplication extends AbstractWebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebProductionApplication.class, args);
	}

}
