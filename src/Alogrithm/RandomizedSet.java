package Alogrithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 设计一种数据结构
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
        if(contain) return false; // 不能包含重复值
        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = nums.contains(val);
        if(!contain) return false;
        int loc = locs.get(val);
        if(loc < nums.size() - 1) { // 不是最后一个数字，将该数字和最后一个数字交换
        	int last = nums.get(nums.size()-1); // 最后一个数字
        	nums.set(loc, last); // 用last覆盖要的删除数字
        	locs.put(last, loc); // 更新last的位置
        }
        locs.remove(val); // 删除val
        nums.remove(nums.size()-1); // 删除last
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
    
	public static void main(String[] args) {

	}
}
