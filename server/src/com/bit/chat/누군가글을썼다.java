package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//ä�ù� ��������
//"0 ä��/1 ���̸�/2 id/3 ����"
public class ������������� {
	String result = "";
	String[] who = new String[0];//ä�ù濡 ��� �ִ� �����
	
	public �������������(String a) {
		File f = new File("./ä�ù�.txt");
		String[] apart = a.split(" ");//"0 ä��/1 ���̸�/2 id/3 ����"

		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;

		String part = "";
		String sum = "";////���̸� / ���̵�� //���� ��ü
		String[] rooms = new String[0];
		String[] user = new String[0];
		String[] temp = new String[0];
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			int cnt = 0;
			while ((part = br.readLine()) != null) {//���̸� 
				cnt++;// �����ΰ�
				sum += part + "\r\n";
			}
//			System.out.println(sum);
			rooms = new String[cnt];
			user = new String[cnt];
			String[] sumArr = sum.split("\r\n");//0 : ���̸�

			
			
			for (int i = 0; i < cnt; i++) {
				temp = new String[0];
				temp = sumArr[i].split(",");//�濡 ���Ե� ������
				
				
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
		
		
		
		

		
		
		
		
		
		for(int i=0;i<user.length;i++){//�Է¹��� ���̸��� ���� �濡 �ִ� �����.
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
//		new �������������("ä�� �׽�Ʈ khj dddddddd");
	}

}
