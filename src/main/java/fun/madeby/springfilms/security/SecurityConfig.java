package fun.madeby.springfilms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private final UserDetailsServiceImpl userDetailsService;
private final PasswordEncoder encoder;


@Override
protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()
		.antMatchers("/**/edit", "/**/new").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/", "/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		//.usernameParameter("email") required if you want email, not username
		.permitAll()
		.and()
		.logout().logoutUrl("/logout").permitAll();

}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService)
		.passwordEncoder(encoder);
}
}
