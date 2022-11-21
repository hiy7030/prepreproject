# prepreproject
## 커피 주문 애플리케이션 만들기
### date : 11.21(Mon) ~ 12.11(Sun)
### 학습 목표 : 복습 및 코드 작성에 익숙해지기

![prepreproject](https://user-images.githubusercontent.com/107395229/202983678-39c9bc3e-47e9-4d85-8f65-7d91113e6ec0.png)

### 11월 21일

- Entity 클래스 구현
- Controller 1차 구현
  - @RestController : Rest API
  - @RequestMapping : URI 작성
  - @RequestBody : Web Request(Json 타입)임을 알려준다.
  - @PathVariable : 해당 메서드의 Path 값
  - @RequestParam : Request URI의 파라미터 값
- Dto 1차 구현
  -  유효성 검증
  - @Validated(클래스레벨) - @Valid(메서드레벨)
  - @Pattern : regexp 애트리뷰트(정규표현식)
  