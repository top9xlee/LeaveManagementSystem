package com.example.leavemanagementsystem.service.export;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.utils.*;
import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class ExportExcelServiceAllUser {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;


    private final List<List<LocalDateResponse>> listLocalDateOff;
    private final List<List<LocalDate>> listLocalDateWorks;
    private final List<LocalDate> listHolidays;
    private final List<LocalDate> listWeekends;
    private final Long year;
    private final Long month;
    private final List<UserEntity> userEntities;
    private final DateMapper dateMapper;


    private static final String FILE_NAME = "Excel.xlsx";
    private static final String New_Source = "static/Excel.xlsx";

    public ExportExcelServiceAllUser(Long year, Long month, List<UserEntity> userEntities, List<LocalDate> listHolidays,
                                     List<LocalDate> listWeekends, List<List<LocalDateResponse>> listLocalDateOff,
                                     List<List<LocalDate>> listLocalDateWorks, DateMapper dateMapper)
            throws IOException, InvalidFormatException {

        this.listLocalDateOff = listLocalDateOff;
        this.listLocalDateWorks = listLocalDateWorks;
        this.listHolidays = listHolidays;
        this.year = year;
        this.month = month;
        this.listWeekends = listWeekends;
        this.userEntities = userEntities;
        this.dateMapper = dateMapper;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String a = classLoader.getResource(New_Source).toString();
        System.out.println(a);
        File e = new File(classLoader.getResource(FILE_NAME).getFile());
        File f = new File(classLoader.getResource(New_Source).getFile());
        if (!f.exists()) f.createNewFile();
        FileCopyUtils.copy(e, f);
        workbook = new XSSFWorkbook(f);
    }

    private void writeHeaderLine() {
        sheet = workbook.getSheet("Sheet1");

    }

    private CellStyle cellStyleDay() {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 200);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        style.setRotation((short) 90);
        style.setFillForegroundColor(IndexedColors.GOLD.index);
        // and solid fill pattern produces solid grey cell fill
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);
        return style;
    }

    private CellStyle cellStyleUser() {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 200);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        return style;
    }

    private CellStyle cellStyleWorkDay() {
        CellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 200);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        return style;
    }

    private CellStyle cellStyleHeader() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeight((short) 360);
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        return style;
    }


    private void writeDataLines() {
        int rowCountUser = 8;
        int rowCountWork = 8;
        int rowCountDayOff = 8;
        CellStyle style = cellStyleUser();
        CellStyle styleDay = cellStyleDay();
        CellStyle style1 = workbook.createCellStyle();
        CellStyle styleHeader = cellStyleHeader();
        Font font1 = workbook.createFont();
        font1.setFontName("Times New Roman");
        font1.setFontHeight((short) 200);
        style1.setAlignment(HorizontalAlignment.LEFT);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setBorderTop(BorderStyle.THIN);
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setFont(font1);
        LocalDate firstDayOfMonth = LocalDate.of(year.intValue(), month.intValue(), 1);
//        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.getMonth().length(firstDayOfMonth.isLeapYear()));
        int numOfDays = firstDayOfMonth.lengthOfMonth();

        List<LocalDate> listDay = Stream.iterate(firstDayOfMonth, date -> date.plusDays(1))
                .limit(numOfDays)
                .collect(Collectors.toList());

//        DayOfWeek day = localDate1.getDayOfWeek();
//        String day1 = day.toString();


        Row header = sheet.getRow(2);
//        sheet.addMergedRegion( new CellRangeAddress(2,2,0,42));
        String headerString = "Bảng báo cáo lương tháng " + month + " năm " + year;
        createCell(header, 0, headerString, styleHeader);
        Row row1 = sheet.getRow(7);


        //Ghi tên ngày theo tháng
        int columnCount1 = 3;
        for (LocalDate local : listDay) {
            String day = local.getDayOfWeek().toString();
            String nameDay = getDay(day);
            createCell(row1, columnCount1, nameDay, styleDay);
            columnCount1++;
            sheet.autoSizeColumn(1);
        }


        List<Integer> holidays =
                listHolidays.stream().map(localDate -> localDate.getDayOfMonth()).collect(Collectors.toList());
        List<Integer> weekends =
                listWeekends.stream().map(localDate -> localDate.getDayOfMonth()).collect(Collectors.toList());
        int stt = 1;
        //write data user
        for (UserEntity userEntity : userEntities) {
            Row row = sheet.getRow(rowCountUser++);
            createCell(row, 0, stt, style1);
            createCell(row, 1, userEntity.getFullName(), style1);
            createCell(row, 2, userEntity.getJobName(), style1);
            stt++;
        }
        //Điền những ngày đi làm và ngày lễ
        for (List<LocalDate> localDates : listLocalDateWorks) {
            List<Integer> days =
                    localDates.stream().map(localDate -> localDate.getDayOfMonth()).collect(Collectors.toList());
            Row row = sheet.getRow(rowCountDayOff++);
            YearMonth yearMonthObject = YearMonth.of(this.year.intValue(), this.month.intValue());
            int daysInMonth = yearMonthObject.lengthOfMonth();
            int columnCount = 3;
            for (int i = 1; i < daysInMonth + 1; i++) {
                for (LocalDate localDate : localDates) {
                    if (days.contains(i)) {
                        createCell(row, columnCount++, "+", style);
                        break;
                    } else if (holidays.contains(i)) {
                        createCell(row, columnCount++, "L", style);
                        break;
                    } else {
                        columnCount++;
                        break;
                    }
                }
            }
        }
        DateUnit dateUnit = new DateUnit();
        dateUnit.setHour(12);
        //Điền những ngày nghỉ
        for (List<LocalDateResponse> localDates : listLocalDateOff) {
            List<Integer> listdays = localDates.stream()
                    .map(localDate -> localDate.getDate().getDayOfMonth())
//            List<DateUnit> days = localDates.stream()
//                    .map(dateMapper::maptoDateUnit)
                    .collect(Collectors.toList());
            List<DateUnit> days = localDates.stream()
                    .map(dateMapper::maptoDateUnit)
                    .collect(Collectors.toList());


            Row row = sheet.getRow(rowCountWork++);

            YearMonth yearMonthObject = YearMonth.of(this.year.intValue(), this.month.intValue());

            int daysInMonth = yearMonthObject.lengthOfMonth();
            int columnCount = 3;
            for (int i = 1; i < daysInMonth + 1; i++) {
                boolean check = false;
                if (weekends.contains(i)) {
                    createCell(row, columnCount++, " ", style);
                    continue;
                }
                for (LocalDateResponse localDate : localDates) {
                    dateUnit.setDate(i);
                    if (listdays.contains(i) && localDate.getDate().getDayOfMonth() == i && localDate.getType() == 1 && days.contains(dateUnit)) {
                        createCell(row, columnCount++, "R/2", style);
                        check = true;
                        break;
//                    } else if (listdays.contains(i) && localDate.getType() == 1) {
//                            System.out.println("true");
//                            if (localDate.getHour()==8) {
//                            createCell(row, columnCount++, "R/2", style);
//                            } else {
//                            createCell(row, columnCount++, "R", style);
//                        }
//                        break;
                    } else if (listdays.contains(i) && localDate.getType() == 3) {
                        dateUnit.setDate(i);
                        if(days.contains(dateUnit)){
                            createCell(row, columnCount++, "Co/2", style);
                        }else{
                            createCell(row, columnCount++, "Co", style);}
                        break;
                    } else if (listdays.contains(i) && localDate.getDate().getDayOfMonth() == i && localDate.getType() == 2 && days.contains(dateUnit)) {
                        createCell(row, columnCount++, "Ro/2", style);
                        check = true;
                        break;
                    } else if (listdays.contains(i) && localDate.getDate().getDayOfMonth() == i && localDate.getType() == 2 && !days.contains(dateUnit)) {
                        createCell(row, columnCount++, "Ro", style);
                        check = true;
                        break;
//


                    }
                }
                if (check == false) {
                    columnCount++;
                }

            }

        }
        //write data working

        //write data dayOff


        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue(String.valueOf(value));
        } else {
            cell.setCellValue((Long) value);
        }
        cell.setCellStyle(style);
    }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    private String getDay(String day) {
        if (day.equals("SUNDAY")) {
            return "CN";
        } else if (day.equals("MONDAY")) {
            return "T.Hai";
        } else if (day.equals("TUESDAY")) {
            return "T.Ba";
        } else if (day.equals("WEDNESDAY")) {
            return "T.Tư";
        } else if (day.equals("THURSDAY")) {
            return "T.Năm";
        } else if (day.equals("FRIDAY")) {
            return "T.Sáu";
        } else return "T.Bảy";
    }
}
