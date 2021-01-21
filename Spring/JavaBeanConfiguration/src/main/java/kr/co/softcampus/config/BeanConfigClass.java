package kr.co.softcampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;

@Configuration
public class BeanConfigClass {
	
	@Bean
	public TestBean1 java1() {
		TestBean1 t1 = new TestBean1();
		return t1;
	}
	
	@Bean(name="java600")
	public TestBean1 java500() {
		TestBean1 t1 = new TestBean1();
		return t1;
	}
	
	@Bean
	@Lazy
	public TestBean2 java2() {
		TestBean2 t2 = new TestBean2();
		return t2;
	}
}
