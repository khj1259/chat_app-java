import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LogAfter extends Frame implements ActionListener, MouseListener{

	Button btnConnec1, btnChatt2, btnOut3;
	static List listPerson, listRoom;
	Panel  p1, p2;
	boolean Pvisible = false;
	boolean Rvisible = false;
	TextField roomTf;
	Dialog mkRoom;
	static String roomName="";
	String clickedID="";
	OutputStream os = null;
	OutputStreamWriter osw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	Socket sockAfter, sock2;
	static Frame chatF;
	static Thread thread;
	Font font = new Font("����ü", Font.LAYOUT_LEFT_TO_RIGHT, 20);
	Font font2 = new Font("����ü", Font.BOLD, 17);
	static boolean roomEnter = false;
	
	public LogAfter(Socket sock, Socket sock2) {
		super("SKY TALK ("+Login.ID+")");
		
		sockAfter = sock;
		this.sock2 = sock2;
//		GridLayout gl;

		Color color = new Color(160,217,226);//������(���Ѱ�)
		
		p1 = new Panel(null);
		p1.setBackground(color);
//		p2 = new Panel(null);
		
		btnConnec1 = new Button("������");
		btnChatt2 = new Button("ä�ù�");
		btnOut3 = new Button("�α׾ƿ�");
		
		btnConnec1.setBackground(Color.white);
		btnChatt2.setBackground(Color.white);
		btnOut3.setBackground(Color.white);
		
		btnConnec1.setFont(font2);
		btnChatt2.setFont(font2);
		btnOut3.setFont(font2);

		btnConnec1.setBounds(35, 40, 100, 40);
		btnChatt2.setBounds(145, 40, 100, 40);
		btnOut3.setBounds(255, 40, 100, 40);
		
//		btnConnec1.setVisible(true);
//		btnChatt2.setVisible(true);
//		btnOut3.setVisible(true);
		
		btnConnec1.addActionListener(this);
		btnChatt2.addActionListener(this);
		btnOut3.addActionListener(this);
		
//		btnConnec1.setBounds(70, 40, 70, 30);
//		btnConnec1.setVisible(true);
//		btnChatt2.setBounds(185, 40, 70, 30);
		

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					os =sockAfter.getOutputStream();
					osw = new OutputStreamWriter(os);
					osw.write("����\n");
					osw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		p1.add(btnConnec1);
		p1.add(btnChatt2);
		p1.add(btnOut3);
		
		add(p1);
		
		Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int x = screen.width/2-400/2;
        int y = screen.height/2-600/2;
        
        setBounds(x, y, 400, 600);
		setVisible(false);
		
		
	}//������ end

//	public static void main(String[] args) {
//		new LogAfter();
//
//	}
//
	public void actionPerformed(ActionEvent e) {
		
		String bt = ((Button) e.getSource()).getLabel();

		if(bt=="������"){			//�α����� - �����ڹ�ư
			try {
				os = sockAfter.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockAfter.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				osw.write("������\n");
				osw.flush();
				//������ ����Ʈ �ޱ�
				String persons = br.readLine();
//				System.out.println(persons);
				String[] ps = persons.split(" ");
				
				listPerson = new List(ps.length, false);
				for (int i = 0; i < ps.length; i++) {
					listPerson.add(ps[i]);
				}
				if(Rvisible==true){
					listRoom.setVisible(false);
					Rvisible = false;
				}
				btnChatt2.enable();
				Pvisible = true;
				int w = p1.getWidth()-70;
				int h = p1.getHeight()-110;
				listPerson.setFont(font);
				listPerson.setLocation(35, 90);
				listPerson.setSize(w, h);
				listPerson.setVisible(true);
				p1.add(listPerson);
				btnConnec1.enable(false);
				
				listPerson.addMouseListener(this);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (bt=="ä�ù�") {			//�α����� - ä�ù��ư
			try {
				os = sockAfter.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockAfter.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				
				osw.write("ä�ù� "+Login.ID+"\n");
				osw.flush();
				//ä�ù� ����Ʈ �ޱ�
				String rooms = br.readLine();
				if(rooms.equals("")){
					//ä�ù��� ����
					String[] rs = rooms.split(" ");

					listRoom = new List(rs.length, false);
					if(Pvisible == true){
						listPerson.setVisible(false);
						Pvisible = false;
					}
					btnConnec1.enable();
					Rvisible = true;
					btnChatt2.enable(false);
				}else{
					//ä�ù��� ����
					String[] rs = rooms.split(" ");
					
					listRoom = new List(rs.length, false);
					for (int i = 0; i < rs.length; i++) {
						listRoom.add(rs[i]);
					}
					if(Pvisible == true){
						listPerson.setVisible(false);
						Pvisible = false;
					}
					btnConnec1.enable();
					Rvisible = true;
					int w = p1.getWidth()-70;
					int h = p1.getHeight()-110;
					listRoom.setFont(font);
					listRoom.setLocation(35, 90);
					listRoom.setSize(w, h);
					listRoom.setVisible(true);
					p1.add(listRoom);
					btnChatt2.enable(false);
					
					listRoom.addMouseListener(this);
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}else if(bt=="�α׾ƿ�"){		//�α����� - �α׾ƿ���ư
			try {
				os = sockAfter.getOutputStream();
				osw = new OutputStreamWriter(os);
				//������ �޼��� ����
				osw.write("�α׾ƿ�\n");
				osw.flush();
				//���� �ʱ�ȭ
				
				//�α��� ȭ������ ���ư���
				Login.tfid1.setText("");
				Login.tfpw2.setText("");
				this.setVisible(false);
				Client.logF.setVisible(true);
				Client.logF.revalidate();
				//ä�ù��� ���������� ä�ù� �ݱ�
				if(roomEnter==true){
					chatF.setVisible(false);
					roomEnter = false;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
		}
		
	}//�׼Ǹ����� end

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){			
			if(Pvisible==true){				//����ȸ�� ����Ŭ��
//				mkRoom.setVisible(true);
				clickedID = ((List)e.getSource()).getSelectedItem();
				try {
					os = sockAfter.getOutputStream();
					osw = new OutputStreamWriter(os);
					is = sockAfter.getInputStream();
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					//�游��� 
					String msg = "�游���"+" "+clickedID+" "+Login.ID;
					osw.write(msg+"\n");
					osw.flush();
					
					//�������� ���̸� �ޱ�
					roomName = br.readLine();
//					-----------------------------------------------------
					//ä�ù������� ����(���� �ѱ��)
					chatF = new ChatFrame(sockAfter,sock2);
					//ä�ù潺���� ����
					thread = new Thread((Runnable) chatF);
					
					//������ ������ ������
					String msg2 = "������ "+roomName;
					osw.write(msg2+"\n");
					osw.flush();
					//ä��â����
					chatF.setVisible(true);
					//����ä�ó��� �޾ƿ���
					while(true){
						String chats = br.readLine();
//						System.out.println(chats);
						if(chats.equals("��")){break;}
//						String[] chat = chats.split(" ");
						ChatFrame.txtA.append(chats+"\n");
					}
					//ä�ù潺���� ��ŸƮ	
					thread.start();
					roomEnter = true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			
			}else if(Rvisible==true){		//ä�ù� ����Ŭ��
				//���� �ѱ�� - �ι�° ����
				chatF = new ChatFrame(sockAfter,sock2);
				//ä�ù潺���� ����
				thread = new Thread((Runnable) chatF);
				try {
					os = sockAfter.getOutputStream();
					osw = new OutputStreamWriter(os);
					is = sockAfter.getInputStream();
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					//������ ������ ������
					roomName = ((List)e.getSource()).getSelectedItem();
					String msg = "������ "+roomName;
					osw.write(msg+"\n");
					osw.flush();
					//ä��â����
					chatF.setVisible(true);
					//����ä�ó��� �޾ƿ���
					while(true){
						String chats = br.readLine();
						if(chats.equals("��")){break;}
						ChatFrame.txtA.append(chats+"\n");
					}
					//ä�ù潺���� ��ŸƮ	
					thread.start();
					roomEnter = true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}	
			
		
	}//���콺������ end

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}