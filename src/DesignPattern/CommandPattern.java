package DesignPattern;

/**
 * ����һ���ӿ�
 * */
interface FileSystemReceiver {
	void openFile();
}

/**
 * �ӿ�ʵ���� ����ʵ�ֽӿڷ���
 * */
class UnixFileSystemReceiver implements FileSystemReceiver {

	@Override
	public void openFile() {
		// TODO Auto-generated method stub
		System.out.println("Open file in unix OS");
	}
}

class WindowsFileSystemReceiver implements FileSystemReceiver{

	@Override
	public void openFile() {
		// TODO Auto-generated method stub
		System.out.println("Open file in windows OS");
	}
}

/**
 * �����������Ҫ�����е�ʱ��ȷ��open in win����unix�����Զ���command��
 * */
interface Command {
	//ִ�з���
	void execute();
}

class OpenFileCommand implements Command {

	//���filesystem����
	private FileSystemReceiver fs;
	
	//���캯��,����filesystem
	public OpenFileCommand(FileSystemReceiver fs) {
		// TODO Auto-generated method stub
		this.fs = fs;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.fs.openFile();
	}
}

//
class FileInvoker {
	public Command comm;
	
	public FileInvoker(Command comm) {
		this.comm = comm;
	}
	
	public void execute() {
		this.comm.execute();
	}
}

public class CommandPattern {
	public static void main(String[] args) {
		//windows||unix
		//FileSystemReceiver fs = new WindowsFileSystemReceiver();
		FileSystemReceiver fsU = new UnixFileSystemReceiver();
		//���������ĺ�������Ҫ��һ���ļ�����comm���һ������
		OpenFileCommand command = new OpenFileCommand(fsU);
		//invo�Ǻ��������ߣ�command�Ǻ�������
		FileInvoker invo = new FileInvoker(command);
		invo.execute();
	}
}
