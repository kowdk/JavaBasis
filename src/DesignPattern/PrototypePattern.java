package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 * [创建模式]
 * 原型设计模式是为了让两个不相关的接口可以共同工作。
 * 场景：当生成对象费时、需要很多资源以及当前已经有相同的对象时，原型模式通过克隆来生成对象，避免了数据的重新加载。
 * 在原型设计模式中，使用java的克隆机制来生成对象而非new。
 * */

class Employee implements Cloneable{
	private List<String> empList;
	
	public Employee(){
		empList = new ArrayList<String>();
	}
	
	public Employee(List<String> list){
		this.empList = list;
	}
	
	public void loadData(){
		empList.add("aaa");
		empList.add("bbb");
		empList.add("ccc");
	}
	
	public List<String> getEmpList(){
		return empList;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		List<String> tmp = new ArrayList<String>();
		for(String s : this.empList){
			tmp.add(s);
		}
		return new Employee(tmp);
	}
}

public class PrototypePattern {
	public static void main(String[] args) throws CloneNotSupportedException
	{
		Employee emps = new Employee();
		emps.loadData();
		
		Employee empsNew = (Employee) emps.clone();
		List<String> list = empsNew.getEmpList();
		list.remove("aaa");
		
		System.out.println(emps.getEmpList());
		System.out.println(list);
	}
}
