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

		Field[] field = p.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = p.getClass().getMethod("get" + name);
					String value = (String) m.invoke(p); // 调用getter方法获取属性值
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
