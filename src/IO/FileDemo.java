package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class FileDemo {
	// 获取文件名后缀
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		// ==-1表示没找到，==0表示在第一个字符
		if (fileName.lastIndexOf('.') != -1 && fileName.lastIndexOf('.') != 0)
			return fileName.substring(fileName.lastIndexOf('.') + 1);
		return "";
	}

	private static void findFiles(String dir, String ext) {
		File file = new File(dir);
		if (!file.exists())
			System.out.println("The dir not exists: " + dir);
		File[] fileList = file.listFiles(new myFileFilter(ext));
		if (fileList.length == 0) {
			System.out.println("There are no files end with " + ext);
		} else {
			for (File f : fileList)
				System.out.println("File : " + dir + File.separator
						+ f.getName());
		}
	}

	public static class myFileFilter implements FileFilter {
		private String ext = null;

		public myFileFilter(String ext) {
			this.ext = ext.toLowerCase();
		}

		public boolean accept(File pathname) {
			// TODO Auto-generated method stub
			return pathname.getName().toLowerCase().endsWith(ext);
		}
	}

	public static void copyFileUsingStream(File src, File dst)
			throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dst);
			byte[] buffer = new byte[65535];
			int length = 0;
			while ((length = is.read(buffer)) > 0)
				os.write(buffer, 0, length);
		} finally {
			is.close();
			os.close();
		}
	}

	public static void copyFileUsingChannel(File src, File dst)
			throws IOException {
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try {
			srcChannel = new FileInputStream(src).getChannel();
			dstChannel = new FileOutputStream(dst).getChannel();
			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
		} finally {
			try {
				dstChannel.close();
				srcChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void copyFileUsingUtil(File src, File dst) throws IOException {
		Files.copy(src.toPath(), dst.toPath());
	}

	public static void readFileByLine(File src, File dst, String key)
			throws IOException {
		BufferedReader br = null;
		BufferedWriter wr = null;
		try {
			br = new BufferedReader(new FileReader(src));
			wr = new BufferedWriter(new FileWriter(dst));

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.contains(key)) {
					wr.write(line, 0, line.length());
					wr.newLine();
				}
			}
		} finally {
			br.close();
			wr.close();
		}
	}

	private static void readUsingBufferedReader(File src, Charset cs) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(src);
			isr = new InputStreamReader(fis, cs);
			br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				isr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void readUsingBufferedReaderJava7(File src, Charset cs) {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(src.toPath(), cs);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void readUsingFiles(File src, Charset cs) throws IOException {
		Path path = src.toPath();
		byte[] bytes = Files.readAllBytes(path);// 用的channel
		List<String> lines = Files.readAllLines(path, cs);// 用的bufferedReader
		Files.write(path, lines, cs);
	}

	private static void writeUsingFiles(File dst, String data)
			throws IOException {
		Files.write(dst.toPath(), data.getBytes());
	}

	private static void writeUsingOutputStream(File dst, String data)
			throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(dst);
			//如果是要追加只需要在FileOutputStream或者FileWriter的构造函数添加true
			//os = new FileOutputStream(dst, true);
			os.write(data.getBytes(), 0, data.length());
		} finally {
			os.close();
		}
	}

	private static void writeUsingBufferedWriter(File dst, String data)
			throws IOException {
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(dst));
			bw.write(data);
			bw.flush();
		}
		finally
		{
			bw.close();
		}
	}
	
	private static void readUsingScanner(File src, Charset cs)
			throws IOException {
		Scanner scanner = new Scanner(src);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
	}

	private static void readWriteUsingStream(File src, File dst)
			throws IOException {
		InputStream is = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dst);
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		is.close();
		os.flush();
		os.close();
	}

	private static void readPropertyFile(File propertyFile, File xmlFile) throws IOException
	{
		Properties prop = new Properties();
		FileReader fr = new FileReader(propertyFile);
		prop.load(fr);
		Set<Object> set = prop.keySet();
		for(Object obj : set)
		{
			System.out.println(obj.toString() + " : " + prop.getProperty(obj.toString()));
		}
		System.out.println(prop.getProperty("db.user"));
		prop.clear();
		prop.loadFromXML(new FileInputStream(xmlFile));
		System.out.println(prop.getProperty("db.user"));
	}
	
	private static void writePropertyFile(File proprtyFile, File xmlFile) throws IOException
	{
		Properties prop = new Properties();
		FileWriter fr = new FileWriter(proprtyFile);
		prop.setProperty("db.user", "root");
		prop.setProperty("db.host", "localhost");
		prop.setProperty("db.pass", "123456");
		prop.store(fr, "db properties");
		prop.storeToXML(new FileOutputStream(xmlFile), "db xml");
	}
	
	private static void tempFileUsingFile(String prefix, String suffix, String dirName) throws IOException
	{
		File tmp = File.createTempFile(prefix, suffix, new File(dirName));
		//options on file
		tmp.deleteOnExit();
	}
	
	public static void main(String[] args) throws IOException {
		File src = new File("G://Experiment//db.xml");
		File dst = new File("G://Experiment//db.txt");
		long startTime = System.currentTimeMillis();
		// copyFileUsingStream(src, dst);
		// copyFileUsingChannel(src, dst);
		// readFileByLine(src, dst, "北京");
		// readUsingBufferedReaderJava7(src, Charset.defaultCharset());
		//readWriteUsingStream(src, dst);
		//writePropertyFile(dst, src);
		readPropertyFile(dst, src);
		System.out.println(String.valueOf(System.currentTimeMillis()
				- startTime));
	}
}
