package kr.co.softcampus.main;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.co.softcampus.beans.DataBean;
import kr.co.softcampus.beans.TestBean;

public class MainClass {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("kr/co/softcampus/config/beans.xml");
		
		TestBean t1 = ctx.getBean("t1",TestBean.class);
		List<String> list1 = t1.getList1();
		for(String str : list1) {
			System.out.printf("list1: %s\n",str);
		}
		
		List<Integer> list2 = t1.getList2();
		for(int value : list2) {
			System.out.printf("list2: %d\n",value);
		}
		
		List<DataBean> list3 = t1.getList3();
		for(DataBean obj : list3) {
			System.out.printf("list3: %s\n",obj);
		}
		ctx.close();

	}

}
