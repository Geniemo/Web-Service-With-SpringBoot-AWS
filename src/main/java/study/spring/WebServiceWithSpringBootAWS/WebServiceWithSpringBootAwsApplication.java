package study.spring.WebServiceWithSpringBootAWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebServiceWithSpringBootAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceWithSpringBootAwsApplication.class, args);
	}

}
