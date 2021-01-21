package kr.co.softcampus.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TestBeanPostProcessor implements BeanPostProcessor{
	//init메서드 호출 전에 호출된다 
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("before");
		switch(beanName){
		case "t1":
			System.out.println("이건 t1객체 생성");
			break;
		case "t2":
			System.out.println("이건 t2객체 생성");
		}
		
		return bean;	//이 bean은 매개변수로 t1이 들어오는데 그 bean이다
	}
	
	//init메서드 호출 후에 호출된다
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("after");
		switch(beanName){
		case "t1":
			System.out.println("이건 t1객체 생성");
			break;
		case "t2":
			System.out.println("이건 t2객체 생성");
		}
		return bean;
	}
	
	
}
