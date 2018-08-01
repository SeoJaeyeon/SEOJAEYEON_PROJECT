package com.fitnessmanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer.ConcurrencyControlConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fitnessmanagement.service.SaveLoginIdHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	SaveLoginIdHandler slh;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/login","/service","/resources/**","/view").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .antMatchers("/**").permitAll()
                .and()
           .formLogin()
           		.successHandler(slh)
           		.loginPage("/login")
           		.loginProcessingUrl("/loginProcess")
                .permitAll()
                .and()
           .logout()
           		.logoutSuccessUrl("/login")
           		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        	
    
        

    }
	

	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
