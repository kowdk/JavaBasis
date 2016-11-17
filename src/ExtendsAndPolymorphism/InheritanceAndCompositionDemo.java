package ExtendsAndPolymorphism;

/*
 * �̳�����ϣ��̳о���is a�Ĺ�ϵ���������has a�Ĺ�ϵ
 * �̳�������̳и���ķ��������ԣ������������Ƕ����һ��������á�
 * 1.�̳е���϶ȸߣ�����̳пɼ̳еķ��������ԣ�����Ͽ���ֻ��ѡ����Ȥ�ķ��������ԣ�Ҳ�����ڵ����ߵ������޸ı������ߵķ���������Ӱ�챻�����ߣ���϶Ƚϵ͡�
 * 2.�̳н�����ķ�������¶�ڵ�������ǰ������Ͽ��Զ��ض��������÷���Ȩ�ޣ���װ�Ը�ǿ��
 * 3.�ڵ�Ԫ���Ե�ʱ�������Ϊ�����ԭ����÷ֿ��⣬���̳б�����Ը��࣬�Ƕ���Ĺ�����
 * �̳���˵���Ҹ����ڼ�����Ұ��˺ܴ��æ���������˵�������˸���ͷ���Ҽ���ɻ��
 * */

class Animal
{   
	private void beat()
	{
		System.out.println("�����ڲ�ͣ������");
	}
	
	public void breath()
	{
		this.beat();
		System.out.println("�����ں���");
	}
}

class WolfInherit extends Animal
{	
	public void run()
	{
		System.out.println("�ҿ�����½���ϱ���");
	}
}

class BirdInherit extends Animal
{
	public void fly()
	{
		System.out.println("�ҿ�������������ɵİ���");
	}
}

class WolfCombine
{
	private Animal a = new Animal();
	public void run()
	{
		System.out.println("�ҿ�����½���ϱ���");
	}
	
	public void breath()
	{
		a.breath();
	}
}

class BirdCombine
{
	private Animal a = new Animal();
	
	public void fly()
	{
		System.out.println("�ҿ�������������ɵİ���");
	}
	
	public void breath()
	{
		a.breath();
	}
}

public class InheritanceAndCompositionDemo {
	public static void main(String[] args)
	{
		//ʹ�ü̳еķ�ʽ��bird��wolf���Ժ���
		BirdInherit bi = new BirdInherit();
		bi.fly();
		bi.breath();
		
		//ʹ����ϵķ�ʽ������AnimalΪprivate���Ա�֤Animal�е��κ�public�������޷������ʵ������̳оͲ��У�breath�ͱ����ʵ���
		Animal a = new BirdInherit();
		a.breath();
		
		/*WolfInherit wi = new WolfInherit();
		wi.run();
		wi.breath();*/
		
		//ʹ����ϵķ�ʽ��bird��wolf���Ժ���
		/*BirdCombine bc = new BirdCombine();
		bc.fly();
		bc.breath();
		WolfCombine wc = new WolfCombine();
		wc.run();
		wc.breath();*/
	}
}

