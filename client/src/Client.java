import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;


public class Client {
	static Socket sock = null;
	static JFrame logF;
	static Client client;

	public Client() {	//������

		logF = new Login(sock); 
	}

	public static void main(String[] args) {

//		OutputStream os = null;
//		OutputStreamWriter osw = null;
		//	InputStream is = null;
		//	InputStreamReader isr = null;
		//	BufferedReader br = null;
		final String ip = "192.168.43.90";
		final int port = 3000;
		
		try {
			
			sock = new Socket(ip, port);	//�α����ļ���
			
//			os = sock.getOutputStream();
//			osw = new OutputStreamWriter(os);
////			osw.write("�α����ļ���\n");
//			osw.flush();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		client = new Client();

	}//main end
}
