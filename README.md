# ESTSOFT 2nd Project
---
# Cookudasse Project 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4169E1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=AmazonAWS&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">



> 다양한 음식과 그에 해당하는 조리법이 궁금한 사람들을 위한 커뮤니티

## 📖Description

### 주제

- 다양한 음식 레시피를 공유하고 탐색할 수 있는 스프링부트 웹 어플리케이션

### 기획 의도

- 다양한 전통 음식과 요리법을 소개하여 사용자들이 보다 많은 요리에 대해 배울 수 있도록 합니다.
- 사용자들이 자신의 레시피를 공유하고 다른 사람들과 소통할 수 있는 커뮤니티 형성을 목표로 합니다.
- 특정 종류(카테고리: 면, 밥, 디저트 등)를 기준으로 레시피를 쉽게 검색하고 필터링할 수 있도록 합니다.



### 1. 💾**개발 환경**
![개발환경](https://github.com/user-attachments/assets/e4d258b9-2623-4b15-87ca-2adb51daaecb)
- Java JDK 17, JavaScript
- 프론트엔드 : HTML, CSS, JS
- 백엔드 : Spring Boot
- 데이터베이스 : MySQL
- ORM : JPA
- 배포환경 : AWS EC2, RDS, Docker
- 협업도구 : GitHub, Notion, DB diagram, Figma

## 2. **기능 명세서**
- 게시판   
![글 목록](https://github.com/user-attachments/assets/b4f2073b-be2a-406d-86a6-82658bc11482)   
![글 쓰기](https://github.com/user-attachments/assets/4e9bdaf4-4420-4c02-a74d-f244919c5a2b)     
![글 수정](https://github.com/user-attachments/assets/cb0d5c89-1937-47be-a198-70b73e43e9ac)     
![글 삭제](https://github.com/user-attachments/assets/b403f193-1bfc-4a19-8512-875708946f8f)      
![글 조회 페이지](https://github.com/user-attachments/assets/f737446a-8b12-490d-807d-157637555323)   

- 회원 및 관리자   
![로그인](https://github.com/user-attachments/assets/18f690d3-022e-4a32-8f31-914fe1b63b62)   
![회원가입](https://github.com/user-attachments/assets/16ab975b-6bfc-444d-a255-b677559721dc)   
![로그아웃 탈퇴](https://github.com/user-attachments/assets/7e36707c-27b6-4395-8018-90a25abacf2b)   
![관리자 페이지](https://github.com/user-attachments/assets/7a9c55c2-78a2-4316-8e04-251e550ea340)   


## 3. ✨UI(화면) 설계서 
- [피그마 링크 바로가기](https://www.figma.com/design/aozGh2OXMbjzGZTOw2yqdf/Food%2FCooking-Recipe-website-design-(Community)?node-id=0-3&t=rwlxgjsQjsXHZpRp-0)

|                                                                                                                       |                                                                                                                   |
|-----------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| Main Page (Member)                                                                                                    | Main Page (Guest)                                                                                                 |
| ![요리조리 멤버 홈페이지](https://github.com/user-attachments/assets/e9974255-f138-41f6-adc1-54690ae1e452) | ![게스트 화면](https://github.com/user-attachments/assets/87f8722d-4eeb-4b61-a61a-938c878e3364)|
| SignUp Page                                                                                              | SignIn Page  
| ![요리조리 회원가입](https://github.com/user-attachments/assets/ad287ff9-46e5-4bab-a328-a0636b113f4d)      | ![요리조리 로그인](https://github.com/user-attachments/assets/3d0895cb-baba-4556-8215-e49ac44fff5b)                           |
| MyPage Info Select                                                                                        | Modify-info Page                                                                                                  |
| ![요리조리 마이페이지 정보 선택](https://github.com/user-attachments/assets/6849807d-486a-4dd9-9446-229def53e687)       | ![요리조리 마이페이지 정보수정](https://github.com/user-attachments/assets/26049d18-c90f-4524-9a1a-1689aeef3b57) |
| Find ID / Password Page                                                                                               | Manage Member Authorization                                                                                       |
| ![요리조리 아이디비번 찾기](https://github.com/user-attachments/assets/e0d001e7-9666-4e45-80a2-fc3a97fe882e) | ![요리조리 회원 권환 관리](https://github.com/user-attachments/assets/f3a70b2d-59be-4484-b8ec-b32d4cddbb8a)    |
| Board Write Page                                                                                             |
| ![요리조리 게시글 생성 화면](https://github.com/user-attachments/assets/870cc776-8e68-4d64-af6f-921d5b35924a) |

## 4. 📖개발일정
![개발 일정표](https://github.com/user-attachments/assets/b7326bec-829f-4b05-812f-4b94a468b820)

### 🌐 Front-End
```
📁 src
└── 📁 main/resources
     ├── 📁 static
     |     ├── 📁 css
     |     |    ├── 📃 admin.css
     |     |    ├── 📃 home.css
     |     |    ├── 📃 login.css
     |     |    ├── 📃 logo.png
     |     |    ├── 📃 noticeStyle.css
     |     |    ├── 📃 searchIcon.css
     |     |    └── 📃 style.css
     |     ├── 📁 img
     |     |    └── 📃 mouse.png
     |     └── 📁 js
     |          ├── 📃 admin.js
     |          ├── 📃 home.js
     |          ├── 📃 login.js
     |          └── 📃 script.js
     └── 📁 templates
           ├── 📁 comment
           |    ├── 📁 css
           |    |    └── 📃 comment.css
           |    └── 📁 html
           |    |    └── 📃 comment.html
           ├── 📁 search
           |    ├── 📁 css
           |    |    └── 📃 search.css
           |    ├── 📁 html
           |    |    └── 📃 search.html
           |    ├── 📁 img
           |    |    ├── 📃 food.png
           |    |    ├── 📃 mouse.png
           |    |    └── 📃 searchIcon-black.png
           |    └── 📁 script
           |         └── 📃 search.js
           ├── 📃 admin.html
           ├── 📃 editNotice.html
           ├── 📃 editPost.html
           ├── 📃 find.html
           ├── 📃 home.html
           ├── 📃 login.html
           ├── 📃 noticeDetail.html
           ├── 📃 postDetail.html
           ├── 📃 writeNotice.html
           └── 📃 writePost.html

```
### ⚙️ Back-End
```
📁 src
├── 📁 admin
│   ├── 📁 application
|   |   └── 📃 AdminService.java
│   └── 📁 dto
|   |   ├── 📃 AdminRequest.java
|   |   └── 📃 AdminResponse.java
│   └── 📁 presentation
|       └── 📃 AdminController.java
├── 📁 auth
│   ├── 📁 controller
|   |   └── 📃 AuthController.java
│   ├── 📁 domain
|   |   ├── 📃 Role.java
|   |   └── 📃 User.java
│   ├── 📁 dto
|   |   ├── 📃 FindPasswordRequest.java
|   |   ├── 📃 LoginRequest.java
|   |   └── 📃 SignupRequest.java
│   ├── 📁 repository
|   |   └── 📃 UserRepository.java
│   └── 📁 service
|       └── 📃 UserService.java
├── 📁 common
│   ├── 📁 entity
|   |   └── 📃 BaseEntity.java
│   └── 📁 handle
|       └── 📃 AuthInterceptor.java
├── 📁 config
│   └── 📁 jpa
|   |   └── 📃 JpaConfig.java
│   └── 📁 web
|       └── 📃 WebConfig.java
├── 📁 exception
|   └── 📃 GlobalExceptionHandler.java
├── 📁 home/controller
│   └── 📃 HomeController.java
├── 📁 notice
│   ├── 📁 application
|   |   └── 📃 NoticeService.java
│   ├── 📁 domain
|   |   └── 📃 Notice.java
│   ├── 📁 dto/request
|   |   └── 📃 NoticeRequest
│   ├── 📁 infrastructure
|   |   └── 📃 NoticeRepository.java
│   └── 📁 presentaion
|       └── 📃 NoticeController.java
└── 📁 post
    ├── 📁 controller
    |   ├── 📃 PostController.java
    ├── 📁 dto
    |   ├── 📁 request
    |   │   └── 📃 PostRequest.java
    |   └── 📁 request
    |        └── 📃 PostSaveResponse.java
    ├── 📁 entity
    |   ├── 📃 FoodCategory.java
    |   ├── 📃 Post.java
    |   └── 📃 PostDetail.java
    ├── 📁 repository
    |   ├── 📃 PostDetailRepository.java
    |   └── 📃 PostRepository.java
    └── 📁 service
        └── 📃 PostService.java


```

## 🏭System Structure
![시스템 구조](https://github.com/user-attachments/assets/c89728f8-868a-450e-873b-17fd510c5ba8)

## 🔐ERD Structure
![erd-diagram](https://github.com/user-attachments/assets/0f301366-3090-46d4-95d4-6f366251009a)

## ♒배포 흐름도
![배포 흐름도](https://github.com/user-attachments/assets/46e6bb59-14c9-4b89-84c4-65e14630f7ee)

## 🎈API 명세서

### 🧑‍🍳 글 목록 조회 / 댓글
| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| getAllPost | GET | /api/posts | 게시판 글 목록 조회 |  |  |  |
| getAllCommentsById | GET | /api/posts/{post_id} | 게시물의 전체 댓글 불러오기 |  |  |  |
| createComment | POST | /api/posts/{post_id}/comment | 해당 게시글에 댓글 생성 |  |  |  |
| updateComment | PATCH  | /api/posts/{post_id}/comment/{id} | 해당 게시글의 해당 댓글 수정 |  |  |  |
| deleteComment | DELETE  | /api/posts/{post_id}/comment/{id} | 해당 게시글의 해당 댓글 삭제 |  |  |  |

### 🧑‍🍳 정보 조회 / 수정

| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| confirmPassword | POST | /api/users/{pasword} | 비밀번호 확인 |  |  |  |
| changePassword | PUT | /api/users/{pasword} | 비밀번호 변경 |  |  |  |
| getUserInfo | GET | /api/users | 사용자 정보 조회 |  |  |  |
| getUserPost | GET | /api/posts/{user_id} | 자신의 게시글 목록 조회 |  |  |  |
| getUserComment | GET | /api/comments/{user_id} | 자신의 댓글 목록 조회 |  |  |  |
| deleteUser | DELETE | /api/users | 회원탈퇴 |  |  |  |

### 🧑‍🍳 관리자

| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| getUserId | GET | /api/users/{id} | 특정 회원의 아이디 조회 |  |  |  |
| modifyAuthority | PUT | /api/auth/{auth} | 특정 회원의 권한 수정 |  |  |  |

### 🧑‍🍳 공지사항

| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| createNotice | POST | /api/notice | 공지사항 쓰기 |  |  |  |
| getNotice | GET | /api/notice/{notice_id} | 공지사항 조회 |  |  |  |
| modifyNotice | PUT | /api/notice/{notice_id} | 공지사항 수정 |  |  |  |
| deleteNotice | DELETE | /api/notice/{notice_id} | 공지사항 삭제 |  |  |  |
| getAllNotice | GET | /api/notices | 공지사항 목록 |  |  |  |

### 🧑‍🍳 게시글 조회/ 작성/ 수정/ 삭제

| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| getPost | GET | /api/post/{postId} | 게시글 조회 |  |  | postId: 게시글 번호 |
| writePost | POST  | /api/post | 게시글 작성 |  |  |  |
| modifyPost | POST  | /api/post/{postId} | 게시글 수정 |  |  |  |
| deletePost | DELETE  | /api/posts/{postId} | 게시글 삭제 |  |  |  |

### 🧑‍🍳 로그인 / 회원가입

| 🏷 NAME | ⚙ METHOD | 📎 URL | 📖 DESCRIPTION |
| --- | --- | --- | --- | 
| login | POST  | /api/users/login | 사용자 로그인 | { "id": string, "password": string } |  |  |
| signup | POST  | /api/users/signup | 회원가입 |  |  |  |
| findId | GET | /api/users/find-id | 아이디 찾기 |  |  |  |
| findPassword | POST  | /api/users/find-password | 비밀번호 찾기 |  |  |  |

## 🎞시연 영상
---

https://github.com/user-attachments/assets/ac982001-9f58-4587-812e-8742355a9cc3


https://github.com/user-attachments/assets/b92e2f32-8812-4194-9f33-5b24e20c112a


https://github.com/user-attachments/assets/f083f64f-840f-48e5-9208-7830f7021c62


https://github.com/user-attachments/assets/cec5d63c-8d8b-42a6-a15c-08fb80474d6b




## 🛠Coding Convention

[코딩 컨벤션](https://www.notion.so/oreumi/b127ced1b2e746f38382d8f0526adc75)

## 👨‍💻Participation Member
- 오한얼
- 백의헌
- 박성환
- 유석호
- 이재준

