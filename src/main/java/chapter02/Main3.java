package chapter02;

import java.util.Scanner;

public class Main3 {

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
				processNewCommand(command.split(""));
				continue;
			} else if(command.startsWith("change")) {
				processChangeCommand(command.split(""));
				continue;
			}
			printHelp();
		}
		
		scanf.close();
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
