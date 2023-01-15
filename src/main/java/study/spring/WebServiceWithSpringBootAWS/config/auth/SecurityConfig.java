package study.spring.WebServiceWithSpringBootAWS.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import study.spring.WebServiceWithSpringBootAWS.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                // antMatchers 로 권한 관리 대상 지정
                // "/" 등 지정한 URL들은 permitAll() 로 전체 열람 권한
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 열람 가능하도록
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외 나머지 URL들은 모두 인증된 사용자들에게만 허용. 즉, 로그인한 사용자들만 가능
                .anyRequest().authenticated()
                .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점.
                .logout()
                // 로그아웃 성공 시 "/" 주소로 이동
                .logoutSuccessUrl("/")
                .and()
                // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // OAUth2 로그인 성공 이후 사용자 정보 가져올 때 설정 담당
                .userInfoEndpoint()
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
                .userService(customOAuth2UserService);
    }
}
