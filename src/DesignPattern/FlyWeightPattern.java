package DesignPattern;

import java.util.HashMap;
import java.util.Map;

import DesignPattern.ShapeFactory.ShapeType;

/*
 * [�ṹģʽ]
 * ��Ԫģʽ����һ������Ҫ��������������ߴ�������ʱ��ܳ�ʱ��Ϊ�˼����ڴ���ģ����Է�����󣬿��ǻ��棬��һ�����ӵ�ϵͳ�д����Ľ�ʡ�ڴ�ռ䡣
 * ��ģʽ�µ���������ڲ����Ժ��ⲿ���ԣ��ڲ����������Լ���ɣ��ⲿ�����ɿͻ����ṩ��
 * ���ݿ����ӳء��̳߳ض�����Ԫģʽ��Ӧ�ã�StringPool�ǵ��͵���Ԫģʽ����װ���valueOfҲʹ����Ԫģʽ��ʹ��˽�еľ�̬�ڲ����ṩcache��������ɲ�����䡣
 * ��Ԫģʽ������Ķ��������ر��ʱ���ͻ�ܺ����ܣ����Ҫ��ʱ����ڴ�֮����һ������
 * ���д������ڲ�����ʱ����Ԫģʽ�Ĺ����������ӡ�
 * 
 * */


interface Shape {
	public void draw(int x, int y, String color);
}

class Line implements Shape {
	public Line() {
		System.out.println("Createing Line Object");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(int x, int y, String color) {
		// TODO Auto-generated method stub
		System.out.println("Line: x=" + x + " y = " + y + " color = " + color);
	}
}

class Oval implements Shape {
	private boolean fill;

	public Oval(boolean fill) {
		this.fill = fill;
		System.out.println("Createing Oval Object");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(int x, int y, String color) {
		// TODO Auto-generated method stub
		if (fill)
			System.out.println("Oval: x=" + x + " y = " + y + " color = "
					+ color + " [filled]");
		else
			System.out.println("Oval: x=" + x + " y = " + y + " color = "
					+ color + " [not filled]");
	}
}

/*��Ԫ������*/
class ShapeFactory {
	public static enum ShapeType {
		OVAL_FILL, OVAL_NOTFILL, LINE
	}

	private static final Map<ShapeType, Shape> shapes = new HashMap<ShapeType, Shape>();

	public static Shape getShape(ShapeType type) {
		Shape shapeImpl = shapes.get(type);
		if (shapeImpl == null) {
			if (type == ShapeType.OVAL_FILL)
				shapeImpl = new Oval(true);
			else if (type == ShapeType.OVAL_NOTFILL)
				shapeImpl = new Oval(false);
			else if (type == ShapeType.LINE)
				shapeImpl = new Line();
			shapes.put(type, shapeImpl);
		}
		return shapeImpl;
	}
}

/* �ͻ��� */
public class FlyWeightPattern {
	private static final ShapeType shapes[] = { ShapeType.LINE,
			ShapeType.OVAL_FILL, ShapeType.OVAL_NOTFILL };
	private static final String colors[] = { "RED", "BLUE", "GREEN"};

	private static final int THRESHOLD = 100;
	private ShapeType getRandomType() {
		return shapes[(int) (Math.random() * shapes.length)];
	}

	private int getRandomX(){
		return (int) (Math.random() * THRESHOLD);
	}
	
	private int getRandomY(){
		return (int) (Math.random() * THRESHOLD);
	}
	
	private String getRandomColor(){
		return colors[(int) (Math.random() * colors.length)];
	}
	
	public FlyWeightPattern() {
		for (int i = 0; i < 10; i++) {
			Shape shape = ShapeFactory.getShape(getRandomType());
			shape.draw(getRandomX(), getRandomY(), getRandomColor());
		}
	}

	public static void main(String[] args) {
		new FlyWeightPattern();
	}
}
