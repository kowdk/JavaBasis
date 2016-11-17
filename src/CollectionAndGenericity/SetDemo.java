package CollectionAndGenericity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	
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


	public String toString() {
		return "Person[name=" + name + ",age=" + age + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.length();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass()!=getClass())
			return false;
		if(obj instanceof Person)
		{
			Person p = (Person) obj;
			if(p.name.equals(this.name) && p.age==this.age)
				return true;
		}
		return false;
	}
}

public class SetDemo {
	
	/*set�������ظ�*/
	private static void testSet()
	{
		Set<String> set = new HashSet<String>();
		Set<Integer> is = new HashSet<Integer>();
		set.add(new String("xutao"));
		set.add("xutao");
		is.add(1);
		is.add(new Integer(1));
		System.out.println(set);
		System.out.println(is);
	}
	
	private static void testMap(){
		Map<String, String> hm = new HashMap<String,String>();
		Map<String, String> ihm = new IdentityHashMap<String, String>();		
		
		hm.put("xutao", "abc");
		hm.put(new String("xutao"), "def");
		System.out.println(hm);
		
		ihm.put("xutao", "abc");
		ihm.put("xutao", "def");
		System.out.println(ihm);
		
		ihm.clear();
		ihm.put(new String("xutao"), "abc");
		ihm.put(new String("xutao"), "def");
		System.out.println(ihm);
		
	}
	
	/*��д��equals��hashcode������Person��*/
	private static void equalsAndHashcodeSetTest()
	{
		Set<Person> set = new HashSet<Person>();
		//value=xutao hashcode=5
		Person p1 = new Person("xutao", 18);
		//value=yin hashcode=3
		Person p2 = new Person("yin", 20);
		set.add(p1);
		set.add(p2);
		System.out.println(set.toString());
		//set֮��p1��hashcode����3
		p1.setName("yin");
		//remove������hashcode��ֵequal�Ķ���ɾ�� value=yin hashcode=3 age=18,δ�ҵ�ɾ��ʧ��
		set.remove(p1);
		System.out.println(set.toString());
		//��� value=yin hashcode=3 age=18��δ�ظ�������ɹ�
		set.add(p1);
		System.out.println(set.toString());
		//value=yin hashcode=3 age=20�����ظ�������ʧ��
		set.add(p2);
		System.out.println(set.toString());
	}
	public static void main(String[] args)
	{
		//testMap();
		//testSet();
		equalsAndHashcodeSetTest();
	}
}
