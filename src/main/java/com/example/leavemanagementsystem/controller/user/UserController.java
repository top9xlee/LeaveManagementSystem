package com.example.leavemanagementsystem.controller.user;

import com.example.leavemanagementsystem.config.*;
import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.service.absenceService.*;
import com.example.leavemanagementsystem.service.appRole.*;
import com.example.leavemanagementsystem.service.department.*;
import com.example.leavemanagementsystem.service.email.*;
import com.example.leavemanagementsystem.service.page.*;
import com.example.leavemanagementsystem.service.userRole.*;
import com.example.leavemanagementsystem.service.userService.*;
import com.example.leavemanagementsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.security.*;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    PageService pageService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    AppRoleService appRoleService;

    @Autowired
    AbsenceMapper absenceMapper;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    AbsenceService absenceService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;

    @GetMapping("/user")
    public String getAbsence(Model model) {
        return "redirect:/user/1";
    }

    @GetMapping("/user/{page}")
    public String getPageAbsence(Model model, Principal principal, @PathVariable(name = "page") int pageNum)
            throws Exception {

        String nameUser = principal.getName();
        UserEntity user = userRepository.findByUserName(nameUser);
        if (user.getRole() == 1) {
            return "redirect:/export";
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setJobName(user.getJobName());
        PageResponse response = userService.getAllByUserId(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE, pageNum, user);
        NewOutput newOutput = pageService.page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE, pageNum, response);
//        List<AbsenceResponse> absence = absenceRepositoryCustom.findAllByUserId(user.getUserId()).stream().map(absenceMapper::maptoDto2).collect(Collectors.toList());
//        user.getEncrytedPassword();
//        List<AbsenceResponse> absence = absenceRepositoryCustom.findAllByUserId(user.getUserId());

        Department department1 = departmentRepository.findByHeadId(user.getUserId());
        if (department1 == null) {
//            DepartmentResponse departmentResponse = departmentService.getDepartment(department.getDepartmentId());
            model.addAttribute("newOutput", newOutput);
            model.addAttribute("users", userResponse);
            model.addAttribute("absence", newOutput.getResponse().getObj());
//            model.addAttribute("department", departmentResponse);
            return "user";

        }
        DepartmentResponse departmentResponse = departmentService.getDepartmentOfManager(department1.getHeadId());
        int numOfRequestUnapproved = absenceService.countByStatusAndDepaId(0, departmentResponse.getDepartmentId());
        userResponse.setNumberOfNotEnable(numOfRequestUnapproved);
        model.addAttribute("newOutput", newOutput);
        model.addAttribute("users", userResponse);
        model.addAttribute("absence", newOutput.getResponse().getObj());
        model.addAttribute("department", departmentResponse);
        return "user";
    }


    @GetMapping("/forget-password")
    public String getForgotPasswordPage() {
        return "forgetPassword";
    }

    @PostMapping("/doForget")
    public String getPostRejectedDepartmentId(@ModelAttribute ForgotPasswordRequest forgotPasswordRequest,
                                              HttpServletRequest request) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if (userEntity == null) {
            return "redirect:/forget-password?error=emailNotFound";
        }
        userService.setVerify(userEntity.getUserId());
        String siteURL = Utiliy.getSiteURL(request);

        emailService.send(userEntity, siteURL);

//        List<AbsenceResponse> absenceResponse = absenceRepositoryCustom.findByAbsenceId(67L);
//        Absence absence = absenceRepository.findByAbsenceId(67L);
//        DepartmentResponse departmentResponse = departmentService.getDepartment(3L);
//
//        UserEntity userEntity1 = userRepository.findById(4L).get();
//        emailService.send(userEntity1,departmentResponse,absence);

        return "redirect:/forget-password?status=success";
    }

    @GetMapping("/verify/{code}")
    public String verify(@PathVariable String code, Model model) {
        boolean verifed = userService.verify(code);
        if (!verifed) {
            return "Mã xác nhận thất bại. Vui lòng kiểm tra lại";
        }
        UserEntity userEntity = userService.getInfoVerify(code);
        model.addAttribute("users", userEntity);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getRePassword())) {
            return "redirect:/forget-password?error=passwordNotMatch";
        }
        Long a = Long.valueOf(changePasswordRequest.getUserId());
        UserEntity userEntity = userRepository.findByUserId(a);
        userEntity.setEncrytedPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getPassword()));
        userEntity.setVerificationCode(null);
        userRepository.save(userEntity);

        return "redirect:/login?status=success";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("appUserDTO", new AppUserDto());
        List<DepartmentResponse> departments = departmentService.getListDepartment();

        List<AppRole> appRoles = appRoleService.getListRole();
        model.addAttribute("appRoles", appRoles);
        model.addAttribute("departments", departments);
        return "userEntity";
    }

    @PostMapping("/register")
    public String createProduct(@ModelAttribute AppUserDto appUserDto, HttpServletRequest request, Model model) {
        if (userService.checkUserName(appUserDto)) {
            return "redirect:/register?error=fail_userName";
        }
        String name = appUserDto.getEmail();
        name = name.concat("@vconnex.vn");
        appUserDto.setEmail(name);
        if (userService.checkEmail(appUserDto)) {
            return "redirect:/register?error=fail_mail";
        }
        if (userService.checkPhoneNumber(appUserDto)) {
            return "redirect:/register?error=fail_phoneNumber";
        }
        if (!appUserDto.getEncrytedPassword().equals(appUserDto.getRepassword())) {
//            return "redirect:/changePassword?error=passwordNotMatch";
        }
        UserEntity userEntity = appUserDto.mapperAppUser();
        userService.createAppUser(userEntity);
        AppRole appRole = appRoleRepository.findByRoleId(appUserDto.getRoleId());
        UserRole userRole = new UserRole();
        userRole.setUser(userEntity);
        userRole.setAppRole(appRole);

        userRoleService.save(userRole);

        return "redirect:/register?status=success";
    }

    @GetMapping("/changepw")
    public String Changepw(Model model, Principal principal) {
        String name = principal.getName();
        UserEntity user = userRepository.findByUserName(name);
        DepartmentResponse departmentResponse = departmentService.getDepartment(user.getDepartment().getDepartmentId());
//        List<DepartmentResponse> departments = departmentService.getListDepartment();
        model.addAttribute("appUserDTO", new AppUserDto());
        model.addAttribute("departmentResponse", departmentResponse);
        model.addAttribute("user", user);
        return "changepw";
    }

    @PostMapping("/changepw")
    public String Changepw(Model model, @ModelAttribute AppUserDto appUserDto) {
        if (!appUserDto.getNewPassword().equals(appUserDto.getRepassword())) {
            return "redirect:/changepw?error=fail";
        }
        Boolean a = userService.updatePw(appUserDto);
        if (a == true) {
            return "redirect:/changepw?status=success";
        } else return "redirect:/changepw?error=fail";
    }

    @GetMapping("/logout")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "loginPage";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getAllUserEntityEnable")
    public String getAllUserEntityEnable(Model model) {
        return "redirect:/getAllUserEntityEnable/1";
    }

    @RequestMapping(value = "/getAllUserEntityEnable/{page}", method = RequestMethod.GET)
    public String get(@PathVariable("page") int pageNum, Model model) {
        Sort sort = Sort.by("department.departmentId").descending();
        PageResponse response = userService.getAllUserEntityEnable(Constants.STATUS_USE.LIMIT_PAGE_USER, pageNum, sort);
        NewOutput newOutput = pageService.page(Constants.STATUS_USE.LIMIT_PAGE_USER, pageNum, response);
        if (newOutput.getResponse().getCode() != 1) {
            return "403";
        }
        List<Department> departments = departmentRepository.findAll();
        List<AppRole> appRoles = appRoleRepository.findAll();
        model.addAttribute("newOutput", newOutput);
        model.addAttribute("userEntityDtos", newOutput.getResponse().getObj());
        model.addAttribute("departments", departments);
        model.addAttribute("appRoles", appRoles);
        return "listUser";
    }

    @PostMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, @ModelAttribute UserEntityDto userEntityDto) throws Exception {
        int page = 0;
        if (userEntityDto.getNumOfList() != 0) {
            page = userEntityDto.getPage();
        } else if (userEntityDto.getNumOfList() == 0) {
            if (userEntityDto.getPage() == 1) {
                page = userEntityDto.getPage();
            } else {
                page = userEntityDto.getPage() - 1;
            }
        }
        Response res = userService.deleteUserEntity(id);
        return "redirect:/getAllUserEntityEnable/" + page + "?status=del_success";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute UserEntityDto userEntityDto, @PathVariable Long id) throws Exception {

        Response res = userService.updateUserEntity(userEntityDto, id);
        if (res.getCode() != 1) {
            return "403";
        }
        return "redirect:/getAllUserEntityEnable/" + userEntityDto.getPage() + "?status=success";
    }


}
