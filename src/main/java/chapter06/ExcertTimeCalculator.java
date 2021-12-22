package chapter06;

// 프록시 디자인 패턴이 적용된 클래스
// 핵심 기능이 아닌 부가 기능을 수정을 하고자 하고
// 그 변경의 여파를 최소화 시킬 수 있는 디자인 패턴

// 실제 팩토리얼 계산은 다른 객체(delegate)에 위임
// 따라서 팩토리얼 계산 외 다른 주가적인 기능을 쉽게 덧붙일 수 있는 것
// 프록시 디자인 패턴이란 핵심 기능은 다른 객체에 위임하고
// 부가적인 기능을 제고하는 클래스(코드)를 의미
// 핵심 기능을 실행하는 객체를 대상 객체 라고 부름

// ExcertTimeCalculator 가 프록시 객체
// ImpCalculator1,ImpCalculator2가 프록시 객체의 대상

// 엄밀히 얘기해서 우리가 만든 ExcertTimeCalculator는 프록시 디자인 패턴을 적용했다기 보다는
// 데코레이터 디자인 패턴에 가까움
// 프록시 디자인 패턴은 접근을 제어하는 것에 중점을 둠
// 데코레이터 디자인 패턴은 기능 추가, 확장에 중점을 둠

// 프록시 디자인 패턴은 핵심 기능을 구현하지 않지만 여러 객체에 공통으로 적용할 수 있는 기능을 구현
// 다시 정리를 하면 ImpCalculator1, ImpCalculator2는 팩토리얼을 계산한다 하는 핵심 기능 구현에 집중
// ExcertTimeCalculator는 실행 시간 측정이라는 공통 기능 구현에 집중
// 공통기능 구현, 핵심 기능 구현을 분리하는 것이 AOP의 핵심
public class ExcertTimeCalculator implements Calculator{

	private Calculator delegate;
	public ExcertTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		
		long result = delegate.factorial(num);
		
		long end = System.currentTimeMillis();
		long duration = (end-start);
		
		System.out.println("실행시간 = "+duration);
		return result;
	}

}
