package chapter15;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	public ResponseEntity<Member> detail(@PathVariable("id") long memberId, HttpServletResponse response) {

		Member member = memberDao.selectById(memberId);
		if(member==null) {
			throw new MemberNotFoundException();
//			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return ResponseEntity
//					.status(HttpStatus.NOT_FOUND) // ????????? ????????? ?????? ???????????? ?????? ????????? ??????.
//					.body(new ErrorResponse("no member")); // ????????? ??????????????? ????????? ????????????.
		}
		return ResponseEntity.status(HttpStatus.OK).body(member); // ??????????????? ????????? member ???????????? ?????? ?????????.
		// ????????? json???????????? ?????? ????????? ????????????.????????? ???????????? json??? ??????
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlenotFound(){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND) // ????????? ????????? ?????? ???????????? ?????? ????????? ??????.
				.body(new ErrorResponse("no member")); // ????????? ??????????????? ????????? ????????????.
	}
	
	@PostMapping("/members")
	public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response, Errors errors) throws IOException {
		if(errors.hasErrors()) {
			String errorCodes="";
			List<ObjectError> allErrors = errors.getAllErrors();
			// ????????? ??????????????? ????????? ?????? ???????????? ?????????
			// ??? ???????????? ????????? ObjectError??? ????????????.
			for(ObjectError error : allErrors) {
				String[] allErrorCodes = error.getCodes();
				// ?????? ?????????  register.done??????????????? .??????????????????????????? ?????? ???????????? String?????? ?????????????????? ????????? ???. 
				// ????????? ?????? ?????? ????????? ????????? ?????????
				String firstErrorCode = allErrorCodes[0];
				// ????????? ??????(????????? ?????? register)??? ?????? ???????????? ????????????.
				errorCodes += (firstErrorCode+",");
				// ????????????????????? ?????? ??????????????? ????????? ????????????.
			}
			errorCodes = errorCodes.substring(0, errorCodes.length()-1);
			
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse("errorCode is ["+errorCodes+"]"));
	
		}
		
		
		try {			
			long newMemberId = registerService.regist(regReq);
			
			return ResponseEntity
					.status(HttpStatus.CREATED) //???????????? ??????
					.header("Location", "/json/member/"+newMemberId) // ?????? ??????
					.build(); 
		}catch(DuplicateMemberException e) {
			// sendErroe??? 500 ?????? ??? ?????? HTML??? ???????????? ???????????? 
			// ?????? HTML ??? ?????? JSON????????? ?????? ???????????? ????????????
			// ?????????????????? ?????? ???????????? ????????? ?????? ???????????? JSON??? ??? ???????????? ???????????? ?????? ?????? ?????? ????????? ??????.
			response.sendError(HttpServletResponse.SC_CONFLICT);
			// ??????????????? ???????????? ??????????????? ????????? ????????? ?????? ???????????? HTML???????????? ?????????.
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message \":\"already used email\"}"); // body??? ???????????? ???????????? ?????? ?????? JSON????????? ?????????. ?????? ???????????? ????????????.
//					.build(); // ????????? ????????? ??????????????? body???????????? ????????? ????????? ??????????????? ??????
					
		}
		
		
	}
}
