package CA.CAPS.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		Object user = request.getSession().getAttribute("usession");

		if (!(user instanceof Student) && uri.startsWith("/student")) {

			request.setAttribute("msg", "please login first");
			request.getRequestDispatcher("/").forward(request, response);
			return false;

		} else if (!(user instanceof Lecturer) && uri.startsWith("/lecturer")) {

			request.setAttribute("msg", "please login first");
			request.getRequestDispatcher("/").forward(request, response);
			return false;

		} else if (!(user instanceof Admin) && (uri.startsWith("/admin") || uri.startsWith("/Admin"))) {

			request.setAttribute("msg", "please login first");
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		return true;
	}
}