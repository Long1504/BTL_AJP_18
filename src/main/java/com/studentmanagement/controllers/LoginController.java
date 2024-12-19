package com.studentmanagement.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Đăng xuất thành công!");
        }
        return "login";
    }

    @GetMapping("/redirect-after-login")
    public String redirectAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String userId = auth.getName();
        
        String role = auth.getAuthorities().stream().findFirst().get().getAuthority();
        
//        httpSession.setAttribute("userId", userId);

        if (role.equals("ROLE_QT")) {
            return "redirect:/admin/home";
        } else if (role.equals("ROLE_GV")) {
            return "redirect:/teacher/home";
        } else if (role.equals("ROLE_SV")) {
            return "redirect:/student/home";
        } else {
            return "redirect:/login?error=true";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
    	request.logout();
        return "redirect:/login?logout=true";
    }
}
