package CA.CAPS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	public boolean sendMail(String email, HttpSession session) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("validation code for CAPS registration");
			String code = randomCode();
			session.setAttribute("email", email);
			session.setAttribute("code", code);
			mailMessage.setText("Your registration verification code is: " + code);
			mailMessage.setTo(email);
			mailMessage.setFrom(from);
			mailSender.send(mailMessage);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String randomCode() {
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

}