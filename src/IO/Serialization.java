package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
	public Serialization() {
	}

	private void SerializePerson(Object o, String filePath) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(
					new File(filePath)));
			oos.writeObject(o);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Object DeSerializePerson(String filePath) {
		Object obj = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(filePath)));
			obj = ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public static void main(String[] args) {
		Serialization ser = new Serialization();
		Person person = new Person("xutao", 24, true);
		String filePath = "E:/Person.txt";
		ser.SerializePerson(person, filePath);
		Person p = (Person) ser.DeSerializePerson(filePath);
		System.out.println(p.toString());
	}
}
