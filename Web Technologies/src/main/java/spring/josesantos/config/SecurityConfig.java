package spring.josesantos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		final String sqlUserName = "select u.email, u.password, u.enabled from utilizadores u where u.email = ?";
		final String sqlAuthorities = "select ur.email, ur.role from utilizadores ur where ur.email = ?";

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(sqlUserName)
				.authoritiesByUsernameQuery(sqlAuthorities).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()// , authorize request
			.antMatchers("/registarEvento", "/registarTempo").access("hasRole('STAFF')")
			.antMatchers("/registarParticipante", "/verInscricoes").access("hasRole('ATLETA')")
			.antMatchers("/*").permitAll()
			.and()
			.formLogin().loginPage("/login")// specifies the location of the log in page
			.loginProcessingUrl("/j_spring_security_check")// login submit target
			.defaultSuccessUrl("/")// default-target-url,
			.failureUrl("/login?error")// authentication-failure-url,
			.usernameParameter("username")// overrides Spring's default
			.passwordParameter("password");			
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
