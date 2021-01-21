package kr.co.softcampus.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;

public class Mainclass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		
		TestBean1 t1 = ctx.getBean("t1",TestBean1.class);
		System.out.printf("t1狼 林家: %s\n",t1);
		System.out.println("=============================");
		
		TestBean2 t2 = ctx.getBean("t2",TestBean2.class);
		System.out.printf("t2狼 林家: %s\n",t1);
		System.out.println("=============================");
		ctx.close();

	}

}
