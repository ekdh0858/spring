package chapter11;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// 컨트롤러는 뷰가 화면을 구성하는데 필요한 데이터를 생성해서 전달해야함
// 이때 사용하는 것이 Model임
// 컨트롤러가 뷰에 데이터를 전달하려면 다음 두 가지를 하면 됨
// 1. 요청 매핑 애노테이션이 적용된 메서드의 매개변수로 Model을 추가
// 2. Model 매개변수의 addAttribute 메서드로 뷰에서 사용할 데이터 전달

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	@GetMapping
	public String form(Model model) {
		List<String> job = new ArrayList<>();
		job.add("서버");job.add("프론트");job.add("풀스택");
		
		List<String> tool = new ArrayList<>();
		job.add("Eclipse");job.add("Intellij");job.add("VS Code");
		
		Question q1 = new Question("당신의 역활은 무엇입니까?",job);
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",tool);
		Question q3 = new Question("하고싶은 말이 있으십니까?");
		
		List<Question> questions = new ArrayList<>();
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		
		model.addAttribute("questions",questions);
		
		return "survey/surveyForm";
	}
	@PostMapping
	public String submit(@ModelAttribute("ansdata") AnsweredData data) {
		return "survey/submitted";
	}
}
