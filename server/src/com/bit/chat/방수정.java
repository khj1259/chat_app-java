package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class 방수정 {

	public 방수정(String[] msg) {// 초대목록 원래방이름 추가아이디
		File f = new File("./채팅방.txt");

		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String temp = "";
			String temp1 = "";
			if (f.exists()) {
				boolean b = true;
				while ((temp = br.readLine()) != null) {
					if (b) {
						temp1 += temp;
						b = false;
					} else {
						temp1 += " " + temp;
					}

				}

				System.out.println(temp1);

				String[] temp2 = temp1.split(" ");
				int cnt = 0;
				for (int i = 0; i < temp2.length; i++) {
					if (temp2[i].equals(msg[1])) {
						cnt = i;
						for (int j = 2; j < msg.length; j++) {
							temp2[i] += "," + msg[j];
						}
					}
				}

//				System.out.println("결과 : " + temp2[1]);

				fw = new FileWriter(f);
				bw = new BufferedWriter(fw);

				for (int i = 0; i < temp2.length; i++) {
					bw.write(temp2[i] + "\r\n");
					bw.flush();
				}
				
				File modi = new File("./"+msg[1]+".txt");
				
				int cnt1=0;
				while( !(modi.renameTo(new File("./"+temp2[cnt]+".txt"))) ){
					cnt1++;
				}
				System.out.println("얼마나.. : "+cnt1);
				
				
				
				
				
				
			
				
				

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
////
//		 String[] msg = new String[3];
//		 msg[0] = "초대목록";
//		 msg[1] = "khj,mimi,juykg";
//		 msg[2] = "song3";
//		 new 방수정(msg);
	}

}
