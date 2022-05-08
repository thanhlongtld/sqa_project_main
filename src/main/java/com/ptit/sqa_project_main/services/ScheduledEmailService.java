package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.ScheduledEmail;
import com.ptit.sqa_project_main.repositories.ScheduledEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ScheduledEmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private ScheduledEmailRepository repository;

    public List<ScheduledEmail> getAll() {
        return (List<ScheduledEmail>) this.repository.findAll();
    }

    public int checkScheduleDay(int day) {
        List<ScheduledEmail> sList = getAll();
        for(ScheduledEmail scheduledEmail :sList){
            if(scheduledEmail.getScheduledDate() == day) return scheduledEmail.getId();
        }
        return -1;
    }

    public void sendBillingEmail(){
        System.out.println("billing email");
    }

    public void sendWarningEmail(){
        System.out.println("warning email");
    }

    public void sendManualMail(String[] mail, String title, String message, MultipartFile[] files) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");

        helper.setText(message, true);
        helper.setSubject(title);
        helper.setTo(mail);

        if(files.length!=0 && !files[0].getOriginalFilename().equals("")){
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    helper.addAttachment(file.getOriginalFilename(), new InputStreamSource(){

                        @Override
                        public InputStream getInputStream() throws IOException {
                            return file.getInputStream();
                        }
                    });
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        }


        // Send Message!
       this.emailSender.send(mimeMessage);
    }


}
