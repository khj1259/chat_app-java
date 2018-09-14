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

public class 서버 extends Thread {

	InputStream is;
	OutputStream os;
	나는누구 me;
	Socket so;
	Socket soc;
	// static OutputStream[] osArr = new OutputStream[0];
	// static InputStream[] isArr = new InputStream[0];
	static 나는누구[] who = new 나는누구[0];// 현재 로그인 사람들
	// static Socket[] sockArr = new Socket[20];
	static int cnt = 0;// 서버접속자
	// static Socket[] sock =
	ServerSocket servc = null;

	서버(Socket s, Socket sc, ServerSocket ss) {
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
				System.out.println("명령어를 받을 준비");
				String msg = br.readLine();
				System.out.println("받은 명령어 : " + msg);// ---------------------------------------------------

				if (msg == null) {

				} else {
					String[] msgArr = msg.split(" ");
					if (msgArr[0].equals("중복체크")) {
						파일 f = new 파일(msgArr);
						if (f.suc == true) {
							bw.write("성공" + "\n");
							bw.flush();
						} else {
							bw.write("실패" + "\n");
							bw.flush();
						}

					} else if (msgArr[0].equals("회원가입")) {
						파일 f = new 파일(msgArr);
					} else if (msgArr[0].equals("로그인")) {
						파일 f = new 파일(msgArr);

						if (f.loginBool == true) {
							
							bw.write("성공" + "\n");
							bw.flush();

							soc = servc.accept();							
							
							
							나는누구[] tempWho = new 나는누구[서버.who.length + 1];
							System.arraycopy(서버.who, 0, tempWho, 0,
									서버.who.length);
							me = f.me;
							me.sockc = soc;
							me.sock = so;						
							
							
							
							
							tempWho[서버.who.length] = me;
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
							bw.write("실패" + "\n");
							bw.flush();
						}
					} else if (msgArr[0].equals("채팅")) {// 채팅 방1 khj 내용

						채팅메시지 f = new 채팅메시지(msgArr);// id text
						누군가글을썼다 d = new 누군가글을썼다(msg);// 그 방에 해당하는 사람들
						String store = f.idtext;
						// -----------------------------------------------------------------------------------------
//						System.out.println(store + "++++++++++++");
						for (int i = 0; i < d.who.length; i++) {
							System.out.println("해당 : " + d.who[i]);
						}
						for (int i = 0; i < who.length; i++) {
							System.out.println("접속자 : " + who[i].id);
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
					} else if (msgArr[0].equals("접속자")) {

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
					} else if (msgArr[0].equals("채팅방")) {
						채팅방목록 f = new 채팅방목록(msg);
						// System.out.println(f.result);
						bw.write(f.result + "\n");
						bw.flush();
					} else if (msgArr[0].equals("방만들기")) {
						방만들기 f = new 방만들기(msgArr);
//						System.out.println("쓰기전 : "+f.sum);
						bw.write(f.sum+"\n");//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//						System.out.println("쓰기후 : "+f.sum);
						bw.flush();
					} else if (msgArr[0].equals("방입장")) {
						방입장 f = new 방입장(msgArr);
						for (int i = 0; i < f.result.length; i++) {
							bw.write(f.result[i] + "\n");
							bw.flush();
						}
						bw.write("끝" + "\n");
						bw.flush();
					} else if (msgArr[0].equals("종료")) {// 종료 / id
						// 접속자에서 제거하기
						
						
						if(who.length==0){
							
						}else{
							for (int i = 0; i < who.length; i++) {
								if (who[i].id.equals(me.id)) {
									 나는누구[] temp = new 나는누구[who.length - 1];
									 System.arraycopy(who, 0, temp, 0, i);
									 System.arraycopy(who, i+1 , temp, i ,who.length - 1 - i);
									 who = temp;
									break;
								}

							}
							System.out.println("나간후 접속자  : ");
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
						
						
					
						
					} else if (msgArr[0].equals("채팅방나가기")) {
						 try {
								osw1 = new OutputStreamWriter(soc.getOutputStream());
								 bw1 = new BufferedWriter(osw1);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						bw1.write("나가라\n");
						bw1.flush();
					}else if(msgArr[0].equals("로그아웃")){
						for (int i = 0; i < who.length; i++) {
							if (who[i].id.equals(me.id)) {
								 나는누구[] temp = new 나는누구[who.length - 1];
								 System.arraycopy(who, 0, temp, 0, i);
								 System.arraycopy(who, i+1 , temp, i ,who.length - 1 - i);
								 who = temp;
								break;
							}

						}
						System.out.println("나간후 접속자  : ");
						for(int i=0;i<who.length;i++){
							System.out.println(who[i].id);
						}
					}else if(msgArr[0].equals("초대")){
						모든유저목록 temp = new 모든유저목록();
						bw.write(temp.result+"\n");
						bw.flush();
					}else if(msgArr[0].equals("초대목록")){
						방수정 roomModi = new 방수정(msgArr);
					}

				}//if0

			}// else1

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("에러1");// ---------------------------------------------------
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
			
			
			System.out.println("그냥 방 나갔을 때");
			//그냥 방 나갔을 때
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
				// InputStream[] tempIn = new InputStream[서버.isArr.length + 1];
				// OutputStream[] temp = new OutputStream[서버.osArr.length + 1];
				// System.arraycopy(서버.osArr, 0, temp, 0, 서버.osArr.length);
				// System.arraycopy(서버.isArr, 0, temp, 0, 서버.isArr.length);
				// temp[서버.osArr.length] = os;
				// tempIn[서버.isArr.length] = is;
				// 서버.osArr = temp;
				// 서버.isArr = tempIn;
				서버 ser = new 서버(so, soc, servc);
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
