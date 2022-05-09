package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import com.ptit.sqa_project_main.models.ScheduledEmail;
import com.ptit.sqa_project_main.models.Usage;
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
    private ClientService clientService;

    @Autowired
    private UsageService usageService;

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

    public void sendBillingEmail(int month, int year) throws MessagingException {
        List<Client> clients = clientService.getAll();
        for(Client client: clients){
            Usage u = usageService.getByClientIdAndMonthYear(client.getId(),month,year);
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");

            helper.setSubject("Thông báo tiền nước");
            helper.setTo(client.getEmail());

            String htmlMessage = "" +
                    "<p>Kính chào quý khánh,</p>\n" +
                    "<p>Tên khách hàng: <strong>"+ client.getName()+"</strong></p>\n" +
                    "<p>Mã Khách hàng: <strong>"+client.getClientCode() + "</strong></p>\n" +
                    "<p>WTHANOI xin thông báo về tiền nước sử dụng tháng"+ month +" / " + year + "</p>\n" +
                    "<table style=\"border-collapse:collapse;width:47.8426%;height:44px\" border=\"1\"><colgroup><col style=\"width:29.7258%\"><col style=\"width:31.6017%\"><col style=\"width:25.2525%\"><col style=\"width:13.4199%\"></colgroup>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td>Chỉ số mới</td>\n" +
                    "<td>Chỉ số cũ</td>\n" +
                    "<td>Sử dụng</td>\n" +
                    "<td>Thành tiền</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td>"+ u.getTotalCBM()+"</td>\n" +
                    "<td>" + Integer.toString(u.getTotalCBM() - u.getRecentUsedCBM()) + "</td>\n" +
                    "<td>"+u.getRecentUsedCBM()+"</td>\n" +
                    "<td>"+u.getBill().getTotalPrice()+"</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<p>Đề nghị quý khách thực hiện thanh toán đúng hạn</p>\n" +
                    "<p>Cảm ơn quý khách đã sử dụng dịch vụ của WTHANOI!</p>\n" +
                    "<p>(Đây là thư do hệ thống tự tạo ra, quý khách vui lòng không trả lời thư này)</p>";

            System.out.println(htmlMessage);

            helper.setText(htmlMessage,true);
            this.emailSender.send(mimeMessage);
        }

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
