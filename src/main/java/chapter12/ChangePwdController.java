package chapter12;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	private ChangePasswordService cps;
	@Autowired
	public void setChangePasswordService(ChangePasswordService cps) {
		this.cps=cps;
	}
	
	@GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand cps) {
		return "/edit/changePwdForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCmd,errors);
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authinfo = (AuthInfo) session.getAttribute("authInfo");
		
		try {
		cps.ChangePassword(authinfo.getEmail(), pwdCmd.getCurrentPassword(), pwdCmd.getNewPassword());
		
		return "/edit/changePwd";
		}catch(WrongIdPasswordException e) {
			errors.rejectValue("currentpassword", "notMatching");
			
			return "/edit/changePwdForm";
		}
	}
}
