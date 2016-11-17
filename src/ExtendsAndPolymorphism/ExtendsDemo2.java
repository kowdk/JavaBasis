package ExtendsAndPolymorphism;

class Dervied3 extends Base1 {
    private String name = "dervied";
    public Dervied3() {
        tellName();
        printName();
    }
    @Override
	public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }
    @Override
	public void printName() {
        System.out.println("Dervied print name: " + name);
    }
}

class Base1 {
    private String name = "base";
    public Base1() {
        tellName();
        System.out.println(name);
        printName();
    }
    public void tellName() {
        System.out.println("Base tell name: " + name);
    }
    public void printName() {
        System.out.println("Base print name: " + name);
    }
}
class Super
{
    static String greeting()
    {
        return "Goodnight";
    }
    String name()
    {
        return "Richard";
    }
    int x = 1;
    static int y = 2;
    int z = 3;
    int method() {
        return x;
    }
}

class SubClass1 extends Super
{
    static String greeting()
    {
        return "Hello";
    }
    @Override
	String name()
    {
        return "Dick";
    }
    int x = 4;
    int y = 5;
    static int z = 6;
    @Override
	int method() {
        return x;
    }
}

class ConstClass{
	static {
		System.out.println("Constant init...");
	}
	
	public static final String finalStr = "hello";//测试类调用final属性，final变量值进入了常量池，因此不会加载静态代码块
	public static String staticStr = "hello";//测试类如果调用static属性，则会加载静态代码块
	
}
public class ExtendsDemo2 extends B implements AA {
	
	public static void main(String[] args)
    {
        /*Super s1 = new SubClass1();
        System.out.println(s1.greeting() + ", " + s1.name()); //说明多态对静态函数不起作用
        SubClass1 s = new SubClass1();
        System.out.println(s.x + " " + s.y + " " + s.z);
        System.out.println(s.method());
        Super b = (SubClass1) s;
        System.out.println(b.x + " " + b.y + " " + b.z);//说明多态对属性不起作用
        System.out.println(b.method());*/
        
        //new Dervied3();
    }
}
