package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.example.demo.service.CustomSuccessHandler;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	

	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(c -> c.disable())
        	
        	.authorizeHttpRequests(request -> request.requestMatchers("/admin_page")
        			.hasAuthority("ADMIN").requestMatchers("/home").hasAuthority("USER")
        			.requestMatchers("/profile").hasAuthority("USER")
        			.requestMatchers("/api/user-email").hasAuthority("USER")
        			.requestMatchers("/api/favorites/**").hasAuthority("USER")
        			
        			 .requestMatchers(
        	                    "/registration**",
        	                    "/js/**",
        	                    "/css/**",
        	                    "/img/**").permitAll()
        			 
        			.anyRequest().authenticated())
	        
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(customSuccessHandler)
                .permitAll()
            )
            
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logoutSuccess=true")
                .permitAll()
            )
            .sessionManagement(session -> session
            	    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            	);

        return http.build();
    }
	
	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	  public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:8080")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowedHeaders("Authorization", "Content-Type")
	        		.allowCredentials(true);
	    }
	

    
}
