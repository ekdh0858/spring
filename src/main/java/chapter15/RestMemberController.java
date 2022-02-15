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
//					.status(HttpStatus.NOT_FOUND) // 이렇게 까지만 하면 데이터가 없는 응답이 간다.
//					.body(new ErrorResponse("no member")); // 메세지 데이터까지 응답에 추가한다.
		}
		return ResponseEntity.status(HttpStatus.OK).body(member); // 스테이터스 코드와 member 정보까지 같이 보낸다.
		// 객체를 json화코드를 따로 작성할 필요없다.알아서 자동으로 json화 해줌
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlenotFound(){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND) // 이렇게 까지만 하면 데이터가 없는 응답이 간다.
				.body(new ErrorResponse("no member")); // 메세지 데이터까지 응답에 추가한다.
	}
	
	@PostMapping("/members")
	public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response, Errors errors) throws IOException {
		if(errors.hasErrors()) {
			String errorCodes="";
			List<ObjectError> allErrors = errors.getAllErrors();
			// 커맨드 객체검증에 실패한 모든 사유들을 가져옴
			// 그 사유들의 정보를 ObjectError로 관리한다.
			for(ObjectError error : allErrors) {
				String[] allErrorCodes = error.getCodes();
				// 에러 코드가  register.done이런식으로 .으로구분되어지는데 결국 여러개의 String으로 이루어진걸로 인식을 함. 
				// 커맨드 객체 검증 실패한 코드를 꺼내서
				String firstErrorCode = allErrorCodes[0];
				// 첫번째 코드(위에서 보면 register)만 따로 분리해서 저장한다.
				errorCodes += (firstErrorCode+",");
				// 에러코드스라는 문자 코드에다가 하나씩 추가한다.
			}
			errorCodes = errorCodes.substring(0, errorCodes.length()-1);
			
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse("errorCode is ["+errorCodes+"]"));
	
		}
		
		
		try {			
			long newMemberId = registerService.regist(regReq);
			
			return ResponseEntity
					.status(HttpStatus.CREATED) //상태코드 지정
					.header("Location", "/json/member/"+newMemberId) // 헤더 지정
					.build(); 
		}catch(DuplicateMemberException e) {
			// sendErroe나 500 에러 등 기본 HTML이 전달되는 상황에서 
			// 기본 HTML 이 아닌 JSON형식의 응답 데이터를 전달하면
			// 클라이언트는 응답 데이터가 없거나 응답 데이터가 JSON일 때 두가지만 처리하면 되니 훨씬 편한 작업이 된다.
			response.sendError(HttpServletResponse.SC_CONFLICT);
			// 에러코드를 보낼때는 에로코드에 더해서 서버가 가진 기본적인 HTML페이지를 보낸다.
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"message \":\"already used email\"}"); // body를 사용해서 메세지를 지정 하면 JSON형식이 아니다. 그냥 먼자열을 전달한다.
//					.build(); // 컴파일 오류가 발생하는데 body메세지를 보내게 해주면 필요없어서 그럼
					
		}
		
		
	}
}
