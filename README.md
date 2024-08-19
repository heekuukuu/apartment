아파트 커뮤니티 서비스

### 1) 회원가입 및 로그인 기능

-  회원 유형
  - [ ] **관리자**
    - [ ] 아파트 관리사무소 직원만 가입 가능.
    - [ ] 가입 시 사원번호를 입력해야 함.
  - [ ] **주민**
    - [ ] 아파트 세대에 거주하는 주민이 가입 가능.
    - [ ] 세대당 하나의 계정만 생성 가능.
- 로그인 및 로그아웃
  - [ ] 로그인한 사용자만 게시물 및 댓글 작성, 수정, 삭제 가능.
  - [ ] 로그인 상태에 따라 접근 권한을 제어.

### 2) 게시글 관리 기능

- [ ] 게시글 CRUD
  - [ ] **주민**
    - [ ] 본인이 작성한 게시글에 대한 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능 제공.
  - [ ] **관리자**
    - [ ] 모든 사용자의 게시글에 대한 CRUD 권한.
    - [ ] 관리자는 새로운 모임 카테고리 생성 가능.
- [ ] 게시물 검색
  - [ ] 제목, 내용, 작성자 등으로 게시물을 검색할 수 있는 기능.

### 3) 댓글 관리 기능

- [ ] 댓글 CRUD
  - [ ] **주민**
    - [ ] 본인이 작성한 댓글에 대한 CRUD 기능 제공.
  - [ ] **관리자**
    - [ ] 모든 댓글에 대한 CRUD 권한.

### 4) 공지사항 관리

- [ ] 공지사항 작성 및 조회
  - [ ] **관리자**
    - [ ] 공지사항 작성 및 수정 가능.
  - [ ] 모든 사용자는 공지사항을 조회할 수 있음.