package Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestPerson {

	public String name;
	public int age;

	public TestPerson(){};
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		TestPerson p = new TestPerson();
		p.setName("xutao");
		p.setAge(24);

		Field[] field = p.getClass().getDeclaredFields(); // ��ȡʵ������������ԣ�����Field����
		try {
			for (int j = 0; j < field.length; j++) { // ������������
				String name = field[j].getName(); // ��ȡ���Ե�����
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // �����Ե����ַ���д�����㹹��get��set����
				String type = field[j].getGenericType().toString(); // ��ȡ���Ե�����
				if (type.equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class "�����������
					Method m = p.getClass().getMethod("get" + name);
					String value = (String) m.invoke(p); // ����getter������ȡ����ֵ
					System.out.println(name + " : " + value);
					/*if (value == null) {
						m = p.getClass().getMethod("set" + name, String.class);
						m.invoke(p, "");
					}*/
				}
				if (type.equals("class java.lang.Integer")) {
					Method m = p.getClass().getMethod("get" + name);
					Integer value = (Integer) m.invoke(p);
					/*if (value == null) {
						m = p.getClass().getMethod("set" + name, Integer.class);
						m.invoke(p, 0);
					}*/
					System.out.println(name + " : " + value);
				}
				
				
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
