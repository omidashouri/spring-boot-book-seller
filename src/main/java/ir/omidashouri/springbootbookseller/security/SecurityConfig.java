package ir.omidashouri.springbootbookseller.security;

import ir.omidashouri.springbootbookseller.security.jwt.JwtAuthorizationFilter;
import ir.omidashouri.springbootbookseller.security.jwt.JwtProviderInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProviderInterface jwtProviderInterface;
    private final PasswordEncoder myPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(myPasswordEncoder);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(); //(Cross Origin Resource Sharing) allow different domain request
        http.csrf().disable(); //(Cross Site Reference) disable cookies
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()
                .anyRequest().authenticated();

//        JWT filter
        http.addFilterBefore(jwtAuthenticationFilter(jwtProviderInterface), UsernamePasswordAuthenticationFilter.class);
    }

    //Here we have difference -> no JwtProviderInterface as input constructor
    @Bean
    public JwtAuthorizationFilter jwtAuthenticationFilter(JwtProviderInterface jwtProviderInterface){
        return new JwtAuthorizationFilter(jwtProviderInterface);
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }


}
