## 📅 프로젝트 일정
팀 프로젝트 기간 (20240513 ~ 20240627)
>
팀 분리 이후 개인으로 프로젝트 개선(20240628 ~ )

## <a name="section0" />🚀바로가기
### [1.💼프로젝트 산출물](#section1)
### [2.🧑🏻프로젝트 멤버](#section2)
### [3.👤역할 및 담당 업무](#section3)
### [4.📱기획 주제 선정](#section4)
### [5.🚨트러블 슈팅](#section5)
### [6.⚙️Stack & Tool](#section6)


### <a name="section1" />💼프로젝트 산출물
✔️ **프로젝트 산출물** 

[프로젝트 발표.pdf](https://drive.google.com/file/d/1GPaqxSfGh2auJNmV_4I6UD4JXhf7xc5V/view?usp=sharing)
>
[간단한 시스템 아키텍처](https://www.figma.com/board/I3S3GViSuWVW6f1XCSudlT/Final-Architecture?node-id=0-1&t=3LeZjxDonI4eDf9b-0)
>
[UI 설계](https://www.figma.com/design/TDAFaHkbFCa1sngiLppZ0Z/pets?node-id=0-1&t=EmBOSQWk0Ypl8dBf-1)



### <a name="section2" />🧑🏻 프로젝트 멤버 [🔝](#section0)

<table>
  <tr>
    <td align="center"><a href="https://github.com/Kim-soung-won"><b>승원</b></a></td>
    <td align="center"><a href="https://github.com/Ryuwongeun"><b>원근</b></a></td>
    <td align="center"><a href="https://github.com/gywls20"><b>혜경</b></a></td>
    <td align="center"><a href="https://github.com/dl11911"><b>승훈</b></a></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/gyuchanlee"><b>규찬</b></a></td>
    <td align="center"><a href="https://github.com/gywls20"><b>효진</b></a></td>
    <td align="center"><a href="https://github.com/jyc961020"><b>용철</b></a></td>
    <td align="center"><a href="https://github.com/cuscus8"><b>훈민</b></a></td>
  </tr>
</table>

## <a name="section3" />👤 역할 및 담당 업무 [🔝](#section0)

1. **알림 / 로그인 권한 관리 / 레디스 캐싱 **
    - 알림 기능 CRUD 구현
    - 명소 추천 CRUD 구현
    - JWT / Spring Security 구현
    - oAuth2 로그인 구현
    - 스프링 캐싱 전략 (레디스 적용) -> 게시물 로딩 속도 성능 향상
2. 🎯**실시간 알림 기능 구현 ) SpringBoot, React**
    - SSE를 통해 실시간 알람 기능을 구현
    - SSE의 지속적인 커넥션 때문에 성능이 저하되는 문제 발생 -> 트러블 슈팅 (채팅서버로 책임을 넘김)
3. **NCP 관련 사용**
    - Object Storage (S3) - 이미지 업로드 및 관리 기능 활용
    - Linux Ubuntu 서버에 docker 배포 산출물 관리 및 로그 확인 
4. 🧑🏻‍💼**개발 외 담당 업무**
    - 협업 툴 **Notion**을 통한 산출물 관리
    - PPT 제작 및 발표
 
## <a name="section4" />📱기획 주제 선정 (20240215~202403) [🔝](#section0)

🧐 **선정 이유**

**커뮤니티, 채팅 플랫폼**

- 실제 서비스 운영, 배포 경험을 위한, 결제 기능이 없는 단순 커뮤니티 서비스 구현하고자 하는 목표를 이루기 위해
- 기존 API 통신이 아닌, 연결형 Socket 통신 서비스 구현 경험에 대한 학습
- NLP 기술을 통한 검색엔진, 금칙어 시스템 구현을 통한 질 좋은 서비스 구축

**MSA 패턴 도입**

- 채팅, 커뮤니티, 관리자로 기능이 명확하게 나뉘고, 분리해서 운영할 수 있을 것 같아서 별개의 서버에 배포, 관리
- 장애 발생시 전체 서비스가 마비되는 불상사 방지, 코드 관리 및 기능 테스트 효율화
- 별개의 서버에서 발생하는 데이터 관리 및 관련되는 기능을 통한 통신 경험

**Redis Cache를 포함한 NoSQL 추가**

- 많은 양의 실시간 데이터 조회 및 쓰기가 필요한 시스템에 RDBMS는 너무 느린 성능을 보일 것으로 예상
- 데이터 캐싱을 통한 조회 성능 개선 및, 일괄 쓰기를 통한 쓰기 비용 감소
- TTL 데이터를 활용한 조회수, 글쓰기 등의 횟수 제한을 통한 쓰기 작업 처리율 제한



## <a name="section5" />🚨트러블 슈팅 [🔝](#section0)

---

### 트러블1. [SSE 서버 분리를 통한 응답 성능 개선]

🚨 **문제 배경**

알림 기능을 수행하기 위한 SSE(Server Sent Event) Connect이 자주 발생하며 기본 API 응답 요청의 지연 발생
특정 조회기능을 연속해서 클릭(광클)할 시 페이지가 멈춰버리는 현상도 발생했다.
React 상태관리 미흡으로 인한 SSE Connection 문제로 파악했으나, 리액트 상태관리를 전부 개선하기에는 프로젝트 기한이 촉박했고 React 활용에 대한 경험 또한 부족했다.

⭐️ **해결 방법**

그래서 Main Community Server에 적재된 SSE 알림 기능을 실시간 채팅 서버로 이관하였다.
이 결정을 내린 근거는, Chatting Server는 채팅방에 있을 때만 Socket Connection이 이뤄졌고,
Chatting 자체가 차지하는 서버 Resource가 크지 않다고 생각해 같은 연결형 기능인 SSE를 함께 다뤄도 좋을 것 같다고 판단했다.
우선 가장 비용이 많이 드는 RDBMS 쓰기, 조회 작업이 많이 발생하는 Main 커뮤니티 서버로 부터 분리해야한다는 목표가 명확했다.

🤩 **해당 경험을 통해 알게된 점**

서버를 분리해서 얻는 이점에 대해 한번 더 경험할 수 있었다. 또 SSE와 같은 연결형 기능의 Connection을 잘 컨트롤해주어야 활용할 수 있다는 사실을 다시한번 느낄 수 있었다.
프론트 단계에서 요청을 잘 컨트롤하여 한번만 연결하고 그 상태를 지속하면 서버 분리보다는 간단하게 문제가 해결될 것이라고 생각된다. 추후에 해당 방안에 대해서도 시도해볼 예정에 있다.

- 관련 채팅 서버 링크
- https://github.com/Palette-Pets/Chat_FrontEnd
- https://github.com/Palette-Pets/Chat_BackEnd

---

### 트러블2. [Spring Cache 적용을 통한 쿼리 응답 성능 개선]

🚨 **문제 배경**

명소 추천 기능 (게시물)의 리스트를 검색하면 관련된 쿼리도 두가지 이상이여서 빠른 새로고침(광클)에 지연이 되는 현상이 발생
동시에 세명 이상의 사용자가 작정하고 새로고침을 굉장히 빠르게 쓴다면 서버의 응답이 따라가지 못해 3초이상 지연이 걸리는 현상도 간간히 발생함

⭐️ **해결 방법**

Spring cache 기능을 통해 자주 쿼리되는 데이터들은 미리 캐시 서버에 넣어두고 어플리케이션단에서 바로 응답을 할 수 있도록 변경하였다.
스프링에서는 어노테이션으로 쉽게 적용할 수 있도록 추상화 처리까지 해놓았으므로 스프링부트를 사용하는 입장에서는 캐싱을 적용하기 적합한 환경이였다.
이 과정에서 Redis 저장소를 적용해서 캐싱을 처리하였는 데, 외부 저장소이기 때문에 어플리케이션 서버가 만약 꺼지더라도 데이터를 보존할 수 있고 TTL로 유효시간을 쉽게 관리할 수 있기 때문에 적용하였다.
확실히 초당 나가는 쿼리가 사라지고 바로 redis 저장소에서 데이터를 반환해서 응답하여 응답 속도와 성능이 향상되었다.

🤩 **해당 경험을 통해 알게된 점**

캐싱을 통한 성능 향상을 눈에 띄게 체감할 수 있었고, 또한 redis를 통해 캐싱을 할 때, 읽기 전략과 쓰기 전략을 제대로 정하고 처리해야 실제 DB의 데이터와 캐싱 서버의 데이터의 정합성을 보장하면서도
빠르게 사용자에게 응답을 보낼 수 있다는 것을 알 수 있었다. 데이터를 응답할 때는 속도가 빨라야 하지만 가장 중요한 0순위는 데이터의 정합성이기 때문에 캐시 전략을 잘 설정하는 것이 중요하다.

- 캐싱 전략에 대한 참고 글
- https://inpa.tistory.com/entry/REDIS-%F0%9F%93%9A-%EC%BA%90%EC%8B%9CCache-%EC%84%A4%EA%B3%84-%EC%A0%84%EB%9E%B5-%EC%A7%80%EC%B9%A8-%EC%B4%9D%EC%A0%95%EB%A6%AC

---



## <a name="section6" />⚙️Stack & Tool [🔝](#section0)

### 프론트엔드
- JavaScript
- React
- Redux

### UI
- Figma
- MUI
- TailWinds

### 백엔드  
- Java
- Spring Boot3
- Spring MVC
- JPA
- QueryDSL
- JWT
- Spring security
- Spring Cache

### DBMS 
- MySQL8
- Redis

### DevOps
- NCP
- Docker
- Jenkins

### 커뮤니케이션
- Notion
- Figma


