# 공감 (개인 프로젝트)
> 취미를 함께 하고자 하는 사람들의 모임

## 기능

### 같은 취미 공유 기능

[ 취미 모집 / 참여 ]
- 같은 취미를 함께 즐기기 위해 모임을 모으는 기능
- 원하는 취미를 참여하는 기능

[ 기술 공유 / 배움 ]
- 배우고 싶은 재능을 보며 


### 장소 대여 기능
- 취미를 즐기고자 하는 장소를 대여하는 기능


# 관련 내용
[자세한 내용 with Figma](https://www.figma.com/file/R99Ku4WrilbQmWSX0yhZxt/%EA%B0%9C%EC%9D%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?type=design&node-id=0%3A1&mode=design&t=nsWQztnXrtVqtfgk-1)

- 마인드 맵
- 필요 기능
- ERD
- User Flow
- API SPEC


## 구현 기능


- 업주 관련 기능
  - [x] 업주 생성
  - [x] 업주 삭제
  - [x] 업주 정보 변경 [API 만]
  - [x] 업주 공유 장소 관리 기능

- 장소 대여 기능
  - [x] 장소 대여
  - [x] 장소 등록
  - [ ] 장소 제거
  - [x] 업주가 관리하는 대여 장소 리스트 반환

- 회원 기능
  - [x] 회원 등록 기능
  - [x] 로그인 기능
  - [x] 내 예약 요청 보기 기능

- 취미 공유 기능 [추후]
  - [] 취미 함께하기 기능
  - [ ] 취미 참여하기 기능
  - [ ] 기술 공유하기 기능
  - [ ] 기술 배우기 기능

## 환경

### Docker-compose
- /docker 폴더 내부에 있습니다.

[일반 실행]
```agsl
docker-compose up
```

[demon으로 실행 시] 
```
docker-compose up -d
```
