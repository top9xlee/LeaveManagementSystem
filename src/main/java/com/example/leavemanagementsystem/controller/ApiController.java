package com.example.leavemanagementsystem.controller;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.DateMapper;
import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.model.Department;
import com.example.leavemanagementsystem.model.UserEntity;
import com.example.leavemanagementsystem.repository.user.UserRepository;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepository;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepositoryCustom;
import com.example.leavemanagementsystem.service.absenceService.AbsenceService;
import com.example.leavemanagementsystem.service.department.DepartmentService;
import com.example.leavemanagementsystem.service.export.ExportExcelServiceAllUser;
import com.example.leavemanagementsystem.service.export.ExportWord;
import com.example.leavemanagementsystem.service.export.ExportWord2;
import com.example.leavemanagementsystem.service.export.ZipFile;
import com.example.leavemanagementsystem.service.holidays.HolidaysService;
import com.example.leavemanagementsystem.service.userService.UserService;
import com.example.leavemanagementsystem.utils.NewOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ApiController {
    private final AbsenceService absenceService;
    private final DepartmentService departmentService;
    private final UserRepository userRepository;
    private final HolidaysService holidaysService;
    private final DateMapper dateMapper;
    private final AbsenceRepositoryCustom absenceRepositoryCustom;
    private final AbsenceRepository absenceRepository;
    private static Gson gson = new Gson();
    private final UserService userService;


//    @PostMapping(  "createAbsence")
//    public ResponseEntity<Void> createPost(@RequestBody Absence post) throws Exception {
//        absenceService.save(post,12L);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//    @PostMapping("/enable-post/{id}")
//    public ResponseEntity<Void> enablePost(@PathVariable Long id, Long userId) throws Exception {
//        log.info("enable-post: id= {}, userId = {} ", id, userId);
//        absenceService.enablePost(id, userId);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

//    @GetMapping("/get2Month")
//    public List<Absence> deleteUserEntity() {
//        return absenceService.get2Month();
//    }
//
//    @GetMapping("/deleteUserEntity/{id}")
//    public UserEntity deleteUserEntity(@PathVariable Long id) {
//        return userService.deleteUserEntity(id);
//    }

//    @GetMapping("/getAllUserEntityEnable")
////    public List<UserEntityDto> getAllUserEntityEnable() throws Exception {
////        return userService.getAllUserEntityEnable();
////    }

    @GetMapping("/list-nonenable")
    public ResponseEntity<List<Absence>> getPostNonEnbale() throws Exception {
        log.info("list non enable");
        return status(HttpStatus.OK).body(absenceService.getListPostNonEnable());
    }

    @GetMapping("/list-enabled")
    public ResponseEntity<List<Absence>> getPostEnbaled() throws Exception {
        log.info("list enable");
        return status(HttpStatus.OK).body(absenceService.getListPostEnabled());
    }

    @PostMapping("/department")
    public ResponseEntity<Void> createDepartment(@RequestBody Department department) throws Exception {
        log.info("create department: {}", gson.toJson(department));
        departmentService.save(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentResponse>> getDepartment() throws Exception {
//        log.info("Get list department ");
        List<DepartmentResponse> result = departmentService.getListDepartment();
        return status(HttpStatus.OK).body(result);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<UserEntity>> getListUser(@PathVariable Long id) throws Exception {
        return status(HttpStatus.OK).body(departmentService.getUsersByDepartment(id));
    }

    @GetMapping("/list-post-by-day/{day1}/{day2}")
    public ResponseEntity<List<Absence>> getListPostByDate(@PathVariable String day1, @PathVariable String day2) throws Exception {
        return status(HttpStatus.OK).body(absenceService.getListPostByDay(day1, day2));
    }

    @GetMapping("/workday/{year}/{month}/{id}")
    public ResponseEntity<List<Date>> getUserListDayOffByMonth(@PathVariable Long year, @PathVariable Long month, @PathVariable Long id) throws Exception {
        return status(HttpStatus.OK).body(absenceService.getWorksDayByUserId(year, month, id));
    }

    @GetMapping("/workday/{year}/{month}")
    public ResponseEntity<List<List<Date>>> getUserList(@PathVariable Long year, @PathVariable Long month) throws Exception {
        return status(HttpStatus.OK).body(absenceService.getWorksDay(year, month));
    }

    @GetMapping("/holiday/{year}/{month}")
    public ResponseEntity<List<Date>> getListHolidayByMonth(@PathVariable Long year, @PathVariable Long month) throws Exception {
        return status(HttpStatus.OK).body(holidaysService.getListHolidayssByMonth(year, month));
    }

    @GetMapping("/weekend/{year}/{month}")
    public ResponseEntity<List<Date>> getListWeekendByMonth(@PathVariable Long year, @PathVariable Long month) throws Exception {
        return status(HttpStatus.OK).body(holidaysService.getListWeekendByMonth(year, month));
    }

    @GetMapping("/unpaid/{year}/{month}")
    public ResponseEntity<List<List<DateResponse>>> getListUnpaidByMonth(@PathVariable Long year, @PathVariable Long month) throws Exception {
        return status(HttpStatus.OK).body(absenceService.getListAbsenceByTypeAndMonth(year, month));
    }

    @PostMapping("/getDay")
    public ResponseEntity<Float> testListAbsenceUser(@RequestBody AbsenceRequest absenceRequest) throws Exception {
        return status(HttpStatus.OK).body(absenceService.countDay(absenceRequest));
    }


    //    @PostMapping("user/forgot-password")
//    public ResponseEntity<String> getPostRejectedDepartmentId(@RequestBody ForgotPasswordRequest forgotPasswordRequest, HttpServletRequest request) throws Exception {
//        UserEntity userEntity = userRepository.findByEmail(forgotPasswordRequest.getEmail());
//        if(userEntity == null){
//            return new ResponseEntity<>("Không tìm thấy email", NOT_FOUND);
//        }
//        userService.setVerify(userEntity.getUserId());
//        String siteURL = Utiliy.getSiteURL(request);
//        emailService.send(userEntity,siteURL);
//        return new ResponseEntity<>("Đã tìm thấy email", OK);
//    }
    @GetMapping("/test")
    public ResponseEntity<Void> test() throws Exception {

        WordDto wordDto = absenceService.getWordAbsence(190L);
        ExportWord2 app = new ExportWord2(wordDto);
        app.export();
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/test2/{year}/{month}")
//    public ResponseEntity<Void> test2(HttpServletResponse response,@PathVariable Long year, @PathVariable Long month) throws Exception {
//        List<WordDto> wordDtos = absenceService.getListWordAbsence(year,month);
//        for(WordDto wordDto:wordDtos){
//            ExportWord app = new ExportWord(wordDto);
//            app.export();
//        }
//        ServletOutputStream outputStream = response.getOutputStream();
//            ZipFile appZip = new ZipFile();
//            appZip.generateFileList(new File("D:/test/"));
//            appZip.zipIt("Danh sach don nghi phep thang "+ month+"/"+year+".zip",response);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @PostMapping("createAbsence")
    public ResponseEntity<String> createAbsence(@RequestBody AbsenceRequest absence, Principal principal) throws MessagingException, UnsupportedEncodingException {
        String name = principal.getName();
        UserEntity userEntity = userRepository.findByUserName(name);
        absenceService.save(absence, userEntity);
        return status(HttpStatus.OK).body("ok");

    }

}
