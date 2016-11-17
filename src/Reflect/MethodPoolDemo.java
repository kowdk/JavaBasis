package Reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

public class MethodPoolDemo {
	private HashMap<String, Object> objectPool = new HashMap<String, Object>();
	private Properties config = new Properties();

	public void init(String fileName) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(fileName);
			config.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Object createObject(String name) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		//�����������һ��class����
		Class<?> clazz = Class.forName(name);
		//����һ��object��ʵ��
		return clazz.newInstance();
	}
	
	public Object getObject1(String name)
	{
		return objectPool.get(name);
	}

	public void initPool() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		for (String name : config.stringPropertyNames()) {
			// System.out.println(name + " " + config.getProperty(name));
			// config.getProperty(name)��ȡ������������createObject����һ�������
			if (!name.contains("%")) {
				objectPool.put(name,
						this.createObject(config.getProperty(name)));
			}
		}
	}

	public void initProperties() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, Exception {
		// eg: a%Title=Test title
		for (String name : config.stringPropertyNames()) {
			if (name.contains("%")) {
				String[] props = name.split("%");
				//����a���JFrame�Ķ���
				Object obj = this.getObject(props[0]);
				//����setTitle����
				String mtdName = "set" + props[1].substring(0, 1).toUpperCase()
						+ props[1].substring(1);
				//���JFrame��Ӧ��class����
				Class<?> clz = obj.getClass();
				//class�����ȡmethod
				Method mtd = clz.getMethod(mtdName, String.class);
				//���ö���ִ��method��config.getProperty(name)��ʾ���ںź����ֵ
				mtd.invoke(obj, config.getProperty(name));
			}
		}
	}

	public Object getObject(String name) {
		return objectPool.get(name);
	}

	private final static String resPath = new File("").getAbsolutePath()
			+ File.separatorChar + "res\\";

	public static void main(String[] args) throws Exception {
		MethodPoolDemo op = new MethodPoolDemo();

		String fileName = resPath + "prop.txt";
		// System.out.println(fileName);
		op.init(fileName);
		op.initPool();
		op.initProperties();
		System.out.println(op.getObject1("a"));
	}
}
