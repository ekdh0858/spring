package chapter06;

public class ImpCalculator1 implements Calculator{

	@Override
	public long factorial(long num) {
		
		
		long result = 1;
		
		for(long i=1; i<=num; i++) {
			result = result * i;
		}
		
		
		return result;
	}

}
