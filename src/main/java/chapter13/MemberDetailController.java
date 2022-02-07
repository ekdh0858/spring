package chapter13;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberDetailController {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
	}
	
	@RequestMapping("/members/{id}")
	public String detail(@PathVariable("id") long memberId, Model model) {
		Member member = memberDao.selectById(memberId);
		if(member==null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("member",member);
		
		return "list/memberDetail";
	}
	// commonExceptionhandler를 사용하면서 중첩이 됨.
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleMemberNotFoundException() {
		return "list/noMember";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchNamingException() {
		return "list/invalidId";
	}
}
