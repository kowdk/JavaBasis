package DesignPattern;

import java.util.ArrayList;
import java.util.List;


/*
 * [结构模式]
 * 组合模式:代表了一种整体-部分的架构，而在这个架构中的所有对象都应该以同样的方式对待。
 * 1. 基本接口：需要一个适用于所有对象的基本接口，客户类使用该接口。
 * 2. 叶子类：实现了基本接口，定义了各自方法，之间不相关。
 * 3. 组合类：操作基本接口，接口元素是叶子类的元素
 * java.awt.Container中使用的add方法就是使用组合模式
 * */

interface CShape{ // 根节点
	public void draw(String fillcolor); //统一接口
}

class Triangle implements CShape{ // 叶子节点

	@Override
	public void draw(String fillcolor) {
		System.out.println("Drawing Triangle with color : " + fillcolor);
	}
}

class Circle implements CShape{ // 叶子节点

	@Override
	public void draw(String fillcolor) {
		System.out.println("Drawing Circle with color : " + fillcolor);
	}
}

class Drawing implements CShape{
	
	private List<CShape> shapes = new ArrayList<CShape>(); // 将根和叶子类统一管理
	@Override
	public void draw(String fillcolor) { // 对外的一致接口
		for(CShape sh : shapes){
			sh.draw(fillcolor);
		}
	}
	
	public void add(CShape s){
		this.shapes.add(s);
	}
	
	public void remove(CShape s){
		this.shapes.remove(s);
	}
	
	public void clear()
	{
		this.shapes.clear();
	}
}

public class CompositePattern {
	public static void main(String[] args) {
		CShape tri = new Triangle();
		CShape cir = new Circle();
		
		Drawing draw = new Drawing();
		draw.add(tri);
		draw.add(cir);
		
		draw.draw("Red");
	}
}
