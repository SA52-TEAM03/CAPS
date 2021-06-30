package CA.CAPS.util;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        if (authorities.contains("admin")){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/admin/student/list");
            return;
        }
        
        if (authorities.contains("student")){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/student/grades");
            return;
        }
        
        if (authorities.contains("lecturer")){
            new DefaultRedirectStrategy().sendRedirect(request, response, "/lecturer/courses");
            return;
        }
        
        new DefaultRedirectStrategy().sendRedirect(request, response, "/");
        
        return;
	}

}
