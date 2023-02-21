package com.example.leavemanagementsystem.controller.manager;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.AbsenceMapper;
import com.example.leavemanagementsystem.mapper.DateMapper;
import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.model.UserEntity;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepository;
import com.example.leavemanagementsystem.repository.HolidaysRepo;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepositoryCustom;
import com.example.leavemanagementsystem.repository.user.UserRepository;
import com.example.leavemanagementsystem.service.department.DepartmentService;
import com.example.leavemanagementsystem.service.export.ExportExcelServiceAllUser;
import com.example.leavemanagementsystem.service.export.ExportWord;
import com.example.leavemanagementsystem.service.export.ExportWord2;
import com.example.leavemanagementsystem.service.export.ZipFile;
import com.example.leavemanagementsystem.service.holidays.HolidaysService;
import com.example.leavemanagementsystem.service.absenceService.AbsenceService;
import com.example.leavemanagementsystem.service.userService.UserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@Controller
public class ManagerController {
    @Autowired
    AbsenceMapper absenceMapper;

    @Autowired
    AbsenceService absenceService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HolidaysRepo holidaysRepo;

    @Autowired
    HolidaysService holidaysService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    UserService userService;

    @Autowired
    DateMapper dateMapper;

    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;


    //excel cho danh sách nghỉ theo post
    @GetMapping("/export")
    public String exportPage(Model model, Principal principal) {
        String name = principal.getName();
        UserEntity user = userRepository.findByUserName(name);
        String date = "2022-01";
        model.addAttribute("date", date);
        return "export";
    }

    @PostMapping("/export")
    public String dataOfExportPage(Model model, Principal principal, @RequestParam("data") String data) {
        String[] parts = data.split("-");
        String part1 = parts[0];

        String part2 = parts[1];
        Long year = Long.parseLong(part1);
        Long month = Long.parseLong(part2);

        String date = year + "-0" + month;

        List<AbsenceResponse> absenceList = absenceService.getListAbsenceResponseByMonth(year, month)
                .stream()
                .map(absenceMapper::maptoDto2)
                .collect(Collectors.toList());
        if (absenceList.size() == 0) {
            model.addAttribute("date", date);
            return "export";
        } else {
            int post = 1;
            model.addAttribute("date", date);
            model.addAttribute("month", month);
            model.addAttribute("year", year);
            model.addAttribute("absence", absenceList);
            model.addAttribute("post", post);
            return "export";
        }
    }

    @PostMapping("/export/all-user")
    public void exportExcelAllUser(HttpServletResponse response, @ModelAttribute ExportDto data) throws IOException, InvalidFormatException {
        response.setContentType("application/octet-stream");
        String[] parts = data.getData().split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        Long year = Long.parseLong(part1);
        Long month = Long.parseLong(part2);
        int test = Integer.parseInt(data.getStatus());
        List<Absence> absenceList = absenceService.getListAbsenceEnabledByMonth(year, month);
        int i = 0;
        int j = 0;
        for (Absence absence : absenceList) {
            j = 0;
            for (Integer integer : data.getType()) {
                if (i == j) {
                    absence.setType(integer);
                    absenceRepository.save(absence);
                }
                j++;
            }
            i++;
        }
        List<WordDto> wordDtos = absenceService.getListWordAbsence(year, month);
        if (test == 2) {
            String header = "Danh sach don nghi phep thang " + month + "/" + year;
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + header + ".zip";
            response.setHeader(headerKey, headerValue);


            for (WordDto wordDto : wordDtos) {
                if (wordDto.getDayOff() > 2) {
                    ExportWord2 app = new ExportWord2(wordDto);
                    app.export();

                } else {
                    ExportWord app = new ExportWord(wordDto);
                    app.export();
                }
            }
            ServletOutputStream outputStream = response.getOutputStream();
            ZipFile appZip = new ZipFile();
            appZip.generateFileList(new File("src/main/resources/word/export/"));
//            appZip.generateFileList(new File("D:/test/"));
            appZip.zipIt("Danh sach don nghi phep thang " + month + "/" + year + ".zip", response);
            appZip.deleteFile();
            outputStream.close();
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String day = sdf3.format(timestamp);
            String header = "Bao cao luong thang " + month + "/" + year;
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + header + "_" +
                    day + ".xlsx";
            response.setHeader(headerKey, headerValue);

//            List<Absence> absenceList = absenceService.getListAbsenceEnabledByMonth(year, month);
//            int i = 0;
//            int j = 0;
//            for (Absence absence : absenceList) {
//                j = 0;
//                for (Integer integer : data.getType()) {
//                    if (i == j) {
//                        absence.setType(integer);
//                        absenceRepository.save(absence);
//                    }
//                    j++;
//                }
//                i++;
//            }

            List<UserEntity> userEntities = userRepository.findAllUser();

            List<Date> holidays = holidaysService.getListHolidayssByMonth(year, month);
            List<Date> weekends = holidaysService.getListWeekendByMonth(year, month);

            List<List<DateResponse>> dateOff = absenceService.getListAbsenceByTypeAndMonth(year, month);
            List<List<LocalDateResponse>> listLocalDateOff = new ArrayList<>();

            List<List<Date>> dateWork = absenceService.getWorksDay(year, month);
            List<List<LocalDate>> listLocalDateWorks = new ArrayList<>();

            for (List<Date> dateWorks : dateWork) {
                List<LocalDate> localDateResponses = dateWorks.stream()
                        .map(dateResponses -> dateResponses.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .collect(Collectors.toList());
                listLocalDateWorks.add(localDateResponses);
            }

            for (List<DateResponse> dateResponses : dateOff) {
                List<LocalDateResponse> localDateResponses = dateResponses.stream()
                        .map(dateMapper::maptoDto)
                        .collect(Collectors.toList());
                listLocalDateOff.add(localDateResponses);
            }

            List<LocalDate> listHolidays = holidays
                    .stream()
                    .map(holiday -> holiday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .collect(Collectors.toList());
            List<LocalDate> listWeekend = weekends
                    .stream()
                    .map(holiday -> holiday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .collect(Collectors.toList());

            ExportExcelServiceAllUser excelExporter = new ExportExcelServiceAllUser(year, month, userEntities, listHolidays, listWeekend, listLocalDateOff, listLocalDateWorks, dateMapper);
            excelExporter.export(response);

        }
    }
    //    @PostMapping("/exportExcel/all-user")
//    public void exportExcel1AllUser(HttpServletResponse response, @RequestParam("data") String data,@ModelAttribute ExportExcelDto exportExcelDto) throws IOException, InvalidFormatException {
//        response.setContentType("application/octet-stream");
//        String[] parts = data.split("-");
//        String part1 = parts[0];
//        String part2 = parts[1];
//        Long year = Long.parseLong(part1);
//        Long month = Long.parseLong(part2);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String day = sdf3.format(timestamp);
//        String header = "Bao cao luong thang " + month + "/" + year;
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=" + header + "_" +
//                day + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        List<UserEntity> userEntities = userRepository.findAllUser();
//
//        List<Date> holidays = holidaysService.getListHolidayssByMonth(year, month);
//        List<Date> weekends = holidaysService.getListWeekendByMonth(year, month);
//
//        List<List<DateResponse>> dateOff = absenceService.getListAbsenceByTypeAndMonth(year, month);
//        List<List<LocalDateResponse>> listLocalDateOff = new ArrayList<>();
//
//        List<List<Date>> dateWork = absenceService.getWorksDay(year, month);
//        List<List<LocalDate>> listLocalDateWorks = new ArrayList<>();
//
//        for (List<Date> dateWorks : dateWork) {
//            List<LocalDate> localDateResponses = dateWorks.stream()
//                    .map(dateResponses -> dateResponses.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                    .collect(Collectors.toList());
//            listLocalDateWorks.add(localDateResponses);
//        }
//
//        for (List<DateResponse> dateResponses : dateOff) {
//            List<LocalDateResponse> localDateResponses = dateResponses.stream()
//                    .map(dateMapper::maptoDto)
//                    .collect(Collectors.toList());
//            listLocalDateOff.add(localDateResponses);
//        }
//
//        List<LocalDate> listHolidays = holidays
//                .stream()
//                .map(holiday -> holiday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                .collect(Collectors.toList());
//        List<LocalDate> listWeekend = weekends
//                .stream()
//                .map(holiday -> holiday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                .collect(Collectors.toList());
//
//        ExportExcelServiceAllUser excelExporter = new ExportExcelServiceAllUser(year, month, userEntities, listHolidays, listWeekend, listLocalDateOff, listLocalDateWorks, dateMapper);
//        excelExporter.export(response);
//
//    }


}
