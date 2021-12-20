package chapter06;

public class ImpCalculator2 implements Calculator{

	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		
		try {
			if(num==0) {
				return 1;
			}else {
				return num * factorial(num-1);
			}
		}finally {
			long end = System.currentTimeMillis();
			long duration = (end - start) / 1000;
			
			System.out.println("실행시간 = "+duration);
		}
		
	}

}
