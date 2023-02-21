package com.example.leavemanagementsystem.controller.holiday;

import com.example.leavemanagementsystem.config.*;
import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.service.holidays.*;
import com.example.leavemanagementsystem.service.page.*;
import com.example.leavemanagementsystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.text.*;
import java.util.*;

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@Controller
public class HolidayController {
    @Autowired
    PageService pageService;
    @Autowired
    HolidaysRepo holidaysRepo;
    @Autowired
    HolidaysService holidaysService;

    //index quan li ngày nghỉ
    @RequestMapping("create-holiday")
    public String formCreateProduct(Model model,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) String page) {
        PageResponse pageResponse =
                holidaysService.getAllHoliday(Constants.STATUS_USE.LIMIT_PAGE_HOLIDAY, Integer.parseInt(page), 1);
        NewOutput newOutput =
                pageService.page(Constants.STATUS_USE.LIMIT_PAGE_HOLIDAY, Integer.parseInt(page), pageResponse);
        List<Holiday> holidays = holidaysRepo.findAllByStatusOrderByStartDateAsc(2);
        model.addAttribute("holiday", holidays);
//        List<Holiday> weekend = holidaysRepo.findAllByStatusOrderByStartDateAsc(1);
        model.addAttribute("weekend", newOutput.getResponse().getObj());
        model.addAttribute("newOutput", newOutput);

        return "create-Holiday";
    }


    // thêm ngày nghỉ lễ
    @RequestMapping(value = "/createHoliday", method = RequestMethod.POST)
    public String createHoliday(@ModelAttribute HolidayDto holidaydto, HttpServletRequest request)
            throws ParseException {

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(holidaydto.getStartDate());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(holidaydto.getEndDate());
        if (holidaydto.getStartDate() == null) {
            holidaydto.setStartDate(holidaydto.getEndDate());
        }
        Holiday holiday = new Holiday();
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holiday.setDescription(holidaydto.getDescription());
        holiday.setStatus(holidaydto.getStatus());
        holiday.setCreatedDate(new Date());
        holidaysService.save(holiday);
        return "redirect:create-holiday?status=success";
    }

    // thêm ngày nghỉ linh động
    @RequestMapping(value = "/createWeekend", method = RequestMethod.POST)
    public String createWeekend(@ModelAttribute WeekendDTO weekendDTO, HttpServletRequest request,
                                @RequestParam(name = "page", defaultValue = "1", required = false) String page)
            throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(weekendDTO.getStartDate());
        Holiday holiday = new Holiday();
        holiday.setStartDate(startDate);
        holiday.setEndDate(startDate);
        holiday.setDescription(weekendDTO.getDescription());
        holiday.setStatus(weekendDTO.getStatus());
        holiday.setCreatedDate(new Date());
        holidaysService.save(holiday);
        return "redirect:create-holiday?page=" + page + "&status=success";
    }

    @RequestMapping(value = "delete-holiday/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable Long id, Holiday holiday,
                             @RequestParam(name = "page", required = false, defaultValue = "1") String page)
            throws Exception {

        holidaysService.deleteHoliday(id);
        return "redirect:/create-holiday" + "?page=" + page + "&status=del_success";
    }

    @RequestMapping(value = "update-holiday/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable Long id, Model model) throws Exception {
        Holiday holiday = holidaysRepo.findByHolidayId(id);
        List<Holiday> holidays = holidaysRepo.findAll();
        model.addAttribute("holidays", holidays);
        model.addAttribute("holiday", holiday);
        model.addAttribute("status", 1);
        return "update-Holiday";
    }

    @RequestMapping(value = "update-holiday", method = RequestMethod.DELETE)
    public String deletePost(HolidayDto holidaydto) throws Exception {
        Holiday holiday = holidaysRepo.findByHolidayId(holidaydto.getHolidayId());
//        Date date1 =new SimpleDateFormat("yyyy-MM-dd").parse(holidaydto.getHoliday());
//        holiday.setHoliday(date1);
//        holiday.setDescription(holidaydto.getDescription());
//        holiday.setStatus(holidaydto.getStatus());
//        holiday.setCreatedDate(new Date());
        holidaysService.save(holiday);
        return "redirect:update-holidays/" + holiday.getHolidayId() + "?status=successfull";
    }

}
