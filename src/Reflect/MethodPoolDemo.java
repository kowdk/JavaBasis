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
		//根据类名获得一个class对象
		Class<?> clazz = Class.forName(name);
		//创建一个object的实例
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
			// config.getProperty(name)获取的是类名，而createObject返回一个类对象
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
				//根据a获得JFrame的对象
				Object obj = this.getObject(props[0]);
				//构造setTitle方法
				String mtdName = "set" + props[1].substring(0, 1).toUpperCase()
						+ props[1].substring(1);
				//获得JFrame对应的class对象
				Class<?> clz = obj.getClass();
				//class对象获取method
				Method mtd = clz.getMethod(mtdName, String.class);
				//利用对象执行method，config.getProperty(name)表示等于号后面的值
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
