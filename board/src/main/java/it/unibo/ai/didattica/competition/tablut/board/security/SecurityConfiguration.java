package it.unibo.ai.didattica.competition.tablut.board.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.unibo.ai.didattica.competition.tablut.board.configuration.AdminConfiguration;
import it.unibo.ai.didattica.competition.tablut.board.model.User;
import it.unibo.ai.didattica.competition.tablut.board.model.UserRole;
import it.unibo.ai.didattica.competition.tablut.board.model.UserRole.Role;
import it.unibo.ai.didattica.competition.tablut.board.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuration class for security
 * 
 * @author a.fontana
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// TODO the admin endpoints has to be configured
	private static final String[] ADMIN_MATCHER = { "/admin", "/admin/**"};
	private static final String[] USER_MATCHER = {  };
	private static final String[] NOAUTH_MATCHER = { "/**" };
	
	@NonNull
	private AdminConfiguration adminConfiguration;

	@NonNull
	private UserDetailsService userDetailsService;
	
	@NonNull
	private UserService userService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http)  throws Exception {
		// this options is active only for h2-console
		http
			.headers()
				.frameOptions()
					.sameOrigin();
		
		http
			.authorizeRequests()
				.antMatchers(ADMIN_MATCHER)
					.hasRole(Role.ADMIN.toString())
				.antMatchers(USER_MATCHER)
					.hasRole(Role.USER.toString())
				.antMatchers(NOAUTH_MATCHER)
					.permitAll()
		.and()
			.httpBasic()
		.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/")
		.and()
			.logout()
				.logoutSuccessUrl("/")
		.and()
			.csrf()
				.disable();
		
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					.expiredUrl("/")
			.and()
				.invalidSessionUrl("/");
	}
	
	@PostConstruct
	public void postConstruct() {
		Set<UserRole> roles = new HashSet<>();
		roles.add(new UserRole(1L, Role.ADMIN));
		
		User user = new User();
		user.setUsername(adminConfiguration.getUsername());
		user.setPassword(encoder().encode(adminConfiguration.getPassword()));
		user.setEnabled(true);
		user.setRoles(roles);
		
		userService.save(user);
		
		log.info("Added admin user: " + adminConfiguration.getUsername() + " password: " + adminConfiguration.getPassword());
	}
}
