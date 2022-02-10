package chapter15;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class RestMemberController {
	private MemberDao memberDao;
	private MemberRegisterService registerService;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberRegisterService(MemberRegisterService registerService) {
		this.registerService = registerService;
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
	
	@PostMapping("/members")
	public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {
		
		try {
			long newMemberId = registerService.regist(regReq);
			
			response.setHeader("location", "/json/member/"+newMemberId);
			response.setStatus(HttpServletResponse.SC_CREATED);
		}catch(DuplicateMemberException e) {
			response.sendError(HttpServletResponse.SC_CONFLICT);
		}
		
		
	}
}
