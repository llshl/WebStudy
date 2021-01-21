package kr.co.softcampus.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class TestBean1 {
	private int data1;
	private DataBean1 data2;

	//자동주입을 변수에 설정
	//자동으로 setter메서드가 추가되어 setter메서드를 통해 주입받는다
	@Autowired
	private DataBean1 data3;
	
	//DataBean2형 객체가 두개(data4,data5)있으므로 이름 설정 필요
	@Autowired
	@Qualifier("obj4")
	private DataBean2 data4;
	
	@Autowired
	@Qualifier("obj5")
	private DataBean2 data5;
	
	@Autowired(required = false)
	@Qualifier("obj6")
	private DataBean2 data6;
	
	
	public int getData1() {
		return data1;
	}

	//필수주입 프로퍼티, (스프링5.1부터 혹은 자바파일로 bean을 등록햇을경우 무시된다)
	@Required
	public void setData1(int data1) {
		this.data1 = data1;
	}


	public DataBean1 getData2() {
		return data2;
	}

	//자동주입(타입) , DataBean1타입으로 설정된 빈객체를 찾아서 주입됨
	@Autowired
	public void setData2(DataBean1 data2) {
		this.data2 = data2;
	}
	
	public DataBean1 getData3() {
		return data3;
	}

	public DataBean2 getData4() {
		return data4;
	}

	public DataBean2 getData5() {
		return data5;
	}
	
	public DataBean2 getData6() {
		return data6;
	}
	
	//data3의 setter는 안만듬. 자동으로 만들도록햇기때문에
	
	
}
