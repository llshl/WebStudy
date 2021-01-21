package kr.co.softcampus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"kr.co.softcampus.beans" , "kr.co.softcampus.advisor"})
@EnableAspectJAutoProxy
public class BeanConfigClass {

}
