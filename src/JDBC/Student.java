package JDBC;

class Student {
	private int Id;
	private String Name;
	private String Sex;
	private int Age;

	Student(String Name, int Age, String Sex) {
		this.Id = 0; // default
		this.Name = Name;
		this.Sex = Sex;
		this.Age = Age;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String Sex) {
		this.Sex = Sex;
	}

	public int getAge() {
		return Age;
	}

	public void setage(int Age) {
		this.Age = Age;
	}

	public String toString() {
		return "{ID=" + Id + ",Name=" + Name + ",Sex=" + Sex + ",Age=" + Age
				+ "}";
	}
}