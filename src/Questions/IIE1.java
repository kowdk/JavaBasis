package Questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;


class IPConfig {
	private long start;
	private long end;
	
	public IPConfig(long start, long end){
		this.start = start;
		this.end = end;
	}

	public long getStart() {
		return start;
	}

	public long getEnd() {
		return end;
	}
}

public class IIE1 {
	
	private HashSet<IPConfig> set = null;
	
	public IIE1(){
		set = new HashSet<IPConfig>();
	}
	
	/**
	 * 将点分十进制IP地址转换为long
	 * @param ip
	 * @return
	 */
	private long ipToLong(String ip){
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip2long = 0L;
		for (int i = 0; i < 4; ++i) {
			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
		}
		return ip2long;
	}
	
	/**
	 * 从ip.txt中加载ip地址段
	 * @param path
	 */
	public void loadConfig(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] strs = line.split(" ");
				long start = this.ipToLong(strs[0]);
				long end = this.ipToLong(strs[1]);
				set.add(new IPConfig(start, end));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 判断IP是否在指定范围内
	 * @param ip
	 * @return
	 */
	public boolean isInRange(String ip){
		long ipLong = this.ipToLong(ip);
		for(IPConfig conf : set) {
			if(ipLong >= conf.getStart() && ipLong <= conf.getEnd()) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		IIE1 tester = new IIE1();
		final String path = "G://ip.txt";
		tester.loadConfig(path);
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine()) {
			String ip = s.nextLine().trim();
			System.out.println(tester.isInRange(ip));
		}
		s.close();
	}
}
