# 서재연의 포트폴리오

### Programming language skill

---

- Java(상, Spring/Spring boot)
- C++, C(중하)
- Html, CSS, Javascript(중상-  Bootstrap Opensource를 커스텀할 수 있을 정도의 수준)



### Tools

---

Eclipse, AWS(EC2-Ubuntu, S3, RDS-MySQL, MariaDB)

### 관심직무

---

SW Developer

Web Service Developer (Backend)

### 경력

---

- 서울산업진흥원(SBA)

**기간**: 2018-07-02~2017-08-24

**직급**: 서울시정인턴

**주요업무**: 홈페이지 컨텐츠 관리 및 FAQ업로드(Zendesk)

- 오스템임플란트

**기간**: 2018-09-03 ~ 2018-12-21

**직급**: 인턴

**주요업무**: 웹 개발 및 SW 테스팅 

### 특이사항

---

[서재연의블로그](http://a1010100z.tistory.com/) - 개발일기를 기록하고 있습니다.



## 진행프로젝트

### 헬스일정예약관리시스템

- 소개: 문자 또는 직접 대화를 통해 예약을 조절해야했던 기존의 헬스 예약 시스템은 착오가 일어날 수 있다. 따라서 이 시스템을 웹페이지로 전산화하여 정확도를 높이고자 한다. 예약 시스템과 더불어 인바디 기록 및 변화 조회, 커뮤니티 기능 등을 추가로 제공한다.
- 팀구성: 개인프로젝트
- 기간: 대부분의 주요 기능은 모두 구현된 상태이며, 지속적으로 Spring 학습에 따라 시스템을 개선하고 있습니다. 
- 부연설명: 학부수업에서 단독 개발을 진행했던 헬스일정예약관리 웹페이지가 시간적, 역량적 한계로 기능구현에만 초점을 맞추고 완성도가 부족하다는 판단이 들어 재개발하게 되었습니다.
- 개발도구 

```xml
- Tools: Java/Eclipse
- Backend: Spring Boot(기존에 Spring Framework로 개발했었고, 비슷한 기능을 조금 다르게 구현해보고 싶어서 Spring boot로 재개발하게 되었습니다.)
- DB: MySQL + MyBatis
- Frontend: Bootstrap 4.0 + JSP(JSP코드는 많지 않으며 대부분 Backend단에서 처리해서 정제된 데이터를 넘겨주는 형식입니다.)
- 기타도구: AWS EC2, RDS, S3(이미지 저장용)
```



**주요요구사항**

1) 사용자 타입 별 인증 및 권한 부여(Spring Security)

2) 관리자의 사용자 등록 기능(S3에 이미지 업로드)

3) 트레이너의 회원 인바디 기록 및 조회 기능(Bootstrap chart libaray)

4) 회원의 스케줄 신청, 예약대기, 예약확정(DB query 구현)

5) 서비스 이용 상담을 위한 이메일 전송(JavaMailSender)

소스코드: [헬스일정예약관리시스템](https://github.com/SeoJaeyeon/fitnessCenterManagementSystem)

---

### 지하철 다물어봐!

- 소개: 지하철을 이용할 때 궁금한 정보들(도착예정정보, 현재위치정보, 주변 정류장 정보 등)을 챗봇으로 제공하여 다수의 카테고리 이동으로 인한 시간 지연을 해결한다. 또, 자연어처리를 통하기 때문에 누구나 쉽고 편리하게 이용할 수 있다.
- 팀구성: ICT 한이음 멘토링 프로젝트 - 4명의 대학생이 공동 개발자 
- 본인 역할: 팀장 및 개발자(지하철 도착예정 시간 정보 제공)
- 개발도구

``` xml
- Tools: Java/Eclipse
- Backend: Spring Framework 5.0
- Frontend: 모바일 카카오플러스친구 
- 기타도구: IBM Conversation(자연어처리 API), 카카오 플러스친구 API, 공공데이터 API(지하철 정보)
```

**주요기능(지하철 도착예정정보 제공)**

1) 사용자가 자연어로 질의를 하면 자연어에서 현 지하철역명 추출(IBM Conversation JDK)

ex) 판교역 언제와?, 판교인데 지하철 언제와? 판교에 언제와? 등등 -> "판교"추출

2) 추출된 위치로 공공 데이터 API를 호출하여 JSON의 응답을 받아옴(서울시공공데이터)

3) 필요한 데이터 파싱- 시간, 지하철 이름, 방향(jackson)

4) 자연어로 사용자에 제공 

소스코드: [지하철다물어봐!](https://github.com/SeoJaeyeon/AllOfSubway)

---

### 오늘뭐먹지?

- 소개: 매일 메뉴를 정하는데 시간을 소요하는 사람들을 위해 선호도를 반영하여 메뉴를 랜덤으로 정해주는 안드로이드 애플리케이션.
- 팀구성: 졸업프로젝트 - 4명의 공동개발자
- 본인역할: 웹서버 - 카카오 로컬 Restful API로 음식점 정보 추출 및 안드로이드 통신, DB 작업.
- 개발도구

```xml
- Tools: Java/Eclipse
- Backend: Spring Framework 4.0
- Frontend: Android Studio
- DB: Maria DB + MyBatis
- 기타도구: 카카오 로컬 RestFul API
```

**주요구현 기능(모든 기능의 백엔드처리)**

1) 완전 랜덤: 사용자의 선호도만 고려하여 주변 음식점 목록 10개 중 하나의 음식점 추출 

- 전달된 좌표 정보로 카카오 로컬 API를 호출하여 주변 음식점 목록 10개를 JSON 응답을 받고, 필요한 정보만을 파싱하여 다시 JSON형태로 리턴
- 이 때, DB에 저장된 음식점id+선호도(HATE/NOTHATE)를 구분하여 HATE인 음식점은 따로 체킹하여 리턴 

2) 커스텀 랜덤

- 커스텀 추가: 사용자가 음식점 아이디와 커스텀 flag를 전달하면 해당 음식점은 DB 커스텀 테이블에 저장
- 커스텀 랜덤: 사용자가 커스텀 랜덤을 호출하면 커스텀 테이블의 음식점을 리턴

3) 키워드 랜덤

- 사용자가 키워드를 전송(ex: 김치찌개)하면 해당 키워드와 좌표로 카카오 API 호출하여 음식점 데이터 응답을 받고 필요 정보를 파싱하여 리턴  

소스코드:[오늘뭐먹지?](https://github.com/SeoJaeyeon/foodSelector)