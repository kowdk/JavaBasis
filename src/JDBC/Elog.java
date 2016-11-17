package JDBC;

import java.sql.Date;

class Elog {
	private long Id;
	private String sip;
	private String dip;
	private String sport;
	private String dport;
	private Date time;
	
	public Elog(String sip, String dip, String sport, String dport, Date time){
		this.Id = 0;
		this.sip = sip;
		this.dip = dip;
		this.sport = sport;
		this.dport = dport;
		this.time = time;
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public String getDip() {
		return dip;
	}
	public void setDip(String dip) {
		this.dip = dip;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getDport() {
		return dport;
	}
	public void setDport(String dport) {
		this.dport = dport;
	}
	
	
}
