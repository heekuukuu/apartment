package com.example.apartment.service;

import com.example.apartment.model.Employee;
import com.example.apartment.model.User;
import com.example.apartment.respository.ApartmentRepository;
import com.example.apartment.respository.BoardRepository;
import com.example.apartment.respository.CommentRepository;
import com.example.apartment.respository.EmployeeRepository;
import com.example.apartment.respository.UserRepository;
import com.example.apartment.type.BoardStatus;
import com.example.apartment.type.CommentStatus;
import com.example.apartment.type.LoginType;
import com.example.apartment.type.UserRole;
import com.example.apartment.type.UserStatus;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encode;

  @Autowired
  private ApartmentRepository apartmentRepository;
  @Autowired
  private BoardRepository boardRepository;
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private EmployeeRepository employeeRepository;


  @Transactional
  public void signUp(User user) {
    // 이메일 중복 확인
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("이미 가입된 이메일입니다.");
    }

    // 비밀번호 암호화
    String rawPassword = user.getPassword();
    String encodedPassword = encode.encode(rawPassword);
    user.setPassword(encodedPassword);

    // 기본 로그인 타입 및 역할 설정
    user.setLoginType(LoginType.GENERAL);
    user.setRole(UserRole.USER);

    try {
      // 사용자 저장
      userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      // 예외 처리: 중복된 데이터가 있을 경우
      throw new IllegalStateException("데이터베이스 오류 발생", e);
    }
  }

  @Transactional(readOnly = true)
  public User findUser(String username) {
    User user = userRepository.findByUsername(username).orElseGet(User::new);
    return user;
  }

  @Transactional(readOnly = true)
  public User findUserByEmail(String email) {
    System.out.println("Searching for user with email: " + email); // 로그로 이메일 확인
    // 이메일이 아닌 사용자 이름이 반환될 경우, 로그로 확인
    System.out.println("Current user email: " + email);
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 사용자를 찾을 수 없습니다."));

  }

  @Transactional
  public void updateBoardAndCommentStatus(Long userId, BoardStatus boardStatus,
      CommentStatus commentStatus) {
    // 게시물 상태 변경
    boardRepository.updateBoardStatusByUserId(userId, boardStatus);

    // 댓글 상태 변경
    commentRepository.updateCommentStatusByUserId(userId, commentStatus);
  }


  @Transactional
  public void updateUserInfo(User user) {
    long id = user.getId();
    User currUser = userRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("failed to load User Info : cannot find User id"));

    // 유저 정보 업데이트, 비밀번호 암호화 후 저장
    if (currUser.getLoginType() == LoginType.GENERAL) {
      String rawPassword = user.getPassword();
      String encodePassword = encode.encode(rawPassword);
      currUser.setPassword(encodePassword);
      currUser.setEmail(user.getEmail());
    }

    userRepository.save(currUser); // 변경된 정보 저장
  }


  // 권한 변경 메서드
  public String changeUserRole(String email, String employeeNumber, UserRole role) {
    // 입력된 이메일로 사용자가 있는지 확인
    Optional<User> userOptional = userRepository.findByEmail(email);
    if (userOptional.isEmpty()) {
      return "해당 이메일로 등록된 사용자가 없습니다.";
    }

    // 입력된 이메일과 사원 번호가 Employee 테이블에 있는지 확인
    Optional<Employee> employeeOptional = employeeRepository.findByEmailAndEmployeeNumber(email,
        employeeNumber);
    if (employeeOptional.isEmpty()) {
      return "이메일 또는 사원 번호가 올바르지 않습니다.";
    }

    User user = userOptional.get();
    user.setRole(role); // 사용자의 권한 변경
    userRepository.save(user); // 변경된 권한 저장

    // 직원의 역할 변경
    Employee employee = employeeOptional.get();
    employee.setRole(role); // 직원의 역할 변경
    employeeRepository.save(employee); // 변경된 직원 정보 저장

    return "권한이 성공적으로 변경되었습니다.";
  }

  public String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      // 일반 로그인일 경우
      if (principal instanceof UserDetails) {
        return ((UserDetails) principal).getUsername(); // 이메일 반환
      }
      // OAuth2 로그인일 경우
      else if (principal instanceof OAuth2User) {
        Map<String, Object> attributes = ((OAuth2User) principal).getAttributes();

        // 속성 로그 출력 (디버깅)
        System.out.println("OAuth2 Attributes: " + attributes);

        // 네이버 로그인의 경우 'response' 안에 속성이 담겨 있음
        if (attributes.containsKey("response")) {
          Map<String, Object> response = (Map<String, Object>) attributes.get("response");
          return (String) response.get("email"); // 네이버 로그인 사용자 이메일 반환
        }

        return (String) attributes.get("email"); // 일반 OAuth2 로그인 사용자 이메일 반환
      }
    }
    return null; // 인증되지 않은 경우
  }

  @Transactional
  public void deleteUser(String email) {
    // 사용자를 이메일로 조회
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

    // 사용자의 상태에 따라 삭제 방식 결정
    if (user.getStatus() == UserStatus.ACTIVE) {
      // 활성화된 사용자는 물리적 삭제
      deleteUserPhysically(user);
    } else if (user.getStatus() == UserStatus.DELETED) {
      // 탈퇴한 사용자는 논리적 삭제 (상태만 변경)
      deleteUserLogically(user);
    } else {
      throw new IllegalArgumentException("유효하지 않은 사용자 상태입니다.");
    }
  }

  private void deleteUserPhysically(User user) {
    // 활성화된 사용자의 경우 물리적 삭제
    boardRepository.deleteAllByUser(user);  // 사용자의 모든 게시글 삭제
    commentRepository.deleteAllByUser(user); // 사용자의 모든 댓글 삭제

    // 사용자의 물리적 삭제
    userRepository.delete(user); // 사용자 삭제
  }

  private void deleteUserLogically(User user) {
    // 탈퇴된 사용자의 경우 논리적 삭제 (상태만 변경)
    boardRepository.updateBoardStatusByUserId(user.getId(), BoardStatus.DELETED);
    commentRepository.updateCommentStatusByUserId(user.getId(), CommentStatus.DELETED);

    // 사용자의 상태를 'DELETED'로 변경
    user.setStatus(UserStatus.DELETED); // 상태를 'DELETED'로 변경
    userRepository.save(user); // 상태 변경 저장
  }
}





