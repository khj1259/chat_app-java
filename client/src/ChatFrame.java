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
//	Button btn = new Button("�Է�");   

	static TextArea txtA = new TextArea();
	TextField txtF = new TextField(15);
	Button btnTransfer = new Button("����");
	Button btnExit = new Button("�ݱ�");
	Button btnInvite = new Button("�ʴ�");
	Panel p1 = new Panel();
	Color color = new Color(160,217,226);//������(���Ѱ�)
	Font font = new Font("����ü", Font.PLAIN, 12);
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
    	super("ä�ù�");
    	
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
    	////����ġ�� ����
    	txtF.addKeyListener(new KeyAdapter() {	
    		@Override
    		public void keyReleased(KeyEvent e) {
    			if(e.getKeyCode()==10){ //������ Ű�ڵ� = 10
    				try {
    					os = sockChat.getOutputStream();
    					osw = new OutputStreamWriter(os);
    					//ä�� ���� ������ ������
    					String msg = "ä�� "+LogAfter.roomName+" "+Login.ID+" "+(txtF).getText();

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
    	//X��ư
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		txtA.setText("");
        		LogAfter.roomEnter = false;
        		try {
    				os =sockChat.getOutputStream();
    				osw = new OutputStreamWriter(os);
    				osw.write("ä�ù泪����\n");
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

    }//������ end

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name = ((Button)e.getSource()).getLabel();
		
		if(name.equals("����")){ 			//ä�ù濡�� ���۹�ư�� ������
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				//ä�� ���� ������ ������
				String msg = "ä�� "+LogAfter.roomName+" "+Login.ID+" "+(txtF).getText();

				osw.write(msg+"\n");
				osw.flush();
				txtF.setText("");

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(name.equals("�ݱ�")){		//�ݱ� ��ư ������ ä�ù� �ݱ�
			
			this.setVisible(false);
			txtA.setText("");
			LogAfter.roomEnter = false;
			try {
				os =sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				osw.write("ä�ù泪����\n");
				osw.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(name.equals("�ʴ�")){		//�ʴ� ��ư ������ ���̾�α� ����
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockChat.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				//������ �ʴ� ��ɾ� ������
				osw.write("�ʴ�\n");
				osw.flush();
				//�������� �ʴ��� ID ��� �ޱ�
				String persons = br.readLine();
				String[] ps = persons.split(" ");
				//�ʴ��� ����Ʈ ����
				listInv = new List(ps.length, true);
				String[] tmp = (LogAfter.roomName).split(",");
				for(int i=0; i<ps.length; i++){
					listInv.add(ps[i]);
					for(int j=0; j<tmp.length; j++){	//������ �濡 �ִ� ����� �ʴ��Ͽ��� ����
						if(ps[i].equals(tmp[j])){
							listInv.remove(ps[i]);
						}
					}
				}
				
				//�ʴ� ���̾�α� ����(����)
				diaInv = new Dialog(this,"�ʴ�");
				Panel p1 = new Panel();
				p1.setBackground(color);
				Label la = new Label("�ʴ��� ����� ������ �ּ���");
				Button bt = new Button("Ȯ��");
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
		}else if(name.equals("Ȯ��")){			//�ʴ� ���̾�α� - Ȯ�ι�ư
			try {
				os = sockChat.getOutputStream();
				osw = new OutputStreamWriter(os);
				//������ "�ʴ��� �������̸� ID ID..."�� ������
				String[] selected = listInv.getSelectedItems();
				String inv = "�ʴ��� "+LogAfter.roomName;
				for(int i=0; i<selected.length; i++){
					inv+=" "+selected[i];
				}
				osw.write(inv+"\n");
				osw.flush();
				
				//���̸� �ٲٰ� ���̾�α� �ݱ�
				int idx = LogAfter.listRoom.getSelectedIndex();
				String newRoom = LogAfter.listRoom.getSelectedItem();
				for(int i=0; i<selected.length; i++){
					newRoom += ","+selected[i]; 
				}
				LogAfter.listRoom.replaceItem(newRoom, idx);
				LogAfter.roomName = newRoom;
				diaInv.setVisible(false);
				
				//ä��â�� �ʴ��� ���
				String msg = "";
				for(int i=0; i<selected.length; i++){
					msg += selected[i]+"��";
				}
				msg += " �� �ʴ�Ǿ����ϴ�";
				txtA.append(msg+"\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			btnInvite.enable();
			
		}
	}//�׼Ǹ����� end

	
	@Override
	public void run() {	//ä�ù� ������
		try {
			is = this.sock2.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			while(true){
				String msg = br.readLine();
				if(msg.equals("������")){
					LogAfter.thread.stop();
				}
				ChatFrame.txtA.append(msg+"\n");
//				System.out.println(msg+"ä�ù����");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//������ end
}
