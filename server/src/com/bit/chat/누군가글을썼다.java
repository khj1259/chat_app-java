package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//채팅방 들어오고나서
//"0 채팅/1 방이름/2 id/3 내용"
public class 누군가글을썼다 {
	String result = "";
	String[] who = new String[0];//채팅방에 들어 있는 사람들
	
	public 누군가글을썼다(String a) {
		File f = new File("./채팅방.txt");
		String[] apart = a.split(" ");//"0 채팅/1 방이름/2 id/3 내용"

		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;

		String part = "";
		String sum = "";////방이름 / 아이디들 //파일 전체
		String[] rooms = new String[0];
		String[] user = new String[0];
		String[] temp = new String[0];
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			int cnt = 0;
			while ((part = br.readLine()) != null) {//방이름 
				cnt++;// 몇줄인가
				sum += part + "\r\n";
			}
//			System.out.println(sum);
			rooms = new String[cnt];
			user = new String[cnt];
			String[] sumArr = sum.split("\r\n");//0 : 방이름

			
			
			for (int i = 0; i < cnt; i++) {
				temp = new String[0];
				temp = sumArr[i].split(",");//방에 포함된 유저들
				
				
				System.out.println(temp);
				
				
				
				
				
				
				
				
				rooms[i] = sumArr[i];

				for (int j = 0; j < temp.length; j++) {

					if (j == 0) {
						user[i] = temp[j];
					} else {
						user[i] += " " + temp[j];
					}
//					System.out.println();

				}

			}// for1
			
			
//			for(int i=0;i<user.length;i++){
//				System.out.println(user[i]+"*****");
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

		
		
		
		
		
		for(int i=0;i<user.length;i++){//입력받은 방이름과 같은 방에 있는 사람들.
			if(apart[1].equals(rooms[i])){
				who = user[i].split(" ");
//				System.out.println(user[i]);
				break;
			}
			
		}//for
		
		
//		if(who.length==2){
//			if(who[0].equals(who[1])){
//				who= new String[]{who[0]};
//			}
//		}
		
		

		
		
		
		
		
	}

	public static void main(String[] args) {
//		new 누군가글을썼다("채팅 테스트 khj dddddddd");
	}

}
