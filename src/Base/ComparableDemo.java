package Base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee implements Comparable<Employee> {
	private int id;
	private String name;
	private int age;
	private long salary;

	public Employee(int id, String name, int age, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id=" + this.id + ",name=" + this.name + ",age=" + this.age
				+ ",salary=" + this.salary + "]\n";
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return (this.id - o.id);
	}

	public static Comparator<Employee> ageComparator = new Comparator<Employee>() {

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return o1.age - o2.age;
		}
	};

	public static Comparator<Employee> salaryComparator = new Comparator<Employee>() {

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return (int) (o1.salary - o2.salary);
		}
	};
	
	public static Comparator<Employee> nameComparator = new Comparator<Employee>(){

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return o1.name.compareTo(o2.name);
		}
	};
}

public class ComparableDemo {

	public static void objectSorting() {
		int[] iArr = new int[] { 1, 5, 2, 3, 4 };
		Arrays.sort(iArr);
		System.out.println(Arrays.toString(iArr));

		String[] sArr = new String[] { "ddd", "bbb", "ccc", "aaa" };
		Arrays.sort(sArr);
		System.out.println(Arrays.toString(sArr));

		List<String> list = new ArrayList<String>();
		list = Arrays.asList(sArr);
		Collections.shuffle(list);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}

	public static void classSorting() {
		Employee[] empArr = new Employee[4];
		empArr[0] = new Employee(10, "Mikey", 25, 10000);
		empArr[1] = new Employee(20, "Arun", 29, 20000);
		empArr[2] = new Employee(5, "Lisa", 35, 5000);
		empArr[3] = new Employee(1, "Pankaj", 32, 50000);
		Arrays.sort(empArr, Employee.nameComparator);
		System.out.println(Arrays.toString(empArr));
	}

	public static void main(String[] args) {
		// objectSorting();
		classSorting();
	}
}
