package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException {
		/*RandomAccessFile raf = new RandomAccessFile("abc.txt", "rw");
		raf.setLength(1024 * 1024); // Ԥ���� 1M ���ļ��ռ�
		raf.close();*/

		// ��Ҫд����ļ�����
		String s1 = "��һ���ַ���";
		String s2 = "�ڶ����ַ���";
		String s3 = "�������ַ���";
		String s4 = "���ĸ��ַ���";
		String s5 = "������ַ���";

		// ���ö��߳�ͬʱд��һ���ļ�
		new FileWriteThread(1024 * 1, s1.getBytes()).start(); // ���ļ���1024�ֽ�֮��ʼд������
		new FileWriteThread(1024 * 2, s2.getBytes()).start(); // ���ļ���2048�ֽ�֮��ʼд������
		new FileWriteThread(1024 * 3, s3.getBytes()).start(); // ���ļ���3072�ֽ�֮��ʼд������
		new FileWriteThread(1024 * 4, s4.getBytes()).start(); // ���ļ���4096�ֽ�֮��ʼд������
		new FileWriteThread(1024 * 5, s5.getBytes()).start(); // ���ļ���5120�ֽ�֮��ʼд������
	}

	static class FileWriteThread extends Thread {
		private int skip;
		private byte[] content;

		public FileWriteThread(int skip, byte[] content) {
			this.skip = skip;
			this.content = content;
		}

		public void run() {
			RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile("abc.txt", "rw");
				raf.seek(skip);
				raf.write(content);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					raf.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private static void randomFile(long pos, String insertContent)
			throws IOException {
		File f = new File("xutao.txt");
		RandomAccessFile raf = null;
		File tmpFile = File.createTempFile("tmp", null);
		// tmpFile.deleteOnExit();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			raf = new RandomAccessFile(f, "rw");
			fos = new FileOutputStream(tmpFile);
			fis = new FileInputStream(tmpFile);
			raf.seek(pos);
			byte[] buf = new byte[1024];
			int hasread = 0;
			while ((hasread = raf.read(buf)) > 0)
				fos.write(buf, 0, hasread);

			raf.seek(pos);
			raf.write(insertContent.getBytes());

			while ((hasread = fis.read(buf)) > 0)
				raf.write(buf, 0, hasread);

		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void fileReader() throws IOException {
		// System.out.println(file.getCanonicalPath() + File.separator +
		// "src\\Test.java");
		String filePath = new File("").getCanonicalPath() + File.separator
				+ "src\\Test.java";
		File f = new File("xutao.txt");
		FileInputStream fis = new FileInputStream(filePath);
		FileOutputStream fos = new FileOutputStream(f);

		byte[] buf = new byte[1024];
		int hasread = 0;
		while ((hasread = fis.read(buf)) > 0) {
			System.out.println(new String(buf, 0, hasread));
			fos.write(buf, 0, hasread);
		}
		fis.close();
		fos.close();
	}

	private static void fileWriter() throws IOException {
		File file = new File("xutao.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("123\r\n");
			fw.write("xutao\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				fw.close();
		}

	}

	private static void FileTest() throws IOException {
		File f = new File("Test.java");
		f.createNewFile();
		System.out.println("f.getpath == " + f.getPath());
		System.out.println("f.getAbsolutePath == " + f.getAbsolutePath());
		System.out.println(f.getAbsoluteFile().getParentFile().getParent());
		System.out.println("f.getName == " + f.getName());
		System.out.println("f.getParent == " + f.getParent());
		File newFile = new File(System.currentTimeMillis() + ".txt");
		System.out.println(newFile.exists());
		// newFile.createNewFile();
		System.out.println(newFile.exists());
		// newFile.mkdir();
		if (f.exists()) {
			String[] fileList = f.list(new myFilter());
			for (String fileName : fileList) {
				System.out.println(fileName);
			}
		}
	}
}

class myFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		if (name.endsWith(".txt"))
			return true;
		return false;
	}
}
