package Base;

import java.util.ArrayList;
import java.util.List;

/*
 * ��װ���ǶԻ����������͵İ�װ�������������Ͳ����򵥣�����װ���������Object�����ϵ��
 * ��װ����Զ����������ṩ�Զ�װ����Զ����书��
 * */
public class WrapperClassDemo {
	
	private static void doSth(Character c){
		
	}
	public static void main(String[] args)
	{
		int i = 1;
		char c = 'a';
		
		doSth(new Character(c));
		List<Integer> list = new ArrayList<Integer>();
		
		//�Զ�װ��
		int j = i+1;
		list.add(j);
		
		//�Զ�����
		Integer in = new Integer(5);
		j = j + in;
		in = null;
	}
}
