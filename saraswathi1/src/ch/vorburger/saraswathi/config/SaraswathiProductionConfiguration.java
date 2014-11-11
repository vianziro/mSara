package ch.vorburger.saraswathi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.vorburger.saraswathi.Interactlet;
import ch.vorburger.saraswathi.math.MathAdditionsLearnlet;

/**
 * @see ch.vorburger.saraswathi.tests.config.SaraswathiTestConfiguration
 */
@Configuration
public class SaraswathiProductionConfiguration {

	@Bean Interactlet interactlet() { return new MathAdditionsLearnlet(10); }
	
}
