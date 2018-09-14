import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Login extends JFrame implements ActionListener{
	static TextField tfid1, tfpw2;
	Button btnLog1, btnJoin2;
	Font font = new Font("����ü", Font.PLAIN, 20);
	Color color = new Color(160,217,226);//������(���Ѱ�)
    Color color2 = new Color(56,173,193);//�۾���
    ImageIcon icon;
	static String ID = "";
	Dialog logFail;
	OutputStream os = null;
	OutputStreamWriter osw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	Socket sockLog, sock2;
	Frame afterF, regitF;

	public Login(Socket sock) {
		
		super("�α���");
		//
		//////////////////////////////////////
		icon = new ImageIcon(".\\img.png");
		 JPanel background = new JPanel(){
	

			public void paintComponent(Graphics g) {

			g.drawImage(icon.getImage(), 0, 0, null);
			
			setOpaque(false);// �׸��� ǥ���ϰ� ����,�����ϰ� ����
			super.paintComponent(g);
			repaint();

    	  }
		};
		background.setLayout(null);
		//////////////////////////////////////
		sockLog = sock;
		
		Label laid1,laid2;

		laid1=new Label("ID");
		laid1.setBackground(color.WHITE);
		laid1.setFont(font);
	    laid1.setForeground(color2);
		laid2=new Label("PASSWORD");
		laid2.setForeground(color2);
		laid2.setBackground(color.WHITE);
		laid2.setFont(font);
		tfid1=new TextField("",20);
		tfid1.setFont(font);
		tfpw2=new TextField("",20);
		tfpw2.setFont(font);
		tfpw2.setEchoChar('*');

		laid1.setBounds(90, 300, 100, 20);
		laid2.setBounds(90, 353, 120, 20);
		tfid1.setBounds(90, 320, 220, 30);
		tfpw2.setBounds(90, 373, 220, 30);
		//-------------------------------------------------------------------
		//����
		tfpw2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){ 
					try {
						os = sockLog.getOutputStream();
						osw = new OutputStreamWriter(os);
						is = sockLog.getInputStream();
						isr = new InputStreamReader(is);
						br = new BufferedReader(isr);
						//�α��� ���� ������
						String msg = "";
						msg = "�α��� "+ tfid1.getText()+" "+tfpw2.getText();
						osw.write(msg+"\n");
						osw.flush();
						//�α��� ����Ȯ�� �ޱ�
						if((br.readLine()).equals("����")){
							//ID �ο�
							Login.ID = tfid1.getText();
							//���ο� ����2 ���� �ѱ��
							String ip = "192.168.43.90";
							int port = 3001;
							sock2 = new Socket(ip, port);
							afterF = new LogAfter(sockLog, sock2);
							//�α����� ȭ������ �ٲٱ�
							setVisible(false);
							afterF.setVisible(true);
							afterF.revalidate();
						}else{
							logFail.setVisible(true);
						}
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//-----------------------------------------------------------------------
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				try {
					os =sockLog.getOutputStream();
					osw = new OutputStreamWriter(os);
					osw.write("����\n");
					osw.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		background.add(laid1);
		background.add(laid1).setFont(font);
		background.add(laid2);
		background.add(laid2).setFont(font);
		background.add(tfid1);
		background.add(tfpw2);
		

		btnLog1=new Button("LOGIN");
		btnLog1.setFont(font);
		btnLog1.setBackground(color);
		btnLog1.setForeground(color.white);
		btnJoin2=new Button("  JOIN   ");
		btnJoin2.setFont(font);
		btnJoin2.setBackground(color);
		btnJoin2.setForeground(color.white);

		btnLog1.addActionListener(this);
		btnJoin2.addActionListener(this);
		
		btnLog1.setBounds(90, 455, 220, 30);
		btnJoin2.setBounds(90, 490, 220 , 30);
		
		background.add(btnLog1);
		background.add(btnJoin2); 
        add(background);

        Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int x = screen.width/2-400/2;
        int y = screen.height/2-600/2;
        
        
        setBounds(x, y, 400, 600);
        setResizable(false);
        setVisible(true);
		//////////////////////////////////////////////
		//�α��� ���н� ���̾�α�
		logFail = new Dialog(this,"�α��ν���");
		Label lf = new Label("    ���̵� ��й�ȣ�� Ȯ���ϼ���!");
		logFail.add(lf);
		int x2 = this.getX()+(int)this.getWidth()/2-200/2;
		int y2 = this.getY()+(int)this.getHeight()/2-150/2;
		logFail.setBounds(x2, y2, 200, 150);
		logFail.setVisible(false);
		logFail.setResizable(false);
		logFail.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logFail.setVisible(false);
			}
		});
		
		//////////////////////////////////////////////////


	}//������ end

	@Override
	public void actionPerformed(ActionEvent e) {
		String bt = ((Button)e.getSource()).getLabel();
		
		if(bt=="LOGIN"){ 				//�α��� ȭ�� - Login ��ư
			try {
				os = sockLog.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockLog.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				//�α��� ���� ������
				String msg = "";
				msg = "�α��� "+ tfid1.getText()+" "+tfpw2.getText();
				osw.write(msg+"\n");
				osw.flush();
				//�α��� ����Ȯ�� �ޱ�
				if((br.readLine()).equals("����")){
					//ID �ο�
					Login.ID = tfid1.getText();
					//���ο� ����2 ���� �ѱ��
					String ip = "192.168.43.90";
					int port = 3001;
					sock2 = new Socket(ip, port);
					afterF = new LogAfter(sockLog, sock2);
					//�α����� ȭ������ �ٲٱ�
					this.setVisible(false);
					afterF.setVisible(true);
					afterF.revalidate();
				}else{
					logFail.setVisible(true);
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}else if(bt=="  JOIN   "){		//�α��� ȭ�� - Join ��ư
			//ȸ������ ȭ�� ����
			this.setVisible(false);
			regitF = new MemberRegister3(sockLog);
			regitF.setVisible(true);
			regitF.revalidate();
		}
		
	}//�׼Ǹ����� end

	
//	public static void main(String[] args) {
//		
//		new Login();
//	}

	
}
