## 개요
커뮤니티 과제

**목적**: 아파트 관리 직원과 세대 주민 사이의 커뮤니티를 원활하게 도와주는 커뮤니티 서비스

---

### 회원 유형

#### 1. 관리자 🏙
- **가입 조건**:

  - [x] 일반주민 으로 가입후 사원인증을 통해 권한변경
  - [x] 아파트 관리사무소 직원만 가입 가능하며, 사원번호(6자리)로 자격을 인증
  - [x] `employees` 테이블에 미리 사원번호와 관련 정보를 입력하여 자격 인증
- **가입 유형**:
  - [x] 로그인 타입: **General**, **Kakao**, **Naver**, **Google** 
- **권한**:
  - [x] **전체 게시글 및 댓글**에  삭제 권한 보유
  - [ ] **새로운 게시판 카테고리 생성** 권한 (추후 구현 예정)

---

#### 2. 주민 🧍‍♀️🧍‍♂️
- **가입 조건**:
  - [x] 이메일 기준으로 하나의 계정만 생성 가능, 중복 가입 불가
- **가입 유형**:
  - [x] 로그인 타입: **General**, **Kakao**, **Naver**, **Google** 
- **권한**:
  - [x] **본인이 작성한 게시글 및 댓글**에 대한 작성, 조회, 수정, 삭제 권한

---

### 게시글 관리

- **게시글 작성**:
  - [x] 게시글은 **2048자 이내**로 작성 가능
- **게시글 조회**:
  - [x] 제목 또는 게시글 내용을 통해 특정 게시글을 조회할 수 있음
- **게시글 수정 및 삭제**:
  - [x] 사용자는 **자신의 게시글**만 수정 및 삭제할 수 있음
  - [x] 관리자는 **모든 게시글**에 대해 삭제 권한을 가짐
- **페이지네이션**:
  - [x] 게시글 목록은 한 페이지당 **20개의 게시글**이 표시되도록 페이지네이션 적용
  - [x] 페이지 번호는 한 번에 최대 **10개까지만 표시**되며, 그 이후 페이지는 **'다음'** 버튼을 통해 넘어갈 수 있음
  - [x] **'이전'** 버튼을 통해 이전 페이지 그룹으로 돌아갈 수 있음

---

### 댓글 관리

- **댓글 작성**:
  - [x] 댓글은 **100자 이내**로 작성 가능
- **댓글 조회**:
  - [x] 댓글 목록은 **페이지네이션**이 적용되어 한 페이지에 일정 수의 댓글이 표시됨
- **댓글 수정 및 삭제**:
  - [x] 사용자는 **자신이 작성한 댓글**만 수정 및 삭제할 수 있음

---

### 계정 관리

- **주민 계정 관리**:
  - [ ] 주민은 자신의 계정만 **삭제**할 수 있음 (탈퇴)
- **관리자 계정 관리**:
  - [ ] 관리자는 **모든 계정**을 삭제할 수 있는 권한을 가짐 (추후 구현 예정)

---

## 관리자 전환 기능

- **관리자 전환**:
  - [x] 모든 사용자는 기본적으로 **주민("USER")**으로 가입됨
  - [x] 프로필 페이지에서 **"관리자로 전환"** 버튼을 클릭하여 관리자로 전환 가능
  - [x] 관리자로 전환하기 위해서는 **사원번호(6자리)**를 입력하여 자격을 인증
  - [x] 인증이 완료되면, 사용자의 권한이 **"ADMIN"**으로 변경됨
  - [x] 인증 실패 시, **관리자 전환**이 불가능하며, 오류 메시지가 표시됨

---

### 관리자 전환 과정

1. **사용자 프로필 페이지**에서 **"관리자로 전환"** 버튼을 클릭
2. **사원번호(6자리)** 입력
3. **인증 완료** 시, 관리자의 권한이 부여됨
4. 관리자는 게시글, 댓글 관리, 계정 관리 기능을 포함한 다양한 관리 작업을 수행할 수 있음 

---

### 관리자 전환 UI 예시
<img width="500" alt="6B6CA509-E95F-4EE5-8398-A40AD65EC193" src="https://github.com/user-attachments/assets/6688234f-a057-4dec-a55b-3f8dc448bd2e">




### ERD 
<img width="500" alt="D71DC424-CCD8-423D-8714-4FF45CD031CF" src="https://github.com/user-attachments/assets/24cb7bed-5fb4-45e5-9487-64e6acf2e213">

---

## 아파트 관리 기능 추가

### 1. 아파트 정보 수정
- **기능**:
  - 관리자는 등록된 아파트 정보를 수정할 수 있습니다.
  - **이름**과 **주소**를 수정할 수 있으며, 수정된 정보는 DB에 저장됩니다.

### 2. 아파트 수정
- **기능**:
  - 관리자는 등록된 아파트를 수정할 수 있습니다.
  - 유저1명당 1개의 아파트만 등록 가능 합니다.

### 3. 로그인된 사용자의 이메일 기반 아파트 관리
- **기능**:
  - 로그인된 사용자의 이메일을 기반으로 아파트 정보 조회, 생성, 수정 등이 가능합니다.
  - **기존 아파트가 존재하면 수정**, **없으면 새로운 아파트 생성**.

### 4. 아파트 연관 관리
- **기능**:
  - **사용자와 아파트의 연관 설정**: 사용자가 등록한 아파트에 대한 정보를 관리합니다.
 

---

## 아파트 관리 기능 흐름

1. **아파트 등록/수정**:
   - 사용자가 로그인 시, **이메일 기반**으로 아파트가 등록된 상태인지 확인
   - **아파트가 존재하면 수정**, **없으면 새로운 아파트 생성**
   - 사용자의 **이메일을 기준**으로 아파트 정보가 저장됨

2. **아파트 정보 조회**:
   - 로그인된 사용자는 **자신의 아파트** 정보를 조회할 수 있음
   - 아파트가 **없는 경우** 예외를 발생시키며, 경고 메시지를 출력

---

### 아파트 관리 UI 예시

- **아파트 등록 페이지**: 사용자가 로그인 후 **"아파트 등록/수정"** 버튼 클릭하여 아파트 정보를 입력
- **정보 수정 시**: 기존 아파트가 있을 경우 수정 화면으로 이동



<img width="500" alt="751DFC15-0AED-486A-A4EA-915806B225D7" src="https://github.com/user-attachments/assets/fc9d67ef-b8ec-4946-8b57-f1f3245d9ae3">

<img width="500" alt="8300EE2A-B8E6-41A2D-87F1-976D0842BFBE" src="https://github.com/user-attachments/assets/7c22ac73-390d-4ec7-a7ea-eb1f26b502d2">



---

