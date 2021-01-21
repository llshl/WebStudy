package kr.co.softcampus.main;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import kr.co.softcampus.beans.TestBean;

public class MainClass {

	public static void main(String[] args) {
		test1();

	}
	//ApplicationContext사용 - 패키지 내부
	public static void test1() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		ctx.close();
	}
}
