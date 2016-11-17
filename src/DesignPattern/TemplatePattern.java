package DesignPattern;


/*
 *[��Ϊģʽ]
 * ģ��ģʽ
 * ģ��ģʽȷ���˷�����ִ��˳�򣬶��������෽����Ĭ��ʵ�֡��ڸ����ж���Ϊfinal�����������า�ǡ�
 * ģ��ģʽ������������ɫ��
 * �����ࣨAbstractClass����ʵ����ģ�巽�����������㷨�ĹǼܡ�
 * �����ࣨConcreteClass)��ʵ�ֳ������еĳ��󷽷���������������㷨��
 * jdk�е�InputStream��OutputStream��Reader��Writer�඼ʹ����ģ��ģʽ
 * */
abstract class HouseTemplate{
	
	//���෽���������෽������������ʵ�֣�˳�򲻿ɱ仯��ģ�巽����������ִ��˳��ģ�巽�����ɱ�
	//�����ඨ���������̹Ǽ�
	public final void buildHouse(){
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built...");
	}
	
	public void buildFoundation(){
		System.out.println("Building foundation...");
	}
	
	//Ĭ��ʵ��
	public void buildWindows(){
		System.out.println("Building Glass Windows...");
	}
	
	//������ʵ�ֵķ���
	public abstract void buildWalls();
	public abstract void buildPillars();
}

class WoodenHouse extends HouseTemplate{

	@Override
	public void buildWalls() {
		System.out.println("Building Wooden Walls...");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Wood...");
	}
}

class GlassHouse extends HouseTemplate{
	
	@Override
	public void buildWalls() {
		System.out.println("Building Glass Walls...");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Glass...");
	}
}

public class TemplatePattern {
	public static void main(String[] args){
		HouseTemplate houseType = new WoodenHouse();
		houseType.buildHouse();
		System.out.println("----------------------");
		houseType = new GlassHouse();
		houseType.buildHouse();
	}
}
