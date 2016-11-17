package DesignPattern;

import java.util.HashMap;

/*
 * [�ṹģʽ]
 * ��Ԫģʽ����һ������Ҫ��������������ߴ�������ʱ��ܳ�ʱ��Ϊ�˼����ڴ���ģ����Է�����󣬿��ǻ��棬��һ�����ӵ�ϵͳ�д����Ľ�ʡ�ڴ�ռ䡣
 * ��ģʽ�µ���������ڲ����Ժ��ⲿ���ԣ��ڲ����������Լ���ɣ��ⲿ�����ɿͻ����ṩ��
 * ���ݿ����ӳء��̳߳ض�����Ԫģʽ��Ӧ�ã�StringPool�ǵ��͵���Ԫģʽ����װ���valueOfҲʹ����Ԫģʽ��ʹ��˽�еľ�̬�ڲ����ṩcache��������ɲ�����䡣
 * ��Ԫģʽ������Ķ��������ر��ʱ���ͻ�ܺ����ܣ����Ҫ��ʱ����ڴ�֮����һ������
 * ���д������ڲ�����ʱ����Ԫģʽ�Ĺ����������ӡ�
 * */

/**
 * ������ӿ�
 * @author xutao
 *
 */
interface Browser {
	public void showBrower();
}

/**
 * �����ʵ����
 * @author xutao
 *
 */
class ConcreteBrowser implements Browser {
	private String name = "";

	public ConcreteBrowser(String name) {
		this.name = name;
	}

	@Override
	public void showBrower() {
		System.out.println("Browser name : " + name);
	}
}

class FlyWeightFactory {
	private HashMap<String, Object> map = new HashMap<String, Object>(); //�����

	public FlyWeightFactory() {
	}

	public Browser getFlyWeight(Object obj) {
		Browser bws = (Browser) map.get(obj);
		if (bws == null) { //û�л����ٴ���
			bws = new ConcreteBrowser(String.valueOf(obj));
			map.put(String.valueOf(obj), bws);
		}
		return bws;
	}

	public int getBrowserPoolSize() {
		return map.size();
	}
}

public class FlyWeightPatternEasy {
	public static void main(String[] args) {
		FlyWeightFactory fac = new FlyWeightFactory();
		Browser b1 = fac.getFlyWeight("google chrome");
		Browser b2 = fac.getFlyWeight("google chrome");
		Browser b3 = fac.getFlyWeight("google chrome");
		Browser b4 = fac.getFlyWeight("360");
		
		b1.showBrower();
		b2.showBrower();
		b3.showBrower();
		b4.showBrower();
		
		System.out.println(fac.getBrowserPoolSize());//2
	}
}
