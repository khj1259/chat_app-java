package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ���� {

	boolean suc = true;
	boolean loginBool = false;
	���´��� me;

	public ����(String[] msgArr) {
		File f = new File("./ȸ������.txt");

		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		String sum = "";

		for (int i = 1; i < msgArr.length; i++) {
			if(i==1){
				sum += msgArr[i];
			}else{
				sum +=" "+msgArr[i];
			}
			
		}
		
		if (msgArr[0].equals("�ߺ�üũ")) {
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				
				if(f.exists()){
					String person = "";
					while ((person = br.readLine()) != null) {
						String[] personArr = person.split(" ");
						if (personArr[0].equals(msgArr[1])) {
							suc = false;
						}
					}
					if (suc == false) {

					} else {
						suc = true;
					}
				}else{
					suc=true;
				}
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (msgArr[0].equals("ȸ������")) {

			try {
				fw = new FileWriter(f, true);
				bw = new BufferedWriter(fw);

				bw.write(sum + "\r\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else if (msgArr[0].equals("�α���")) {

			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String person = "";
				
				while ((person = br.readLine()) != null) {
					String[] personArr = person.split(" ");
					if ( (personArr[0].equals(msgArr[1]))
							&& ( personArr[1].equals(msgArr[2]))) {
						me = new ���´���(personArr);
						loginBool = true;
						break;
					}
				}
	

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}// ������
	

	public static void main(String[] args) {

	}

}
