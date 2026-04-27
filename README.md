# Spring Learning Test

스프링 부트 학습 테스트를 통해 웹 백엔드 레벨 2에서 자주 다루는 기능을 직접 구현하고, 테스트로 검증하면서 익히기 위한 저장소입니다.

이 저장소는 "완성된 앱"을 만드는 프로젝트라기보다, 스프링 기능을 작은 단위로 나눠 연습하는 실습 모음에 가깝습니다.

## 프로젝트 구조

- `spring-*/initial`
  - 직접 실습하는 공간입니다.
  - 보통 이 디렉터리 안의 `src/main/java`, `src/main/resources`를 수정합니다.
- `spring-*/complete`
  - 비교용 정답 예시입니다.
  - 처음부터 열어두기보다, 충분히 고민한 뒤 막혔을 때 비교하는 용도로 쓰는 편이 좋습니다.
- `initial/src/test/java`
  - 요구사항이 들어 있는 문제지입니다.
  - README보다 먼저 읽어도 될 정도로 중요합니다.

## 학습할 때 보는 순서

각 모듈은 아래 순서로 보면 됩니다.

1. `initial/README.md`를 빠르게 읽고 주제를 파악한다.
2. `initial/src/test/java`의 테스트 코드를 읽고 무엇을 만족해야 하는지 확인한다.
3. `initial/src/main/java`, `initial/src/main/resources`를 수정한다.
4. 테스트를 실행해 통과시키고, 왜 통과했는지 설명해본다.
5. 막혔을 때만 `complete`와 비교한다.

핵심은 README가 아니라 테스트입니다.  
README는 개념 소개이고, 테스트는 실제 요구사항입니다.

## 테스트 성격

- 웹 모듈
  - `@SpringBootTest`와 `RestAssured`를 사용합니다.
  - 실제 HTTP 요청/응답 흐름을 보면서 학습합니다.
- JDBC 모듈
  - `@JdbcTest`를 사용합니다.
  - `JdbcTemplate` 기반 DB 접근에 집중합니다.
- JPA 모듈
  - `@DataJpaTest`를 사용합니다.
  - 엔티티, 리포지토리, 연관관계 매핑에 집중합니다.

## 모듈별 학습 주제

- `spring-core-1`
  - Bean 등록, 의존성 주입, 컴포넌트 스캔
- `spring-core-2`
  - `@Configuration`, `@Bean`, `@PropertySource`, `@Profile`
- `spring-mvc-1`
  - 정적 리소스, 템플릿, JSON 응답
- `spring-mvc-2`
  - CRUD API 기본기
- `spring-jdbc-1`
  - `JdbcTemplate` 조회/수정
- `spring-mvc-3`
  - `@ExceptionHandler`, `@ControllerAdvice`
- `spring-auth-1`
  - Basic Auth, Session Login, Token Login
- `spring-mvc-4`
  - `WebMvcConfigurer`, Interceptor, Argument Resolver
- `spring-data-jpa-1`
  - 엔티티 매핑, Repository, Query Method
- `spring-data-jpa-2`
  - 연관관계 매핑
- `spring-http-client-1`
  - `RestTemplate`, `RestClient`

## 추천 학습 순서

### 1. 스프링 개념을 먼저 잡고 싶은 경우

`spring-core-1 -> spring-core-2 -> spring-mvc-1 -> spring-mvc-2 -> spring-jdbc-1 -> spring-mvc-3 -> spring-auth-1 -> spring-mvc-4 -> spring-data-jpa-1 -> spring-data-jpa-2 -> spring-http-client-1`

처음 학습할 때는 이 순서를 더 추천합니다.  
빈 등록, DI, 설정, 프로퍼티, 프로필을 먼저 이해하면 이후 MVC와 JPA를 덜 기계적으로 받아들이게 됩니다.

### 2. 미션 흐름대로 따라가고 싶은 경우

`spring-mvc-1 -> spring-mvc-2 -> spring-jdbc-1 -> spring-core-1 -> spring-core-2 -> spring-mvc-3 -> spring-auth-1 -> spring-mvc-4 -> spring-data-jpa-1 -> spring-data-jpa-2 -> spring-http-client-1`

## 한 모듈 학습 루틴

모듈 하나를 아래 흐름으로 진행하는 것을 추천합니다.

- README 읽기: 5분
- 테스트 코드 읽기: 10분
- 직접 구현: 30~60분
- `complete`와 비교: 10분
- 짧게 메모 정리: 5분

추천 메모 항목:

- 이 테스트가 확인하는 스프링 기능은 무엇인지
- 어떤 어노테이션/설정을 왜 썼는지
- 비슷한 상황에서 내 프로젝트에는 어떻게 적용할 수 있을지

## 실행 예시

루트에서 필요한 모듈만 골라 실행하면 됩니다.

```bash
./gradlew :spring-mvc-1:initial:test
./gradlew :spring-mvc-2:initial:test --tests cholog.CRUDTest
./gradlew :spring-core-1:initial:test --tests cholog.BeanTest
```

비교가 필요하면 아래처럼 `initial`과 `complete`를 비교할 수 있습니다.

```bash
git diff --no-index spring-mvc-2/initial spring-mvc-2/complete
```

## 학습 팁

- 처음부터 `complete`를 보지 않는다.
- 한 번에 전체 테스트를 돌리기보다, 테스트 클래스 단위로 잘라서 본다.
- 20~30분 정도는 공식 문서, 디버깅, 로그를 보면서 스스로 해결해본다.
- "어떻게 고쳤는가"보다 "왜 이 방식이 필요한가"를 설명할 수 있어야 한다.
- 특히 `spring-mvc-4`, `spring-auth-1`, `spring-data-jpa-2`는 디버깅하면서 흐름을 보는 것이 도움이 된다.

## 주의할 점

- 저장소에 있는 모듈 목록 중 `spring-core-2`는 꼭 포함해서 학습하는 것을 추천합니다.
- `spring-http-client-1`은 외부 HTTP 호출이 포함될 수 있어 실행 환경에 따라 인터넷 연결이 필요할 수 있습니다.

## 추천 시작점

처음 시작한다면 아래 둘 중 하나를 추천합니다.

- 스프링이 아직 낯설다면: `spring-core-1`
- 웹 요청/응답이 먼저 궁금하다면: `spring-mvc-1`
