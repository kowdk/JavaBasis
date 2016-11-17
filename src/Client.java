import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	// 客户端的主入口
	public static void main(String[] args) throws IOException {
		socketClient();
	}

	// 与服务器连通地址本机，端口8888
	public static void socketClient() throws IOException {
		Socket socket = new Socket("127.0.0.1", 8888);
		if (socket.isConnected()) {
			// 如果连接成功了就开启写和读的进程
			new writer(socket).start();
			// new read(socket).start();
		} else {
			System.out.println("服务器未开启");
		}
	}
}

// 写入到通道的线程
class writer extends Thread {
	private Socket socket;
	private PrintWriter printWriter;
	private Scanner scanner = new Scanner(System.in);
	private String str = null;

	public writer(Socket socket) throws IOException {
		this.socket = socket;
		this.printWriter = new PrintWriter(socket.getOutputStream());
	}

	@Override
	public void run() {
		scanner.useDelimiter("\r\n");
		System.out.print("Please type in：");
		// 产生扫描器的线程阻塞
		str = scanner.next();
		// System.out.println("我说："+str);
		printWriter.write(str + "\r\n");
		printWriter.flush();
	}
}