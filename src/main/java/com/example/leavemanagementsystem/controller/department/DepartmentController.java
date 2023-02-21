package com.example.leavemanagementsystem.controller.department;

import com.example.leavemanagementsystem.config.*;
import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.service.absenceService.*;
import com.example.leavemanagementsystem.service.department.*;
import com.example.leavemanagementsystem.service.page.*;
import com.example.leavemanagementsystem.service.telegram.TelegramService;
import com.example.leavemanagementsystem.service.telegram.TelegramServiceImp;
import com.example.leavemanagementsystem.service.userRole.*;
import com.example.leavemanagementsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.*;
import java.util.*;

//@PreAuthorize("hasAnyRole('DEPARTMENT')")
//@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
@Controller
public class DepartmentController {

    @Autowired
    PageService pageService;

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
    UserRoleService userRoleService;

    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;

    @Autowired
    TelegramRepository telegramRepository;

    @Autowired
    TelegramService telegramService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("telegram")
    public String telegramFunction(Model model){
        List<Telegram> telegramList = telegramRepository.findAll();
        model.addAttribute("telegram", telegramList);
        return "telegram";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "createTelegram", method = RequestMethod.POST)
    public String createTelegram(@ModelAttribute Telegram telegram, HttpServletRequest request) {
        telegramService.save(telegram);
        return "redirect:/telegram?status=success";
    }
    @RequestMapping(value = "/updateTelegram", method = RequestMethod.POST)
    public String updateTelegram(@ModelAttribute Telegram telegram, HttpServletRequest request) {
        telegramService.update(telegram);
        return "redirect:/telegram?status=update_success";
    }

    @RequestMapping(value = "update-telegram/{id}", method = RequestMethod.GET)
    public String ViewUpdateTele(Model model, @PathVariable Long id) {
        Telegram telegram = telegramRepository.findById(id).get();
        List<Telegram> telegramList = telegramRepository.findAll();
        model.addAttribute("telegram", telegram);
        model.addAttribute("telegramList", telegramList);
        return "updateTelegram";
    }

    @RequestMapping(value = "delete-telegram/{id}", method = RequestMethod.GET)
    public String deleteTele(@PathVariable Long id) throws Exception {
        telegramService.deleteTelegram(id);
        return "redirect:/telegram?status=del_success";
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("create-department")
    public String formCreateProduct(Model model) {
        List<UserEntity> userEntities = userRepository.findAllByStatus(1);
        List<DepartmentResponse> departments = departmentService.getListDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("users", userEntities);
        return "createDepartment";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "createDepartment", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute Department department, HttpServletRequest request) {
        departmentService.save(department);
        return "redirect:/create-department?status=success";
    }

    @GetMapping("/department1")
    public String sortAbsence(
            @ModelAttribute SortDto sortDto,
            Model model, Principal principal,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {
        System.out.println("PAGE: " + page);

        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
//        List<UserRoleDto> userRoleDtos = userRoleService.getListRoleOfUser(userEntity.getUserId());
//        boolean isAdmin = userRoleDtos.stream().anyMatch(o -> "ROLE_ADMIN".equals(o.getRoleName()));
        Department department = departmentRepository.findAllByHeadId(userEntity.getUserId());
        if (department == null) {
            return "redirect:/403";
        }
        DepartmentResponse departmentResponse = departmentService.getDepartmentOfManager(userEntity.getUserId());
        model.addAttribute("department", departmentResponse);
        if (sortDto.getSort() == 1) {
            PageResponse response = departmentService
                    .getAllAbsenceByDepaId(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page),
                            department);
            NewOutput newOutput = pageService
                    .page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page), response);
            model.addAttribute("absence", newOutput.getResponse().getObj());
            model.addAttribute("newOutput", newOutput);

            return "department";
        } else if (sortDto.getSort() == 2) {
            PageResponse response = departmentService
                    .getAllAbsenceByDepaIdAndStatus(0L, Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT,
                            Integer.parseInt(page), department);
            NewOutput newOutput = pageService
                    .page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page), response);
            model.addAttribute("absence", newOutput.getResponse().getObj());
            model.addAttribute("newOutput", newOutput);
            return "department";
        } else if (sortDto.getSort() == 3) {
            PageResponse response = departmentService
                    .getAllAbsenceByDepaIdAndStatus(1L, Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT,
                            Integer.parseInt(page), department);
            NewOutput newOutput = pageService
                    .page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page), response);
            model.addAttribute("absence", newOutput.getResponse().getObj());
            model.addAttribute("newOutput", newOutput);
            return "department";
        } else {
            PageResponse response = departmentService
                    .getAllAbsenceByDepaIdAndStatus(2L, Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT,
                            Integer.parseInt(page), department);
            NewOutput newOutput = pageService
                    .page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page), response);
            model.addAttribute("absence", newOutput.getResponse().getObj());
            model.addAttribute("newOutput", newOutput);
            return "department";
        }

    }


    @GetMapping("/list-nonenable/{id}")
    public String getPostNonEnbaleByDepartmentId(@PathVariable Long id, Model model) throws Exception {
        DepartmentResponse departmentResponse = departmentService.getDepartment(id);
        model.addAttribute("department", departmentResponse);
        List<AbsenceResponse> posts = absenceService.getListPostResponseNonEnableByDepartmentId(id);
        model.addAttribute("posts", posts);
        return "listNonenableByDepartmentId";
    }

    @GetMapping("/list-enabled/{id}")
    public String getPostEnbaledByDepartmentId(@PathVariable Long id, Model model) throws Exception {
        DepartmentResponse departmentResponse = departmentService.getDepartment(id);
        model.addAttribute("department", departmentResponse);
        List<AbsenceResponse> posts = absenceService.getListPostResponseEnabledByDepartmentId(id);
        model.addAttribute("posts", posts);
        return "listPostEnabledByDepartmentId";
    }

    @GetMapping("/list-posted/{id}")
    public String test(@PathVariable Long id, Model model) throws Exception {
        DepartmentResponse departmentResponse = departmentService.getDepartment(id);
        model.addAttribute("department", departmentResponse);
        List<AbsenceResponse> posts = absenceService.getListPostByDepartmentId(id);
        model.addAttribute("posts", posts);
        return "listPostDepartmentId";
    }

    @GetMapping("/list-rejected/{id}")
    public String getPostRejectedDepartmentId(@PathVariable Long id, Model model) throws Exception {
        DepartmentResponse departmentResponse = departmentService.getDepartment(id);
        model.addAttribute("department", departmentResponse);
        List<AbsenceResponse> posts = absenceService.getListPostResponseRejectedDepartmentId(id);
        model.addAttribute("posts", posts);
        return "listPostRejectedByDepartmentId";
    }

    @RequestMapping(value = "/updatedepartment", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute Department department, HttpServletRequest request) {
        departmentService.update(department);
        return "redirect:/create-department?status=update_success";
    }

    @RequestMapping(value = "update-department/{id}", method = RequestMethod.GET)
    public String formCreateProduct(Model model, @PathVariable Long id) {
        Department depa = departmentRepository.findByDepartmentId(id);
        List<UserEntity> userEntities = userRepository.findAllByStatus(1);
        List<DepartmentResponse> departments = departmentService.getListDepartment();
        model.addAttribute("departments", departments);
        model.addAttribute("depa", depa);
        model.addAttribute("users", userEntities);
        return "updateDepartment";
    }

    @RequestMapping(value = "delete-department/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable Long id) throws Exception {
        departmentService.deleteDepartment(id);
        return "redirect:/create-department?status=del_success";
    }

    //    @GetMapping("/manager")
//    public String getPostEnbaled(Model model) throws Exception {
//        List<AbsenceResponse>  posts = absenceService.getListPostEnabled1();
//        model.addAttribute("posts",posts);
//        return "export";
//    }
    @GetMapping("/department")   // trang hiển thị của duyệt đơn (mặc định là đơn chưa duyệt)
    public String getListUser(Model model, Principal principal,
                              @RequestParam(value = "page", defaultValue = "1", required = false) String page)
            throws Exception {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        Department department = departmentRepository.findAllByHeadId(userEntity.getUserId());
        if (department == null) {
            return "redirect:/403";
        }
        DepartmentResponse departmentResponse = departmentService.getDepartmentOfManager(userEntity.getUserId());
        PageResponse response = departmentService
                .getAllAbsenceByDepaIdAndStatus(0L, Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT,
                        Integer.parseInt(page), department);
        NewOutput newOutput =
                pageService.page(Constants.STATUS_USE.LIMIT_PAGE_ABSENCE_DEPARTMENT, Integer.parseInt(page), response);
        model.addAttribute("department", departmentResponse);
        model.addAttribute("absence", newOutput.getResponse().getObj());
        model.addAttribute("newOutput", newOutput);
        return "department";
    }

    @PostMapping("/note")
    public String Approval(@ModelAttribute NotePostRequest notePostRequest, Model model, Principal principal)
            throws MessagingException, UnsupportedEncodingException {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        if (notePostRequest.getType() == 2) {
            absenceService.rejectPost(notePostRequest.getAbsenceId(), userEntity.getUserId(),
                    notePostRequest.getDescription());
            return "redirect:/department?error=fail";
        } else {
            absenceService.enablePost(notePostRequest.getAbsenceId(), userEntity, notePostRequest.getDescription());
            return "redirect:/department?status=success";
        }
    }
}
