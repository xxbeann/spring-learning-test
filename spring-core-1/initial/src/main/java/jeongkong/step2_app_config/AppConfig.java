package jeongkong.step2_app_config;

public class AppConfig {
    // AppConfig가 객체 생성과 연결을 전담합니다.
    // 구현체를 바꿔도 Car는 수정되지 않습니다.
    public Tire tire() {
        return new HankookTire();
    }

    public Car car() {
        return new Car(tire());
    }
}
