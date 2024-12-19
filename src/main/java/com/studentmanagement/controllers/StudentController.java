package com.studentmanagement.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentmanagement.models.Feedback;
import com.studentmanagement.models.ScoreCourse;
import com.studentmanagement.models.Student;
import com.studentmanagement.models.StudentSchedule;
import com.studentmanagement.models.Teacher;
import com.studentmanagement.services.FeedbackService;
import com.studentmanagement.services.ScoreCourseService;
import com.studentmanagement.services.StudentService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private ScoreCourseService scoreCourseService;
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("/home")
	public String studentHomeGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "student/studentHome";
	}
	
	@GetMapping("/infor")
	public String studentInforGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "student/studentInfor";
	}
	
	@PostMapping("/infor")
	public String updateStudentInforPost(HttpServletRequest request, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
        student.setStudentId(studentId);
		try {
			studentService.updateStudent(student);
	        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
		return "redirect:/student/infor";
	}
	
	@GetMapping("/infor/change")
	public String studentInforChangeGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
		return "student/studentInforChange";
	}
	
	@GetMapping("/score-course-list")
	public String studentScoreCourseGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
        Student student = studentService.getStudentById(studentId);
        ArrayList<ScoreCourse> scoreCourses = scoreCourseService.getAllScoreCourseByStudentId(studentId);
        double GPA = scoreCourseService.getGPAByStudentId(studentId);
        model.addAttribute("student", student);
        model.addAttribute("scoreCourses", scoreCourses);
        model.addAttribute("GPA", GPA);
		return "student/studentScoreCourseList";
	}
	
	@GetMapping("/schedule")
	public String studentScheduleGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
        Student student = studentService.getStudentById(studentId);
        ArrayList<StudentSchedule> studentSchedules = studentService.getScheduleByStudentId(studentId);
        model.addAttribute("student", student);
        model.addAttribute("studentSchedules", studentSchedules);
		return "student/studentSchedule";
	}
	
	@GetMapping("/feedback")
	public String studentFeedbackGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentId = auth.getName();
        Student student = studentService.getStudentById(studentId);
		Feedback feedback = new Feedback();
		model.addAttribute("student", student);
		model.addAttribute("feedback", feedback);
		return "student/studentFeedback";
	}
	
	@PostMapping("/feedback")
	public String saveStudentFeedbackPost(HttpServletRequest request, @ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes) {
		if(feedback.getContent() == "") {
			return "redirect:/student/feedback";
		}
		try {
			feedbackService.saveFeedback(feedback);
	        redirectAttributes.addFlashAttribute("message", "Đã gửi đánh giá!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Gửi đánh giá thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
		return "redirect:/student/feedback";
	}
}
