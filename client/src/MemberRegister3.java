import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class MemberRegister3 extends Frame implements ActionListener{
    

	Label lblId, lblPwd, lblPwd2, lblName, lblHp, lblGen;
	TextField tfId, tfPwd, tfPwd2, tfName;
	TextField tfHp1, tfHp2, tfHp3;
	Checkbox cbMale,cbFeMale;
	Button btnSubmit, btnCancel, btnId;
//	String MemInfo = ""; 
	Dialog joinSuc, joinFail, joinFail2, idCheck;
	Label idckMsg;
	boolean idChecked = false;
	OutputStream os = null;
	OutputStreamWriter osw = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	Socket sockRegit;
	
    public MemberRegister3(Socket sock) {
         super("회원가입");
        
         sockRegit = sock;
         setLayout(null);

         Panel pb = new Panel(null);
         
         Color color = new Color(160,217,226);
         Font font = new Font("굴림", Font.BOLD, 15);
         Font font2 = new Font("굴림", Font.BOLD, 20);
         
         btnId = new Button("중복체크");
         tfId =new TextField(20);
         tfPwd =new TextField(20);
         tfPwd.setEchoChar('*');
         tfPwd2 = new TextField(20);
         tfPwd2.setEchoChar('*');
         tfName= new TextField(20);
         Label lblhipen1 = new Label("-");
         Label lblhipen2 = new Label("-");    
         tfHp1 = new TextField();
         tfHp2 = new TextField();
         tfHp3 = new TextField();
         btnCancel = new Button("취소"); 
         btnSubmit = new Button("가입완료");
         
         btnId.addActionListener(this);
         btnId.setBackground(color);
         btnId.setForeground(Color.white);
         btnId.setFont(font);
                
         lblId = new Label("아이디"); 
         lblPwd = new Label("패스워드");
         lblPwd2 = new Label("패스워드 확인");
         lblName = new Label("이름");
         lblHp = new Label("전화번호");
         lblGen = new Label("성별");

         lblId.setFont(font);
         lblPwd.setFont(font);
         lblPwd2.setFont(font);
         lblName.setFont(font);
         lblHp.setFont(font);
         lblGen.setFont(font);
         btnSubmit.setFont(font2);
         btnCancel.setFont(font2);
        
         lblId.setBounds(25, 100, 100, 20);
         btnId.setBounds(280, 100, 20, 20);
         
         lblPwd.setBounds(25, 150, 100, 20);
         lblPwd2.setBounds(25, 200, 120, 20);
         lblName.setBounds(25, 250, 100, 20);
         lblHp.setBounds(25, 300, 100 , 20);
         lblGen.setBounds(25, 350, 100, 20);
         
         add(lblId);
         add(btnId); // 중복검사
         
         btnId.setBounds(305, 100, 80, 20);;
                 
         add(lblPwd);
         add(lblPwd2);  // 비밀번호 재검사
         add(lblName);
         add(lblHp); // 핸드폰 번호
         add(lblGen); // 성별

        
        
         
         Panel panGen = new Panel(new FlowLayout(FlowLayout.LEFT));
//         Panel btnId = new Panel(new FlowLayout(FlowLayout.RIGHT));


         CheckboxGroup group = new CheckboxGroup();
         cbMale = new Checkbox("남자",group,false);
         cbFeMale = new Checkbox("여자",group,false);
         
         panGen.add(cbMale);
         panGen.add(cbFeMale);
         panGen.setBounds(145, 350, 100, 30);         
        

        
         btnCancel.addActionListener(this);
         btnSubmit.addActionListener(this);
         btnCancel.setBackground(color);
         btnSubmit.setBackground(color);
         btnCancel.setForeground(color.WHITE);
         btnSubmit.setForeground(color.WHITE);
         
         
                 
         //가입완료 취소버튼
         btnSubmit.setBounds(30, 500,160, 45);
         btnCancel.setBounds(200, 500, 160, 45);
        //--------------------------------------
         tfId.setBounds(145, 100, 150, 20);
         tfPwd.setBounds(145, 150, 150, 20);
         tfPwd2.setBounds(145, 200, 150, 20);
         tfName.setBounds(145, 250, 150, 20);
        
         tfHp1.setBounds(145, 300, 60, 20);
         lblhipen1.setBounds(215, 300, 10, 20);
         tfHp2.setBounds(235, 300, 60, 20);
         lblhipen2.setBounds(300, 300, 10, 20);
         tfHp3.setBounds(315, 300, 60, 20);
                 
                 

//         Panel paButton = new Panel(null);
              
         add(btnSubmit);

         add(btnCancel);




         //         Panel paButton2 = new Panel();

         add(btnId);



   
          
         add(pb);
         add(tfId);
         add(tfPwd);
         add(tfPwd2);
         add(tfName);
         add(tfHp1);
         add(lblhipen1);
         add(tfHp2);
         add(lblhipen2);
         add(tfHp3);
         add(panGen);
//         add(paButton);
//         add(paButton2);
 
         

         setBackground(color.WHITE);
         
         Toolkit kit  = Toolkit.getDefaultToolkit();
         Dimension screen = kit.getScreenSize();
         int x2 = screen.width/2-400/2;
         int y2 = screen.height/2-600/2;
         
         
         setBounds(x2, y2, 400, 600);
         setResizable(false);
         setVisible(false);
    
         
         //---------------------------------------------------------------
         addWindowListener(new WindowAdapter() {


        	 public void windowClosing(WindowEvent e) {
        		 dispose();
        		 try {
 					os =sockRegit.getOutputStream();
 					osw = new OutputStreamWriter(os);
 					osw.write("종료\n");
 					osw.flush();
 				} catch (IOException e1) {
 					e1.printStackTrace();
 				}
        	 }
         });
        
         ///////////////////////////////////////////////////////////////
         //다이어로그
         joinSuc = new Dialog(this, "가입완료");
         Label js = new Label("가입이 완료되었습니다");
         joinSuc.add(js);
         int x = this.getX()+(int)this.getWidth()/2-200/2;
         int y = this.getY()+(int)this.getHeight()/2-150/2;
         joinSuc.setBounds(x, y, 200, 150);
         joinSuc.setVisible(false);
         joinSuc.setResizable(false);
         joinSuc.addWindowListener(new WindowAdapter() {

        	 public void windowClosing(WindowEvent e) {
        		 joinSuc.setVisible(false);
        	 }
         });
         joinFail = new Dialog(this, "가입실패");
         Label jf = new Label("비밀번호가 틀렸습니다");
         joinFail.add(jf);
         joinFail.setBounds(x, y, 200, 150);
         joinFail.setVisible(false);
         joinFail.setResizable(false);
         joinFail.addWindowListener(new WindowAdapter() {
        	 
        	 public void windowClosing(WindowEvent e) {
        		 joinFail.setVisible(false);
        	 }
         });
         joinFail2 = new Dialog(this,"로그인실패");
         Label jf2 = new Label("    ID 중복체크를 해주세요!");
         joinFail2.add(jf2);
         joinFail2.setBounds(x, y, 200, 150);
         joinFail2.setVisible(false);
         joinFail2.setResizable(false);
         joinFail2.addWindowListener(new WindowAdapter() {
        	 
        	 public void windowClosing(WindowEvent e) {
        		 joinFail2.setVisible(false);
        	 }
         });


         idCheck = new Dialog(this, "id 중복체크");
         Panel idP  = new Panel();
         idckMsg = new Label();
         idP.add(idckMsg);
         idCheck.add(idP);
         idCheck.setBounds(x, y, 200, 150);
         idCheck.setVisible(false);
         idCheck.setResizable(false);
         idCheck.addWindowListener(new WindowAdapter() {
        	 
        	 public void windowClosing(WindowEvent e) {
        		 idCheck.setVisible(false);
        	 }
         });
         ////////////////////////////////////////////////////////
         
    }//생성자 end

	
	public void actionPerformed(ActionEvent e) {
		String bt = ((Button)e.getSource()).getLabel();
		
		if (bt=="중복체크") {		//회원가입 화면 - ID 중복체크 버튼
			try {
				os = sockRegit.getOutputStream();
				osw = new OutputStreamWriter(os);
				is = sockRegit.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);

				String msg1 = "";

				msg1 += "중복체크 " + tfId.getText(); 
				
				osw.write(msg1 + "\n");
				osw.flush();
				
				if((br.readLine()).equals("성공")){
					idckMsg.setText("사용가능한 ID입니다");
					idChecked = true;
				}else{
					idckMsg.setText("이미 존재하는 ID입니다");
					tfId.setText("");
				}
				idCheck.setVisible(true);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if (bt == "가입완료") {		//회원가입 화면 - 가입완료 버튼
			try {
				os = sockRegit.getOutputStream();
				osw = new OutputStreamWriter(os);

				if( (idChecked)==false){	//회원가입 - 중복체크X
					joinFail2.setVisible(true);

				}else if((idChecked)==true){	//회원가입 - 중복체크O
					if(((tfPwd.getText()).equals(tfPwd2.getText()))==false){ // - 비밀번호 불일치
						joinFail.setVisible(true);
					}else if(((tfPwd.getText()).equals(tfPwd2.getText()))==true){//중복체크O,비밀번호 일치 : 회원가입성공
						System.out.println("가입완료");
						String msg2 = "";
						String m = "";
						if(cbMale.getState()==true){
							m = "남자";
						}else{m = "여자";}

						msg2 += "회원가입 " + tfId.getText()+" "
								+ tfPwd.getText()+" "
								+ tfName.getText()+" "
								+ tfHp1.getText()
								+ tfHp2.getText()
								+ tfHp3.getText()+" "
								+ m;

						osw.write(msg2 + "\n");
						osw.flush();

						//회원가입창 -> 로그인창
						this.setVisible(false);
						Client.logF.setVisible(true);
						Client.logF.revalidate();
						joinSuc.setVisible(true);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}


		} else if (bt == "취소") {		//회원가입 - 취소버튼
			//회원가입창 초기화
			idChecked = false;
			tfId.setText("");
			tfPwd.setText("");
			tfPwd2.setText("");
			tfName.setText("");
			tfHp1.setText("");
			tfHp2.setText("");
			tfHp3.setText("");
			//회원가입창 -> 로그인창
			this.setVisible(false);
			Client.logF.setVisible(true);
			Client.logF.revalidate();
			
		}
		
	} //액션리스너 end

//	public static void main(String[] args) {
//        new MemberRegister3();
//    }


}
         

    
