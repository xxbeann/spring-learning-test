package jeongkong.step3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Tire 구현체 2
// Tire bean 후보가 둘이므로 @Primary로 기본 선택 대상을 지정합니다.
@Component
@Primary
public class KumhoTire implements Tire {
    @Override
    public String getBrand() {
        return "금호타이어";
    }
}
