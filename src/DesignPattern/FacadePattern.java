package DesignPattern;

import com.mysql.jdbc.Connection;


/*
 * [结构模式]
 * 外观模式，又称门面模式，为一堆子系统接口提供一个统一的接口，以此规避子系统之间的调用关系，只有Facade角色与客户进行交互。
 * */

class MySqlHelper{
	public static Connection getMysqlConnection(){
		System.out.println("Connect mysql db...");
		return null;
	}
	
	public void generateHtmlReport(String tablename, Connection con){
		System.out.println("Generate html report with mysql...");
	}
	
	public void generatePdfReport(String tablename, Connection con){
		System.out.println("Generate pdf report with mysql...");
	}
}

class OracleHelper{
	public static Connection getOracleConnection(){
		System.out.println("Connect oracle db...");
		return null;
	}
	
	public void generateHtmlReport(String tablename, Connection con){
		System.out.println("Generate html report with oracle...");
	}
	
	public void generatePdfReport(String tablename, Connection con){
		System.out.println("Generate pdf report with oracle...");
	}
}

class HelperFacade{
	public static void generateReport(DBTypes db, ReportTypes report, String tablename)
	{
		Connection con = null;
		switch(db){
		case MYSQL:
			con = MySqlHelper.getMysqlConnection();
			MySqlHelper mh = new MySqlHelper();
			switch(report){
			case HTML:
				mh.generateHtmlReport(tablename, con);
				break;
			case PDF:
				mh.generatePdfReport(tablename, con);
				break;
			}
			break;
		case ORACLE:
			con = OracleHelper.getOracleConnection();
			OracleHelper oh = new OracleHelper();
			switch(report){
			case HTML:
				oh.generateHtmlReport(tablename, con);
				break;
			case PDF:
				oh.generatePdfReport(tablename, con);
				break;
			}
			break;
		}
	}
	
	public static enum DBTypes{
		MYSQL, ORACLE;
	}
	
	public static enum ReportTypes{
		HTML, PDF;
	}
}

public class FacadePattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tableName = "aaa";
		Connection con = MySqlHelper.getMysqlConnection();
		MySqlHelper mysql = new MySqlHelper();
		mysql.generateHtmlReport(tableName, con);
		
		//HelperFacade对客户类并不隐藏细节
		HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
	}

}
