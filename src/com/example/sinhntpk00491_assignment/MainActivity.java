package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;

import com.example.sinhntpk00491_assignment.ListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		UserName=(EditText) findViewById(R.id.username);
		Pass=(EditText) findViewById(R.id.repass);
        Login=(Button) findViewById(R.id.btnLogin);
        notifi = (TextView) findViewById(R.id.textView);
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
					Intent intent = new Intent(getApplicationContext(),DsLop.class);
					startActivity(intent);
				}else{
					notifi.setText("Tài khoản hoặc mật khẩu không đúng");
				}
			}
		});
		
	}

}
