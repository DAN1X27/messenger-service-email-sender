package danix.app.emailsenderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl {
    
    private final String FROM;
    
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderServiceImpl(@Value("${spring.mail.username}") String from, JavaMailSender javaMailSender) {
        FROM = from; 
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String to, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setText(message);
        msg.setFrom(FROM);
        msg.setSubject("Spring messenger service application");
        javaMailSender.send(msg);
    }
}
