
import java.awt.*;
import java.awt.event.*;


public class MouseR extends Frame {


    PopupMenu pm;
    MenuItem miOpen, miInvite;

    
    public MouseR() {
 
    	
    super("��Ŭ�� ����");

    
    MyHandler my = new MyHandler();

    
  // Frame�� ���콺������ ���� ~~~
     this.addMouseListener(my);

     
     pm = new PopupMenu();
     add(pm);
  //�˾� �޴� �ݵ�� add()����� �Ѵ�
    

     pm.add(miOpen = new MenuItem("������"));
     pm.addSeparator();       // ----- ���м� �Դϴ�                         
     pm.add(miInvite=new MenuItem("�ʴ��ϱ�"));       // �ϴ� ��� ����.....

     
  //�˾� ���� 
  // pm.show(this,200,200);

     
 } // ������ -------------------------------

 class MyHandler implements MouseListener{
 
	 
     public void mouseClicked(MouseEvent e) {
 
   
  // int x = e.getXOnScreen() ;
  // int y = e.getYOnScreen();


     Point p = e.getPoint();
     int x = p.x;
     int y = p.y;
     setTitle("��Ŭ�� ����");        // ���콺 ��Ŭ�� �� ��ǥ���� �߱⿡ �̰ɷ� ���� 

   
  // if(e.getButton() == 3)
     if(e.getButton() ==MouseEvent.BUTTON3) {        // ���콺 ��Ŭ�� �̺�Ʈ 
   
     pm.show(MouseR.this,x,y);
     
    }
     
  }
     
     
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e){}
  

 } 
 
    public static void main(String[] args) {
       MouseR p = new MouseR();
 
       
       p.setSize(350,300);
       p.setVisible(true);
       p.setResizable(false);
  
  
 }
    
}

