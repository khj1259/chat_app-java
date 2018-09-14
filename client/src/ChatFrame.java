import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.net.UnknownHostException;
import java.util.ListResourceBundle;


public class ChatFrame extends Frame implements ActionListener, Runnable{
//	static TextField tf=new TextField(8);
//	Button btn = new Button("입력");   

	static TextArea txtA = new TextArea();
	TextField txtF = new TextField(15);
	Button btnTransfer = new Button("전송");
	Button btnExit = new Button("닫기");
	Button btnInvite = new Button("초대");
	Panel p1 = new Panel();
	Color color = new Color(160,217,226);//바탕색(연한거)
	Font font = new Font("굴림체", Font.PLAIN, 12);
	OutputStream os = null;
	OutputStreamWriter osw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	Socket sockChat, sock2;
	List listInv;
	Dialog diaInv;
	String roomName2 ="";
    
    public ChatFrame(Socket sock, Socket sock2) {
    	super("채팅방");
    	
    	sockChat = sock;
    	this.sock2 = sock2;
    	
    	btnTransfer.addActionListener(this);
    	btnExit.addActionListener(this);
    	btnInvite.addActionListener(this);
    	
    	txtA.setBackground(Color.white);
//    	txtA.setFont(font);
    	add("Center", txtA);
    	txtA.setEditable(false);
    	
    	///////////////////////////////////////////
    	////엔터치면 전송
    	txtF.addKeyListener(new KeyAdapter() {	
    		@Override
    		public void keyReleased(KeyEvent e) {
    			if(e.getKeyCode()==10){ //엔터의 키코드 = 10
    				try {
    					os = sockChat.getOutputStream();
    					osw = new OutputStreamWriter(os);
    					//채팅 내용 서버에 보내기
    					String msg = "채팅 "+LogAfter.roomName+" "+Login.ID+" "+(txtF).getText();

    					osw.write(msg+"\n");
    					osw.flush();
    					txtF.setText("");

    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    			}
    		}
		});
    	///////////////////////////////////////////////
    	//X버튼
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		txtA.setText("");
        		LogAfter.roomEnter = false;
        		try {
    				os =sockChat.getOutputStream();
    				osw = new OutputStreamWriter(os);
    				osw.write("채팅방나가기\n");
    				osw.flush();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
        		setVisible(false);
        	}
		});
        
        btnExit.setBackground(Color.white);
        btnTransfer.setBackground(Color.white);
        btnInvite.setBackground(Color.white);
        p1.setBackground(color);
        p1.add(txtF);
        p1.add(btnTransfer);
        p1.add(btnExit);
        p1.add(btnInvite);
        add("South", p1);
        
        Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int x = screen.width/2-350/2;
        int y = screen.height/2-550/2;
        
        setBounds(x, y, 350, 550);
        setVisible(false); 

    }//생성자 end

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = ((Button)e.getSource()).getLabel();
		
		if(name.equals("전송")){ 			//채팅방에서 전송버튼을 누르면
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				//채팅 내용 서버에 보내기
				String msg = "채팅 "+LogAfter.roomName+" "+Login.ID+" "+(txtF).getText();

				osw.write(msg+"\n");
				osw.flush();
				txtF.setText("");

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(name.equals("닫기")){		//닫기 버튼 누르면 채팅방 닫기
			
			this.setVisible(false);
			txtA.setText("");
			LogAfter.roomEnter = false;
			try {
				os =sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				osw.write("채팅방나가기\n");
				osw.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(name.equals("초대")){		//초대 버튼 누르면 다이어로그 띄우기
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockChat.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				//서버에 초대 명령어 보내기
				osw.write("초대\n");
				osw.flush();
				//서버에서 초대할 ID 목록 받기
				String persons = br.readLine();
				String[] ps = persons.split(" ");
				//초대목록 리스트 생성
				listInv = new List(ps.length, true);
				String[] tmp = (LogAfter.roomName).split(",");
				for(int i=0; i<ps.length; i++){
					listInv.add(ps[i]);
					for(int j=0; j<tmp.length; j++){	//기존에 방에 있는 사람은 초대목록에서 삭제
						if(ps[i].equals(tmp[j])){
							listInv.remove(ps[i]);
						}
					}
				}
				
				//초대 다이어로그 생성(띄우기)
				diaInv = new Dialog(this,"초대");
				Panel p1 = new Panel();
				p1.setBackground(color);
				Label la = new Label("초대할 사람을 선택해 주세요");
				Button bt = new Button("확인");
				bt.setBackground(Color.white);
				bt.addActionListener(this);
				p1.add(la);
				p1.add(bt);
				diaInv.add(p1,BorderLayout.NORTH);
				diaInv.add(listInv,BorderLayout.CENTER);
				int x2 = this.getX()+(int)this.getWidth()/2-250/2;
				int y2 = this.getY()+(int)this.getHeight()/2-250/2;
				diaInv.setBounds(x2, y2, 250, 250);
				diaInv.setVisible(true);
				diaInv.setResizable(false);
				btnInvite.enable(false);
				
				diaInv.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						diaInv.setVisible(false);
						btnInvite.enable();
					}
				});
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(name.equals("확인")){			//초대 다이어로그 - 확인버튼
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				//서버에 "초대목록 원래방이름 ID ID..."을 보내기
				String[] selected = listInv.getSelectedItems();
				String inv = "초대목록 "+LogAfter.roomName;
				for(int i=0; i<selected.length; i++){
					inv+=" "+selected[i];
				}
				osw.write(inv+"\n");
				osw.flush();
				
				//방이름 바꾸고 다이어로그 닫기
				int idx = LogAfter.listRoom.getSelectedIndex();
				String newRoom = LogAfter.listRoom.getSelectedItem();
				for(int i=0; i<selected.length; i++){
					newRoom += ","+selected[i]; 
				}
				LogAfter.listRoom.replaceItem(newRoom, idx);
				LogAfter.roomName = newRoom;
				diaInv.setVisible(false);
				
				//채팅창에 초대결과 출력
				String msg = "";
				for(int i=0; i<selected.length; i++){
					msg += selected[i]+"님";
				}
				msg += " 이 초대되었습니다";
				txtA.append(msg+"\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			btnInvite.enable();
			
		}
	}//액션리스너 end

	
	@Override
	public void run() {	//채팅방 스레드
		try {
			is = this.sock2.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			while(true){
				String msg = br.readLine();
				if(msg.equals("나가라")){
					LogAfter.thread.stop();
				}
				ChatFrame.txtA.append(msg+"\n");
//				System.out.println(msg+"채팅방소켓");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//스레드 end
}
