package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ���� extends Thread {

	InputStream is;
	OutputStream os;
	���´��� me;
	Socket so;
	Socket soc;
	// static OutputStream[] osArr = new OutputStream[0];
	// static InputStream[] isArr = new InputStream[0];
	static ���´���[] who = new ���´���[0];// ���� �α��� �����
	// static Socket[] sockArr = new Socket[20];
	static int cnt = 0;// ����������
	// static Socket[] sock =
	ServerSocket servc = null;

	����(Socket s, Socket sc, ServerSocket ss) {
		this.soc = sc;
		this.so = s;
		this.servc = ss;
	}

	public void run() {
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		OutputStreamWriter osw1 = null;
		BufferedWriter bw1 = null;
		InputStreamReader isr1 = null;
		BufferedReader br1 = null;

		try {
			osw = new OutputStreamWriter(so.getOutputStream());
			bw = new BufferedWriter(osw);
			isr = new InputStreamReader(so.getInputStream());
			br = new BufferedReader(isr);
			while (true) {
				System.out.println("��ɾ ���� �غ�");
				String msg = br.readLine();
				System.out.println("���� ��ɾ� : " + msg);// ---------------------------------------------------

				if (msg == null) {

				} else {
					String[] msgArr = msg.split(" ");
					if (msgArr[0].equals("�ߺ�üũ")) {
						���� f = new ����(msgArr);
						if (f.suc == true) {
							bw.write("����" + "\n");
							bw.flush();
						} else {
							bw.write("����" + "\n");
							bw.flush();
						}

					} else if (msgArr[0].equals("ȸ������")) {
						���� f = new ����(msgArr);
					} else if (msgArr[0].equals("�α���")) {
						���� f = new ����(msgArr);

						if (f.loginBool == true) {
							
							bw.write("����" + "\n");
							bw.flush();

							soc = servc.accept();							
							
							
							���´���[] tempWho = new ���´���[����.who.length + 1];
							System.arraycopy(����.who, 0, tempWho, 0,
									����.who.length);
							me = f.me;
							me.sockc = soc;
							me.sock = so;						
							
							
							
							
							tempWho[����.who.length] = me;
							who = tempWho;
							// -----------------------------------------------------------------------------------------
							// InputStream issoc = soc.getInputStream();
							// InputStreamReader isrsoc = new
							// InputStreamReader(issoc);
							// BufferedReader brsoc = new
							// BufferedReader(isrsoc);
//							System.out.println(soc.getLocalSocketAddress());
//							System.out.println(so.getLocalSocketAddress());
							// -----------------------------------------------------------------------------------------

						} else {
							bw.write("����" + "\n");
							bw.flush();
						}
					} else if (msgArr[0].equals("ä��")) {// ä�� ��1 khj ����

						ä�ø޽��� f = new ä�ø޽���(msgArr);// id text
						������������� d = new �������������(msg);// �� �濡 �ش��ϴ� �����
						String store = f.idtext;
						// -----------------------------------------------------------------------------------------
//						System.out.println(store + "++++++++++++");
						for (int i = 0; i < d.who.length; i++) {
							System.out.println("�ش� : " + d.who[i]);
						}
						for (int i = 0; i < who.length; i++) {
							System.out.println("������ : " + who[i].id);
						}
						// -----------------------------------------------------------------------------------------

						for (int i = 0; i < d.who.length; i++) {
//							System.out.println("1..........................");
							for (int j = 0; j < who.length; j++) {
//								System.out.println("2..........................");
								if (who[j].id.equals(d.who[i])) {
//									System.out.println("3..........................");
									System.out.println(who[j].sockc
											.getRemoteSocketAddress());
									osw1 = new OutputStreamWriter(
											who[j].sockc.getOutputStream());
									bw1 = new BufferedWriter(osw1);
									// --------------------------------------------------------------------------------------------
									System.out.println(store + "=============");
									// --------------------------------------------------------------------------------------------

									bw1.write(store + "\n");
									bw1.flush();

									
//									System.out.println(store);
								}

							}
						}
//						System.out.println("+++++++++++++++++++++++++++++++++++++");
//						 bw.write(store + "\n");
						// bw.flush();
					} else if (msgArr[0].equals("������")) {

						// System.out.println(who[0].id);
						String whos = "";
						for (int i = 0; i < who.length; i++) {
							if (i == 0) {
								whos += who[i].id;
							} else {
								whos += " " + who[i].id;
							}

						}

						// System.out.println(whos+"*********************************");
						bw.write(whos + "\n");
						bw.flush();
					} else if (msgArr[0].equals("ä�ù�")) {
						ä�ù��� f = new ä�ù���(msg);
						// System.out.println(f.result);
						bw.write(f.result + "\n");
						bw.flush();
					} else if (msgArr[0].equals("�游���")) {
						�游��� f = new �游���(msgArr);
//						System.out.println("������ : "+f.sum);
						bw.write(f.sum+"\n");//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//						System.out.println("������ : "+f.sum);
						bw.flush();
					} else if (msgArr[0].equals("������")) {
						������ f = new ������(msgArr);
						for (int i = 0; i < f.result.length; i++) {
							bw.write(f.result[i] + "\n");
							bw.flush();
						}
						bw.write("��" + "\n");
						bw.flush();
					} else if (msgArr[0].equals("����")) {// ���� / id
						// �����ڿ��� �����ϱ�
						
						
						if(who.length==0){
							
						}else{
							for (int i = 0; i < who.length; i++) {
								if (who[i].id.equals(me.id)) {
									 ���´���[] temp = new ���´���[who.length - 1];
									 System.arraycopy(who, 0, temp, 0, i);
									 System.arraycopy(who, i+1 , temp, i ,who.length - 1 - i);
									 who = temp;
									break;
								}

							}
							System.out.println("������ ������  : ");
							for(int i=0;i<who.length;i++){
								System.out.println(who[i].id);
							}
							
							System.out.println();
							
							
							bw1.close();//
							osw1.close();
							soc.close();
						}
						
						
			
						bw.close();
						osw.close();
						
						br.close();
						isr.close();
						
//						is.close();//
//						os.close();
						so.close();
						
						
					
						
					} else if (msgArr[0].equals("ä�ù泪����")) {
						 try {
								osw1 = new OutputStreamWriter(soc.getOutputStream());
								 bw1 = new BufferedWriter(osw1);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						bw1.write("������\n");
						bw1.flush();
					}else if(msgArr[0].equals("�α׾ƿ�")){
						for (int i = 0; i < who.length; i++) {
							if (who[i].id.equals(me.id)) {
								 ���´���[] temp = new ���´���[who.length - 1];
								 System.arraycopy(who, 0, temp, 0, i);
								 System.arraycopy(who, i+1 , temp, i ,who.length - 1 - i);
								 who = temp;
								break;
							}

						}
						System.out.println("������ ������  : ");
						for(int i=0;i<who.length;i++){
							System.out.println(who[i].id);
						}
					}else if(msgArr[0].equals("�ʴ�")){
						���������� temp = new ����������();
						bw.write(temp.result+"\n");
						bw.flush();
					}else if(msgArr[0].equals("�ʴ���")){
						����� roomModi = new �����(msgArr);
					}

				}//if0

			}// else1

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����1");// ---------------------------------------------------
			System.out.println();
//			System.out.println(soc.isClosed());
			if(!(so.isClosed())){
				try {
					so.close();System.out.println("1socket close");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(!(soc.isClosed())){
				try {
					soc.close();System.out.println("2socket close");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
//			 e.printStackTrace();
		} catch(java.lang.NullPointerException ee){
//			ee.printStackTrace();
//			System.out.println(so.isClosed());
//			System.out.println(soc.isClosed());
			
			
			System.out.println("�׳� �� ������ ��");
			//�׳� �� ������ ��
		}finally {

		}

	}

	public static void main(String[] args) {
		ServerSocket serv = null;
		ServerSocket servc = null;
		try {
			serv = new ServerSocket(3000);
			servc = new ServerSocket(3001);

			while (true) {
				cnt = 0;

				Socket so = new Socket();
				Socket soc = new Socket();
				so = serv.accept();
				// soc = servc.accept();

				// InputStream is =so.getInputStream();
				// InputStreamReader isr = new InputStreamReader(is);
				// BufferedReader br = new BufferedReader(isr);
				// System.out.println(br.readLine());

				//
				// InputStream isc =soc.getInputStream();
				// InputStreamReader isrc = new InputStreamReader(is);
				// BufferedReader brc = new BufferedReader(isr);
				// System.out.println(brc.readLine());
				//
				// OutputStream os = sockArr[cnt].getOutputStream();
				// InputStream[] tempIn = new InputStream[����.isArr.length + 1];
				// OutputStream[] temp = new OutputStream[����.osArr.length + 1];
				// System.arraycopy(����.osArr, 0, temp, 0, ����.osArr.length);
				// System.arraycopy(����.isArr, 0, temp, 0, ����.isArr.length);
				// temp[����.osArr.length] = os;
				// tempIn[����.isArr.length] = is;
				// ����.osArr = temp;
				// ����.isArr = tempIn;
				���� ser = new ����(so, soc, servc);
				ser.start();
				cnt++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}// try
	}// main

}
