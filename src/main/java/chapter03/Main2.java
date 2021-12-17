package chapter03;

import java.util.Scanner;

// 앞서 chapter2까지는 생성자, 세터메서드 방식의 직접 의존 주입을 배웠음
// Chapter2의 가장 마지막에 잠깐 @Autowired 애노테이션을 사용한 자동 의존 주입을 배웠음

// 스프링을 사용해서 자동 의존 주입을 할 때는 @Autowired, @Resource 애노테이션을 사용하면 됨
// @Inject 애노테이션

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) {
		ctx = new AnnotationConfigApplicationContext(AppCtx2.class);
		Scanner scanf = new Scanner(System.in);
		
		while(true) {
			System.out.println("명령어를 입력하세요.");
			
			String command = scanf.nextLine();
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

	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
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
		ChangePasswordService cps = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		cps.ChangePasswordService(arg[1], arg[2], arg[3]);
		
		System.out.println("비밀번호가 변경되었습니다.\n");
		
		}catch(MemberNotFoundException e) {
			System.out.println("일치하는 회원 정보가 없습니다.\n");
		}catch(WrongIdPasswordException e) {
			System.out.println("현재 비밀번호가 다릅니다.\n");
		}
	}

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
