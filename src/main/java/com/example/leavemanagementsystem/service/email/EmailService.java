package com.example.leavemanagementsystem.service.email;


import com.example.leavemanagementsystem.dto.AbsenceResponse;
import com.example.leavemanagementsystem.dto.DepartmentResponse;
import com.example.leavemanagementsystem.model.Absence;
import com.example.leavemanagementsystem.model.UserEntity;
import com.example.leavemanagementsystem.utils.Utils;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendMailNotification(UserEntity user, String headDepartmentName, Absence absenceResponse1, String[] email) throws UnsupportedEncodingException, MessagingException {
        String subject = "Thông báo nghỉ phép ";
        String senderName = "tms.vconenx.vn";
        String mailContentDayInfo;
        boolean a = Utils.checkNaturalNumber(absenceResponse1.getDayOff());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime startDate1 = LocalDateTime.ofInstant(absenceResponse1.getStartDate().toInstant(), ZoneId.systemDefault());
        LocalDateTime endDate1 = LocalDateTime.ofInstant(absenceResponse1.getEndDate().toInstant(), ZoneId.systemDefault());
        String startDate = startDate1.toLocalDate().format(formatter);
        String endDate = endDate1.toLocalDate().format(formatter);

        mailContentDayInfo = "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\"> Từ " + startDate1.getHour() + "h" + startDate1.getMinute() + " ngày " + startDate + " đến " + endDate1.getHour() + "h" + endDate1.getMinute() + " ngày " + endDate + "</td>\n";

        String mailContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\" />" +
                "<style>\n" +
                "\n" +
                "table {\n" +
                "    /*font-family: arial, sans-serif;*/\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "    border: 1px solid #cccccc;\n" +
                "    text-align: center;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                ".tr{\n" +
                "    margin: auto!important;\n" +
                "}\n" +
                "\n" +
                "\n" +
                ".th{\n" +
                "   text-align:left!important;\n" +
                "   }\n" +
                ".td{\n" +
                "    color: #545454;\n" +
                "    font-family: Poppins;\n" +
                "}\n" +
                "\n" +
                ".tr:nth-child(odd){\n" +
                "    background-color: #cccccc;\n" +
                "}\n" +
                "tr:nth-child(even) {\n" +
                "    background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>" +

                "<p>Kính gửi: Anh/Chị " + headDepartmentName + "</p>\n" +
                "<table style=\"width:60%; border: 1px solid black;border-collapse: collapse;\">" +
                "  <tr>\n" +
                "<th style=\"width:40%; border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Họ và tên:</th>" +
                "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + user.getFullName() + "</td>\n" +
                "  </tr>\n" +
                "<tr style=\" padding: 5px;border: 1px solid black;border-collapse: collapse;\">" +
                "<th style=\" border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Chức vụ:</th>" +
                "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + user.getJobName() + "</td>\n" +
                "  </tr>\n" +
                "  <tr style=\" border: 1px solid black;border-collapse: collapse;\">" +
                "<th style=\" border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Phòng ban:</th>" +
                "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + user.getDepartment().getDepartmentName() + "</td>\n" +
                "</tr>" +
                "  <tr style=\" border: 1px solid black;border-collapse: collapse;\">" +
                "<th style=\" border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Xin được phép nghỉ:</th>" +
                mailContentDayInfo +
                "  </tr>\n" +
                "  <tr style=\" border: 1px solid black;border-collapse: collapse;\">" +
                "<th style=\" border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Số ngày:</th>" +
                "\n";
        if (a == true) {
            int dayOff = (int) absenceResponse1.getDayOff();
            mailContent +=
                    "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + dayOff + "</td>\n";
        } else {
            mailContent +=
                    "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + absenceResponse1.getDayOff() + "</td>\n";
        }
        mailContent +=
                "  </tr>\n" +
                        "  \n" +
                        "  <tr style=\" border: 1px solid black;border-collapse: collapse;\">" +
                        "<th style=\" border: 1px solid black;border-collapse: collapse;text-align: left; padding: 5px;\">Lí do xin nghỉ:</th>" +
                        "    <td style=\"text-align: left; padding: 5px;border: 1px solid black; border-collapse: collapse;\">" + absenceResponse1.getDescription() + "</td>\n" +
                        "  </tr>" +
                        "   </table>" +

                        " <p> Kính mong Cấp trên xem xét và chấp thuận." +
                        "</body>\n" +
                        "</html>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, CharEncoding.UTF_8);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

//        FileSystemResource file = new FileSystemResource(new File(classLoader.getResource("word/BM1a-NghiphepNV.docx").getFile()));
//        helper.addAttachment("Đơn xin nghỉ phép.docx",file);
        helper.setFrom("tms.vconnex.vn@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }

    @Override
    public void send(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String verifyURL = siteURL + "/verify/" + user.getVerificationCode();
        System.out.println(verifyURL);
        String subject = "Đổi mật khẩu tms.vconnex.vn";
        String senderName = "tms.vconenx.vn";
        String mailContent = "<p>Dear " + user.getUserName() + ",</p>";
        mailContent += "<p>Hãy click vào link bên dưới để xác nhận thay đổi mật khẩu</p>";
        mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
        mailContent += "<p>No reply necessary<br> The admin </p>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tms.vconnex.vn@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }

    @Override
    public void sendPass(UserEntity user, String siteURL, String newPass) throws UnsupportedEncodingException, MessagingException {
        String loginUrl = siteURL + "/_admin/login";
        String subject = "Reset password success";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getUserName() + ",</p>";
        mailContent += "<p>New password for you : " + newPass + " </p>";
        mailContent += "<p>Please click the link below to login again with new password:</p>";
        mailContent += "<h3><a href=\"" + loginUrl + "\">Login</a></h3>";
        mailContent += "<p>Thank you<br> =))</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        helper.setFrom("nhom4fptaptech@gmail.com", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        mailSender.send(message);
    }

    @Override
    public void sendNotiToHeadDepa(UserEntity user) throws UnsupportedEncodingException, MessagingException {
//        String verifyURL = siteURL + "/verify/" + user.getVerificationCode();
//        System.out.println(verifyURL);
        String subject = "Thông báo đơn nghi";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getUserName() + ",</p>";
        mailContent += "<p>Please click the link below to verify your registration:</p>";
//        mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
        mailContent += "<p>Thank you<br> The admin </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("SystemTMS", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        helper.setText(mailContent, true);
        mailSender.send(message);

    }

    @Override
    public void sendNotiToUser(UserEntity user) throws UnsupportedEncodingException, MessagingException {

    }

    @Override
    public void sendNotiToManager(UserEntity user) throws UnsupportedEncodingException, MessagingException {

    }
}
