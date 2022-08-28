package net.fablat.fablatres.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableWebFlux
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors();
//                .and()
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().permitAll()
//                                //.antMatchers("/", "/public/**").permitAll()
//                                //.antMatchers("/hello").authenticated()
//                                //.anyRequest().authenticated()
//                )
//                .mvcMatcher("/hello")
//                .authorizeRequests()
//                .mvcMatchers("/hello")
//                .access("hasAuthority('SCOPE_fablat.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

/*    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http.cors()
                .and()
//                .mvcMatcher("/hello")
//                .authorizeRequests()
//                .mvcMatchers("/hello")
//                .access("hasAuthority('SCOPE_fablat.read')")
//                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

    @Bean
    public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }*/

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest()/*.antMatchers("/", "/public/**")*/; // No security at all
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("net.fablat.fablatres.entities");
        // Hibernate properties
        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect"); // for MySQL 5.x to 8.x
        sessionBuilder.setProperty("hibernate.globally_quoted_identifiers", "true");
        //sessionBuilder.setProperty("current_session_context_class", "thread");
        return sessionBuilder.buildSessionFactory();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return passwordEncoder;
    }
}
