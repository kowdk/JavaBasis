package DesignPattern;

/*
 * [�ṹģʽ]
 * ����ģʽ��Ϊ�ö��󴴽�����ȷ�����������Ƿ���Ȩ��ʹ�øö����൱�������ʿ�����...
 * */

interface CommandExecutor{
	public void runCommand(String cmd) throws Exception;
}

class CommandExecutorImpl implements CommandExecutor{

	@Override
	public void runCommand(String cmd) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("[" + cmd + "] is executed");
	}	
}

class CommandExecutorProxy implements CommandExecutor{

	private boolean isAdmin;
	private CommandExecutor executor;
	public CommandExecutorProxy(String user, String pwd){
		if(user.equalsIgnoreCase("aaa") && pwd.equalsIgnoreCase("111")) isAdmin = true;
		executor = new CommandExecutorImpl();
	}
	
	@Override
	public void runCommand(String cmd) throws Exception {
		// TODO Auto-generated method stub
		if(isAdmin){
			executor.runCommand(cmd);
		}
		else{
			if(cmd.trim().contains("rm"))
				throw new Exception("rm is not allowed for non-admin users");
			else
				executor.runCommand(cmd);
		}
	}
}

public class ProxyPattern {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CommandExecutorProxy executor = new CommandExecutorProxy("aaa", "wrong_pwd");
		try {
			executor.runCommand("ls -lh");
			executor.runCommand("rm -rf /");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
