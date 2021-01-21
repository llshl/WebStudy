package kr.co.softcampus.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;
import kr.co.softcampus.beans.TestBean3;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		
		TestBean1 obj1 = ctx.getBean("obj1",TestBean1.class);
		System.out.printf("obj1.data1 : %s\n",obj1.getData1());
		System.out.printf("obj2.data2 : %s\n",obj1.getData2());
		System.out.println("==========================================");
		
		//이건 자동주입
		TestBean1 obj2 = ctx.getBean("obj2", TestBean1.class);
		System.out.printf("obj1.data1 : %s\n",obj2.getData1());
		System.out.printf("obj2.data2 : %s\n",obj2.getData2());
		System.out.println("==========================================");
		
		//TestBean2
		TestBean2 obj3 = ctx.getBean("obj3", TestBean2.class);
		System.out.printf("obj3.data1 : %s\n",obj3.getData1());
		System.out.printf("obj3.data2 : %s\n",obj3.getData2());
		System.out.println("==========================================");
		
		//TestBean3
		TestBean3 obj4 = ctx.getBean("obj4", TestBean3.class);
		System.out.printf("obj4.data1 : %s\n",obj4.getData1());
		System.out.printf("obj4.data2 : %s\n",obj4.getData2());
		System.out.println("==========================================");
		
		ctx.close();
	}

}
