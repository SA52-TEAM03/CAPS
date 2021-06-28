package CA.CAPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import CA.CAPS.interceptor.LoginInterceptor;


@Component
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor testInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(testInterceptor);
	}
}
