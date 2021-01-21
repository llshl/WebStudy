package kr.co.softcampus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;
import kr.co.softcampus.beans2.TestBean3;
import kr.co.softcampus.config.BeanConfigClass;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx1 = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		TestBean1 xml1 = ctx1.getBean(TestBean1.class);
		System.out.printf("xml1의 주소: %s\n",xml1);
		TestBean2 xml2 = ctx1.getBean("xml2",TestBean2.class);
		System.out.printf("xml2의 주소: %s\n",xml2);
		TestBean2 xml3 = ctx1.getBean("xml3",TestBean2.class);
		System.out.printf("xml3의 주소: %s\n",xml3);
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
		TestBean3 xml4 = ctx1.getBean("xml4",TestBean3.class);
		System.out.printf("xml4의 주소: %s\n",xml4);
		ctx1.close();
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
		
		AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(BeanConfigClass.class);
		TestBean1 java1 = ctx2.getBean(TestBean1.class);	//빈클래스의 타입이 유일하다면 이름 안붙혀서 타입만으로 호출해도됨(TestBean1은 configuration에서 유일)
		System.out.printf("java1의 주소: %s\n",java1);
		TestBean2 java2 = ctx2.getBean("java2",TestBean2.class);
		System.out.printf("java2의 주소: %s\n",java2);
		TestBean2 java3 = ctx2.getBean("java3",TestBean2.class);
		System.out.printf("java3의 주소: %s\n",java3);
		ctx2.close();
		

	}

}
