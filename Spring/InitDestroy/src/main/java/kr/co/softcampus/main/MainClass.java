package kr.co.softcampus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampu.config.BeanConfigClass;
import kr.co.softcampus.beans.TestBean1;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("kr/co/softcampu/config/beans.xml");
		TestBean1 xml1 = ctx1.getBean("xml1",TestBean1.class);
		System.out.printf("xml1¿« ¡÷º“: %s\n",xml1);
		ctx1.close();
		
		System.out.println("============================================");
		AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(BeanConfigClass.class);
		TestBean1 java1 = ctx2.getBean("java1",TestBean1.class);
		ctx2.close();

	}

}
