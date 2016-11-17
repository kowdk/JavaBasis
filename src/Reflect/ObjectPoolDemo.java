package Reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/*
 * Spring就是使用xml配置文件来配置对象，然后通过读取配置来创建对象的
 * */
public class ObjectPoolDemo {
	private	HashMap<String, Object> objectPool = new HashMap<String, Object>();
	
	public Object createObject(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> clazz = Class.forName(name);
		return clazz.newInstance();
	}
	
	public void initPool(String fileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			Properties prop = new Properties();
			prop.load(fis);
			for(String name : prop.stringPropertyNames())
			{
				objectPool.put(name, this.createObject(prop.getProperty(name)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Object getObject(String name)
	{
		return objectPool.get(name);
	}
	
	private final static String resPath = new File("").getAbsolutePath() + File.separatorChar + "res\\";
	public static void main(String[] args) throws Exception
	{
		ObjectPoolDemo op = new ObjectPoolDemo();
		
		String fileName = resPath + "prop.txt";
		System.out.println(fileName);
		op.initPool(fileName);
		System.out.println(op.getObject("a"));
	}
}
