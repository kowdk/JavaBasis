package DesignPattern;

/* 
 * [ｽ盪ｹﾄ｣ﾊｽ]
 * ﾊﾊﾅ菷｣ﾊｽ｣ｺﾊﾊﾓﾃﾓﾚﾒｻｸﾔﾏﾉﾄﾜﾐ靨ｪｶ犧ﾊﾅ菷ﾄﾇ鯀｣ﾓﾖｳﾆｱ葭ｹﾆ｣ﾊｽ｣ｬﾊｹｵﾃﾔｭｱｾｽﾓｿﾚｲｻﾆ･ﾅ荼ﾞｷｨﾒｻﾆ､ﾗﾄﾁｽｸ狷ﾜｹｻﾒｻﾆ､ﾗ｣
 * ﾊﾊﾅ菷｣ﾊｽﾏ｣ﾍ鄕ｴﾓﾃﾒｻﾐｩﾏﾖｴ豬ﾄﾀ爛ｬｵｫﾊﾇﾓﾖﾓ�ﾊｹﾓﾃｻｷｾｳｲｻﾒｻﾖﾂ｣ｬﾔﾚｴ惲�ｸｴﾓﾃｺﾍﾀ狒簓ｨﾒﾆｷｽﾃ賣ﾘｱﾐﾓﾃ｡｣
 * ﾔﾚjdkﾖﾐ｣ｬasList｡｢java.io.InputStreamReader(InputStream)ﾒﾔｼｰjava.io.OutputStreamWriter(OutputStream)ｶｼﾊｹﾓﾃﾊﾊﾅ菷｣ﾊｽ｡｣
 * ﾊﾊﾅ菷｣ﾊｽﾓﾐﾀ犒ﾄﾊﾊﾅ菷｣ﾊｽ｣ｬextendsﾄﾇｸｻﾊﾊﾅ菎爛ｻﾒｲｿﾉﾒﾔﾒﾔﾗ鮗ﾏｵﾄｷｽﾊｽｽｫｱｻﾊﾊﾅ菎犹ﾘﾁｪｽｴ｡｣
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
