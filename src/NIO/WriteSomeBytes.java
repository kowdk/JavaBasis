package NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteSomeBytes {
	static private final byte message[] = { 83, 111, 109, 101, 32, 98, 121,
			116, 101, 115, 46 };
	
	private final static String resPath = new File("").getAbsolutePath() + File.separatorChar + "res\\";

	static public void main(String args[]) throws Exception {
		
		FileOutputStream fout = new FileOutputStream(resPath + "writesomebytes.txt");
		FileInputStream fin = new FileInputStream(resPath + "readandshow.txt");
		FileChannel fc = fout.getChannel();
		FileChannel inChannel = fin.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(fin.available());
		
		int bytesRead = inChannel.read(buffer);
		buffer.flip();
		
		while(buffer.hasRemaining()) {
			System.out.println((char) buffer.get());
		}
		
		buffer.clear();
		fin.close();

		String str = "xutao";
		ByteBuffer.allocate(str.getBytes().length);
		System.out.println(buffer);
		buffer.put(str.getBytes(), 0, str.getBytes().length);
		
		buffer.flip();

		fc.write(buffer);

		fout.close();
	}
}
