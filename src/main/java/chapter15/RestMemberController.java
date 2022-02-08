package chapter15;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class RestMemberController {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@GetMapping("/members")
	public List<Member> list(@ModelAttribute("cmd") ListCommand listCommand, Errors errors,
			HttpServletResponse response) {

		if (errors.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectbyRegDate(listCommand.getFrom(), listCommand.getTo());

			return members;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@GetMapping("/member/{id}")
	public Member detail(@PathVariable("id") long memberId, HttpServletResponse response) {
		Member member = memberDao.selectById(memberId);
		if(member==null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return member;
	}
}
