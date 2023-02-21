package com.example.leavemanagementsystem.service.absenceService;

import com.example.leavemanagementsystem.config.Constants;
import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.exception.AbsenceNotFoundException;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepository;
import com.example.leavemanagementsystem.repository.absenceRepository.AbsenceRepositoryCustom;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.service.department.DepartmentService;
import com.example.leavemanagementsystem.service.email.EmailService;
import com.example.leavemanagementsystem.service.holidays.*;
import com.example.leavemanagementsystem.service.telegram.TelegramService;
import com.example.leavemanagementsystem.service.telegram.TelegramServiceImp;
import com.example.leavemanagementsystem.service.userRole.*;
import com.example.leavemanagementsystem.utils.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.stream.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {
    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    HolidaysRepo holidaysRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    HolidaysService holidaysService;

    @Autowired
    EmailService emailService;

    @Autowired
    TelegramService telegramService;

    @Autowired
    TelegramRepository telegramRepository;

    private final AbsenceMapper absenceMapper;

    public void save(AbsenceRequest absence, UserEntity user) throws MessagingException, UnsupportedEncodingException {
        Absence absenceCreate = new Absence();

        absenceCreate.setUserId(user.getUserId());
        absenceCreate.setType(absence.getType());
        absenceCreate.setTitle(absence.getTitle());

        absenceCreate.setDescription(absence.getDescription());
        absenceCreate.setAbsenceBy(0L);
        absenceCreate.setPersonInCharge(absence.getPersonInCharge());

        int shiftFrom = absence.getTypeFromDate();
        int shiftTo = absence.getTypeToDate();
        Float numOfDays = countLeaveDay(absence.getStartDate(), absence.getEndDate(), shiftFrom, shiftTo);

        absenceCreate.setDayOff(numOfDays);
        absenceCreate.setStartDate(createStartWorkTimeToAbsence(absence.getStartDate().getTime(), shiftFrom));
        absenceCreate.setEndDate(createEndWorkTimeToAbsence(absence.getEndDate().getTime(), shiftTo));
            absenceCreate.setDepaId(user.getDepartment().getDepartmentId());

            UserEntity headDepartment = userRepository.findById(user.getDepartment().getHeadId()).get();
            List<String> listMails = new ArrayList<>();
            String headDepartmentEmail = headDepartment.getEmail(); // mail trưởng phòng
            List<UserEntity> admin = userRepository.findByRole(Constants.STATUS_USE.ROLE_ADMIN); // list mail admin

            for (UserEntity userEntity : admin) {
                listMails.add(userEntity.getEmail());
            }
            listMails.add(user.getEmail());
            listMails.add(headDepartmentEmail);


            if (absenceCreate.getDayOff() > 2) {
                Optional<Department> department = departmentRepository.findById(1L);
                Optional<UserEntity> users = userRepository.findById(department.get().getHeadId());
                listMails.add(users.get().getEmail());
                String[] arrMails = new String[listMails.size()];
                arrMails = listMails.toArray(arrMails);
                emailService.sendMailNotification(user, headDepartment.getFullName(), absenceCreate,
                        arrMails);
            } else {
                String[] arrMails = new String[listMails.size()];
                arrMails = listMails.toArray(arrMails);
                emailService.sendMailNotification(user, headDepartment.getFullName(), absenceCreate,
                        arrMails);
            }
            Telegram telegram = telegramRepository.getById(15L);
            telegramService.sendToTelegram(user, absenceCreate, telegram.getApiToken().trim(), telegram.getChatId().trim());
            absenceRepository.save(absenceCreate);
//        }

    }


    @Override
    public Boolean update(AbsenceRequest absence) {

        Absence absence1 = absenceRepository.findById(absence.getId()).orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + absence.getId()));

        if (absence1.getStatus() != Constants.STATUS_USE.NOT_ACTIVATE) {
            return false;
        }
        absence1.setTitle(absence.getTitle());
        absence1.setType(absence.getType());
        absence1.setDescription(absence.getDescription());
        absence1.setStartDate(absence.getStartDate());
        absence1.setEndDate(absence.getEndDate());
        int shiftFrom = absence.getTypeFromDate();
        int shiftTo = absence.getTypeToDate();
        Float numOfDays = countLeaveDay(absence1.getStartDate(), absence1.getEndDate(), shiftFrom, shiftTo);
        absence1.setDayOff(numOfDays);

        absence1.setStartDate(createStartWorkTimeToAbsence(absence.getStartDate().getTime(), shiftFrom));
        absence1.setEndDate(createEndWorkTimeToAbsence(absence.getEndDate().getTime(), shiftTo));

        UserEntity user = userRepository.findByUserId(absence1.getUserId());
        absence1.setDepaId(user.getDepartment().getDepartmentId());
        absenceRepository.save(absence1);
        return true;
    }

    @Override
    public void enablePost(Long id, UserEntity user, String note) throws MessagingException, UnsupportedEncodingException {
        Absence absence = absenceRepository.findById(id).orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + id));
        String nameOfDepartment = user.getDepartment().getDepartmentName();
        UserEntity author = userRepository.findById(absence.getUserId()).get();
        if (absence.getDayOff() > 2 && user.getUserId() != 1) {
            absence.setDepaId(1L);
            absence.setEnableDate(new Date());
            absence.setAbsenceBy(user.getUserId());
            absence.setAbsenceResponse(note);
            absenceRepository.save(absence);
//            emailService.sendMailNotification(author, user.getFullName(), nameOfDepartment, absence, user.getEmail());
        } else {
            absence.setStatus(Constants.STATUS_USE.ACTIVATE);
            absence.setEnableDate(new Date());
            absence.setAbsenceBy(user.getUserId());
            absence.setAbsenceResponse(note);
            absenceRepository.save(absence);
//            try {
//                List<UserEntity> admin = userRepository.findByRole(Constants.STATUS_USE.ROLE_ADMIN);
//                for (UserEntity userEntity : admin) {
////                    emailService.sendMailNotification(author, user.getFullName(), nameOfDepartment, absence, userEntity.getEmail());
//                }
//            }catch (Exception ex){
//               log.error(ex.getMessage(), ex);
//        }
        }

    }

    @Override
    public void deletePost(Long id) {
        Absence absence = absenceRepository.findById(id).orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + id));
        ;
        absenceRepository.delete(absence);
    }

    @Override
    public List<Absence> getListPostNonEnable() {
        int check = 0;
        List<Absence> absence = absenceRepository.findAllByStatus(check);
        return absence;
    }

    @Override
    public List<Absence> getListAbsenceEnabledByMonth(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), Constants.STATUS_USE.FIRST_DAY_OF_MONTH);
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return absenceRepository.getListAbsenceByMonth(firstDateOfMonth, endDateOfMonth);
    }

    @Override
    public List<Absence> getListPostEnabled() {
        int check = 1;
        List<Absence> absence = absenceRepository.findAllByStatus(check);
        return absence;
    }

    @Override
    public List<Absence> getListPostNonEnableByDepartmentId(Long id) {
        int check = 0;
        List<Absence> absence = absenceRepository.findAllByStatusAndDepaIdOrderByEndDateDesc(check, id);
        return absence;

    }

    private boolean betweenInputDate(Date itemDate, Date startDate, Date endDate) {
        if (itemDate.compareTo(startDate) >= 0 && itemDate.compareTo(endDate) <= 0) return true;
        return false;
    }

    @Override
    public List<Date> getListHoliday(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
        List<Date> holidayLst = new ArrayList<>();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Holiday> holidays = holidaysService.getListHolidaysByMonth(year, month);
        for (Holiday item : holidays) {
            List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, firstDateOfMonth, endDateOfMonth))
                    holidayLst.add(itemDate);
            }
        }

        return holidayLst;
    }

    @Override
    public List<List<Date>> getWorksDay(Long year, Long month)//Lấy danh sách những ngày đi làm của Tất cả mọi người
    {
        List<List<Date>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAllUser();
        for (UserEntity users : userEntities) {
            // lấy ngày đầu tiên và ngày cuối cùng của tháng để tính toán và covert từ localdate sang date
            LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), Constants.STATUS_USE.FIRST_DAY_OF_MONTH);
            LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
            Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


            List<Absence> absenceList = absenceRepository.getListPostByMonthAndUserId(firstDateOfMonth, endDateOfMonth, users.getUserId());
            //Lấy danh sách ngày nghỉ lễ trong tháng
            List<Holiday> listHolidays = holidaysRepo.getListHolidayByMonth(firstDateOfMonth, endDateOfMonth);
            List<Date> holidayLst = new ArrayList<>();

            //Danh sach ngay nghi trong thang

            for (Holiday item : listHolidays) {
                List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
                for (Date itemDate : itemDates) {
                    if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, firstDateOfMonth, endDateOfMonth))
                        holidayLst.add(itemDate);
                }
            }
            //Danh sách số ngày nghỉ trong đơn của người dùng
            for (Absence absence : absenceList) {
                List<Date> itemDates = Utils.getDaysBetweenDates(absence.getStartDate(), absence.getEndDate());
                for (Date itemDate : itemDates) {
                    if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, absence.getStartDate(), absence.getEndDate()))
                        holidayLst.add(itemDate);
                }
            }
            List<Date> daysOfMonth = Utils.getDaysBetweenDates(firstDateOfMonth, endDateOfMonth);
            Optional<List<Date>> workingDays = Optional.of(holidayLst);
            Predicate<Date> isWorkingDays = date -> workingDays.isPresent()
                    && workingDays.get().contains(date);
            List<Date> daysWorking = daysOfMonth.stream()
                    .filter(isWorkingDays
                            .negate())
                    .collect(Collectors.toList());
            System.out.println(daysWorking.size());
            listList.add(daysWorking);
        }

        return listList;

    }

    @Override
    public Page<Absence> getListPageAbsenceByStatus(Pageable pageable, int status) {
        return absenceRepository.findAllByStatus(pageable, status);
    }


    @Override
    public List<List<DateResponse>> getListAbsenceByTypeAndMonth(Long year, Long month) {
        List<List<DateResponse>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAllUser();
        for (UserEntity users : userEntities) {
            LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
            LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
            Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Absence> absenceList = absenceRepository.getListPostByMonthAndUserId(firstDateOfMonth, endDateOfMonth, users.getUserId());
            //Lấy danh sách ngày nghỉ lễ trong tháng
//            List<Holiday> listHolidays = holidaysRepo.getListHolidayByMonth(firstDateOfMonth, endDateOfMonth);
            List<DateResponse> holidayLst = new ArrayList<>();

            //Danh sach ngay nghi trong thang

            //Danh sách số ngày nghỉ trong đơn của người dùng
            for (Absence absence : absenceList) {
                List<DateResponse> itemDates = Utils.getDaysAndTypeBetweenDates(absence);
                for (DateResponse itemDate : itemDates) {
                    if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate.getDate(), absence.getStartDate(), absence.getEndDate()))
                        holidayLst.add(itemDate);
                }
            }
//            List<Date> daysOfMonth = Utils.getDaysBetweenDates(firstDateOfMonth, endDateOfMonth);
//            Optional<List<Date>> workingDays = Optional.of(holidayLst);
//            Predicate<Date> isWorkingDays = date -> workingDays.isPresent()
//                    && workingDays.get().contains(date);
//            List<Date> daysWorking = daysOfMonth.stream()
//                    .filter(isWorkingDays
//                            .negate())
//                    .collect(Collectors.toList());
//            System.out.println(daysWorking.size());
            listList.add(holidayLst);
        }

        return listList;

    }

    @Override
    public List<Date> getWorksDayByUserId(Long year, Long month, Long id)//Lấy danh sách những ngày đi làm của từng người
    {

        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
//        localDate.getMonth();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        Date firstDateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateOfMonth = Date.from(lastDayOfMonthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

//        List<Date> itemDates = Utils.getDaysBetweenDates(firstDateOfMonth,endDateOfMonth ;
        List<Absence> absenceList = absenceRepository.getListPostByMonthAndUserId(firstDateOfMonth, endDateOfMonth, id);
        //Lấy danh sách ngày nghỉ lễ trong tháng
        List<Holiday> listHolidays = holidaysRepo.getListHolidayByMonth(firstDateOfMonth, endDateOfMonth);
        List<Date> holidayLst = new ArrayList<>();

        //Danh sach ngay nghi trong thang

        for (Holiday item : listHolidays) {
            List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, firstDateOfMonth, endDateOfMonth))
                    holidayLst.add(itemDate);
            }
        }
        //Danh sách số ngày nghỉ trong đơn của người dùng
        for (Absence absence : absenceList) {
            List<Date> itemDates = Utils.getDaysBetweenDates(absence.getStartDate(), absence.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, absence.getStartDate(), absence.getEndDate()))
                    holidayLst.add(itemDate);
            }
        }
        List<Date> daysOfMonth = Utils.getDaysBetweenDates(firstDateOfMonth, endDateOfMonth);
        Optional<List<Date>> workingDays = Optional.of(holidayLst);
        Predicate<Date> isWorkingDays = date -> workingDays.isPresent()
                && workingDays.get().contains(date);
        List<Date> daysWorking = daysOfMonth.stream()
                .filter(isWorkingDays
                        .negate())
                .collect(Collectors.toList());

        return daysWorking;
    }

    private List<List<LocalDate>> getWorksDay(final LocalDate startDate,
                                              final LocalDate endDate)//Lấy danh sách những ngày đi làm của từng người
    {
        List<List<LocalDate>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity uuser : userEntities) {
            //Lấy danh sách ngày nghỉ lễ
            List<Holiday> holidaysses = holidaysRepo.findAll();
//                    && workingDays.get().contains(date);
            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);

            UserEntity userEntity = userRepository.findByUserId(uuser.getUserId());

            Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                    || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        }
        return listList;
    }

    private List<List<LocalDate>> getDayOff(final LocalDate startDate,
                                            final LocalDate endDate)//Lấy danh sách những ngày đi làm của từng người
    {
        List<List<LocalDate>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity uuser : userEntities) {

            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);

            List<LocalDate> businessDays = Stream
                    .iterate(startDate, date -> date.plusDays(1))
                    .limit(numOfDays)
//                    .filter(isDayOff)
                    .collect(Collectors.toList());
            listList.add(businessDays);
        }
        return listList;
    }

    private List<List<LocalDate>> getOff(final LocalDate startDate,
                                         final LocalDate endDate)//Lấy danh sách những ngày đi làm của từng người
    {
        List<List<LocalDate>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity uuser : userEntities) {

            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
            List<LocalDate> businessDays = Stream
                    .iterate(startDate, date -> date.plusDays(1))
                    .limit(numOfDays)

                    .collect(Collectors.toList());
            listList.add(businessDays);
        }
        return listList;
    }

    private List<List<LocalDate>> getOffChild(final LocalDate startDate,
                                              final LocalDate endDate)//Lấy danh sách những ngày đi làm của từng người
    {
        List<List<LocalDate>> listList = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity uuser : userEntities) {
//            List<LocalDate> dayOffs = uuser.getListOffChild();
//            Optional<List<LocalDate>> listDayOff = Optional.of(dayOffs);
//            Predicate<LocalDate> isDayOff = date -> listDayOff.isPresent()
//                    && listDayOff.get().contains(date);
            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
            List<LocalDate> businessDays = Stream
                    .iterate(startDate, date -> date.plusDays(1))
                    .limit(numOfDays)
//                    .filter(isDayOff)
                    .collect(Collectors.toList());
            listList.add(businessDays);
        }
        return listList;
    }


    @Override
    public List<AbsenceResponse> getListAbsenceResponseByMonth(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
        localDate.getMonth();
        LocalDate lastDayOfMonthDate = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        List<AbsenceResponse> absens = absenceRepositoryCustom.getListAbsenceResponseByMonth(localDate, lastDayOfMonthDate);
        return absens;
    }

    @Override
    public List<Absence> getListPostByDay(String day1, String day2) {
        LocalDate localDate1 = LocalDate.parse(day1);
        LocalDate localDate2 = LocalDate.parse(day2);
        Date date1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Absence> absens = absenceRepository.getListPostByDate(date1, date2);
        return absens;
    }


    @Override
    public List<AbsenceResponse> getListPostResponseNonEnableByDepartmentId(Long id) {
        int check = 0;
        List<Absence> absence = absenceRepository.findAllByStatusAndDepaIdOrderByEndDateDesc(check, id);
        return absence.stream()
                .map(absenceMapper::maptoDto1)
                .collect(Collectors.toList());

    }

    @Override
    public List<AbsenceResponse> getListPostResponseEnabledByDepartmentId(Long id) {
        int check = 1;
        List<Absence> absence = absenceRepository.findAllByStatusAndDepaIdOrderByEndDateDesc(check, id);
        return absence.stream()
                .map(absenceMapper::maptoDto1)
                .collect(Collectors.toList());

    }

    @Override
    public List<AbsenceResponse> getListPostResponseRejectedDepartmentId(Long id) {
        int check = 2;
        List<Absence> absence = absenceRepository.findAllByStatusAndDepaIdOrderByEndDateDesc(check, id);
        return absence.stream()
                .map(absenceMapper::maptoDto1)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceResponse> getListPostByDepartmentId(Long id) {
        List<Absence> absens = absenceRepository.findAllByDepaId(id);
        return absens.stream()
                .map(absenceMapper::maptoDto1)
                .collect(Collectors.toList());
    }

    @Override
    public void rejectPost(Long id, Long userId, String note) {
        Absence absence = absenceRepository.findById(id).orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + id));
        ;
        absence.setStatus(2);
        absence.setAbsenceBy(userId);
        absence.setEnableDate(new Date());
        absence.setAbsenceResponse(note);
        absenceRepository.save(absence);
    }

    @Override
    public boolean deleteAbsence(Long id, Long userId) {
        Absence absence = absenceRepository.findById(id).orElseThrow(() -> new AbsenceNotFoundException("Post Not Found with ID - " + id));

        if (ObjectUtils.isEmpty(absence) || absence.getUserId() != userId) {
            throw new AbsenceNotFoundException("Không tìm thấy đơn");
        }
        if (absence.getStatus() != 0) {
            throw new AbsenceNotFoundException("Đơn đã được duyệt");
        }
        absenceRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AbsenceResponse> getListPostEnabled1() {
        int check = 1;
        List<Absence> absence = absenceRepository.findAllByStatus(check);
        return absence.stream().map(absenceMapper::maptoDto1).collect(Collectors.toList());
    }

    @Override
    public List<AbsenceResponse> getListPostResponseByUser(Long id) {
        List<Absence> absens = absenceRepository.findAllByUserId(id);
        return absens.stream().map(absenceMapper::maptoDto1).collect(Collectors.toList());
    }

    @Override
    public int countByStatusAndDepaId(int status, Long departmentId) {
        return absenceRepository.countByStatusAndDepaId(status, departmentId);
    }

    @Override
    public void hideAbsence(Absence absence) {
        absence.setStatus(3);
        absenceRepository.save(absence);
    }

    @Override
    public WordDto getWordAbsence(Long id) {
        List<WordResponse> absenceResponse = absenceRepositoryCustom.findByAbsenceId(id);

        WordResponse absenceResponse1 = absenceResponse.get(0);

        WordDto wordDto = absenceMapper.mapToWord(absenceResponse1);

        return wordDto;
    }

    @Override
    public List<WordDto> getListWordAbsence(Long year, Long month) {
        LocalDate localDate = LocalDate.of(year.intValue(), month.intValue(), 1);
        localDate.getMonth();
        LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.getMonth().length(localDate.isLeapYear()));
        List<WordResponse> absense = absenceRepositoryCustom.getListWordResponseByMonth(localDate, lastDayOfMonth);
        return absense.stream().map(absenceMapper::mapToWord).collect(Collectors.toList());
    }

    @Override
    public Float countDay(AbsenceRequest absenceRequest) {
        int fromDate = absenceRequest.getTypeFromDate();
        int toDate = absenceRequest.getTypeToDate();
        float numOfDays = countLeaveDay(absenceRequest.getStartDate(), absenceRequest.getEndDate(), fromDate, toDate);
        return numOfDays;
    }

    @Override
    public Float countLeaveDay(Date startInputDate, Date endInputDate, int shiftFrom, int shiftTo) {
        List<Date> holidayLst = new ArrayList<>();
//        Date startDate = Date.from(startInputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date endDate = Date.from(endInputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Count day between 2 date
        float countDay = Utils.countBetweenDate(startInputDate, endInputDate, shiftFrom == 1 ? true : false,
                shiftTo == 1 ? true : false);

        //Count holidays between 2 date
        List<Holiday> listHolidays = holidaysRepo.getListHolidayByMonth(startInputDate, endInputDate);

        // add date to list
        for (Holiday item : listHolidays) {
            List<Date> itemDates = Utils.getDaysBetweenDates(item.getStartDate(), item.getEndDate());
            for (Date itemDate : itemDates) {
                if (!holidayLst.contains(itemDate) && betweenInputDate(itemDate, startInputDate, endInputDate))
                    holidayLst.add(itemDate);
            }
        }

        int countHoliday = holidayLst.size();

        return Float.valueOf(countDay - countHoliday);
    }

    private Date createStartWorkTimeToAbsence(long time, int shift) {
        if (shift == 1) return new Date(time + TimeUnit.HOURS.toMillis(8) + TimeUnit.MINUTES.toMillis(30));
        return new Date(time + TimeUnit.HOURS.toMillis(12));
    }

    private Date createEndWorkTimeToAbsence(long time, int shift) {
        if (shift == 1) return new Date(time + TimeUnit.HOURS.toMillis(12));
        return new Date(time + TimeUnit.HOURS.toMillis(17) + TimeUnit.MINUTES.toMillis(30));
    }
}
