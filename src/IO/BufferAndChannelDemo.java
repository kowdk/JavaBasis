package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class BufferAndChannelDemo {
	
	private static void toString(CharBuffer cb)
	{
		System.out.println("capcity: " + cb.capacity() + "; position: " + cb.position() + "; limit: " + cb.limit());
	}
	
	private static void testPosition()
	{
		CharBuffer buff = CharBuffer.allocate(10);
		toString(buff);
		buff.put('A');
		buff.put('B');
		toString(buff);
		buff.flip();
		toString(buff);
		char[] dst = new char[buff.capacity()];
		buff.get(dst, 0, buff.length());
		toString(buff);
		buff.clear();
		toString(buff);
	}
	
	private static void appendToFileUsingChannel(File file) throws IOException
	{
		FileChannel channel = null;
		try{
			channel = new RandomAccessFile(file, "rw").getChannel();
			ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			channel.position(file.length());
			channel.write(buffer);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void getCharsetList()
	{
		SortedMap<String, Charset> map = Charset.availableCharsets();
		System.out.println(map.size());
		for(String alias : map.keySet())
		{
			System.out.println(alias);
		}
	}
	
	private static void charSetTest() throws IOException
	{
		Charset cn = Charset.forName("UTF-8");
		CharsetEncoder cnEncoder = cn.newEncoder();
		CharsetDecoder cnDecoder = cn.newDecoder();
		CharBuffer cbuff = CharBuffer.allocate(8);
		cbuff.put('A');
		cbuff.put('B');
		cbuff.flip();
		ByteBuffer bbuff = cnEncoder.encode(cbuff);
		for(int i=0; i<bbuff.capacity(); i++)
			System.out.println(bbuff.get(i));
		System.out.println(cnDecoder.decode(bbuff));
	}
	
	private static final String baseDir = "G://Experiment//";
	public static void main(String[] args) throws IOException {
		String filePath = baseDir + "1.txt";
		//System.out.println(filePath);
		File file = new File(filePath);
		/*if(file.exists())
			appendToFileUsingChannel(new File(filePath));*/
		//getCharsetList();
		charSetTest();
	}
}
