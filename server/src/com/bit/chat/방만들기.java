package com.bit.chat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class 방만들기 {
//방만들기 방이름 아이디 아이디
//방만들기 아이디 아이디
	static String sum="";
	static String result = "";
	public 방만들기(String[] msg){
		File f = new File("./채팅방.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
	
		
		
		for(int i=1;i<msg.length;i++){
			if(i==1){
				sum = msg[i];
			}else{
				if(sum.startsWith(msg[i])){
					
				}
				else{
					sum += ","+ msg[i];
				}
				
				
			}
		}
		
		File makef = new File("./"+sum+".txt");//*************************************************
		if(makef.exists()){
			
		}else{
			if(msg[0].equals("방만들기")){
				try {
					makef.createNewFile();
					fw = new FileWriter(f,true);
					bw = new BufferedWriter(fw);
					
					bw.write(sum + "\r\n");//방이름
					bw.flush();
					fw.flush();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
		//**************************************************************************
	}
	public static void main(String[] args) {
//		String[] msg = {"방만들기","방5","khj","ooo"};
//		new 방만들기(msg);
	}

}
