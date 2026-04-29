package jeongkong.step2;

public class Car {
    /* step2
    Car는 구체 클래스가 아니라 Tire 인터페이스에만 의존합니다.
    어떤 구현체를 사용할지는 외부 조립 코드가 결정합니다.
     */
    private final Tire tire;

    // 생성자 주입: 필요한 의존 객체를 외부에서 전달받습니다.
    public Car(Tire tire) {
        this.tire = tire;
    }

    public void drive() {
        System.out.println(tire.getBrand() + " 장착하고 달립니다!");
    }
}
