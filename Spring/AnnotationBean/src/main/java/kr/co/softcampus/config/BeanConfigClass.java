package kr.co.softcampus.config;

import org.springframework.context.annotation.Bean;

import kr.co.softcampus.beans.DataBean1;
import kr.co.softcampus.beans.DataBean2;
import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;

public class BeanConfigClass {

	@Bean
	public TestBean1 java1() {
		return new TestBean1();
	}
	
	@Bean
	public DataBean1 data_bean1() {
		return new DataBean1();
	}
	
	@Bean
	public DataBean2 obj4() {
		return new DataBean2();
	}
	
	@Bean
	public DataBean2 obj5() {
		return new DataBean2();
	}
	
	@Bean
	public TestBean2 java2() {
		return new TestBean2();
	}
	
	
}

