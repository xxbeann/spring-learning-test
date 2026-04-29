package jeongkong.step2_spring_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // step2_spring_config
    // 수동 조립용 AppConfig를 Spring 설정 클래스로 올린 버전입니다.
    @Bean
    public Tire tire() {
        return new HankookTire();
    }

    @Bean
    public Car car() {
        return new Car(tire());
    }
}
