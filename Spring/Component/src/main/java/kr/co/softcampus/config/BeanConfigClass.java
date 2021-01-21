package kr.co.softcampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;


@Configuration
//지정된 패키지의 Bean 클래스들의 어노테이션을 분석하여 Bean을 등혹하라고 지정한다
@ComponentScan(basePackages = "kr.co.softcampus.beans2")
public class BeanConfigClass {

	@Bean
	public TestBean1 java1() {
		return new TestBean1();
	}
	
	@Bean
	public TestBean2 java2() {
		return new TestBean2();
	}
	
	@Bean
	public TestBean2 java3() {
		return new TestBean2();
	}
}
