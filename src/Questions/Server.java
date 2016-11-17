package Questions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	
	// �����
	public static void main(String[] args) throws IOException {
		scoketServer();
	}

	// ������tcp8888�����˿�
	private static void scoketServer() throws IOException {
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			// δ��ͨǰ�߳���������ͨ����һ��socketͨ���̺߳��������8888�˿�
			Socket socket = server.accept();
			new SocketThread(socket).start();
		}
	}
}

// һ���������˿��м�������ͷ���ͨ���߳�
class SocketThread extends Thread {
	private Socket socket = null;
	private BufferedReader bufferedReader = null;
	private String context = "";
	private long span = 0;
	private int threadNo = 0;

	public SocketThread(Socket socket) throws IOException {
		this.socket = socket;
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
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
