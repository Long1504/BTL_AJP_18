package com.studentmanagement.controllers;

import java.util.ArrayList;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentmanagement.models.Course;
import com.studentmanagement.models.Feedback;
import com.studentmanagement.models.ScoreCourse;
import com.studentmanagement.models.Teacher;
import com.studentmanagement.models.TeacherSchedule;
import com.studentmanagement.services.CourseService;
import com.studentmanagement.services.FeedbackService;
import com.studentmanagement.services.ScoreCourseService;
import com.studentmanagement.services.TeacherService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
//	private String teacherId = "GV005";
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
	}
	
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ScoreCourseService scoreCourseService;
	
	@Autowired CourseService courseService;
	
	@GetMapping("/home")
	public String teacherHomeGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher/teacherHome";
	}
	
	@GetMapping("/infor")
	public String teacherInforGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher/teacherInfor";
	}
	
	@PostMapping("/infor")
	public String updateTeacherInforPost(HttpServletRequest request, @ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		teacher.setTeacherId(teacherId);
		try {
			teacherService.updateTeacher(teacher);
	        redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
		return "redirect:/teacher/infor";
	}
	
	@GetMapping("/infor/change")
	public String teacherInforChangeGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher/teacherInforChange";
	}
	
	@GetMapping("/course-list")
	public String teacherCourseListGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		ArrayList<Course> courses = teacherService.getCourseByTeacher(teacherId);
		model.addAttribute("courses", courses);
		return "teacher/teacherCourseList";
	}
	
	
	@GetMapping("/course-list/score-management")
	public String teacherScoreManagementGet(HttpServletRequest request, Model model, @RequestParam String courseId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("course", course);
		ArrayList<ScoreCourse> scoreCourses = scoreCourseService.getScoreCourseByCourseId(courseId);
		model.addAttribute("scoreCourses", scoreCourses);
		return "teacher/teacherScoreManagement";	
	}
	
	@PostMapping("/course-list/score-management")
	public String updateScoreCoursePost(HttpServletRequest request,
										@RequestParam String courseId,
										@RequestParam ArrayList<Double> score1,  // Lấy all điểm thường xuyên 1
								        @RequestParam ArrayList<Double> score2,
								        @RequestParam ArrayList<Double> scoreFinal) {
		
		ArrayList<ScoreCourse> scoreCourses = scoreCourseService.getScoreCourseByCourseId(courseId);
		
		for (int i = 0; i < score1.size(); i++) {
			scoreCourses.get(i).setScore1(score1.get(i));
			scoreCourses.get(i).setScore2(score2.get(i));
			scoreCourses.get(i).setScoreFinal(scoreFinal.get(i));
	    }
		
		System.out.println("scoreCourses: " + scoreCourses);
		
		for(ScoreCourse scoreCourse : scoreCourses) {
			System.out.println("scoreCourse	: " + scoreCourse);
			scoreCourseService.updateScoreForStudent(scoreCourse);
		}
		
		return "redirect:/teacher/course-list/score-management?courseId=" + courseId;
	}
	
	
	@GetMapping("/schedule")
	public String teacherScheduleGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		ArrayList<TeacherSchedule> teacherSchedules = teacherService.getScheduleByTeacher(teacherId);
		model.addAttribute("teacherSchedules", teacherSchedules);
		return "teacher/teacherSchedule";
	}
	
	@GetMapping("/feedback")
	public String teacherFeedbackGet(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String teacherId = auth.getName();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		Feedback feedback = new Feedback();
		model.addAttribute("feedback", feedback);
		return "teacher/teacherFeedback";
	}
	
	@PostMapping("/feedback")
	public String saveTeacherFeedbackPost(HttpServletRequest request, @ModelAttribute Feedback feedback, RedirectAttributes redirectAttributes) {
		if(feedback.getContent() == "") {
			return "redirect:/teacher/feedback";
		}
		try {
			feedbackService.saveFeedback(feedback);
	        redirectAttributes.addFlashAttribute("message", "Đã gửi đánh giá!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Gửi đánh giá thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
		return "redirect:/teacher/feedback";
	}
}
