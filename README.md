
# Github API 를 이용한 이슈 관리 코드 
---

## 코드
### [실행 코드](https://github.com/lee-maru/githubApi/blob/master/githubApiTest/src/main/java/org/example/GithubConnection.java)
### [테스트 코드](https://github.com/lee-maru/githubApi/blob/master/githubApiTest/src/test/java/org/example/GithubConnectionTest.java)

---
## 기능
> 이슈에 참여한 인원들을 확인하고, 각 인원별로 이슈 참여율을 퍼센트로 계산한다. 
>> 결과 console 예시

>> lee-maru : 12.55%

>> 홍길동 : 12.23%

---

## 이슈사항
> githubAPI 를 통해 userID 또는 email을 산출하는데,
> Email 이나 userID가 없고, **오로지 닉네임** 만 가지고 있을 경우 null을 반환.
## 해결
> **junit4** testCode 를 통한 디버깅 진행

---
## 참고

> **githubAPI** : https://github-api.kohsuke.org

> **whiteShipStudy** https://github.com/whiteship/live-study/issues/4

이 레파지토리는 개인적으로 공부하고 저장하는 용도입니다. 
