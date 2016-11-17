package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 * [����ģʽ]
 * ԭ�����ģʽ��Ϊ������������صĽӿڿ��Թ�ͬ������
 * �����������ɶ����ʱ����Ҫ�ܶ���Դ�Լ���ǰ�Ѿ�����ͬ�Ķ���ʱ��ԭ��ģʽͨ����¡�����ɶ��󣬱��������ݵ����¼��ء�
 * ��ԭ�����ģʽ�У�ʹ��java�Ŀ�¡���������ɶ������new��
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
