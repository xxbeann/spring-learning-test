package jeongkong.step1;

public class InternalCreationDemo {
    public static void main(String[] args) {
        Car mycar = new Car();
        mycar.drive(); // 출력: 한국타이어 장착하고 달립니다!

        /* 핵심
        - Car는 타이어를 외부에서 전달받지 않고 내부에서 직접 생성합니다.
        - 생성 책임과 사용 책임이 한 클래스에 함께 들어 있습니다.
        - 그래서 구체 구현체에 강하게 결합됩니다.
         */
    }
}
