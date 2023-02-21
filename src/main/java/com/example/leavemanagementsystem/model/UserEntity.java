package com.example.leavemanagementsystem.model;

import lombok.*;
import org.springframework.security.core.*;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.FetchType.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "encryted_password")
    private String encrytedPassword;

//    @Column(name="ENABLED")
//    private Boolean enabled;

    @Column(name = "VERIFICATION_CODE", length = 64)
    private String verificationCode;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Column(name = "role")
    private List<UserRole> userRoles = new ArrayList<>();

    public UserEntity(Long userId, String encrytedPassword, String userName, int status) {
        this.userId = userId;
        this.encrytedPassword = encrytedPassword;
        this.userName = userName;
        this.status = status;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
    private Department department;
    @Column(name = "jobName")
    private String jobName;


    @Column(name = "role")
    private Long role;
    @Column(name = "status")
    private int status;


    public UserEntity(Long userId, String encrytedPassword, String userName, List<GrantedAuthority> grantList) {
    }

    @Override
    public String toString() {
        return this.userName + "/" + this.encrytedPassword;
    }


//    public UserEntity(String userName, String encrytedPassword, List<GrantedAuthority> grantList) {
//    }
}
