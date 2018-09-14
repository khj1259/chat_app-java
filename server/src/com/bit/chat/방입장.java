package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class 방입장 {//방입장 +방이름
	String msg = "";
	String sum = "";
	String[] result = new String[0];
	String roomName = "";
	public 방입장(String[] stArr){
		
//		for(int i=1;i<stArr.length;i++){
//			if(i==1){
//				roomName += stArr[i];
//			}else{
//				roomName += " "+stArr[i];
//			}
//			
//		}
		
		
		
		File f = new File("./",stArr[1]+".txt");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			boolean b = true;
			while(	(msg = br.readLine()) != null ){
				if(b){
					sum += msg;
					msg="";
					b=false;
					
				}else{
					sum += ("\r\n"+msg);
					msg="";
				}
				
			}
//			System.out.println(sum);
			
			result = sum.split("\r\n");
//			System.out.println(result.length);
			sum = "";
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
//		방입장 a = new 방입장("입장 방1".split(" "));
		
	}

}
