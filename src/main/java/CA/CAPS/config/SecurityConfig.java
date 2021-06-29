package CA.CAPS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import CA.CAPS.service.UserServiceImple;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImple userServiceImple;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandlerImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.rememberMe()
		.and()
		.formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(authenticationSuccessHandlerImpl)
		.and()
		.authorizeRequests()
		.antMatchers("/login.html", "/login","/afterlogout.html","/register","/sendEmail/**","/regist").permitAll()
		.antMatchers("/student/**").hasAnyAuthority("student")
		.antMatchers("/lecturer/**").hasAnyAuthority("lecturer")
		.antMatchers("/admin/**","/AdminStudent/**").hasAnyAuthority("admin")
		.anyRequest().authenticated()
		.and()
        .logout()
        .logoutSuccessUrl("/afterlogout.html");
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImple).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/image/**", "/style/**");
	}
}
