package jeongkong.step3;

import org.springframework.stereotype.Component;

// Tire 구현체 1
// @Component 덕분에 Spring이 스캔 대상으로 인식합니다.
@Component
public class HankookTire implements Tire {

    @Override
    public String getBrand() {
        return "한국타이어";
    }
}
