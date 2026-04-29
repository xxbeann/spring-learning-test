package jeongkong.step2_spring_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppConfigDemo {
    public static void main(String[] args) {
        // 이제는 AppConfig를 Spring 컨테이너가 읽고 bean을 생성하고 보관합니다.
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean(Car.class);
        car.drive();

        Car car1 = context.getBean(Car.class);
        Car car2 = context.getBean(Car.class);

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car1 == car2);
    }
}
