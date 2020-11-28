package com.example.pc_chat;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.AsyncTask;

public class MessageSender extends AsyncTask<String, Void, Void>{
	
	Socket sr;
	DataOutputStream dout;
	PrintWriter pw;

	@Override
	protected Void doInBackground(String... voids) {
		// TODO Auto-generated method stub
		
		String message = voids[0];
		
		try {
			sr=new Socket("192.168.43.81",7800);
			pw= new PrintWriter(sr.getOutputStream());
			pw.write(message);
			pw.flush();
			pw.close();
			sr.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

}
