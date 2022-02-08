package chapter15;

import java.util.Collections;
import java.util.List;

public class Question {
	// 항목 타이틀(제목)
	private String title;
	// 선택지
	private List<String> options;
	
	// 객관식 항목을 만들때 사용할 생성자
	public Question(String title, List<String> options) {
		this.title=title;
		this.options=options;
	}
	
	// 주관식 항목을 만들 때 사용할 생성자
	public Question(String title) {
		this(title,Collections.<String>emptyList());
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
}
