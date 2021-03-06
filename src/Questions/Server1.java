package Questions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server1 {

	private int threadNum = 0;
	private int threadMax = Integer.MAX_VALUE;

	// 主入口
	public static void main(String[] args) throws IOException {
		new Server1().scoketServer();
	}

	// 开启的tcp8888监听端口
	private void scoketServer() throws IOException {
		ServerSocket server = new ServerSocket(8888);
		// 未连通前线程阻塞，连通后开启一个socket通道线程后继续监听8888端口
		Socket socket = server.accept();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		String line = null;
		line = bufferedReader.readLine();
		this.threadMax = Integer.parseInt(line.split(" ")[1]);
		
		while(true) {
			if(threadNum <= threadMax) {
				new SocketThread1(socket).start();
			} else {
				System.out.println("Already the maximum threads, so reject...");
				break;
			}
		}
	}
}

// 一个服务器端口中监听多个客服端通道线程
class SocketThread1 extends Thread {
	// private static List<PrintWriter> list =new ArrayList<PrintWriter>();

	private Socket socket = null;
	private BufferedReader bufferedReader = null;
	private String context = "";
	private long span = 0;
	private int threadNo = 0;

	public SocketThread1(Socket socket) throws IOException {
		this.socket = socket;
		this.bufferedReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}

	@Override
	public void run() {
		String line = null;
		String addr = socket.getInetAddress().getHostAddress();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			line = bufferedReader.readLine();
			String[] strs = line.split(" ");
			this.threadNo = Integer.parseInt(strs[1]);
			this.context = strs[2];
			this.span = Long.parseLong(strs[3]);
			System.out.println(threadNo + ":[" + addr + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			String now = df.format(new Date());
			System.out.println(now + " " + threadNo + " " + context);
			try {
				Thread.sleep(1000 * span);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
