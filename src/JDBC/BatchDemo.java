package JDBC;

import java.io.IOException;

public class BatchDemo {
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		try {
			JDBCOperation.batchInsert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}
}
