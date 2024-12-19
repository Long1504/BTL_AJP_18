package com.studentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/static/**").permitAll()
                .requestMatchers("/admin/**").hasRole("QT")
                .requestMatchers("/teacher/**").hasRole("GV")
                .requestMatchers("/student/**").hasRole("SV")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // URL trang đăng nhập
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("userId") // Tên trường mã người dùng
                .passwordParameter("password") // Tên trường mật khẩu
                .defaultSuccessUrl("/redirect-after-login", true) // URL chuyển hướng đăng nhập thành công
                .failureUrl("/login?error=true") // URL chuyển hướng đăng nhập thất bại
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL xử lý logic đăng xuất
                .logoutSuccessUrl("/login?logout=true") // URL chuyển hướng đăng xuất
                .invalidateHttpSession(true) // Hủy phiên làm việc
                .deleteCookies("JSESSIONID")
//                .addLogoutHandler((request, response, authentication) -> {
//                	HttpSession session = request.getSession(false);
//                	if (session != null) {
//                        session.removeAttribute("userId"); // Xóa userId khi đăng xuất
//                    }
//                })
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Không mã hóa mật khẩu (dùng mật khẩu gốc lưu trong CSDL)
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
