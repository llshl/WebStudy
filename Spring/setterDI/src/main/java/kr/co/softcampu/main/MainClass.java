package kr.co.softcampu.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.Beans.TestBean;

public class MainClass {

	public static void main(String[] args) {
		
		TestBean obj1 = new TestBean();
		obj1.setData1(100);
		System.out.printf("obj1.data1 : %d\n",obj1.getData1());
		
		ClassPathXmlApplicationContext ctx =  new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean t1 = ctx.getBean("t1",TestBean.class);
		System.out.printf("t1.data1 : %d\n",t1.getData1());
		ctx.close();

	}

}
