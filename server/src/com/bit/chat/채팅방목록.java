package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ä�ù��� {
	 String result ="";
	public ä�ù���(String a) {//ä�ù� id
		File f = new File("./ä�ù�.txt");//���̵� ���̵� ���̵�
		String[] apart = a.split(" ");// ä�ù� id
		
		
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
				cnt++;// �����ΰ�
				sum += part + "\r\n";
			}
			
			
			boolean b=true;
			rooms = new String[cnt];
			user = new String[cnt];
			String[] sumArr = sum.split("\r\n");//ä�ù��� �ٸ��� �迭�� �ֱ�
			for(int i=0;i<sumArr.length;i++){
				user = sumArr[i].split(",");//�ش� ���� ������ �迭�� ����
				
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
//				temp = sumArr[i].split(" ");//���̵� ���̵� ���̵�
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
