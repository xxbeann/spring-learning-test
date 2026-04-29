package jeongkong.step2_app_config;

public class AppConfigDemo {
    public static void main(String[] args) {
        // main()은 조립을 직접 하지 않고 AppConfig에 위임합니다.
        AppConfig appConfig = new AppConfig();
        Car car = appConfig.car();
        car.drive();

        Car car1 = appConfig.car();
        Car car2 = appConfig.car();

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car1 == car2);
    }
}
