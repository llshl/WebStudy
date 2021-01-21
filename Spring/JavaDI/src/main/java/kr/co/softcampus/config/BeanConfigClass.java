package kr.co.softcampus.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.co.softcampus.beans.DataBean;
import kr.co.softcampus.beans.DataBean2;
import kr.co.softcampus.beans.TestBean1;
import kr.co.softcampus.beans.TestBean2;

@Configuration
public class BeanConfigClass {

	@Bean
	public TestBean1 java1() {
		return new TestBean1(200,"문자열2",new DataBean());
	}
	
	@Bean
	public TestBean1 java2() {
		TestBean1 t1 = new TestBean1();
		t1.setData1(400);
		t1.setData2("문자열4");
		t1.setData3(new DataBean());
		return t1;
	}
	
	@Bean
	public DataBean2 data1() {
		return new DataBean2();
	}
	
	@Bean
	public DataBean2 data2() {
		return new DataBean2();
	}
	
	@Bean(autowire = Autowire.BY_NAME)
	public TestBean2 java3() {
		return new TestBean2();
	}
}
