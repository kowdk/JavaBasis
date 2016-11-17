package DesignPattern;

import java.util.HashMap;
import java.util.Map;

import DesignPattern.ShapeFactory.ShapeType;

/*
 * [结构模式]
 * 享元模式：当一个类需要创建大量对象或者创建对象时间很长时，为了减少内存损耗，可以分享对象，考虑缓存，在一个复杂的系统中大量的节省内存空间。
 * 该模式下的类对象有内部属性和外部属性，内部属性由类自己完成，外部属性由客户类提供。
 * 数据库连接池、线程池都是享元模式的应用，StringPool是典型的享元模式，包装类的valueOf也使用享元模式，使用私有的静态内部类提供cache数组来完成拆箱封箱。
 * 享元模式当缓存的对象数量特别大时，就会很耗性能，因此要在时间和内存之间做一个均衡
 * 当有大量的内部属性时，享元模式的工厂类会很冗杂。
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

/*享元工厂类*/
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

/* 客户类 */
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
