package chapter06;

public class ImpCalculator1 implements Calculator{

	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		
		long result = 1;
		
		for(long i=1; i<=num; i++) {
			result = result * i;
		}
		
		long end = System.currentTimeMillis();
		
		//factorial의 수행 시간을 게산하고 초단위로 변환
		long duration = (end - start) / 1000;
		
		System.out.println("실행시간 = "+duration);
		
		return result;
	}

}
