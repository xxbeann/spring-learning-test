package jeongkong.step1;

public class Car {
    /* step1
    Car가 타이어 구현체를 직접 선택하고, 생성까지 책임집니다.
    그래서 다른 타이어로 교체하려면 Car 코드를 수정해야 합니다.
     */
    private HankookTire tire;

    public Car() {
        this.tire = new HankookTire();
    }

    public void drive() {
        System.out.println(tire.getBrand() + " 장착하고 달립니다!");
    }
}
