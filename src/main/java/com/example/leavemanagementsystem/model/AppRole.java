package com.example.leavemanagementsystem.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "app_role")
public class AppRole {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;


//    @OneToMany(mappedBy = "appRole", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<UserRole> userRoles = new ArrayList<>();

//    public AppRole(Long roleId, String roleName) {
//        this.roleId = roleId;
//        this.roleName = roleName;
//    }
//
//    public Long getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(Long roleId) {
//        this.roleId = roleId;
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
}
