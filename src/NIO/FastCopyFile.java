package NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class FastCopyFile {

	private final static String resPath = new File("").getAbsolutePath()
			+ File.separatorChar + "res\\";

	static public void main(String args[]) {
		String infile = resPath + "readandshow.txt";
		String outfile = resPath + "writesomebytes.txt";

		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(infile);
			fout = new FileOutputStream(outfile);

			FileChannel fcin = fin.getChannel();
			FileChannel fcout = fout.getChannel();

			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

			while (true) {
				buffer.clear();
				int r;
				r = fcin.read(buffer);

				if (r == -1) {
					break;
				}

				buffer.flip();
				fcout.write(buffer);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				fin.close();
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
