package com.ahad.hauspilot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
@Configuration public class SecurityConfig {
 @Bean UserDetailsService users(@Value("${app.demo-user}") String username,@Value("${app.demo-password}") String password){return new InMemoryUserDetailsManager(User.withUsername(username).password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password)).roles("ADMIN").build());}
 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {return http.cors(c->c.configurationSource(cors())).csrf(csrf->csrf.disable()).authorizeHttpRequests(a->a.requestMatchers("/actuator/health").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).build();}
 private UrlBasedCorsConfigurationSource cors(){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of("http://localhost:5173"));c.setAllowedMethods(List.of("GET","POST","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
}
