package com.example.leavemanagementsystem.controller.absence;

import com.example.leavemanagementsystem.config.*;
import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.exception.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.service.absenceService.*;
import com.example.leavemanagementsystem.service.department.*;
import com.example.leavemanagementsystem.service.userService.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import java.io.*;
import java.security.*;
import java.util.*;

@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
@Controller
public class AbsenceController {

    @Autowired
    AbsenceService absenceService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/updateAbsence")
    public String updateAbsence(Model model, Principal principal, @ModelAttribute AbsenceRequest absence)
            throws Exception {
        boolean a = absenceService.update(absence);
        if (a = false) {
            return "redirect:/user?failed";
        } else {
            return "redirect:/user/" + absence.getPage() + "?status=update_success";
        }
    }

    @GetMapping("create-absence")
    public String createPost(Model model, Principal principal) {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        if (userEntity.getRole() == Constants.STATUS_USE.ROLE_MANAGER) {
            Department department = departmentRepository.findByHeadId(userEntity.getUserId());
            List<UserEntity> users = userService.getListUserByDepartment(department.getDepartmentId());
            model.addAttribute("user", userEntity);
            model.addAttribute("users", users);
            return "createPost";
        } else {
            List<UserEntity> users = userService.getListUserByDepartment(userEntity.getDepartment().getDepartmentId());
            for (Iterator<UserEntity> it = users.iterator(); it.hasNext(); ) {
                UserEntity user = it.next();
                if (user.getFullName().equals(userEntity.getFullName())) {
                    it.remove();
                }
            }
            model.addAttribute("user", userEntity);
            model.addAttribute("users", users);
            return "createPost";
        }
    }


    @PostMapping("createAbsence")
    public String createAbsence(@ModelAttribute AbsenceRequest absence, Principal principal)
            throws MessagingException, UnsupportedEncodingException {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        String a = "Đơn xin nghỉ phép ngày " + absence.getTitle();
        absence.setTitle(a);
        absenceService.save(absence, userEntity);
        return "redirect:/user/1?status=success";

    }
//    @PostMapping("/test/PersonSubmit")
//    public ResponseEntity<Void> createAbsence1(@RequestBody AbsenceRequest absence, Principal principal) throws MessagingException, UnsupportedEncodingException {
//        String name = principal.getName();
//        UserEntity userEntity = userRepository.findByUserName(name);
////        absenceService.save(absence, userEntity);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

//    @RequestMapping(value = "enable-absence/{id}", method = RequestMethod.GET)
//    public String enableAbsence(@PathVariable Long id, Principal principal) throws Exception {
//        String name = principal.getName();
//        UserEntity userEntity = userRepository.findByUserName(name);
//        absenceService.enablePost(id, userEntity);
//        return "redirect:/department?status=success";
//    }

    @RequestMapping(value = "/delete-absence/{id}", method = RequestMethod.GET)
    public String deleteAbsence(@PathVariable Long id, Principal principal, @ModelAttribute AbsenceRequest absence)
            throws Exception {
        int page = 0;
        if (absence.getNumOfList() != 0) {
            page = absence.getPage();
        } else if (absence.getNumOfList() == 0) {
            if (absence.getPage() == 1) {
                page = absence.getPage();
            } else {
                page = absence.getPage() - 1;
            }
        }
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        boolean a = absenceService.deleteAbsence(id, userEntity.getUserId());

        if (a == true)
            return "redirect:/user/" + page + "?status=del_success";
        else
            return "redirect:/user/" + page + "?status=del_success";

    }


    @GetMapping("/hide-absence/{id}")
    public String hideAbsence(@PathVariable Long id, Principal principal, @ModelAttribute AbsenceRequest absences)
            throws Exception {
        int page = 0;
        if (absences.getNumOfList() != 0) {
            page = absences.getPage();
        } else if (absences.getNumOfList() == 0) {
            if (absences.getPage() == 1) {
                page = absences.getPage();
            } else {
                page = absences.getPage() - 1;
            }
        }
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        Absence absence = absenceRepository.findById(id)
                .orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + id));
        if (userEntity.getUserId() == absence.getUserId()) {
            absenceService.hideAbsence(absence);
            return "redirect:/user/" + page + "?status=hide_success";
        } else
            return "redirect:/403";

    }
}
