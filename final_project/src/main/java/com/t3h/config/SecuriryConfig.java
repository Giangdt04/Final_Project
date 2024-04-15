package com.t3h.config;


import com.t3h.securiry.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecuriryConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) {
        try {
            builder.userDetailsService(customUserDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeHttpRequests((author) -> author.requestMatchers("/", "/login").permitAll() // config cho phép vào page login mà không cần đăng nhập
                        .requestMatchers("/view/home/admin").hasAnyRole("admin") // config chỉ cho phép vào url /templates/admin/Admin khi có quyền admin
                        .requestMatchers("/product/**").hasAnyRole("admin") // chỉ cho phép truy cập vào url "/staff/**", /product/** khi có quyền admin
                        .requestMatchers("/staff/**").hasAnyRole("staff") // cho phép truy cập vào /staff/** khi có quyền staff
                        .requestMatchers("/process-after-login").hasAnyRole(new String[]{"admin", "staff"})
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                ).formLogin(form ->
                        form.
                                loginPage("/login") // GET
                                .loginProcessingUrl("/authentication") // POST
                                .defaultSuccessUrl("/process-after-login") // sau khi login thành công sẽ truy cập vào url process-after-login để điều hướng phân quyền
                                .failureUrl("/login").permitAll()
                ).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
        return http.build();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
    }
}
