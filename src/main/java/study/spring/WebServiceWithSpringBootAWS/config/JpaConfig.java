package study.spring.WebServiceWithSpringBootAWS.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
@EnableJpaAuditing을 사용하기 위해서는 최소 하나의 엔티티 클래스가 필요한데,
@WebMvcTest에서는 당연히 엔티티 클래스가 없다.
@EnableJpaAuditing이 @SpringBootApplication과 함께 있다보니 @WebMvcTest에서도 이를 스캔하게 되었다.
그래서 @SpringBootApplication과 분리.
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
