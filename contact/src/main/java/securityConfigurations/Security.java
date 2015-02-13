package securityConfigurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class Security extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http      
			.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
			.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/dist/**", "/img/**", 
						"/viewRegister", "/register", "/validate", "/forgotPassword", "/recoverPassword").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
				
		/*http            //.csrf().disable()

		.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
		.authorizeRequests()
		.antMatchers("/", "/home").permitAll()
			//.anyRequest().authenticated()
		.and().formLogin()
			.loginPage("/login").permitAll()
		.and().logout().permitAll();*/
	
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
					"select username, user_password, enabled from users where username = ?")
				.authoritiesByUsernameQuery(
					"select users.username, user_roles.role from users inner join user_roles "
					+ "on users.user_id = user_roles.user_id "
					+ "where users.username = ?");
	}

}