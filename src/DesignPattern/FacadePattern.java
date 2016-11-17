package DesignPattern;

import com.mysql.jdbc.Connection;


/*
 * [�ṹģʽ]
 * ���ģʽ���ֳ�����ģʽ��Ϊһ����ϵͳ�ӿ��ṩһ��ͳһ�Ľӿڣ��Դ˹����ϵͳ֮��ĵ��ù�ϵ��ֻ��Facade��ɫ��ͻ����н�����
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
		
		//HelperFacade�Կͻ��ಢ������ϸ��
		HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
	}

}
