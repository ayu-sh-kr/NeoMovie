package dev.arhimedes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${google.username}")
    private String googleMail;

    @Value("${google.password}")
    private String googlePassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> {
                    csrf.disable();
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/resources/**", "/*.css", "/*.js", "/images/*.*", "/javascript/*.*").permitAll();
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers("/register/**").permitAll();
                    authorize.requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();
                    form.loginProcessingUrl("/login").permitAll();
                    form.defaultSuccessUrl("/").permitAll();
                    form.failureUrl("/login?failure=true").permitAll();
                })
                .logout(logout -> {
                    logout.logoutSuccessUrl("/login?logout");
                });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager manager(UserDetailsService userDetailsService, PasswordEncoder encoder){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(authenticationProvider);
//    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(googleMail);
        mailSender.setPassword(googlePassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }
}
