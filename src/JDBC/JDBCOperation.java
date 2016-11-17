package JDBC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 数据库插入优化：
 * 数据库连接默认采用自动提交模式。
 * 使用预备语句PreparedStatement在后台拼装SQL语句。
 * 预备语句相比于基础执行语句，有很多优点：
 * 1. 可以预防SQL注入
 * 2. 参数输入的动态查询
 * 3. 更快更稳定
 * 通过使用addBatch和executeBatch这一对方法实现批量处理数据,
 * 同时还应该关闭自动提交模式conn.setAutoCommit(false)，等提交时使用commit提交
 * rewriteBatchedStatements=true打开mysql的batch处理
 * useServerPrepStmts=false设置本地sql语句拼装
 * */

/*
 * 数据库查询优化：
 * 1.建立索引可以加快数据的检索速度、表之间的连接和减少分组和排序的时间
 * 建立索引的原则：在主键上创建索引，在需要经常搜索的列上创建索引，还有经常
 * 用于连接的列、需要排序的列和使用where子句的列上建立。
 * */

/*
 * 使用行集无需保持与数据库的连接，RowSet。
 * 被缓存的行集的优点是断开数据库连接后仍然可以使用行集。
 * 如果行集上的数据都来自同一张数据表，就可以安全地写回数据了。
 * */
public class JDBCOperation {
	private static final String sexQuery = "select sex,age from user where name = ?";

	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/student";
		String username = "root";
		String password = "";
		Connection conn = null;
		try {
			Class.forName(driver);
			url += "?useServerPrepStmts=false&rewriteBatchedStatements=true";
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void getSexByName(String name) {
		Connection conn = getConnection();
		try {
			PreparedStatement sexSelectStmt = conn.prepareStatement(sexQuery);
			sexSelectStmt.setString(1, name);
			ResultSet rs = sexSelectStmt.executeQuery();
			while (rs.next()) {
				System.out.println(name + " " + rs.getString(1) + " "
						+ rs.getString(2));
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getTableLabel()
	{
		Connection conn = getConnection();
		ResultSet mrs = null;
		try {
			DatabaseMetaData meta = (DatabaseMetaData) conn.getMetaData();
			mrs = meta.getTables(null, null, null, new String[] {"Table"});
			while(mrs.next())
			{
				System.out.println(mrs.getString(3));
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		finally
		{
			try {
				mrs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static final String insertSql = "INSERT INTO e_log_detail (time,sip,dip,sport,dport,protocol) VALUES (?,?,?,?,?,?)";
	private static final String filePath = "G://Experiment//jdbcTest.txt";
	public static void batchInsert() throws IOException {
		Connection conn = getConnection();
		
		BufferedReader br = null;
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(insertSql);

			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			int count = 0;
			boolean debug = true;
			while ((line = br.readLine()) != null) {
				if (!debug)
					System.out.println(line);
				String[] fields = line.split(" ");

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					date = dateFormat.parse(fields[0].replace("T", " "));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Timestamp tt = new Timestamp(date.getTime());
				ps.setTimestamp(1, tt);
				if (!debug) {
					System.out
							.println(fields[1].substring(
									fields[1].indexOf("[") + 1,
									fields[1].indexOf("-")));
					System.out.println(fields[1].substring(fields[1]
							.indexOf(">") + 1));
					System.out.println(fields[3].substring(0,
							fields[3].indexOf("]")));

				}
				ps.setString(2, fields[1].substring(fields[1].indexOf("[") + 1,
						fields[1].indexOf("-")));
				ps.setString(3, fields[1].substring(fields[1].indexOf(">") + 1));
				ps.setString(4, fields[2].split(":")[0]);
				ps.setString(5, fields[2].split(":")[1]);
				ps.setString(6, fields[3].substring(0, fields[3].indexOf("]")));
				ps.addBatch();
				count++;
				if (count % 10000 == 0) {
					ps.executeBatch();
					conn.commit();
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				ps.close();
				conn.close();
				br.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void showResultSet(Statement stat) throws SQLException {
		ResultSet result = stat.getResultSet();
		ResultSetMetaData metaData = (ResultSetMetaData) result.getMetaData();
		int columnCnt = metaData.getColumnCount();

		for (int i = 1; i <= columnCnt; i++) {
			if (i > 1)
				System.out.print(", ");
			System.out.print(metaData.getColumnLabel(i));
		}
		System.out.println();
		while (result.next()) {
			for (int i = 1; i <= columnCnt; i++) {
				if (i > 1)
					System.out.print(", ");
				System.out.print(result.getString(i));
			}
			System.out.println();
		}
		result.close();
	}

	public static Integer getAll() {
		Connection conn = getConnection();
		String sql = "select * from students";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			System.out.println("============================");
			while (rs.next()) {
				for (int i = 1; i <= col; i++) {
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8)) {
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
			System.out.println("============================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int insert(Student student) {
		Connection conn = getConnection();
		int i = 0;
		String sql = "insert into user (Name,Age,Sex) values(?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			pstmt.setString(3, student.getSex());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}