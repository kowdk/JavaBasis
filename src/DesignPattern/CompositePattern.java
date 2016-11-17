package DesignPattern;

import java.util.ArrayList;
import java.util.List;


/*
 * [�ṹģʽ]
 * ���ģʽ:������һ������-���ֵļܹ�����������ܹ��е����ж���Ӧ����ͬ���ķ�ʽ�Դ���
 * 1. �����ӿڣ���Ҫһ�����������ж���Ļ����ӿڣ��ͻ���ʹ�øýӿڡ�
 * 2. Ҷ���ࣺʵ���˻����ӿڣ������˸��Է�����֮�䲻��ء�
 * 3. ����ࣺ���������ӿڣ��ӿ�Ԫ����Ҷ�����Ԫ��
 * java.awt.Container��ʹ�õ�add��������ʹ�����ģʽ
 * */

interface CShape{ // ���ڵ�
	public void draw(String fillcolor); //ͳһ�ӿ�
}

class Triangle implements CShape{ // Ҷ�ӽڵ�

	@Override
	public void draw(String fillcolor) {
		System.out.println("Drawing Triangle with color : " + fillcolor);
	}
}

class Circle implements CShape{ // Ҷ�ӽڵ�

	@Override
	public void draw(String fillcolor) {
		System.out.println("Drawing Circle with color : " + fillcolor);
	}
}

class Drawing implements CShape{
	
	private List<CShape> shapes = new ArrayList<CShape>(); // ������Ҷ����ͳһ����
	@Override
	public void draw(String fillcolor) { // �����һ�½ӿ�
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
