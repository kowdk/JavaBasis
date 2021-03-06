package DesignPattern;

/* 
 * [结构模式]
 * 适配器模式：适用于一个对象可能需要多个适配器的情况。又称变压器模式，使得原本接口不匹配而无法一起工作的两个类能够一起工作。
 * 适配器模式希望复用一些现存的类，但是又与使用环境不一致，在代码复用和类库迁移方面特别有用。
 * 在jdk中，asList、java.io.InputStreamReader(InputStream)以及java.io.OutputStreamWriter(OutputStream)都使用适配器模式。
 * 适配器模式有类的适配器模式，extends那个被适配类；也可以以组合的方式将被适配类关联进来。
 *  */

class Volt{
	private int volts;
	public Volt(int v){
		this.volts = v;
	}
	public int getVolts() {
		return volts;
	}
	public void setVolts(int volts) {
		this.volts = volts;
	}
}

class Socket{
	public Volt getVolt(){
		return new Volt(120);
	}
}

interface SocketAdapter{
	public Volt get120Volt();
	public Volt get12Volt();
	public Volt get3Volt();
}

class SocketAdapterImpl implements SocketAdapter{
	private Socket sock = new Socket();

	@Override
	public Volt get120Volt() {
		return sock.getVolt();
	}

	@Override
	public Volt get12Volt() {
		Volt v = sock.getVolt();
		return convertVolt(v, 10);
	}

	@Override
	public Volt get3Volt() {
		Volt v = sock.getVolt();
		return convertVolt(v, 40);
	}
	
	private Volt convertVolt(Volt v, int i){
		return new Volt(v.getVolts() / i);
	}
}

public class AdapterPattern {

	public static void main(String[] args) {
		SocketAdapter sa = new SocketAdapterImpl();
		Volt v3 = getVolt(sa, 3);
		System.out.println("v3 volts using class adapter = " + v3.getVolts());
		
		Volt v5 = getVolt(sa, 12);
		System.out.println("v5 volts using class adapter = " + v5.getVolts());
	}
	
	private static Volt getVolt(SocketAdapter sa, int i){
		switch(i){
		case 3: return sa.get3Volt();
		case 12: return sa.get12Volt();
		case 120: return sa.get120Volt();
		default: return sa.get120Volt();
		}
	}
}
