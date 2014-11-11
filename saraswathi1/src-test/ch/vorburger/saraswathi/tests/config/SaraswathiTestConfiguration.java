package ch.vorburger.saraswathi.tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.saraswathi.Interactlet;
import ch.vorburger.saraswathi.config.SaraswathiProductionConfiguration;
import ch.vorburger.saraswathi.examples.EchoInteractlet;

/**
 * @see SaraswathiProductionConfiguration
 */
@Configuration
public class SaraswathiTestConfiguration {
	
	@Bean Interactlet interactlet() { return new EchoInteractlet(); }

}
