package com.example.leavemanagementsystem.service.email;


import com.example.leavemanagementsystem.model.*;

import javax.mail.*;
import java.io.*;
import java.util.List;

public interface EmailSender {
    void send(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendMailNotification(UserEntity user, String headDepartmentName,
                              Absence absenceResponse, String[] email)
            throws UnsupportedEncodingException, MessagingException;

    void sendPass(UserEntity user, String siteURL, String newPass)
            throws UnsupportedEncodingException, MessagingException;

    void sendNotiToHeadDepa(UserEntity user) throws UnsupportedEncodingException, MessagingException;

    void sendNotiToUser(UserEntity user) throws UnsupportedEncodingException, MessagingException;

    void sendNotiToManager(UserEntity user) throws UnsupportedEncodingException, MessagingException;
}
