package hello.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@GetMapping("text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "hello Spring!");
		return "basic/text-basic";
	}
	
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring!</b>");
		return "basic/text-unescaped";
	}
	
	@GetMapping("variable")
	public String variable(Model model) {
		User userA = new User("userA",20);
		User userB = new User("userB",30);
		
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		Map<String,User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		model.addAttribute("User", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);
		
		return "basic/variable";
	}
	
	@GetMapping("basic-objects")
	public String basicObjects(HttpSession httpSession) {
		
		httpSession.setAttribute("sessionData", "Hello Session");
		
		return "basic/basic-objects";
	}
	
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}
	
	@GetMapping("link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}
	
	@GetMapping("literal")
	public String literal(Model m) {
		m.addAttribute("data", "hello");
		return "basic/literal";
	}
	
	@GetMapping("operation")
	public String operation(Model m) {
		m.addAttribute("data", "spring");
		m.addAttribute("nullData", null);
		return "basic/operation";
	}
	
	
	@Component("helloBean")
	static class HelloBean{
		public String hello(String data) {
			return "Hello"+data;
		}
	}
	
	@Data
	static class User{
		private String username;
		private int age;
	public User(String username, int age) {
		this.username = username;
		this.age = age;
	}
	
	}
}
