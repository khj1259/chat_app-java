package com.bit.chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ���´��� {
	//id,pw,�̸�,����ȣ,����
	String id = "";
	String pw = "";
	String name = "";
	String phone = "";
	String gender = ""; 
//	InputStream in =null;
//	OutputStream out =null;
	Socket sock = null;
	Socket sockc = null;
	
	
	
	public ���´���(String[] a){
		 id = a[0];
		 pw = a[1];
		 name = a[2];
		 phone = a[3];
		 gender = a[4];
		 System.out.println(name+ "����");
		
	}
	
	public static void main(String[] args) {

	}

}
