package com.bit.chat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class �游��� {
//�游��� ���̸� ���̵� ���̵�
//�游��� ���̵� ���̵�
	static String sum="";
	static String result = "";
	public �游���(String[] msg){
		File f = new File("./ä�ù�.txt");
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
			if(msg[0].equals("�游���")){
				try {
					makef.createNewFile();
					fw = new FileWriter(f,true);
					bw = new BufferedWriter(fw);
					
					bw.write(sum + "\r\n");//���̸�
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
//		String[] msg = {"�游���","��5","khj","ooo"};
//		new �游���(msg);
	}

}
