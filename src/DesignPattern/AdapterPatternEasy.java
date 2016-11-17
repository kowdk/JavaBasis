package DesignPattern;

/** 
 * [�ṹģʽ]
 * ������ģʽ��������һ�����������Ҫ�����������������ֳƱ�ѹ��ģʽ��ʹ��ԭ���ӿڲ�ƥ����޷�һ�������������ܹ�һ������
 * ������ģʽϣ������һЩ�ִ���࣬��������ʹ�û�����һ�£��ڴ��븴�ú����Ǩ�Ʒ����ر����á�
 * ��jdk�У�asList��java.io.InputStreamReader(InputStream)�Լ�java.io.OutputStreamWriter(OutputStream)��ʹ��������ģʽ��
 * ������ģʽ�����������ģʽ��extends�Ǹ��������ࣻҲ��������ϵķ�ʽ�������������������
 **/

class Adaptee{
	public void specificFunc(){
		System.out.println("���нӿڣ���Ҫ������");
	}
}

interface StandardInterface{
	public void standardFunc();
}

class StandardClass implements StandardInterface {

	@Override
	public void standardFunc() {
		System.out.println("��׼�ӿڵı�׼ʵ��");
	}
	
}

class Adapter implements StandardInterface{
	private Adaptee adaptee; // �����Ҫ����������з���
	
	public Adapter(Adaptee tee){
		this.adaptee = tee;
	}
	
	@Override
	public void standardFunc() { //��׼�ӿ�
		this.adaptee.specificFunc();//����ʵ�֣�����˴��븴��
	}
}

public class AdapterPatternEasy {
	public static void main(String[] args) {
		StandardClass sc = new StandardClass();
		sc.standardFunc();
		
		Adapter sc1 = new Adapter(new Adaptee());
		sc1.standardFunc();
	}
}
