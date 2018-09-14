
import java.awt.*;
import java.awt.event.*;


public class MouseR extends Frame {


    PopupMenu pm;
    MenuItem miOpen, miInvite;

    
    public MouseR() {
 
    	
    super("우클릭 예시");

    
    MyHandler my = new MyHandler();

    
  // Frame에 마우스리스너 부착 ~~~
     this.addMouseListener(my);

     
     pm = new PopupMenu();
     add(pm);
  //팝업 메뉴 반드시 add()해줘야 한다
    

     pm.add(miOpen = new MenuItem("나가기"));
     pm.addSeparator();       // ----- 구분선 입니다                         
     pm.add(miInvite=new MenuItem("초대하기"));       // 일단 기능 보류.....

     
  //팝업 띄우기 
  // pm.show(this,200,200);

     
 } // 생성자 -------------------------------

 class MyHandler implements MouseListener{
 
	 
     public void mouseClicked(MouseEvent e) {
 
   
  // int x = e.getXOnScreen() ;
  // int y = e.getYOnScreen();


     Point p = e.getPoint();
     int x = p.x;
     int y = p.y;
     setTitle("우클릭 예시");        // 마우스 우클릭 시 좌표값이 뜨기에 이걸로 변경 

   
  // if(e.getButton() == 3)
     if(e.getButton() ==MouseEvent.BUTTON3) {        // 마우스 우클릭 이벤트 
   
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

