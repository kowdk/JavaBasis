package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * [行为模式]
 * 迭代模式：为一组对象或者对象集合提供访问的标准.
 * iterator使用私有内部类隐藏了其实现，而且iterator也可以有多种定制实现.
 * java集合类中的iterator就是迭代模式，Scanner也使用了迭代模式.
 * 
 * */

enum ChannelTypeEnum {
	ENGLISH, HINDI, FRENCH, ALL;
}

class Channel {
	private double frequency;
	private ChannelTypeEnum type;

	public Channel(double frequency, ChannelTypeEnum type) {
		super();
		this.frequency = frequency;
		this.type = type;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public ChannelTypeEnum getType() {
		return type;
	}

	public void setType(ChannelTypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Channel [frequency=" + frequency + ", type=" + type + "]";
	}
}

interface ChannelCollection {
	public void addChannel(Channel c);

	public void removeChannel(Channel c);

	public ChannelIterator iterator(ChannelTypeEnum type);
}

interface ChannelIterator {
	public boolean hasNext();

	public Channel next();
}

class ChannelCollectionImpl implements ChannelCollection {

	private List<Channel> list;

	public ChannelCollectionImpl() {
		list = new ArrayList<Channel>();
	}

	@Override
	public void addChannel(Channel channel) {
		list.add(channel);
	}

	@Override
	public void removeChannel(Channel channel) {
		list.remove(channel);
	}

	@Override
	public ChannelIterator iterator(ChannelTypeEnum type) {
		return new ChannelIteratorImpl(type, this.list);
	}

	private class ChannelIteratorImpl implements ChannelIterator {

		private ChannelTypeEnum type;
		private List<Channel> list;
		private int pos;

		public ChannelIteratorImpl(ChannelTypeEnum type, List<Channel> list) {
			this.type = type;
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			while(pos < list.size()) {
				Channel c = list.get(pos);
				if(c.getType().equals(type) || type.equals(ChannelTypeEnum.ALL)){
					return true;
				}
				else {
					pos++;
				}
			}
			return false;
		}

		@Override
		public Channel next() {
			Channel c = list.get(pos++);
			return c;
		}

	}

}

public class IteratorPattern {

	public static void main(String[] args) {
		ChannelCollection list = new ChannelCollectionImpl();
		list.addChannel(new Channel(333, ChannelTypeEnum.ENGLISH));
		list.addChannel(new Channel(111, ChannelTypeEnum.FRENCH));
		list.addChannel(new Channel(222, ChannelTypeEnum.HINDI));
		
		ChannelIterator it = list.iterator(ChannelTypeEnum.ALL);
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		
		System.out.println("-------------------------------------");
		it = list.iterator(ChannelTypeEnum.ENGLISH);
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		
	}

}
