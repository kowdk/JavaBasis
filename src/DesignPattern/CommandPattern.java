package DesignPattern;

/**
 * 定义一个接口
 * */
interface FileSystemReceiver {
	void openFile();
}

/**
 * 接口实现类 必须实现接口方法
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
 * 你的问题是想要在运行的时候确定open in win还是unix，所以定义command类
 * */
interface Command {
	//执行方法
	void execute();
}

class OpenFileCommand implements Command {

	//组合filesystem进来
	private FileSystemReceiver fs;
	
	//构造函数,传入filesystem
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
		//这个就是你的函数，是要打开一个文件，把comm想成一个函数
		OpenFileCommand command = new OpenFileCommand(fsU);
		//invo是函数调用者，command是函数参数
		FileInvoker invo = new FileInvoker(command);
		invo.execute();
	}
}
