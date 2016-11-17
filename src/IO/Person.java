package IO;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 8952103100200724187L;

	private String name;
	private int age;
	private boolean sex;

	public Person() {
	}

	public Person(String name, int age, boolean sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
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

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String toString() {
		return "name = " + name + "; age = " + age + "; sex = " + sex + ";";
	}
}
