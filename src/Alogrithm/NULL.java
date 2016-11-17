package Alogrithm;

public class NULL {
	public static void print(){
		System.out.println("M");
	}
	
	public static void main(String[] args) {
		try{
			NULL p = null;
		} catch(NullPointerException e) {
			System.out.println("NULLP");
		}
	}
}
