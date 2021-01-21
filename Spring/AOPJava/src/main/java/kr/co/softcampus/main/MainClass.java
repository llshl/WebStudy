package kr.co.softcampus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.config.BeanConfigClass;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean1 xml1 = ctx1.getBean(TestBean1.class);
		xml1.method1();
		ctx1.close();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(BeanConfigClass.class);
		TestBean1 java1 = ctx2.getBean(TestBean1.class);
		java1.method1();
		ctx2.close();

	}

}
