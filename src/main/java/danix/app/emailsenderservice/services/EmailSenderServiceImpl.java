package danix.app.emailsenderservice.services;

import jakarta.activation.DataContentHandler;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl {
    
    private final String FROM;
    
    private final JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

    @Autowired
    public EmailSenderServiceImpl(@Value("${spring.mail.username}") String from, JavaMailSender javaMailSender) {
        FROM = from; 
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String to, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setFrom(FROM);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject("Spring messenger service application");
            mimeMessage.setContent(
                            """
                                <body style = "color: white; text-align: center">
                                        <h1 style = "color: #000720;">Spring messenger service</h1>
                                        <p style="background-color: #000720; font-size: 25px; display: inline;">""" + message + """
                                        </p>
                                </body>
                            """,
                    "text/html;charset=UTF-8");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }

    }
}
