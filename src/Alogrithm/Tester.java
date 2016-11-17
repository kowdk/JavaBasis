package Alogrithm;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tester{
    public static void main(String[] args) {
    	Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        System.out.println(n1 == n2);
        Integer a = Integer.valueOf(47);
        Integer b = Integer.valueOf(127);
        System.out.println(a == n1);
        ArrayList<Integer> list = new ArrayList<Integer>();
        LinkedList<Integer> lin = new LinkedList<Integer>();
        
	}

	public static StringBuilder test() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("22");
			return sb;
		} catch(RuntimeException e){
			return null;
		} finally {
			sb.append("33");
			//return sb;
		}
	}
}

