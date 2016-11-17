package Alogrithm;

import java.util.BitSet;

class SimpleHash{
	private int cap;
	private int seed;
	public SimpleHash(int cap, int seed) {
		super();
		this.cap = cap;
		this.seed = seed;
	}
	public int hash(String value) {
		int res = 0;
		for(int i = 0; i < value.length(); i++) {
			res = seed * res + value.charAt(i);
		}
		return (cap-1) & res;
	}
}

public class BloomFilter {
	private static final int SIZE=1<<25;
	
	private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};
	private BitSet bits = new BitSet(SIZE);
	private SimpleHash[] func = new SimpleHash[seeds.length];
	
	public BloomFilter(){
		for(int i = 0; i < seeds.length; i++) {
			func[i] = new SimpleHash(SIZE, seeds[i]);
		}
	}
	
	public void add(String str) {
		for(SimpleHash f : func){
			bits.set(f.hash(str), true);
		}
	}
	
	public boolean contains(String str){
		if(str == null) {
			return false;
		}
		
		boolean ret = true;
		for(SimpleHash f : func){
			ret = ret && bits.get(f.hash(str));
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		BloomFilter bf = new BloomFilter();
		bf.add("xutao");
		bf.add("yintao");
		System.out.println(bf.contains("yafei"));
		System.out.println(bf.contains("xutao"));
	}
}