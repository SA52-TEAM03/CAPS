package CA.CAPS.repo;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import CA.CAPS.CapsApplication;
import CA.CAPS.service.MailService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class testmail {

	@Autowired
	MailService mailservice;
	
	@Test
	public void sendMail() {
		mailservice.sendMail("e0696868@u.nus.edu", "subject", "some test text.");
		System.out.println("mail task deploy success");
	}
}