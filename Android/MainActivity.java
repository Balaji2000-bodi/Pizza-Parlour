package com.example.pc_chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText msgarea;
	MultiAutoCompleteTextView pane;
	Button transfer;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		msgarea =(EditText)findViewById(R.id.msg);
		transfer=(Button)findViewById(R.id.send);
		pane=(MultiAutoCompleteTextView)findViewById(R.id.msgpane);
		
		Thread myThread=new Thread(new MyServerThread());
		myThread.start();
		
		transfer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				MessageSender msgsender=new MessageSender();
				
				msgsender.execute(msgarea.getText().toString());
				String str=msgarea.getText().toString();
				if (pane.getText().toString().equals("")) {
                    pane.setText("Android: "+str);
                 } 
                else {
                pane.setText(pane.getText() + "\nAndroid: " + str);
            }
				msgarea.setText("");
				
			}
		});
	}
	class MyServerThread implements Runnable{
	
		Socket s;
		ServerSocket st;
		InputStreamReader isr;
		BufferedReader br;
		String message;
		Handler h=new Handler();
		
		public void run(){
			try {
				ServerSocket st=new ServerSocket(7801);
				while (true) {
					s=st.accept();
					isr=new InputStreamReader(s.getInputStream());
					br=new BufferedReader(isr);
					message=br.readLine();
					h.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (pane.getText().toString().equals("")) {
			                    pane.setText("PC: "+message);
			                 } 
			                else {
			                pane.setText(pane.getText() + "\nPC: " + message);
			            }
							
						}
					});
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
