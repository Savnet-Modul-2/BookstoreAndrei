package modul2proiect.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String code){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Verificare cont aplicatie Bookstore");
        message.setText("Codul tau de verificare este: "+code);
        mailSender.send(message);
    }
}
