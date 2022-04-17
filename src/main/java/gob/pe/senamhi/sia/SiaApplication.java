package gob.pe.senamhi.sia;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SiaApplication {
	private static final Logger LOGGER = LogManager.getLogger(SiaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SiaApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		try {
			ResourceBundle rs = ResourceBundle.getBundle("config");
			Integer connect = Integer.parseInt(rs.getString("time.connect"));
			Integer read = Integer.parseInt(rs.getString("time.read"));
			return builder.setConnectTimeout(Duration.ofMillis(connect)).setReadTimeout(Duration.ofMillis(read)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

}
