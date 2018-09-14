
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class 테스트용 extends Frame implements ActionListener,MouseListener{

	Button btnConnec1, btnChatt2;
	List listPerson, listRoom;
	Panel p1, p2;
	boolean Pvisible = false;
	boolean Rvisible = false;
	TextField roomTf;
	Dialog mkRoom;
	Button diaBt;
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
	
	public 테스트용() {
		super("로그인 후 창");
		
		Panel p = new Panel();
		listPerson = new List(9, true);
		listPerson.add("3");
		listPerson.add("2");
		listPerson.add("1");
//		listPerson.addMouseListener(this);
		p.add(listPerson);
		diaBt = new Button("버튼");
		diaBt.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
			
		});
		
		p.add(diaBt);
		add(p);
		Toolkit kit  = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int x = screen.width/2-330/2;
        int y = screen.height/2-353/2;
        
        setBounds(x, y, 330, 353);
		setVisible(true);
		
		
		
	}//생성자 end

	public static void main(String[] args) {
		new 테스트용();

	}

	public void actionPerformed(ActionEvent e) {
		
		
		//
	String[] itms = listPerson.getSelectedItems();
	for(int i=0; i<itms.length; i++){
		System.out.println(itms[i]);
	}
		
	}//액션리스너 end

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((List)e.getSource()).getSelectedItem());
			
		
	}//마우스리스너 end

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}