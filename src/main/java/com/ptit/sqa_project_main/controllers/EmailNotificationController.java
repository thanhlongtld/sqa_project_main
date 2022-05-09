package com.ptit.sqa_project_main.controllers;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.User;
import com.ptit.sqa_project_main.services.ClientService;
import com.ptit.sqa_project_main.services.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.NamedAttributeNode;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class EmailNotificationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ScheduledEmailService scheduledEmailService;

    @GetMapping("/email-notification")
    public String index(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if(user!=null){
            List<Client> clients = clientService.getAll();
            //    System.out.println(clients.size());
            model.addAttribute("clients",clients);

            return "email-notification";
        }
        return "redirect:/login";

    }

    @PostMapping("/email-notification/send")
    public String sendManualMail(@Validated @RequestParam("client_mail") String[] mail, @RequestParam("title") String title,
                                 @RequestParam("message") String message, @RequestParam("file") MultipartFile[] files ) throws MessagingException {

        scheduledEmailService.sendManualMail(mail,title,message,files);

        return "redirect:/email-notification";
    }
}

