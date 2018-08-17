package messageNotification.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import messageNotification.configs.services.CustomUserDetailsService;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
		  .authorizeRequests()
		//allow access to all static resources  
		  	.antMatchers("/js/**","/css/**","/html/**","/images/**")
		  	.permitAll()  
		//allow only ADMIN to enter the following pages
		    .antMatchers("/admin/**")
		    .hasAuthority("ADMIN")
		    .antMatchers("/authorized/**")
		    .hasAuthority("AUTHORIZED")
//			//allow anonymous users to access signup
		    .antMatchers("/signup")
		    .anonymous()
		// requires authorization for all requests
		//user need only be authenticated with any "role" 
			.anyRequest()
			.authenticated()
			.and()
		//send all unauthenticated users to login page
		// login page is accessible to anonymous
		  .formLogin()
		    .loginPage("/login")
		    .usernameParameter("email")
		    .passwordParameter("password")
		    .failureUrl("/login?error=true")
		    .defaultSuccessUrl("/")
		    .permitAll()
		    .and()
		//logout page is accessible to all
		  .logout()
		  	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		  	.logoutSuccessUrl("/login")
		    .permitAll();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(getUserDetailsService())
        	.passwordEncoder(getPasswordEncoder());
	}
}
