package Base;

public enum MsgEnum {  
	HTTP_DATA("http"),
	FTP_DATA("ftp"),    
	MAIL_DATA("mail");
	
	private String dirName;

	private MsgEnum(String dirName) {
		this.dirName = dirName;
	}

	public String getDirName() {
		return this.dirName;
	}

	public static MsgEnum enumOf(String protocol){
		
		switch(protocol){
			case "http":
				return HTTP_DATA;
			case "ftp":
				return FTP_DATA;
			case "mail":
				return MAIL_DATA;
			default:
				return null;
		}
	}
    
} 
