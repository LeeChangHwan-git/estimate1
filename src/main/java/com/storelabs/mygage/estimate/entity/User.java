package com.storelabs.mygage.estimate.entity;

import com.storelabs.mygage.estimate.enums.UserStateCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

/**
 * 사용자 정보 도메인
 */

@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, length = 50, unique = true)
    private String userId;

    @Column(length = 100)
    private String encryptedPassword;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String mobile;

    @Column(name = "email_addr", length = 100, unique = true)
    private String email;

    // 현재 역할 ROLE_USER
    @Column(nullable = false)
    private String roleAt;

    @Column(nullable = false, length = 20, columnDefinition = "varchar(10) default '00'")
    private String userStateCode;

    @Column
    private String refreshToken;

    // 다중역할 경우 ROLE_USER|ROLE_BIZUSER
    @Column(nullable = false)
    private String roles;

    @Column(name="login_date")
    private LocalDateTime lastLoginDate;

    @Column(name="fail_count", nullable = false, columnDefinition = "tinyint default 0")
    private Integer loginFailCount;

    @Column(length = 100)
    private String googleId;

    @Column(length = 100)
    private String kakaoId;

    @Column(length = 100)
    private String naverId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "fcm_key")
    private String fcmKey;

    // 휴대폰 인증 unique_key - CI
    @Column(length = 100)
    private String uniqueKey;

    @Builder
    public User(String userId, String email, String encryptedPassword, String name, String roleAt, String userStateCode,
                String googleId, String kakaoId, String naverId, String mobile,
                String fcmKey, String uniqueKey) {
        this.userId = userId;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.name = name;
        this.roleAt = roleAt;
        this.userStateCode = userStateCode;
        this.googleId = googleId;
        this.kakaoId = kakaoId;
        this.naverId = naverId;
        this.mobile = mobile;
        this.fcmKey = fcmKey;
        this.uniqueKey = uniqueKey;
        addRole(roleAt);
    }

    /**
     * 역할 추가
     *
     */
    public void addRole(String roleAt) {
        if(this.roles != null) {
            this.roles+=roleAt;
        } else {
            this.roles = roleAt;
        }
    }

    /**
     * 소셜 사용자 여부 반환
     *
     * @return boolean 소셜 사용자 여부
     */
    public boolean isSocialUser() {
        if (this.googleId != null && !"".equals(this.googleId))
            return true;
        else if (this.kakaoId != null && !"".equals(this.kakaoId))
            return true;
        else if (this.naverId != null && !"".equals(this.naverId))
            return true;
        else if (this.uniqueKey != null && !"".equals(this.uniqueKey))
            return true;

        return false;
    }

    /**
     * 로그인 실패 시 로그인실패수를 증가시키고 5회 이상 실패한 경우 회원상태를 정지로 변경
     *
     * @return User 사용자 엔티티
     */
    public User failLogin() {
        this.loginFailCount = loginFailCount + 1;
        if (this.loginFailCount >= 5) {
            this.userStateCode = UserStateCode.HALT.getKey();
        }
        return this;
    }

    /**
     * 로그인 성공 시 로그인실패수와 마지막로그인일시 정보를 갱신
     *
     * @return User 사용자 엔티티
     */
    public User successLogin() {
        this.loginFailCount = 0;
        this.lastLoginDate = LocalDateTime.now();
        return this;
    }

    /**
     * 현재 역할을 변경한다.
     *
     * @param roleAt
     * @return
     */
    public User updateRoleAt(String roleAt) {
        this.roleAt = roleAt;
        return this;
    }

    /**
     * 프로필 사진을 수정한다.
     *
     * @param avatar	attachment_code
     * @return
     */
    public User updateAvatar(String avatar) {
        this.avatar = avatar;

        return this;
    }

    /**
     * 사용자 비밀번호 정보를 필드에 입력한다.
     *
     * @param encryptedPassword 암호화 비밀번호
     * @return User 사용자 엔티티
     */
    public User updatePassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    /**
     * 사용자 휴대폰 정보를 필드에 입력한다.
     *
     * @param mobile
     * @return
     */
    public User updateMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 사용자 상태 코드 정보를 필드에 입력한다.
     *
     * @param userStateCode 상태 코드
     * @return User 사용자 엔티티
     */
    public User updateUserStateCode(String userStateCode) {
        this.userStateCode = userStateCode;
        return this;
    }
}