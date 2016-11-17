package DesignPattern;

/**
 * 定义Iterator接口
 * @author xutao
 *
 */
interface Iterator{
	public boolean hasNext();
	public Object next();
}

class NameList {
	public String[] names = {"xutao", "yintao", "sangyafei"};
	
	/**
	 * 返回聚合在数据实体内部的迭代器
	 * @return
	 */
	public Iterator getIterator(){
		return new NameIterator();
	}
	
	/**
	 * 私有内部类，实现一个具体的迭代器
	 * @author xutao
	 *
	 */
	private class NameIterator implements Iterator{
		private int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length)
				return true;
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()) {
				return names[index++];
			}
			return null;
		}
		
	}
}

public class IteratorPatternEasy {
	public static void main(String[] args) {
		NameList names = new NameList();
		Iterator it = names.getIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
