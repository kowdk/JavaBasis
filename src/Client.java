import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	// �ͻ��˵������
	public static void main(String[] args) throws IOException {
		socketClient();
	}

	// ���������ͨ��ַ�������˿�8888
	public static void socketClient() throws IOException {
		Socket socket = new Socket("127.0.0.1", 8888);
		if (socket.isConnected()) {
			// ������ӳɹ��˾Ϳ���д�Ͷ��Ľ���
			new writer(socket).start();
			// new read(socket).start();
		} else {
			System.out.println("������δ����");
		}
	}
}

// д�뵽ͨ�����߳�
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
		System.out.print("Please type in��");
		// ����ɨ�������߳�����
		str = scanner.next();
		// System.out.println("��˵��"+str);
		printWriter.write(str + "\r\n");
		printWriter.flush();
	}
}