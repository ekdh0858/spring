package chapter12;

import java.util.List;

// 설문지 역활을 할 클래스
// RegisterRequest 클래스와 다른점
// 1. 리스트 타입의 멤버 변수가 있음
// 2. 중첩 멤버 변수를 가짐
// 3.스프링 MVC는 이 클래스와 같이 멤버 변수의 구조가 복잡할 때도
// 요청 파라미터의 값을 알맞게 커맨드 객체에 저장할 수 있는 기능을 제공함
// 요청 파라미터 이름이 "멤버변수이름[인덱스]" 형식이면 List 타입 멤버 변수의 값들로 처리함
// 요청 파라미터 이름이 "멤버 변수이름.멤버변수이름" 형식이면 중첩 멤버 변수의 값으로 처리함
public class AnsweredData {
	
	private List<String> response;
	private Respondent res;
	public List<String> getResponse() {
		return response;
	}
	public void setResponse(List<String> response) {
		this.response = response;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
}
