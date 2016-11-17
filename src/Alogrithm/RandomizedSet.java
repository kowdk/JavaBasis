package Alogrithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * ���һ�����ݽṹ
 * @author xutao
 *
 */
public class RandomizedSet {

	ArrayList<Integer> nums;
	HashMap<Integer, Integer> locs;
	Random random = new Random();
	/** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = nums.contains(val);
        if(contain) return false; // ���ܰ����ظ�ֵ
        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = nums.contains(val);
        if(!contain) return false;
        int loc = locs.get(val);
        if(loc < nums.size() - 1) { // �������һ�����֣��������ֺ����һ�����ֽ���
        	int last = nums.get(nums.size()-1); // ���һ������
        	nums.set(loc, last); // ��last����Ҫ��ɾ������
        	locs.put(last, loc); // ����last��λ��
        }
        locs.remove(val); // ɾ��val
        nums.remove(nums.size()-1); // ɾ��last
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
    
	public static void main(String[] args) {

	}
}
