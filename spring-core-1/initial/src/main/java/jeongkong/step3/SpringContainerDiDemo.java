package jeongkong.step3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContainerDiDemo {
    public static void main(String[] args) {
        // 핵심
        // - 수동 조립 코드를 직접 작성하지 않습니다.
        // - Spring 컨테이너가 패키지를 스캔해 bean을 등록합니다.
        // - 생성자 주입으로 필요한 의존관계까지 자동으로 연결합니다.
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("jeongkong.step3");

        // 직접 new로 조립하지 않고, 컨테이너가 조립한 Car bean을 꺼내서 사용합니다.
        Car myCar = context.getBean(Car.class);
        myCar.drive();
    }
}
