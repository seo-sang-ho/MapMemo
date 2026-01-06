# Changelog.md

---
## [2026-01-06]
### Added
- 공공데이터 (공중화장실)정보 엑셀 파일 추가

### Error
- curl 명령어는 잘 작동이 되나 postman으로 작동할 시 403 forbidden 오류 발생
 

---
## [2025-12-17]

### Added
- 메모 검색 기능 추가

---

## [2025-12-10]
### Added
- 로그아웃 기능 추가

---

## [2025-12-09]
### Added
- member ↔ memo 연동 기능 추가 (2회 커밋)

---

## [2025-12-08]
### Added
- 프로젝트용 README.md 최초 작성

---

## [2025-11-30]
### Fixed
- 토큰 재발급 기능 오류 해결

---

## [2025-11-28]
### Added
- Access / Refresh Token 관련 코드 작성
- JWT 로그인 구현
- Spring Security 설정 갱신
- CORS 관련 config 재작성
### Removed
- SecurityConfig에 중복된 코드 제거

---

## [2025-11-27]
### Added
- 로그인/회원가입 기본 기능
- 비밀번호 암호화
- 회원가입 DTO
- 회원 엔티티 생성
- 기본 세션 로그인 방식
### Updated
- Security 기본 설정 수정
- JWT 설정 및 JWT 관련 코드 추가

---

## [2025-11-24]
### Added
- 정적 배포 (dist)

---

## [2025-11-20]
### Updated
- 개발/배포 환경 분리 yml 설정
### Removed
- yml 파일 Git 추적에서 제거

---

## [2025-11-17]
### Added
- MemoRequestDto 생성

---

## [2025-11-15]
### Added
- .gitignore 파일 설정
- MySQL 설정 적용

---

## [2025-11-14]
### Updated
- 프론트 연결 및 memo 생성자 수정
### Removed
- secret.yml 파일 삭제

---

## [2025-10-29]
### Added
- QueryDSL 의존성 및 설정
- 카테고리 기반 조회 기능 (findByCategory)

---

## [2025-10-28]
### Added
- 카테고리 기능 추가 (2회)

---

## [2025-10-24]
### Security
- 노출된 API Key 재발급

### Added
- MapMemo 프로젝트 초기 생성 (2회 커밋)

