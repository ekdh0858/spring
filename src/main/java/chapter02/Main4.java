package chapter02;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main4 {
	// 객체를 생성하고 의존 객체를 주입하고 
	// 의존 객체가 주입된 객체를 관리하는건 스프링 컨테이너가 하므로
	// 설정 클래스를 사용해서 스프링 컨테이너를 만들어야함
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		// 스프링 컨테이너가 만드러질 때 객체를 생성하고 의존 객체를 주입함
		// Assembler와 스프링 컨테이너와 차이점은 Assembler는 우리가 직접 생성자를 만들었지만
		// 스프링 컨테이너는 설정 파일(AppCtx 클래스)를 이용해 컨테이너가 만들어질 때 자동으로 객체를 생성하고 의존 객체를 주입해줌
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Scanner scanf = new Scanner(System.in);
		
		while(true) {
			System.out.println("명령어를 입력하세요.");
			
			String command = scanf.nextLine();
			// equalsIgnoreCase() -> 해당 객체가 갖고 있는 문자열의 대소문자를 무시하고 
			//						 인자로 전달 받은 문자열과 같은지 비교
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if(command.startsWith("list")) {
				processListCommand();
				continue;
			} else if(command.startsWith("info")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if(command.startsWith("version")) {
				processversionCommand();
				continue;
			}
			printHelp();
		}
		
		scanf.close();
		
	}
	
	private static void processversionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		
		versionPrinter.print();
	}

	// 회원 가입 되어있는 특정한 사용자의 정보를 출력
	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	//회원가입 되어있는 사용자들의 목록을 출력
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processChangeCommand(String[] arg) throws MemberNotFoundException{
		if(arg.length != 4) {
			printHelp();
			return;
		}
		
	try {
		// 스프링 컨테이너(ctx) 안에 들어있는 이름이 changepwdSvc인 ChangePasswordService타입의 객체를 가져와
		// cps변수에 저장
		ChangePasswordService cps = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		cps.ChangePasswordService(arg[1], arg[2], arg[3]);
		
		System.out.println("비밀번호가 변경되었습니다.\n");
		
		}catch(MemberNotFoundException e) {
			System.out.println("일치하는 회원 정보가 없습니다.\n");
		}catch(WrongIdPasswordException e) {
			System.out.println("현재 비밀번호가 다릅니다.\n");
		}
	}

	// 회원 가입 메서드 
	private static void processNewCommand(String[] arg) throws WrongIdPasswordException{
		if(arg.length != 5) {
			printHelp();
			return;
		}
		
		RegisterRequest rr = new RegisterRequest();
		rr.setEmail(arg[1]);
		rr.setName(arg[2]);
		rr.setPassword(arg[3]);
		rr.setConfirmPassword(arg[4]);
		
		if(!rr.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return ;
		}
		
		try {
			// 스프링 컨테이너(ctx) 안에 들어있는 이름이 memberRegSvc인 MemberRegisterService타입의 객체를 가져와
			// mrs 변수에 저장
			MemberRegisterService mrs = ctx.getBean("memberRegSvc",MemberRegisterService.class);
			
			long id = mrs.regist(rr);
			
			System.out.println("아이디가"+id+"인 사용자가 등록되었습니다.");
		}catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		
		System.out.println("잘못된 명령어 입니다. 아래 명령어 사용방법을 확인하세요.");
		System.out.println("<명령어 사용 방법>");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change  이메일 현재비번 변경비번");
		System.out.println("list");
		System.out.println("info 이메일");
		
		System.out.println();
	}


}
