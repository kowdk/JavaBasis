package NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ReadAndShow {
	private final static String resPath = new File("").getAbsolutePath() + File.separatorChar + "res\\";
	
	static public void main(String args[]) throws Exception {
		FileInputStream fin = new FileInputStream(resPath + "readandshow.txt");
		FileChannel fc = fin.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		fc.read(buffer);

		buffer.flip();

		int i = 0;
		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			System.out.println("Character " + i + ": " + ((char) b));
			i++;
		}
		
		fin.close();
	}
}
