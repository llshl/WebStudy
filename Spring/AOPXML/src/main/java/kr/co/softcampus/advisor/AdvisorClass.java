package kr.co.softcampus.advisor;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdvisorClass {

	public void beforeMethod() {
		System.out.println("beforeMethod 호출");
	}
	
	public void afterMethod() {
		System.out.println("afterMethod 호출");
	}
	
	public int aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("aroundMethod 호출1");
		//원래의 메서드 호출
		int a1 = (Integer) pjp.proceed();
		System.out.println("aroundMethod 호출2");
		return a1;
	}
	
	public void afterReturningMethod() {
		System.out.println("afterReturningMethod 호출");
	}
}
