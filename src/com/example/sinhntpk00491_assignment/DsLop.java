package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;

import com.example.sinhntpk00491_assignment.ListAdapter;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class DsLop extends Activity {
	ArrayList<DsLop> ar= null;
	ListView lv;
	Button btnBack, btnAdd;
	Connect_Database database;
	ListAdapter adapter;
	Dialog dialogSua,dialogXoa,dialogThem;
	TextView Title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ds_lop);
		lv=(ListView)findViewById(R.id.LvLop);
		database = new Connect_Database(this);
        Title=(TextView) findViewById(R.id.textView);
        Typeface typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/UTM FLAVOUR.TTF");
        Title.setTypeface(typeFace);
		
//	    	database.AddDsLop(new DsLop("Mã 01","Lập Trình"));
//	    	database.AddDsLop(new DsLop("Mã 02","Ứng Dụng"));
//	    	database.AddDsLop(new DsLop("Mã 03","Đồ Họa"));
//	    	database.AddDsLop(new DsLop("Mã 04","Kinh Tế"));
//	    	database.AddDsLop(new DsLop("Mã 06","Mobile")); 
    	
	    	ar=database.GetAllDsLop();
	        adapter = new ListAdapter(getApplicationContext(), R.layout.dong_lop, ar);
	       
	        lv.setAdapter(adapter);
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(getApplicationContext(),SinhVien.class);
					startActivity(intent);
					
				}
				
			});
	        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

	        	@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
					PopupMenu popupMenu = new PopupMenu(getApplicationContext(), lv);
	                popupMenu.getMenuInflater().inflate(R.menu.popup_sv, popupMenu.getMenu());
	                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
	                    @Override
	                    public boolean onMenuItemClick(MenuItem menuItem) {
//	                        Toast.makeText(MainActivity.this,"You Clicked : " + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
	                    	switch(menuItem.getItemId()){
	                    	case R.id.mnOne:
	                    		DialogInsert();
	                    		break;
	                    	case R.id.mnTwo:
	                    		Xoalop(ar.get(position).getID());
	            				break;
	                    	case R.id.mnThree:
	                    		DialogUpdate(ar.get(position).getID());
	                    		break;
	                    	}
	                    	
	                    	return true;
	                    }

	                });
	                popupMenu.show();
					return true;
					
				}
			});
	}
	

	public void DialogUpdate(final int id) {
		dialogSua= new Dialog(this);
		dialogSua.requestWindowFeature(dialogSua.getWindow().FEATURE_NO_TITLE);
		dialogSua.setContentView(R.layout.dialog_sualop);
		Button btnEdit, btnDong;
		 final EditText maLop,tenLop;
		btnEdit = (Button) dialogSua.findViewById(R.id.btnEdit);
		btnDong = (Button) dialogSua.findViewById(R.id.btnDong);
		maLop = (EditText) dialogSua.findViewById(R.id.txtmaLop);
		tenLop = (EditText) dialogSua.findViewById(R.id.txtTenLop);
		TextView tv=(TextView) dialogSua.findViewById(R.id.textView1);
		Typeface typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/UTM KHUCCAMTA.TTF");
		maLop.setTypeface(typeFace);
		tenLop.setTypeface(typeFace);
		tv.setTypeface(typeFace);
		DsLop lop = database.GetDsLop(id);
		maLop.setText(lop.getMaLop());
		tenLop.setText(lop.getTenLop());
		dialogSua.show();
		btnEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DsLop lop=new DsLop();
				lop.setID(id);
				lop.setMaLop(maLop.getText().toString());
				lop.setTenLop(tenLop.getText().toString());
				Connect_Database db=new Connect_Database(getApplicationContext());
				db.UpdateLop(lop);
				ar=db.GetAllDsLop();
				ar=database.GetAllDsLop();
		        adapter = new ListAdapter(getApplicationContext(), R.layout.dong_lop, ar);
		        lv.setAdapter(adapter);
				dialogSua.dismiss();
				
			}
		});
		btnDong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogSua.dismiss();
				
			}
		});

	}
	
	private void DialogInsert() {
		dialogThem= new Dialog(this);
		dialogThem.requestWindowFeature(dialogThem.getWindow().FEATURE_NO_TITLE);
		dialogThem.setContentView(R.layout.dialog_themlop);
		Button btnAdd, btnDong;
		 final EditText MaLop,TenLop;
		btnAdd = (Button) dialogThem.findViewById(R.id.btnAdd);
		btnDong = (Button) dialogThem.findViewById(R.id.btnDong);
		MaLop = (EditText) dialogThem.findViewById(R.id.maLop);
		TenLop = (EditText) dialogThem.findViewById(R.id.tenLop);
		dialogThem.show();
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String malop = MaLop.getText().toString();
				String tenlop = TenLop.getText().toString();
				if( malop.equals("") ){
					MaLop.setHint("Mã lớp còn trống");
				}else if(tenlop.equals("")){
					TenLop.setHint("Tên lớp còn trống");
				}else{
					DsLop lop=new DsLop();
					lop.setMaLop(MaLop.getText().toString());
					lop.setTenLop(TenLop.getText().toString());
					Connect_Database db=new Connect_Database(getApplicationContext());
					db.InsertLop(lop);
					ar=db.GetAllDsLop();
					ar=database.GetAllDsLop();
			        adapter = new ListAdapter(getApplicationContext(), R.layout.dong_lop, ar);
			        lv.setAdapter(adapter);
					dialogThem.dismiss();
				}
			}
		});
		btnDong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogThem.dismiss();
				
			}
		});
	}
	private void Xoalop(final int id){
		DsLop lop=new DsLop();
		lop.setID(id);
		Connect_Database db=new Connect_Database(getApplicationContext());
		db.Xoalop(lop);
		ar=database.GetAllDsLop();
        adapter = new ListAdapter(getApplicationContext(), R.layout.dong_lop, ar);
        lv.setAdapter(adapter);
	}
	protected void DelDialog() {
		AlertDialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Xóa lớp");
		builder.setMessage("Bạn có muốn xóa tất cả các lớp ?");
		builder.setIcon(android.R.drawable.ic_delete);
		builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which ) { 
				DsLop dslop=new DsLop();
				database.XoaAlllop(dslop);
				ar=database.GetAllDsLop();
		        adapter = new ListAdapter(getApplicationContext(), R.layout.dong_lop, ar);
		        lv.setAdapter(adapter);
			dialog.dismiss();
			}
			});
		builder.setNeutralButton("Hủy", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			}
			});
			dialog = builder.create();
			dialog.show();
			return;
			
			};
			@Override
		    public boolean onCreateOptionsMenu(Menu menu) {
		        getMenuInflater().inflate(R.menu.menu_lop, menu);
		        return true;
		    }
		 
		    @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        int id = item.getItemId();
		        if (id == R.id.mnAdd) {
		        	DialogInsert();
		        }
		        if (id == R.id.mnDell) {
		        	DelDialog();
					
		        }
		 
		        return super.onOptionsItemSelected(item);
		    }
	
	
	
	
	public int ID;
	private String MaLop;
	private String TenLop;
	
	public DsLop() {
		super();
	}

	public DsLop(String maLop, String tenLop) {
		super();
		MaLop = maLop;
		TenLop = tenLop;
	}

	public DsLop(int iD, String maLop, String tenLop) {
		super();
		ID = iD;
		MaLop = maLop;
		TenLop = tenLop;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
	}

	public String getTenLop() {
		return TenLop;
	}

	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	
	
	
}
