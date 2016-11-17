package DesignPattern;

/*
 * [创建模式]
 * 建造者模式：使用共有静态内部类完成外部类对象的创建，关注产品建造的细节
 * 抽象工厂模式不能够适应于对象参数太多的情况，极其容易出错，所以应该一个一个去set
 * 
 * */
class LargeComputer {
	private String cpu;
	private String ram;

	private boolean isGraphicsOn;
	private boolean isBluetoothOn;

	public String getCpu() {
		return cpu;
	}

	public String getRam() {
		return ram;
	}

	public boolean isGraphicsOn() {
		return isGraphicsOn;
	}

	public boolean isBluetoothOn() {
		return isBluetoothOn;
	}

	/**
	 * 私有的外部类构造函数，不允许使用外部类构造函数
	 * */
	private LargeComputer(ComputerBuilder c) {
		this.cpu = c.cpu;
		this.ram = c.ram;
		this.isBluetoothOn = c.isBluetoothOn;
		this.isGraphicsOn = c.isGraphicsOn;
	}

	public String toString() {
		return "RAM = " + this.ram + ", CPU = " + this.cpu + ", isGraphicsOn = " + this.isGraphicsOn + ", isBluetoothOn = " + this.isBluetoothOn;
	}
	
	/* 
	 * 产品类的静态内部类：
	 * 1. 拷贝外部类所有的属性
	 * 2. 提供公共的构造方法，构造外部类必须属性
	 * 3. 提供set方法，设置外部类所需属性，并返回当前外部类实例对象
	 * 4. 最后提供builder方法，返回外部类对象，new的时候使用外部类的私有构造函数
	 * */
	public static class ComputerBuilder {
		private String cpu;
		private String ram;

		private boolean isGraphicsOn;
		private boolean isBluetoothOn;

		public ComputerBuilder(String cpu, String ram) {
			this.cpu = cpu;
			this.ram = ram;
		}
		
		public ComputerBuilder setGraphicsOn(boolean isGraphicsOn)
		{
			this.isGraphicsOn = isGraphicsOn;
			return this;
		}
		
		public ComputerBuilder setBluetoothOn(boolean isBluetoothOn)
		{
			this.isBluetoothOn = isBluetoothOn;
			return this;
		}
		
		public LargeComputer builder()
		{
			return new LargeComputer(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpu == null) ? 0 : cpu.hashCode());
		result = prime * result + (isBluetoothOn ? 1231 : 1237);
		result = prime * result + (isGraphicsOn ? 1231 : 1237);
		result = prime * result + ((ram == null) ? 0 : ram.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LargeComputer other = (LargeComputer) obj;
		if (cpu == null) {
			if (other.cpu != null)
				return false;
		} else if (!cpu.equals(other.cpu))
			return false;
		if (isBluetoothOn != other.isBluetoothOn)
			return false;
		if (isGraphicsOn != other.isGraphicsOn)
			return false;
		if (ram == null) {
			if (other.ram != null)
				return false;
		} else if (!ram.equals(other.ram))
			return false;
		return true;
	}
}

/* 
 * 客户使用类 
 * jdk中StringBuilder就使用这样的方式
 * 
 * */
public class BuilderPattern {
	public static void main(String[] args) {
		LargeComputer c = new LargeComputer.ComputerBuilder("2 GB", "2.4 GHz").setBluetoothOn(true).setGraphicsOn(true).builder();
		System.out.println(c);
	}
}
