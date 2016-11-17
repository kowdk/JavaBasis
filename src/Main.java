
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
	
	private static final long TIME_SPAN = 1000 * 30; // ��ʾÿ�����
	
	public static void main(String[] args) {
		long timestamp = getCurrTime(); // �ֲ�ʱ�����
		String fileName = getFileName(timestamp); // ��ǰ������ļ���
		while(true) {
			if(timestamp != getCurrTime()) { // ����Ѿ�������һ�������
				//HdfsDumper.hdfsDumper(set, fileName); // ��setд��file��
				System.out.println(fileName + "call dumper...");
				
				// �����ļ�����ʱ���
				timestamp = getCurrTime();
				fileName = getFileName(timestamp);
			} else { // ��ǰ�����
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
	 * ��ȡ��ǰʱ���
	 * @return
	 */
	private static long getCurrTime(){
		return new Date().getTime() / TIME_SPAN * TIME_SPAN;
	}
	
	/**
	 * ���ݵ�ǰʱ�乹���ļ���
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
