package nl.rabobank.powerofattorney.application;


import nl.rabobank.powerofattorney.application.model.Role;
import nl.rabobank.powerofattorney.application.model.User;
import nl.rabobank.powerofattorney.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootPoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPoaApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner init(UserRepository repository) {
        return args -> {
            Stream.of("0001", "0002", "0003", "0004", "admin").forEach(name -> {
                User user = new User();
                user.setUsername(name);
                user.setPassword(passwordEncoder.encode("password"));
                if (name.equals("admin")) {
                    user.grantAuthority(Role.ADMIN);
                } else {
                    user.grantAuthority(Role.USER);
                }
                userRepository.save(user);
                System.out.println(user.getUsername() + user.getAuthorities());
            });
            repository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
