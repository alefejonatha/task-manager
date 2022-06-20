package desafio.taskmanager.security;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final JwtFilter jwtFilter;

    private static final String ACCESS_USER_URL = "/api/v1/users/**";
    private static final String ACCESS_TASK_URL = "/api/v1/tasks/**";
    private static final String ACCESS_PROJECT_URL = "/api/v1/projects/**";
    private static final String H2_CONSOLE_URL = "/h2-console/**";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private static final String[] SWAGGER_RESOURCES = {
            // -- swagger ui
            "/**.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/configuration/**",
            "/swagger-resources/**",
            "/swagger-ui/**"
    };


    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

                .antMatchers(ACCESS_PROJECT_URL).hasRole(ROLE_ADMIN)
                .antMatchers(ACCESS_TASK_URL).hasAnyRole(ROLE_USER, ROLE_ADMIN)

                .antMatchers(H2_CONSOLE_URL, ACCESS_USER_URL).permitAll()
                .antMatchers(SWAGGER_RESOURCES).permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings(H2_CONSOLE_URL);
        return registrationBean;
    }

}
