
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
	
	private static final long TIME_SPAN = 1000 * 30; // 表示每半分钟
	
	public static void main(String[] args) {
		long timestamp = getCurrTime(); // 局部时间变量
		String fileName = getFileName(timestamp); // 当前五分钟文件名
		while(true) {
			if(timestamp != getCurrTime()) { // 如果已经到了下一个半分钟
				//HdfsDumper.hdfsDumper(set, fileName); // 将set写入file中
				System.out.println(fileName + "call dumper...");
				
				// 更新文件名和时间戳
				timestamp = getCurrTime();
				fileName = getFileName(timestamp);
			} else { // 当前半分钟
				//System.out.println(fileName + " " + timestamp);
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	private static long getCurrTime(){
		return new Date().getTime() / TIME_SPAN * TIME_SPAN;
	}
	
	/**
	 * 根据当前时间构造文件名
	 * @param now
	 * @return
	 */
	private static String getFileName(long now) {
		final String suffix = ".txt";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		//long now = new Date().getTime() / span * span;
		String name = df.format(new Date(now));
		return name + suffix;
	}
	
	private void preProcess() {
		Set<String> domainSet = load("D://WorkingOn//whitedomains_cn.cn");
		Set<String> suffixSet = load("D://WorkingOn//specialTLD");
		Set<String> res = new HashSet<String>();
		for (String host : domainSet) {
			String[] strs = host.split("\\.");
			int len = strs.length;
			if (len <= 2) {
				res.add("." + host);
			} else if (len == 3) {
				String suffix = strs[len - 2] + "." + strs[len - 1];
				if (suffixSet.contains(suffix)) {
					res.add("." + host);
				}
			} else {
				String suffix = strs[len - 2] + "." + strs[len - 1];
				if (suffixSet.contains(suffix)) {
					res.add("." + strs[len - 3] + "." + strs[len - 2] + "."
							+ strs[len - 1]);
				}
			}
		}
		dump(res);
	}

	private void dump(Set<String> set) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(
					"D://WorkingOn//1.txt")));
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String item = it.next();
				bw.write(item, 0, item.length());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private Set<String> load(String filePath) {
		Set<String> set = new HashSet<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			String line = "";
			while ((line = br.readLine()) != null) {
				set.add(line);
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
		return set;
	}
}
