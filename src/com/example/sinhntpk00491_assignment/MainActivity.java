package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;

import com.example.sinhntpk00491_assignment.ListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
	
public class MainActivity extends Activity{
	EditText UserName,Pass;
	TextView notifi;
	Button Login;
	Connect_Database database;
	BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			if(connectivityManager.getActiveNetworkInfo()!=null){
				Login.setEnabled(true);
				Toast.makeText(getApplicationContext(), "Đã kết nối internet", Toast.LENGTH_LONG).show();
			}else{
				Login.setEnabled(false); 
				Toast.makeText(getApplicationContext(), "Bạn đã mất mạng", Toast.LENGTH_LONG).show();
			}
//			if(connectivityManager.getActiveNetworkInfo().isConnected()==false){
//				Toast.makeText(getApplicationContext(), "Bạn đã mất mạng", Toast.LENGTH_LONG).show();
			
//			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		UserName=(EditText) findViewById(R.id.username);
		Pass=(EditText) findViewById(R.id.repass);
        Login=(Button) findViewById(R.id.btnLogin);
        notifi = (TextView) findViewById(R.id.txtTitle);
        Event();
//		 database=new Connect_Database(getApplicationContext());
//		 
//		 Users user = new Users();
//			user.setID(1);
//			user.setUsename("ntsinh123");
//			user.setPassword("123456");
//			user.setRole("admin");
//			database.InsertUser(user);
//			
//        database.getSinlgeEntry("ntsinh", "123");
//        
//        int a=1;
		
	}
	private void Event() {
		Login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Users = UserName.getText().toString();
				String Password = Pass.getText().toString();
				 
//				if((UserName.equals("")) || (Pass.equals(""))){
//					notifi.setText("Bạn chưa nhập tài khoản");
//					Users user = new Users();
//					user.setID(1);
//					user.setUsename("ntsinh");
//					user.setPassword("12345");
//					user.setRole("admin");
//					database.AddUser(user);
//					
//					
//				}
				if((Users.equals("")) || (Password.equals(""))){
					notifi.setText("Bạn chưa nhập tài khoản");
				}else if((Users.equals("admin")) && (Password.equals("admin"))){
					Intent intent = new Intent(getApplicationContext(),SlideMenu.class);
					startActivity(intent);
				}else{
					notifi.setText("Tài khoản hoặc mật khẩu không đúng");
				}
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(receiver!=null){
			unregisterReceiver(receiver);
		}
		
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}
}
