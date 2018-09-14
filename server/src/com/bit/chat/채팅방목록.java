package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class 채팅방목록 {
	 String result ="";
	public 채팅방목록(String a) {//채팅방 id
		File f = new File("./채팅방.txt");//아이디 아이디 아이디
		String[] apart = a.split(" ");// 채팅방 id
		
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;

		String part = "";
		String sum = "";
		String[] rooms = new String[0];
		String[] user = new String[0];
		String[] temp = new String[0];
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			int cnt = 0;
			while ((part = br.readLine()) != null) {
				cnt++;// 몇줄인가
				sum += part + "\r\n";
			}
			
			
			boolean b=true;
			rooms = new String[cnt];
			user = new String[cnt];
			String[] sumArr = sum.split("\r\n");//채팅방목록 줄마다 배열에 넣기
			for(int i=0;i<sumArr.length;i++){
				user = sumArr[i].split(",");//해당 방의 유저들 배열로 저장
				
				for(int j=0;j<user.length;j++){
					if(apart[1].equals(user[j])){
						
						if(b){
							result += sumArr[i];
							b=false;break;
						}else{
							result += " "+sumArr[i];
							break;
						}
						
						
					}
				}
			}
//			for (int i = 0; i < cnt; i++) {
//				temp = sumArr[i].split(" ");//아이디 아이디 아이디
//				rooms[i] = sumArr[i];
//				user = rooms;
//				
//				for (int j = 1; j < temp.length; j++) {
//
//					if (j == 1) {
//						user[i] = temp[j];
//					} else {
//						user[i] += " " + temp[j];
//					}
					
//					if(temp[j].equals(apart[apart.length-1])){
//						
//						if(i==0){
//							result = rooms[i];
//							
//						}else{
//							result += " "+rooms[i];
//						}
//						break;
//					}
					
					
//
//				}

//			}//for1
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	}

}
