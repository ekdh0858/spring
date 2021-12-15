package chapter02;

import java.util.Scanner;

public class Main3 {
	private static Assembler assembler = new Assembler();

	public static void main(String[] args) {
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
			}
			printHelp();
		}
		
		scanf.close();
	}

	private static void processChangeCommand(String[] arg) throws MemberNotFoundException{
		if(arg.length != 4) {
			printHelp();
			return;
		}
		
	try {
		ChangePasswordService cps = assembler.getChangePasswordService();
		
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
			MemberRegisterService mrs = assembler.getMemberRegisterService();
			
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
		
		System.out.println();
	}

}
