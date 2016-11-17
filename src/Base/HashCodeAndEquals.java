package Base;

import java.util.HashSet;

class Person {
	private int age;
	private String name;

	public Person() {
	};

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + age; result = prime * result + ((name == null)
	 * ? 0 : name.hashCode()); return result; }
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}

public class HashCodeAndEquals {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setAge(10);
		p1.setName("xutao");
		Person p2 = new Person();
		p2.setAge(11);
		p2.setName("xutao");

		HashSet<Person> set = new HashSet<Person>();
		set.add(p1);
		set.add(p2);
		System.out.println(p2.hashCode());
		System.out.println(set.toString());
		Object p3 = new Person(11, "xutao");
		System.out.println(p3.hashCode());
		set.remove(p3);
		System.out.println(set.toString());
	}
}
