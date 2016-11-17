package JDBC;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ConnectDemo {	
	public static void main(String[] args)
	{
		JDBCOperation.getConnection();
		JDBCOperation.insert(new Student("Achilles", 14, "M"));
		
		/*Connection conn = (Connection) JDBCOperation.getConnection();
		try {
			Statement stat = (Statement) conn.createStatement();
			String cmd = "select * from user";
			boolean hasResult = stat.execute(cmd);
			if(hasResult)
			{
				JDBCOperation.showResultSet(stat);
			}
			JDBCOperation.getSexByName("Achilles");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			while(e != null)
			{
				e.printStackTrace();
				e.getNextException();
			}
		}*/
		
	}
}
