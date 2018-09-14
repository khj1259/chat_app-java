package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//채팅방 들어오고나서
//"0채팅 1방이름 2id 3내용"
public class 채팅메시지 {
	String idtext = "";
	public 채팅메시지(String[] msgArr){
		File f = new File("./",msgArr[1]+".txt");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		
		if (msgArr[0].equals("채팅")) {
			try {
				
				fw = new FileWriter(f, true);
				bw = new BufferedWriter(fw);
				
				for(int i=2;i<msgArr.length;i++){
					
					if(i==2){
						idtext += msgArr[i];
					}else{
						idtext += " "+msgArr[i];
					}
				}
				
				bw.write( idtext+ "\r\n");
				
				bw.flush();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public static void main(String[] args) {
		
	}

}
