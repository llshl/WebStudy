package kr.co.softcampus.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean bean1 = ctx.getBean("xml1",TestBean.class);
		int a1 = bean1.method1();
		System.out.println("a1Àº : "+a1);
		ctx.close();

	}

}
