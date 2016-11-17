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

/** ������ */
abstract class APerson{
    //�����ඨ���������̹Ǽ�
    public final void prepareGotoSchool(){
         dressUp();
         eatBreakfast();
         takeThings();
    }
    //�����ǲ�ͬ�����������������ɵľ��岽��
    protected abstract void dressUp();
    protected abstract void eatBreakfast();
    protected abstract void takeThings();
}

/** ����ʵ���� */
class Student extends APerson{
    @Override
    protected void dressUp() {
         System.out.println("��У��");
    }
    @Override
    protected void eatBreakfast() {
         System.out.println("���������õ��緹");
    }

    @Override
    protected void takeThings() {
         System.out.println("����������ϼ�ͥ��ҵ�ͺ����");
    }
}

class Teacher extends APerson{
    @Override
    protected void dressUp() {
         System.out.println("��������");
    }
    @Override
    protected void eatBreakfast() {
         System.out.println("���緹���չ˺��ӳ��緹");
    }

    @Override
    protected void takeThings() {
         System.out.println("��������׼���Ŀ���");
    }
}

public class TemplatePatternEasy {
	public static void main(String[] args) {
		APerson person = new Student();
		person.prepareGotoSchool();
		
		person = new Teacher();
		person.prepareGotoSchool();
	}
}
