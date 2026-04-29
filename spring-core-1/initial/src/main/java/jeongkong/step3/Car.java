package jeongkong.step3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    // step3
    // Car는 여전히 Tire 인터페이스에만 의존합니다.
    // 이제는 외부 조립 코드 대신 Spring 컨테이너가 구현체를 찾아 연결합니다.
    private final Tire tire;

    // 생성자 주입
    // Spring이 Car bean을 만들 때 알맞은 Tire bean을 자동으로 전달합니다.
    @Autowired
    public Car(Tire tire) {
        this.tire = tire;
    }

    public void drive() {
        System.out.println(tire.getBrand() + " 장착하고 달립니다!");
    }
}
