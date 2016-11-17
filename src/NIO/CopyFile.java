package NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	private final static String resPath = new File("").getAbsolutePath()
			+ File.separatorChar + "res\\";

	static public void main(String args[]) throws Exception {
		
		String infile = resPath + "readandshow.txt";
		String outfile = resPath + "writesomebytes.txt";

		FileInputStream fin = new FileInputStream(infile);
		FileOutputStream fout = new FileOutputStream(outfile);

		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		while (true) {
			buffer.clear();

			int r = fcin.read(buffer);

			if (r == -1) {
				break;
			}

			buffer.flip();

			fcout.write(buffer);
		}
		
		fin.close();
		fout.close();
	}
}
