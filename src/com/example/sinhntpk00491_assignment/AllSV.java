package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AllSV extends Activity {
	ArrayList<SinhVien> ar = null;
	ListView lv;
	Button btnBack;
	Connect_Database database;
	ListAdapterSV adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qly_svien);
		lv=(ListView)findViewById(R.id.LvSV);
		database = new Connect_Database(this);  
        btnBack=(Button) findViewById(R.id.btnBack);
        TextView Title=(TextView) findViewById(R.id.txtTitle);
        
        
        Typeface typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/UTM FLAVOUR.TTF");
        Title.setTypeface(typeFace);
        btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),DsLop.class);
				startActivity(intent);
				
			}
		});

			ar=database.GetAllSinhVien();
	        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(getApplicationContext(),SinhVien.class);	         
					intent.putExtra("MaLop", ar.get(position).getMalop());
	         
					startActivity(intent);
					
				}
				
			});
	}
}
