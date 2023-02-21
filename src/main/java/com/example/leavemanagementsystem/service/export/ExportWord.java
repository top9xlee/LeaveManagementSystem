package com.example.leavemanagementsystem.service.export;

import com.example.leavemanagementsystem.dto.WordDto;
import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.utils.Utils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.apache.commons.lang3.StringUtils;



import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

public class ExportWord {

    private static final String Source = "word/BM1a-NghiPhepNV.docx";
    private final XWPFDocument document;
    private final WordDto wordDto;



    public ExportWord(WordDto wordDto) throws IOException, InvalidFormatException {
        this.wordDto = wordDto;
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        File e = new File(classLoader.getResource(Source).getFile());
//        File f = new File(classLoader.getResource(New_Source).getFile());
//        if (!f.exists()) f.createNewFile();
//        FileCopyUtils.copy(e, f);
//        document = new XWPFDocument(OPCPackage.open(f));
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream  a = new FileInputStream(classLoader.getResource(Source).getFile());
        document = new XWPFDocument(OPCPackage.open(a));
    }


    private void writeDataLines() throws IOException, InvalidFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime localDateStartDate = wordDto.getStartDate();
        LocalDateTime localDateEndDate = wordDto.getEndDate();
        String startDate = wordDto.getStartDate().format(formatter);
        String endDate = wordDto.getEndDate().format(formatter);
        int year = localDateStartDate.getYear();
        int hourStartDate = localDateStartDate.getHour();
        int minuteStartDate = localDateStartDate.getMinute();
        int hourEndDate = localDateEndDate.getHour();
        int minuteEndDate = localDateEndDate.getMinute();
        LocalDateTime createDate = wordDto.getCreateDate();
        int createDay = createDate.getDayOfMonth();
        int createMonth = createDate.getMonthValue();

//        String name = wordDto.getUserName();
//        for(int k =name.length();k<=0;k--){
//
//        }


    int i = 0;
    List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph:paragraphs){
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                int a =1;
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
//                    if(i == 4 && a == (runs.size())  ){
//                        text.concat("Phòng công nghệ");
//                        r.setText(text,0);
//                    }
                    a++;
                    if (text != null && text.contains("phận")&& i ==4  ) {
                        text = text.replace("phận", "phận:     "+wordDto.getDepartmentName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("là")&& i ==6  ) {
                        text = text.replace("là", "là:      "+wordDto.getUserName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("danh)")&& i ==7  ) {
                        text = text.replace("danh)", "danh):        "+wordDto.getJobName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("tác")&& i ==8  ) {
                        text = text.replace("tác", "tác:        "+ wordDto.getDepartmentName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("năm")&& i ==9 ) {
                        text = text.replace("năm", "năm:       "+ year);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("nghỉ")&& i ==10  ) {
                        boolean checkNaturalNumber = Utils.checkNaturalNumber(wordDto.getDayOff());
                        if(checkNaturalNumber == true){
                            int dayOff = (int) wordDto.getDayOff();
                            text = text.replace("nghỉ", "nghỉ:      "+ dayOff);
                            r.setText(text, 0);
                        }else{
                            text = text.replace("nghỉ", "nghỉ:      "+ wordDto.getDayOff());
                            r.setText(text, 0);
                        }
                    }
                    if (text != null && text.contains("Từ")&& i ==11  ) {
                        text = text.replace("Từ", "Từ:  "+ hourStartDate + "h" + minuteStartDate+ "  ngày "+startDate + "  đến  "+hourEndDate+"h"+minuteEndDate +"  ngày  "+endDate);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("phép")&& i ==12  ) {
                        text = text.replace("phép", "phép:    "+ wordDto.getDescription());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("cần")&& i ==13  ) {
                        text = text.replace("cần", "cần:     "+ wordDto.getPhoneNumber());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains(")")&& i ==14  ) {
                        text = text.replace(")", "):    "+ wordDto.getNamePersonInCharge());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("danh")&& i ==15  ) {
                        text = text.replace("danh", "danh:    "+ wordDto.getJobNamePersonInCharge())+"  ";
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("phận")&& i ==15  ) {
                        text = text.replace("phận", "phận:    "+ wordDto.getDepartmentName()) + "  ";
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("ngày")&& i ==17  ) {
                        text = text.replace("ngày", "ngày "+ createDay);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("tháng")&& i ==17  ) {
                        text = text.replace("tháng", "tháng "+ createMonth);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("năm")&& i ==17  ) {
                        text = text.replace("năm", "năm "+ year);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("Nguyễn")&& i ==22  ) {
                        text = text.replace("Nguyễn", wordDto.getUserName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("Nguyên")&& i ==22  ) {
                        text = text.replace("Nguyên", wordDto.getHeadName());
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("duyệt")&& i ==25   ) {
                        if(wordDto.getType() == 1 ){
                            text = text.replace("duyệt" , "duyệt :  Có phép");
                            r.setText(text, 0);
                        }
                        if(wordDto.getType() == 2 ){
                            text = text.replace("duyệt" , "duyệt:  Không phép");
                            r.setText(text, 0);
                        }
                    }
                    if (text != null && text.contains("hello")&& i ==27  ) {
                        text = text.replace("hello", wordDto.getAbsenceResponse());
                        r.setText(text, 0);
                    }


//                    if (text != null && text.contains("tác")&& i ==8  ) {
//                        text = text.replace("tác", "tác:        "+ wordDto.getDepartmentName());
//                        r.setText(text, 0);
//                    }if (text != null && text.contains("tác")&& i ==8  ) {
//                        text = text.replace("tác", "tác:        "+ wordDto.getDepartmentName());
//                        r.setText(text, 0);
//                    }if (text != null && text.contains("tác")&& i ==8  ) {
//                        text = text.replace("tác", "tác:        "+ wordDto.getDepartmentName());
//                        r.setText(text, 0);
//                    }






                }
            }
            i++;
        }




    }

    public void export() throws IOException, InvalidFormatException {
        String name = StringUtils.stripAccents(wordDto.getUserName());
        String newName = name.replace(" ","_");
        System.out.println(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDate = wordDto.getStartDate().format(formatter);
        String docName =newName+"-Don_xin_nghi_phep-"+startDate;
        writeDataLines();
        String source = "src/main/resources/word/export/"+docName+".docx";
//        String source = "D:\\test\\"+docName+".docx";
        FileOutputStream fileOut = new FileOutputStream(source);
        document.write(fileOut);

        document.close();
        fileOut.close();
    }


}
