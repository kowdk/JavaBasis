

package Alogrithm;

import java.util.Arrays;
import java.util.Random;

public class BitMap {

	private int SHIFT = 5;//2 ^ 5 == 32
	private int array[] = null;
	

	public BitMap(int size) {
		initBitMap(size);
	}

	private void initBitMap(int size) {
		int arraySize = size / 32 + 1;
		this.array = new int[arraySize];
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}
	
	/**
	 * ��array[i >> SHIFT]��0
	 * */
	public void clear(int i) {
		int pos = i % 32;
		array[i >> SHIFT] &= (1 << pos);
	}
	
	/**
	 * array[0]ֻ�ܱ�ʾ32λ�����i>>SHIFT�����Ӧ����array[i],
	 * ��1����posλ������|=����ʾ��array[i>>SHIFT]�ĵ�posλ��Ϊ1
	 * */
	public void set(int i) {
		int pos = i % 32;
		array[i >> SHIFT] |= (1 << pos);
	}

	/**
	 * array[i >> SHIFT] & (1 << pos);����posλ�ǲ���Ϊ1
	 */
	public int get(int i) {
		int pos = i % 32;
		return array[i >> SHIFT] & (1 << pos);
	}
	
	/*
	public void setOneIfExist(int pos) {
		array[pos >> 5] = array[pos >> 5] | (1 << (31 - pos % 32));
	}

	public int getOne(int pos) {
		return array[pos >> 5] & (1 << (31 - pos % 32));
	}*/

	public static void main(String[] args) {
		int N = 10;
		BitMap bitmap = new BitMap(N);
		Random random = new Random();
		int array[] = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = random.nextInt(N);
		}
		System.out.println(Arrays.toString(array));
		for (int i = 0; i < array.length; i++) {
			bitmap.set(array[i]);
		}
		for (int i = 0; i < N; i++) {
			if (bitmap.get(i) != 0) {
				System.out.print((i) + " ");
			}
		}
	}
}
