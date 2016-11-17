package Base;

import java.util.Arrays;

/*
 * final�ؼ��ֿ��������ࡢ�����ͷ�������ʾ�����εĶ��󲻿ɸı�
 * final��Ա���������������Ժ�ʵ�����ԣ���������ʽ��ʼ����ϵͳ������ʽ����ֵ
 * ���ܶ�final���ε��βθ�ֵ
 * 
 * */
public class FinalDemo {
	
	//����������final���λ�����������
	private final String BASE_DIR = "d://blabla";
	final int a = 6;
	final String str;
	final int c;
	final static double d;
	{
		str = "hello";
		//a = 9;������ʱ���Ѿ����˳�ʼֵ������ڳ�ʼ�����в��ܸ�ֵ
	}
	
	static
	{
		d = 1.1;
	}
	
	public FinalDemo()
	{
		c = 5;
	}
	
	//��ͨ�������ܶ�final��Ա��������ֵ��ָ����ʼֵ
	public void changeFinal()
	{
		//d = 1.2;
		//str = "hi";
	}
	
	public static void main(String[] args)
	{
		//����final�����������ͱ���ʱ��ֻ��֤������õĵ�ַ���䣬����������Ա��ı�
		final int[] iArr = {1,2,3,4};
		Arrays.sort(iArr);
		iArr[1] = 0;
		
		//�����ĸ�ֵ�Ǵ����
		//iArr = null;
	}
}

//////////////////////////////////////////////////////////////////
class FinalMethod
{
	//final�����������أ�����д�޹�
	public final void PublicFinalMethod(){};
	public final void PublicFinalMethod(String str){};
	private final void PrivateFinalMethod(){};
	protected final void ProtectedFinalMethod(){};
}

class Sub extends FinalMethod
{
	//������д�����final����
	//public void PublicFinalMethod(){}
	//protected void ProtectedFinalMethod(){};
	//private��������Ϊ�������ã�������ȫû��ϵ������������ֻ��������ͬ
	private void PrivateFinalMethod(){};
}
//////////////////////////////////////////////////////////////////////

//final�಻�ܱ��̳�
final class FinalClass{}
//class sub extends FinalClass{}

/*
 * ��Ʋ��ɱ��ࣺ
 * 1. ʹ��private final���θ����ԡ�
 * 2. �ṩ�������Ĺ�������
 * 3. ���ṩsetter������ֻ�ṩgetter������
 * 4. ��дhashcode��equals������
 * ��Ʋ��ɱ���ʱ��ע���������͵����ԣ���������������Ե���ʱ�ɱ�ģ�����ʹ����������ķ������������������õĶ��󲻻ᱻ�޸ġ�
*/


