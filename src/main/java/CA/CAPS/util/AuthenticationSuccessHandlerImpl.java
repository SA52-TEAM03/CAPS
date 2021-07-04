package CA.CAPS.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.UserServiceImple;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	UserServiceImple userService;

	@Autowired
	HttpSession session;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		String username = authentication.getName();

		if (authorities.contains("admin")) {
			Admin admin = userService.findAdminByUserName(username);
			session.setAttribute("role", "Admin");
			session.setAttribute("user", admin);
			new DefaultRedirectStrategy().sendRedirect(request, response, "/admin/student/list");
			return;
		}

		if (authorities.contains("student")) {
			Student student = userService.findStudentByUserName(username);
			session.setAttribute("role", "Student");
			session.setAttribute("user", student);
			new DefaultRedirectStrategy().sendRedirect(request, response, "/student/grades");
			return;
		}

		if (authorities.contains("lecturer")) {
			Lecturer lecturer = userService.findLecturerByUserName(username);
			session.setAttribute("role", "Lecturer");
			session.setAttribute("user", lecturer);
			new DefaultRedirectStrategy().sendRedirect(request, response, "/lecturer/courses");
			return;
		}

		new DefaultRedirectStrategy().sendRedirect(request, response, "/");

		return;
	}

}
