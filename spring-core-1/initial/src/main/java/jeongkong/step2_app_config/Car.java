package jeongkong.step2_app_config;

public class Car {
    // step2_app_config
    // Car는 여전히 Tire 인터페이스에만 의존합니다.
    // 달라진 점은 조립 책임이 main()이 아니라 AppConfig로 이동했다는 것입니다.
    private final Tire tire;

    // 생성자 주입: 필요한 의존 객체를 외부에서 전달받습니다.
    public Car(Tire tire) {
        this.tire = tire;
    }

    public void drive() {
        System.out.println(tire.getBrand() + " 장착하고 달립니다!");
    }
}
