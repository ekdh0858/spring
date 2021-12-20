package chapter05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Context.class);
		
		Client2 host1 = ctx.getBean("client2", Client2.class);
		Client2 host2 = ctx.getBean("client2", Client2.class);

		System.out.println("host1 == host2 = "+ (host1==host2));
		
		ctx.close();
	}

}
