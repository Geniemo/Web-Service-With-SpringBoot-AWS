package study.spring.WebServiceWithSpringBootAWS.config.auth.dto;

import lombok.Getter;
import study.spring.WebServiceWithSpringBootAWS.domain.user.User;

import java.io.Serializable;

/*
굳이 User 클래스 말고 SessionUser 클래스를 만들어서 사용하는 이유

세션에 저장하려면 Serializable을 구현해야 한다.
그런데 엔티티인 User 클래스에 이를 구현하게 된다면
다른 엔티티와 관계가 형성되었을 때 직렬화 대상에 자식들까지 포함되니 성능 이슈, 부수 효과가 발생할 확률이 높다.

그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 시 많은 도움이 된다.
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
