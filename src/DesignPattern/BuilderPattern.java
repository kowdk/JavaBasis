package DesignPattern;

/*
 * [����ģʽ]
 * ������ģʽ��ʹ�ù��о�̬�ڲ�������ⲿ�����Ĵ�������ע��Ʒ�����ϸ��
 * ���󹤳�ģʽ���ܹ���Ӧ�ڶ������̫���������������׳�������Ӧ��һ��һ��ȥset
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
	 * ˽�е��ⲿ�๹�캯����������ʹ���ⲿ�๹�캯��
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
	 * ��Ʒ��ľ�̬�ڲ��ࣺ
	 * 1. �����ⲿ�����е�����
	 * 2. �ṩ�����Ĺ��췽���������ⲿ���������
	 * 3. �ṩset�����������ⲿ���������ԣ������ص�ǰ�ⲿ��ʵ������
	 * 4. ����ṩbuilder�����������ⲿ�����new��ʱ��ʹ���ⲿ���˽�й��캯��
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
 * �ͻ�ʹ���� 
 * jdk��StringBuilder��ʹ�������ķ�ʽ
 * 
 * */
public class BuilderPattern {
	public static void main(String[] args) {
		LargeComputer c = new LargeComputer.ComputerBuilder("2 GB", "2.4 GHz").setBluetoothOn(true).setGraphicsOn(true).builder();
		System.out.println(c);
	}
}
