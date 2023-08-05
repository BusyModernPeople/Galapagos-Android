# Core 모듈 설명

## 1. Design

여러 화면에서 공통적으로 쓰이는 디자인 요소를 담는 모듈

**component 패키지**

여러 화면에서 공통적으로 쓰이는 `Composable`을 담는다.
`feature`별로 화면을 나누었을 때, 여러 `feature`에서 쓰일 경우 component 패키지에 그대로 넣고,
`feature`구분 없이 여러 `feature`에서 사용된다면, 해당 `feature`명의 패키지를 생성하고 안에 넣어서 사용한다.

예시)<br>
* [JoinProgressBar](./design/ui/join/JoinProgressBar.kt)는 `:feature:join` 모듈에서만 사용하므로, join 패키지에 넣는다.
* [Button](./design/ui/component/Button.kt)은 `feature` 구분 없이 여러 곳에서 사용되므로 component 패키지에 그대로 넣는다.

예외로, 특정 화면에서만 쓰이는 `composable`은 해당 화면 파일 내 `private` 함수로 정의한다.<br>

예시)
* [LoginScreen의 SocialLoginButton](../feature/login/src/main/java/com/busymodernpeople/feature/login/LoginScreen.kt)

**또한, `composable`의 이름은 `PascalCase`를 따르며, `Button`과 같이 기존 `composable`과 이름이 겹치는 경우 `G`를 앞에 붙인다.**
