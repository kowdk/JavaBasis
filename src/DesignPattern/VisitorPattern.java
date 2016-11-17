package DesignPattern;

/**
 * [行为模式] 访问者模式：用于对一系列类似的对象做相同的操作。借助访问者模式，可以把逻辑从系列对象中剥离出来。
 * 抽象访问者：抽象类或者接口，声明访问者可以访问哪些元素，具体到程序中就是visit方法中的参数定义哪些对象是可以被访问的。
 * 访问者：实现抽象访问者所声明的方法，它影响到访问者访问到一个类后该干什么，要做什么事情。
 * 抽象元素类：接口或者抽象类，声明接受哪一类访问者访问，程序上是通过accept方法中的参数来定义的。抽象元素一般有两类方法，一部分是本身的业务逻辑，另外就是允许接收哪类访问者来访问。
 * 元素类：实现抽象元素类所声明的accept方法，通常都是visitor.visit(this)，基本上已经形成一种定式了。
 * 优点在于：
 * 如果操作逻辑需要改变，不需要对每一个元素类进行更改，只需要修改访问者类的逻辑就行。
 * 同样地，如果需要增加元素类，也只需要在访问者类中增加逻辑就可以。
 * */

/** 抽象元素类 */
interface ItemElement {
	public int accept(ShoppingCartVisitor visitor);
}

/** 元素类*/
class Book implements ItemElement {

	private int price;
	private String isbnNumber;

	public Book(int cost, String isbn) {
		this.price = cost;
		this.isbnNumber = isbn;
	}

	public int getPrice() {
		return price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}
}

class Fruit implements ItemElement {

	private int pricePerKg;
	private int weight;
	private String name;

	public Fruit(int priceKg, int wt, String nm) {
		this.pricePerKg = priceKg;
		this.weight = wt;
		this.name = nm;
	}

	public int getPricePerKg() {
		return pricePerKg;
	}

	public int getWeight() {
		return weight;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int accept(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}

}

/** 抽象访问者*/
interface ShoppingCartVisitor {
	int visit(Book book);

	int visit(Fruit fruit);
}

/** 访问者*/
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

	@Override
	public int visit(Book book) {
		int cost = 0;
		// apply 5$ discount if book price is greater than 50
		if (book.getPrice() > 50) {
			cost = book.getPrice() - 5;
		} else
			cost = book.getPrice();
		System.out.println("Book ISBN::" + book.getIsbnNumber() + " cost ="
				+ cost);
		return cost;
	}

	@Override
	public int visit(Fruit fruit) {
		int cost = fruit.getPricePerKg() * fruit.getWeight();
		System.out
				.println("Fruit name::" + fruit.getName() + " cost = " + cost);
		return cost;
	}

}

/** 客户类 */
public class VisitorPattern {
	public static void main(String[] args) {
		ItemElement[] items = new ItemElement[] { new Book(20, "1234"),
				new Book(100, "5678"), new Fruit(10, 2, "Banana"),
				new Fruit(5, 5, "Apple") };
		int total = 0;
		
		ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
		for (ItemElement item : items) {
			total = total + item.accept(visitor);
		}
		
		System.out.println("Total Cost = " + total);
	}
}
