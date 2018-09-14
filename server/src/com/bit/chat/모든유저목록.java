package com.bit.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.security.auth.UserPrincipal;

public class 모든유저목록 {

	String alluser = "";
	String result = "";

	public 모든유저목록() {
		File f = new File("./회원정보.txt");
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

			// System.out.println(alluser);//회원정보
			// khj 1111 김현정 00000000000 여자
			// mimi 1234 오미래 01077287934 여자
			// juykg 0000 강주영 111111111111 여자
			// song 1111 송승현 111111111 남자

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
			// System.out.println("유저 목록 : "+temp1[i] );
			//
			// }
			// 유저 목록 : khj
			// 유저 목록 : mimi
			// 유저 목록 : juykg
			// 유저 목록 : song

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
//		new 모든유저목록();
	}

}
