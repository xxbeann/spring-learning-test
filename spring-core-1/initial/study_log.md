# spring-core-1 study log

## 학습 개요

- 모듈: `spring-core-1`
- 주제: Spring Bean, 의존성 주입, Component Scan
- 목표: 스프링 컨테이너가 객체를 어떻게 빈으로 등록하고, 어떻게 주입하고, 어떻게 스캔하는지 직접 테스트를 통과시키며 이해한다.

## 이 모듈에서 얻어가야 할 학습 목표

### 1. Spring Bean

- 스프링 빈이 무엇인지 설명할 수 있다.
- 클래스에 어떤 애너테이션을 붙이면 스프링이 빈으로 등록하는지 이해한다.
- 스프링 컨테이너에서 빈을 직접 조회할 수 있다.

### 2. Dependency Injection

- 의존성 주입이 왜 필요한지 설명할 수 있다.
- 생성자 주입, 세터 주입, 필드 주입의 차이를 구분할 수 있다.
- 같은 객체를 "직접 생성"하는 것과 "주입받아 사용하는 것"의 차이를 이해한다.

### 3. Component Scan

- `@ComponentScan`이 어떤 역할을 하는지 설명할 수 있다.
- `@SpringBootApplication`이 내부적으로 `@ComponentScan`을 포함하고 있다는 점을 이해한다.
- 스캔 범위를 어떻게 지정하는지 감을 잡는다.

## 테스트별 학습 체크

### 1. `BeanTest.registerBean`

- [ ] `SpringBean`이 빈으로 등록되는 이유를 설명할 수 있는가?
- [ ] `context.getBean("springBean")`이 가능한 이유를 설명할 수 있는가?
- [ ] 빈 이름이 어떻게 결정되는지 감을 잡았는가?

생각해볼 것

- `new SpringBean()`으로 직접 만드는 것과 컨테이너에서 가져오는 것은 어떤 차이가 있을까?
- 모든 객체를 스프링 빈으로 만들어야 할까?

### 2. `BeanTest.autowiredBean`

- [ ] `AutowiredBean`은 어떻게 `SpringBean`을 사용할 수 있게 되었는가?
- [ ] `@Autowired`가 어떤 시점에 동작한다고 이해하고 있는가?

생각해볼 것

- 의존 객체를 직접 생성하지 않고 주입받으면 테스트나 유지보수에 어떤 장점이 있을까?
- 만약 같은 타입의 빈이 여러 개면 어떻게 될까?

### 3. `DependencyInjectionTest.constructorInjection`

- [ ] 생성자 주입 방식으로 의존성을 연결할 수 있는가?
- [ ] 생성자 주입이 왜 자주 권장되는지 설명할 수 있는가?

생각해볼 것

- 생성자 주입은 왜 "필수 의존성" 표현에 유리할까?
- 객체가 생성될 때 필요한 값이 빠져 있으면 어떤 문제가 생길까?

### 4. `DependencyInjectionTest.setterInjection`

- [ ] 세터 주입이 어떻게 동작하는지 설명할 수 있는가?
- [ ] 세터 주입은 어떤 경우에 사용할 수 있을지 떠올릴 수 있는가?

생각해볼 것

- 세터 주입은 왜 선택 의존성에 어울린다고 할까?
- 세터를 열어두면 객체 상태가 바뀔 가능성은 어떻게 될까?

### 5. `DependencyInjectionTest.autowiredInjection`

- [ ] 필드 주입으로도 의존성 주입이 가능하다는 점을 이해했는가?
- [ ] 필드 주입이 편하지만 왜 실무에서 덜 선호되는지 설명할 수 있는가?

생각해볼 것

- 필드 주입은 테스트 코드에서 왜 불편할 수 있을까?
- 객체의 의존관계가 외부에 잘 드러나는 방식은 무엇일까?

### 6. `ComponentScanTest.scanComponent`

- [ ] `@ComponentScan`이 없을 때와 있을 때의 차이를 설명할 수 있는가?
- [ ] 어떤 패키지까지 스캔되는지 이해했는가?

생각해볼 것

- 스캔 범위를 너무 넓게 잡으면 어떤 일이 생길 수 있을까?
- 스캔 범위를 명시적으로 관리하는 것이 왜 중요할까?

## DI 실험 정리

빠른 이동

- [step1](./src/main/java/jeongkong/step1/InternalCreationDemo.java)
- [step2](./src/main/java/jeongkong/step2/ManualDiDemo.java)
- [step2_app_config](./src/main/java/jeongkong/step2_app_config/AppConfigDemo.java)
- [step2_spring_config](./src/main/java/jeongkong/step2_spring_config/AppConfigDemo.java)
- [step3](./src/main/java/jeongkong/step3/SpringContainerDiDemo.java)

### 실험 목적

이번 실험은 의존성 주입이 왜 필요한지 직접 비교해보기 위해 진행했다.  
단순히 `@Autowired` 문법을 익히는 것이 아니라, 객체가 의존 대상을 직접 만들 때와 외부에서 전달받을 때 어떤 차이가 생기는지 확인하는 것이 목표였다.

### [step1 - 내부 생성, 강결합](./src/main/java/jeongkong/step1/InternalCreationDemo.java)

`Car`가 `HankookTire`를 내부에서 직접 `new`로 생성하도록 구성했다.

```java
public Car() {
    this.tire = new HankookTire();
}
```

이 방식에서는 `Car`가 타이어를 사용하는 책임뿐 아니라, 어떤 타이어를 만들지까지 함께 결정한다.  
즉, `Car`가 구체 구현체에 직접 의존하게 되고, 다른 타이어로 교체하려면 `Car` 내부 코드를 수정해야 한다.

느낀 점

- 객체가 의존 대상을 직접 생성하면 강결합이 발생한다.
- 기능 확장이나 구현체 교체가 어렵다.
- 테스트용 가짜 객체를 넣는 것도 어렵다.
- 이 단계에서는 아직 DI가 없다.

### [step2 - 수동 DI, 느슨한 결합](./src/main/java/jeongkong/step2/ManualDiDemo.java)

`Tire` 인터페이스를 만들고, `Car`가 `Tire` 인터페이스에만 의존하도록 변경했다.  
그리고 어떤 구현체를 넣을지는 `Application`이 생성자를 통해 전달하도록 했다.

```java
public Car(Tire tire) {
    this.tire = tire;
}
```

이 방식에서는 `Car`가 더 이상 `HankookTire`, `KumhoTire` 같은 구체 클래스에 직접 의존하지 않는다.  
외부에서 어떤 구현체를 넣어줄지만 결정하면 되므로, `Car` 코드는 수정하지 않아도 된다.

느낀 점

- DI는 Spring 전용 기술이 아니라, 객체 설계 방식이다.
- 생성자 주입을 사용하면 필요한 의존성이 명확하게 드러난다.
- 구현체 교체가 쉬워진다.
- mock 객체나 람다 같은 테스트용 구현체도 쉽게 넣을 수 있다.

### [step2_app_config - 조립 책임을 AppConfig로 분리](./src/main/java/jeongkong/step2_app_config/AppConfigDemo.java)

step2에서는 `ManualDiDemo`가 직접 객체를 만들고 연결했다.  
이 방식도 DI이긴 하지만, 객체 수가 많아질수록 `main()`이 점점 조립 공장처럼 비대해진다.

그래서 이번 단계에서는 `AppConfig`를 두고, 객체 생성과 연결 책임을 한 곳으로 분리했다.

```java
public class AppConfig {
    public Tire tire() {
        return new HankookTire();
    }

    public Car car() {
        return new Car(tire());
    }
}
```

이제 `Car`는 여전히 `Tire` 인터페이스에만 의존하고, 어떤 구현체를 넣을지는 `AppConfig`가 결정한다.  
구현체를 바꾸고 싶을 때도 `Car`가 아니라 조립 담당 클래스만 수정하면 된다.

느낀 점

- DI 구조는 유지하면서 조립 책임을 별도의 클래스에 모을 수 있다.
- 객체를 사용하는 역할과 생성/연결하는 역할이 더 선명하게 분리된다.
- 실무에서 객체 수가 많아질수록 이런 조립 전담 계층의 필요성이 커진다.
- `appConfig.car()`를 여러 번 호출하면 매번 새로운 `Car` 객체가 생성된다.
- 즉, 이 단계에서는 조립 구조는 좋아졌지만 객체 생성과 재사용 규칙은 여전히 내가 직접 관리해야 한다.

### [step2_spring_config - AppConfig를 Spring 설정 클래스로 올리기](./src/main/java/jeongkong/step2_spring_config/AppConfigDemo.java)

이번 단계에서는 앞에서 직접 만들었던 `AppConfig`를 Spring이 이해할 수 있는 설정 클래스로 바꾸었다.

- `AppConfig`에 `@Configuration`
- 객체 생성 메서드에 `@Bean`
- `AnnotationConfigApplicationContext(AppConfig.class)`로 컨테이너 실행

```java
@Configuration
public class AppConfig {
    @Bean
    public Tire tire() {
        return new HankookTire();
    }

    @Bean
    public Car car() {
        return new Car(tire());
    }
}
```

즉, 수동 조립 구조 자체는 그대로 두되, 이제는 Spring 컨테이너가 그 `AppConfig`를 읽고 bean을 만들고 보관해준다.

느낀 점

- Spring은 갑자기 새로운 설계를 강요하는 것이 아니라, 기존 수동 조립 구조를 컨테이너로 끌어올려 관리하게 해준다.
- `@Configuration`과 `@Bean`은 AppConfig 방식과 Spring 컨테이너 사이를 이어주는 중간 다리 역할을 한다.
- 이 단계가 있기 때문에 `@Component`, `@Autowired` 기반 자동 조립도 더 자연스럽게 이해된다.
- 같은 `Car` bean을 두 번 조회하면 같은 인스턴스가 반환된다.
- 즉, Spring은 객체를 한 번 만들고 끝내는 것이 아니라, 생성한 bean을 컨테이너 안에서 보관하고 재사용한다.

### singleton 비교 실험

이번에는 `step2_app_config`와 `step2_spring_config`에서 객체가 실제로 몇 번 생성되는지 비교해보았다.

#### 1. `step2_app_config`

```java
AppConfig appConfig = new AppConfig();

Car car1 = appConfig.car();
Car car2 = appConfig.car();

System.out.println(car1);
System.out.println(car2);
System.out.println(car1 == car2);
```

이 경우 `appConfig.car()`를 호출할 때마다 `new Car(...)`가 다시 실행된다.  
즉, `car1`과 `car2`는 서로 다른 객체이고, 비교 결과는 `false`가 나온다.

#### 2. `step2_spring_config`

```java
ApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

Car car1 = context.getBean(Car.class);
Car car2 = context.getBean(Car.class);

System.out.println(car1);
System.out.println(car2);
System.out.println(car1 == car2);
```

이 경우 Spring 컨테이너는 `Car` bean을 기본적으로 singleton으로 관리한다.  
즉, `car1`과 `car2`는 같은 객체이고, 비교 결과는 `true`가 나온다.

#### 비교를 통해 이해한 점

- `step2_app_config`는 좋은 DI 구조이지만 객체 생성 규칙은 여전히 개발자가 직접 관리한다.
- `step2_spring_config`는 같은 조립 구조를 Spring 컨테이너가 관리하면서 객체를 보관하고 재사용한다.
- Spring이 들어오면서 달라지는 핵심은 DI 자체보다, 객체 생성/보관/재사용을 일관되게 관리할 수 있다는 점이다.

### singleton에 대해 정리한 내용

이번 실험을 통해 singleton을 다음과 같이 이해했다.

- Spring singleton은 "하나의 인스턴스를 컨테이너가 공유 관리한다"는 뜻이다.
- 이것이 유용한 이유는 서비스, 리포지토리 같은 객체가 보통 상태 없이 동작하기 때문이다.
- 즉, 요청마다 새로 만들 필요 없이 하나의 객체를 여러 요청이 함께 사용해도 된다.
- 대신 요청마다 바뀌는 상태를 필드에 저장하면 동시성 문제가 생길 수 있다.

예를 들어 `Service` 안에 이런 필드가 있다면 위험하다.

```java
private Long currentUserId;
```

이 값은 여러 요청이 동시에 들어올 때 서로 덮어쓸 수 있다.  
그래서 singleton bean은 보통 상태를 저장하지 않는 stateless 구조로 설계해야 한다.

또한 이 개념은 POJO에서 배운 불변객체, 방어적 복사와는 초점이 다르다.

- 불변객체/방어적 복사: 데이터 객체의 상태를 안전하게 보호하기 위한 개념
- singleton: Spring bean의 생명주기와 공유 전략에 대한 개념

즉, 도메인 객체는 상태를 안전하게 다루는 것이 중요하고,  
Spring bean은 상태를 들고 있지 않게 설계해서 공유 재사용이 가능하도록 만드는 것이 중요하다고 정리했다.

### [step3 - Spring 컨테이너를 이용한 자동 조립](./src/main/java/jeongkong/step3/SpringContainerDiDemo.java)

step2와 구조는 같지만, 이번에는 `Application`이 직접 조립하지 않고 Spring 컨테이너가 bean을 등록하고 의존성을 연결하도록 구성했다.

- `Car`, `HankookTire`, `KumhoTire`를 `@Component`로 등록
- `Car`는 생성자 주입 사용
- `Tire` 구현체가 2개라 `@Primary`로 기본 선택 대상을 지정

```java
@Autowired
public Car(Tire tire) {
    this.tire = tire;
}
```

즉, `step2_app_config`, `step2_spring_config`에서는 `AppConfig`가 조립 책임을 담당했다면,  
step3에서는 그 수동 조립 코드조차 줄이고 Spring이 컴포넌트 스캔과 자동 주입으로 조립을 대신 수행한다.

느낀 점

- DI의 본질은 step2에서 이미 등장했다.
- Spring은 DI 자체를 만드는 것이 아니라, 그 조립 과정을 자동화해준다.
- `@Autowired`는 자동 주입 도구이지, DI 그 자체는 아니다.
- 구현체가 여러 개일 때는 `@Primary`, `@Qualifier` 같은 선택 기준이 필요하다.

### 최종 정리

이번 실험을 통해 정리한 흐름은 다음과 같다.

- step1: 객체가 의존 대상을 직접 생성하면 강결합이 발생한다.
- step2: 의존 대상을 외부에서 주입하면 느슨한 결합을 만들 수 있다.
- step2_app_config: 조립 책임을 `AppConfig`로 분리할 수 있다.
- step2_spring_config: 그 `AppConfig`를 Spring 설정 클래스로 등록해 컨테이너가 관리할 수 있다.
- step3: Spring은 컴포넌트 스캔과 자동 주입으로 이 조립 과정을 더 자동화해준다.

결론적으로, DI의 핵심은 객체가 필요한 의존성을 직접 만들지 않고 외부에서 전달받는 것이다.  
그리고 Spring은 그 구조를 더 편하게 사용할 수 있게 도와주는 프레임워크라고 이해했다.

### 추가로 생각해볼 점

- 생성자 주입이 왜 가장 권장되는가?
- `final` 필드를 쓰면 무엇이 좋아지는가?
- `@Autowired` 없이도 DI를 할 수 있다는 사실이 왜 중요한가?
- singleton bean이 상태를 가지면 왜 위험한가?

## 직접 정리해볼 질문

### Q1. 스프링 빈은 왜 필요할까?

- 내 답:

### Q2. 의존성 주입은 "편리함" 말고 어떤 설계상의 이점을 줄까?

- 내 답:

### Q3. 생성자 주입, 세터 주입, 필드 주입 중 지금 기준으로 가장 선호하는 방식은 무엇이고 왜 그런가?

- 내 답:

### Q4. `@SpringBootApplication`이 이미 스캔을 해주는데도 `@ComponentScan`을 따로 알아야 하는 이유는 무엇일까?

- 내 답:

## 실습하면서 확인해볼 포인트

- [ ] 테스트가 어떤 클래스를 직접 조회하는지 확인했다.
- [ ] 빈 등록 여부를 `ApplicationContext` 기준으로 이해했다.
- [ ] 각 주입 방식이 코드 모양에 어떤 차이를 만드는지 비교했다.
- [ ] 단순히 테스트를 통과시키는 데서 끝내지 않고, 왜 그렇게 동작하는지 설명해봤다.

## 학습 후 회고

### 오늘 새롭게 이해한 것

- 이번 `step1`, `step2`, `step3` 실험을 통해 스프링의 핵심 개념인 IoC와 DI를 흐름으로 이해할 수 있었다.
- `step1`에서는 객체가 의존 대상을 직접 생성할 때 강결합이 생긴다는 점을 확인했고, `step2`에서는 의존 객체를 외부에서 주입받으면 구조가 훨씬 유연해진다는 점을 느꼈다.
- 그리고 `step3`에서는 이 외부 조립 과정을 Spring 컨테이너가 대신 수행한다는 점을 보면서, DI의 본질과 IoC 컨테이너의 역할이 어떻게 연결되는지 이해할 수 있었다.
- 즉, DI는 객체가 필요한 의존성을 외부에서 전달받는 설계 방식이고, IoC는 그 조립과 제어를 컨테이너가 맡는 구조라고 정리할 수 있다.

### 아직 헷갈리는 것

- 

### 다음에 공식 문서나 추가 자료로 더 볼 것

- 

### 내 코드에서 적용해보고 싶은 점

- 
