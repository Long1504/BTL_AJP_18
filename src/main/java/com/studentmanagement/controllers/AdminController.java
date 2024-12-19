package com.studentmanagement.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.studentmanagement.models.Admin;
import com.studentmanagement.models.Class;
import com.studentmanagement.models.Course;
import com.studentmanagement.models.Department;
import com.studentmanagement.models.Feedback;
import com.studentmanagement.models.Student;
import com.studentmanagement.models.Teacher;
import com.studentmanagement.services.AdminService;
import com.studentmanagement.services.ClassService;
import com.studentmanagement.services.CourseService;
import com.studentmanagement.services.DepartmentService;
import com.studentmanagement.services.FeedbackService;
import com.studentmanagement.services.StudentService;
import com.studentmanagement.services.TeacherService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
//	private String adminId = "QT001";
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired 
	private ClassService classService;
	
	@GetMapping("/home")
	public String adminHomeGet(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		model.addAttribute("admin", admin);
		return "admin/adminHome";
	}
	
	@GetMapping("/student-management")
	public String adminStudentManagementGet(@RequestParam(value = "departmentId", required = false, defaultValue = "all") String departmentId,
											@RequestParam(value = "classId", required = false, defaultValue = "all") String classId,
											Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Department> departments = departmentService.getAllDepartment();
		ArrayList<Class> classes = classService.getAllClass();
		ArrayList<Student> students = studentService.getStudentByClassIdAndDepartmentId(classId, 	departmentId);
		model.addAttribute("admin", admin);
		model.addAttribute("departments", departments);
		model.addAttribute("classes", classes);
		model.addAttribute("students", students);
		model.addAttribute("selectedDepartment", departmentId);
		model.addAttribute("selectedClass", classId);
		return "admin/adminStudentManagement";
	}
	
	
	@GetMapping("/student-management/update-student/{studentId}")
    public String editStudentGet(@PathVariable String studentId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		Student student = studentService.getStudentById(studentId);
		ArrayList<Class> classes = classService.getAllClass();
		ArrayList<Department> departments = departmentService.getAllDepartment();
		model.addAttribute("admin", admin);
		model.addAttribute("student", student);
		model.addAttribute("classes", classes);
		model.addAttribute("departments", departments);
        return "/admin/adminEditStudent";
    }
	
	@PostMapping("/student-management/update-student/{studentId}")
    public String editStudentPost(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
		try {
			studentService.updateStudent(student);
			redirectAttributes.addFlashAttribute("message", "Cập nhật sinh viên thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Cập nhật sinh viên thất bại!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/student-management";
    }
	
	@GetMapping("/student-management/delete-student/{studentId}")
	public String deleteStudentGet(@PathVariable String studentId, RedirectAttributes redirectAttributes) {
		try {
			studentService.deleteStudentById(studentId);
			redirectAttributes.addFlashAttribute("message", "Xóa sinh viên thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Không thể xóa sinh viên vì đã liên kết với dữ liệu khác!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/student-management";
    }
	
	
	@GetMapping("/student-management/add-student")
    public String addStudentGet(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Class> classes = classService.getAllClass();
		ArrayList<Department> departments = departmentService.getAllDepartment();
		model.addAttribute("admin", admin);
		model.addAttribute("student", new Student("", "", "", null, "", "", "", "SV@123", "SV", "", ""));
		model.addAttribute("classes", classes);
		model.addAttribute("departments", departments);
        return "/admin/adminAddStudent";
    }
	
	@PostMapping("/student-management/add-student")
	public String addStudentPost(@ModelAttribute Student student, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(student);
	    try {
	        studentService.addStudent(student);
	        redirectAttributes.addFlashAttribute("message", "Thêm sinh viên thành công!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Thêm sinh viên thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
	    return "redirect:/admin/student-management";
	}
	
	@GetMapping("/teacher-management")
	public String adminTeacherManagementGet(@RequestParam(value = "departmentName", required = false, defaultValue = "all") String departmentName, 
											Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Department> departments = departmentService.getAllDepartment();
		ArrayList<Teacher> teachers = teacherService.getTeacherByDepartmentName(departmentName);
		model.addAttribute("admin", admin);
		model.addAttribute("departments", departments);
		model.addAttribute("teachers", teachers);
		model.addAttribute("selectedDepartment", departmentName);
		return "admin/adminTeacherManagement";
	}
	
	@GetMapping("/teacher-management/update-teacher/{teacherId}")
    public String editTeacherGet(@PathVariable String teacherId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		ArrayList<Department> departments = departmentService.getAllDepartment();
		model.addAttribute("teacher", teacher);
		model.addAttribute("admin", admin);
		model.addAttribute("departments", departments);
		System.out.println(teacher);
        return "/admin/adminEditTeacher";
    }
	
	@PostMapping("/teacher-management/update-teacher/{teacherId}")
    public String editTeacherPost(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
		try {
			teacherService.updateTeacher(teacher);
			redirectAttributes.addFlashAttribute("message", "Cập nhật giảng viên thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Cập nhật giảng viên thất bại!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/teacher-management";
    }
	
	@GetMapping("/teacher-management/delete-teacher/{teacherId}")
	public String deleteTeacherGet(@PathVariable String teacherId, RedirectAttributes redirectAttributes) {
		try {
			teacherService.deleteTeacherById(teacherId);
			redirectAttributes.addFlashAttribute("message", "Xóa giảng viên thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Không thể xóa giảng viên vì đã liên kết với dữ liệu khác!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/teacher-management";
    }
	
	@GetMapping("/teacher-management/add-teacher")
    public String addTeacherGet(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Department> departments = departmentService.getAllDepartment();
		model.addAttribute("admin", admin);
		model.addAttribute("teacher", new Teacher("", "", "", null, "", "", "", "GV@123", "GV", "", ""));
		model.addAttribute("departments", departments);
        return "/admin/adminAddTeacher";
    }
	
	@PostMapping("/teacher-management/add-teacher")
	public String addTeacherPost(@ModelAttribute Teacher teacher, Model model, RedirectAttributes redirectAttributes) {
	    try {
	        teacherService.addTeacher(teacher);
	        redirectAttributes.addFlashAttribute("message", "Thêm giảng viên thành công!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Thêm giảng viên thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
	    return "redirect:/admin/teacher-management";
	}
	
	@GetMapping("/course-management")
	public String adminCourseManagementGet(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Course> courses = courseService.getAllCourse();
		System.out.println(courses);
		model.addAttribute("admin", admin);
		model.addAttribute("courses", courses);
		return "admin/adminCourseManagement";
	}
	
	@GetMapping("/course-management/update-course/{courseId}")
    public String editCourseGet(@PathVariable String courseId, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		Course course = courseService.getCourseById(courseId);
		System.out.println(course);
		model.addAttribute("admin", admin);
		model.addAttribute("course", course);
        return "/admin/adminEditCourse";
    }
	
	@PostMapping("/course-management/update-course/{courseId}")
    public String editCoursePost(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
		try {
			courseService.updateCourse(course);
			redirectAttributes.addFlashAttribute("message", "Cập nhật học phần thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Cập nhật học phần thất bại!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/course-management";
    }
	
	@GetMapping("/course-management/delete-course/{courseId}")
	public String deleteCourseGet(@PathVariable String courseId, RedirectAttributes redirectAttributes) {
		try {
			courseService.deleteCourseById(courseId);
			redirectAttributes.addFlashAttribute("message", "Xóa học phần thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Không thể xóa học phần vì đã liên kết với dữ liệu khác!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/course-management";
    }
	
	@GetMapping("/course-management/add-course")
    public String addCCourseGet(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		model.addAttribute("admin", admin);
		model.addAttribute("course", new Course());
        return "/admin/adminAddCourse";
    }
	
	@PostMapping("/course-management/add-course")
	public String addCoursePost(@ModelAttribute Course course, Model model, RedirectAttributes redirectAttributes) {
	    try {
	        courseService.addCourse(course);
	        redirectAttributes.addFlashAttribute("message", "Thêm học phần thành công!");
	    	redirectAttributes.addFlashAttribute("messageType", "success");
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Thêm học phần thất bại!");
	    	redirectAttributes.addFlashAttribute("messageType", "error");
	    }
	    return "redirect:/admin/course-management";
	}
	
	@GetMapping("/feedback-management")
	public String adminFeedbackManagementGet(@RequestParam(value = "status", required = false, defaultValue = "all") String status, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
		Admin admin = adminService.getAdminById(adminId);
		ArrayList<Feedback> feedbacks = feedbackService.getFeedbackByStatus(status);
		ArrayList<String> statuses = new ArrayList<String>();
		statuses.add("Đã duyệt");
		statuses.add("Chưa xem");
		model.addAttribute("admin", admin);
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("statuses", statuses);
		model.addAttribute("selectedStatus", status);
		return "admin/adminFeedbackManagement";
	}
	
	@GetMapping("/feedback-management/delete-feedback/{feedbackId}")
	public String deleteFeedbackGet(@PathVariable String feedbackId, RedirectAttributes redirectAttributes) {
		try {
			feedbackService.deleteFeedbackById(feedbackId);
			redirectAttributes.addFlashAttribute("message", "Xóa đánh giá thành công!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Không thể xóa đánh giá vì đã liên kết với dữ liệu khác!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
        return "redirect:/admin/feedback-management";
    }
	
	@GetMapping("/feedback-management/update-feedback/{feedbackId}")
    public String editFeedbackGet(@PathVariable String feedbackId, Model model, RedirectAttributes redirectAttributes) {
		try {
			feedbackService.updateFeedbackStatusById(feedbackId);
			redirectAttributes.addFlashAttribute("message", "Đã cập nhật trạng thái!");
			redirectAttributes.addFlashAttribute("messageType", "success");
		}
		catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Cập nhật trạng thái thất bại!");
			redirectAttributes.addFlashAttribute("messageType", "error");
		}
		return "redirect:/admin/feedback-management";
    }
}
