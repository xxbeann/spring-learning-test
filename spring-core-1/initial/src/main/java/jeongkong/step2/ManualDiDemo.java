package jeongkong.step2;

public class ManualDiDemo {
    public static void main(String[] args) {
        /* 핵심
        - Car는 타이어를 직접 만들지 않습니다.
        - 외부 조립 코드가 구현체를 선택해 생성자로 전달합니다.
        - Spring 없이도 생성자 주입으로 DI를 구현할 수 있습니다.
        - 다만 조립 코드가 main()에 모이면 점점 복잡해집니다.
         */

        // 1. 한국타이어 주입
        Tire hankook = new HankookTire();
        Car car1 = new Car(hankook);
        car1.drive();

        // 2. 금호타이어로 교체
        Tire kumho = new KumhoTire();
        Car car2 = new Car(kumho);
        car2.drive();

        // 3. 테스트용 가짜 구현체 주입
        Tire mockTire = () -> "테스트용 가짜 타이어";
        Car testCar = new Car(mockTire);
        testCar.drive();
    }
}
