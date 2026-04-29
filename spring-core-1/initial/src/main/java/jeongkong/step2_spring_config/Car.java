package jeongkong.step2_spring_config;

public class Car {
    // Car는 여전히 Tire 인터페이스에만 의존합니다.
    // 다만 이번 단계에서는 그 조립을 Spring 설정 클래스가 담당합니다.
    private final Tire tire;

    // 생성자 주입: 필요한 의존 객체를 외부에서 전달받습니다.
    public Car(Tire tire) {
        this.tire = tire;
    }

    public void drive() {
        System.out.println(tire.getBrand() + " 장착하고 달립니다!");
    }
}
