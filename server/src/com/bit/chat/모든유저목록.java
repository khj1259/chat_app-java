package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.security.auth.UserPrincipal;

public class ���������� {

	String alluser = "";
	String result = "";

	public ����������() {
		File f = new File("./ȸ������.txt");
		// FileWriter fw = null;
		// BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String temp = "";

			if (f.exists()) {
				boolean b = true;
				while ((temp = br.readLine()) != null) {

					if (b) {
						alluser += temp;
						b = false;
					} else {
						alluser += "\n" + temp;
					}

				}

			}

			// System.out.println(alluser);//ȸ������
			// khj 1111 ������ 00000000000 ����
			// mimi 1234 ���̷� 01077287934 ����
			// juykg 0000 ���ֿ� 111111111111 ����
			// song 1111 �۽��� 111111111 ����

			String[] user = new String[0];

			user = alluser.split("\n");

			String[] temp1 = new String[user.length];

			for (int i = 0; i < user.length; i++) {
				String[] temp2 = user[i].split(" ");
				temp1[i] = temp2[0];
			}

			for (int i = 0; i < temp1.length; i++) {
				if (i == 0) {
					result += temp1[i];
				} else {
					result += " " + temp1[i];
				}

			}

			// for(int i=0;i<temp1.length;i++){
			// System.out.println("���� ��� : "+temp1[i] );
			//
			// }
			// ���� ��� : khj
			// ���� ��� : mimi
			// ���� ��� : juykg
			// ���� ��� : song

//			System.out.println(result);

			// khj mimi juykg song

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		new ����������();
	}

}
