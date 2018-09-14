package com.bit.chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class 나는누구 {
	//id,pw,이름,폰번호,성별
	String id = "";
	String pw = "";
	String name = "";
	String phone = "";
	String gender = ""; 
//	InputStream in =null;
//	OutputStream out =null;
	Socket sock = null;
	Socket sockc = null;
	
	
	
	public 나는누구(String[] a){
		 id = a[0];
		 pw = a[1];
		 name = a[2];
		 phone = a[3];
		 gender = a[4];
		 System.out.println(name+ "입장");
		
	}
	
	public static void main(String[] args) {

	}

}
