package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class SinhVien extends Activity {
	ArrayList<SinhVien> ar = null;
	ListView lv;
	Button btnBack;
	Connect_Database database;
	ListAdapterSV adapter;
	Dialog dialogEdit,dialogAdd,dialogDel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qly_svien);
		lv=(ListView)findViewById(R.id.LvSV);
		database = new Connect_Database(this);  
        btnBack=(Button) findViewById(R.id.btnBack);
        TextView Title=(TextView) findViewById(R.id.txtTitle);
        Intent intent = getIntent();
        String MaSV=intent.getStringExtra("MaLop");
        Title.setText("Danh sách SV lớp "+ MaSV.toString()+" ");
        Typeface typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/UTM FLAVOUR.TTF");
        Title.setTypeface(typeFace);
        btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),DsLop.class);
				startActivity(intent);
				
			}
		});
//			database.AddSinhVien(new SinhVien("pk001 ","Anh ","Nam ","25-10-1995 ","Đồ họa ","pt001"));
//			database.AddSinhVien(new SinhVien("pk002 ","Hoàng","Nam ","13-4-1994 ","Đồ họa ","pt001"));
//			database.AddSinhVien(new SinhVien("pk003 ","Ngọc ","Nữ ","21-6-1995 ","Đồ họa ","pt001"));
//			database.AddSinhVien(new SinhVien("pk004 ","Quỳnh ","Nữ ","26-11-1995 ","Đồ họa ","pt001"));
			ar=database.GetAllSinhVien1(MaSV);
	        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
	        lv.setAdapter(adapter);
        
        
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				PopupMenu popupMenu = new PopupMenu(getApplicationContext(), lv);
                popupMenu.getMenuInflater().inflate(R.menu.popup_sv, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        Toast.makeText(MainActivity.this,"You Clicked : " + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                    	switch(menuItem.getItemId()){
                    	case R.id.mnTwo:
                    		XoaSV(ar.get(position).getID());
            				break;
                    	case R.id.mnThree:
                    		DialogSua(ar.get(position).getID());
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
	
	
	private void DialogSua(final int id) {
		dialogEdit= new Dialog(this,R.style.FullHeightDialog);
//		dialogEdit.requestWindowFeature(dialogEdit.getWindow().FEATURE_NO_TITLE);
		dialogEdit.setContentView(R.layout.dialog_suasv);
		Button btnEdit, btnDong;
		
		final EditText maSV,tenSV,ngaySinh,nganh;
		final RadioButton nam,nu;
		final Spinner maLop;
		btnEdit = (Button) dialogEdit.findViewById(R.id.btnThemsv);
		btnDong = (Button) dialogEdit.findViewById(R.id.btnClose1);
		maSV=(EditText) dialogEdit.findViewById(R.id.txtMaSV);
		tenSV=(EditText) dialogEdit.findViewById(R.id.txtTenSV);
		nam = (RadioButton) dialogEdit.findViewById(R.id.male);
		nu = (RadioButton) dialogEdit.findViewById(R.id.fermale);
		ngaySinh=(EditText) dialogEdit.findViewById(R.id.txtNgaysinh);
		nganh=(EditText) dialogEdit.findViewById(R.id.txtNganh);
		maLop=(Spinner) dialogEdit.findViewById(R.id.txtMaLop);
		
		TextView tv = (TextView) dialogEdit.findViewById(R.id.txtTitle);
		TextView tv1 = (TextView) dialogEdit.findViewById(R.id.textView1);
		TextView tv2 = (TextView) dialogEdit.findViewById(R.id.textView2);
		TextView tv3 = (TextView) dialogEdit.findViewById(R.id.textView3);
		TextView tv4 = (TextView) dialogEdit.findViewById(R.id.textView4);
		TextView tv5 = (TextView) dialogEdit.findViewById(R.id.textView5);
		TextView tv6 = (TextView) dialogEdit.findViewById(R.id.textView6);
		Typeface typeFace=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/UTM KHUCCAMTA.TTF");
		tv.setTypeface(typeFace);
		tv1.setTypeface(typeFace);
		tv2.setTypeface(typeFace);
		tv3.setTypeface(typeFace);
		tv4.setTypeface(typeFace);
		tv5.setTypeface(typeFace);
		tv6.setTypeface(typeFace);
		maSV.setTypeface(typeFace);
		tenSV.setTypeface(typeFace);
//		gioiTinh.setTypeface(typeFace);
		ngaySinh.setTypeface(typeFace);
		nganh.setTypeface(typeFace);
//		maLop.setTypeface(typeFace);
		btnEdit.setTypeface(typeFace);
		btnDong.setTypeface(typeFace);
		
		SinhVien sv = database.GetSinhVien(id);
		maSV.setText(sv.getMaSV());
		tenSV.setText(sv.getTenSV());
//		gioiTinh.setText(sv.getGioitinh());
		if(sv.getGioitinh().equals("Nam")){
			nam.setChecked(true);
		}else{
			nu.setChecked(true);
		}
		ngaySinh.setText(sv.getNgaysinh());
		nganh.setText(sv.getNganhhoc());
//		maLop.setSelection(sv.getma);
		dialogEdit.show();
		btnEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
		        final String MaSV=intent.getStringExtra("MaLop");
				String masv = maSV.getText().toString();
				String tensv = tenSV.getText().toString();
//				String Nam = nam.getText().toString();
//				String Nu = nu.getText().toString();
				String ngaysinh = ngaySinh.getText().toString();
				String nganhhoc = nganh.getText().toString();
//				String malop = maLop.getText().toString();
				if(masv.equals("")){
					maSV.setHint("Chưa nhập mã SV");
				}else if(tensv.equals("")){
					tenSV.setHint("Chưa nhập tên");
//				}else if(nam.isChecked()){
//					gioiTinh.setHint("Chưa nhập giới tính");
				}else if(ngaysinh.equals("")){
					ngaySinh.setHint("Chưa nhập ngày sinh");
				}else if(nganhhoc.equals("")){
					nganh.setHint("Chưa nhập ngành học");
//				}else if(malop.equals("")){
//					maLop.setHint("Chưa nhập mã lớp");
				}else {
				final SinhVien sv=new SinhVien();
				sv.setID(id);
				sv.setMaSV(maSV.getText().toString());
				sv.setTenSV(tenSV.getText().toString());
//				sv.setGioitinh(gioiTinh.getText().toString());
				if(nam.isChecked()){
					sv.setGioitinh("Nam");
				}else{
					sv.setGioitinh("Nữ");
				}
				sv.setNgaysinh(ngaySinh.getText().toString());
				sv.setNganhhoc(nganh.getText().toString());
				sv.setMalop(maLop.getSelectedItem().toString());
				final Connect_Database db=new Connect_Database(SinhVien.this);
				if(!sv.getMalop().equals(MaSV)){
					AlertDialog.Builder builder1 = new AlertDialog.Builder(SinhVien.this);
					builder1.setTitle("Chú ý");
					builder1.setMessage("Việc đổi mã lớp sẽ đưa SV qua một lớp khác");
					builder1.setCancelable(true);

					builder1.setNegativeButton(
					    "Tiếp tục",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					        	db.UpdateSV(sv);
								ar=database.GetAllSinhVien1(MaSV);
						        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
						        lv.setAdapter(adapter);
					        }
					    });

					builder1.setPositiveButton(
					    "Thôi",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					            dialog.cancel();
					        }
					    });

					AlertDialog alert11 = builder1.create();
					alert11.show();
				}else{
					db.UpdateSV(sv);
					ar=database.GetAllSinhVien1(MaSV);
			        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
			        lv.setAdapter(adapter);
				}
				dialogEdit.dismiss();
				}
			}
		});
		btnDong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogEdit.dismiss();
				
			}
		});
	}
	private void DialogThem() {
		dialogAdd= new Dialog(this,R.style.FullHeightDialog);
//		dialogAdd.requestWindowFeature(dialogAdd.getWindow().FEATURE_NO_TITLE);
		dialogAdd.setContentView(R.layout.dialog_themsv);
		Button btnThem, btnClose;
		 final EditText maSV,tenSV,ngaySinh,nganhHoc;
		 final RadioButton nam,nu;
		 final Spinner maLop;
		 
		btnThem = (Button) dialogAdd.findViewById(R.id.btnThemsv);
		btnClose = (Button) dialogAdd.findViewById(R.id.btnClose1);
		maSV = (EditText) dialogAdd.findViewById(R.id.txtMaSV);
		tenSV = (EditText) dialogAdd.findViewById(R.id.txtTenSV);
		nam = (RadioButton) dialogAdd.findViewById(R.id.male);
		nu = (RadioButton) dialogAdd.findViewById(R.id.fermale);
		ngaySinh = (EditText) dialogAdd.findViewById(R.id.txtNgaysinh);
		nganhHoc = (EditText) dialogAdd.findViewById(R.id.txtNganh);
		maLop = (Spinner) dialogAdd.findViewById(R.id.txtMaLop);
		dialogAdd.show();
		btnThem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
		        final String MaSV=intent.getStringExtra("MaLop");
				String masv = maSV.getText().toString();
				String tensv = tenSV.getText().toString();
//				String Nam = nam.getText().toString();
//				String Nu = nu.getText().toString();
				String ngaysinh = ngaySinh.getText().toString();
				String nganhhoc = nganhHoc.getText().toString();
//				String malop = maLop.getText().toString();
				if(masv.equals("")){
					maSV.setHint("Chưa nhập mã SV");
				}else if(tensv.equals("")){
					tenSV.setHint("Chưa nhập tên");
//				}else if(nam.isChecked()){
//					gioiTinh.setHint("Chưa nhập giới tính");
				}else if(ngaysinh.equals("")){
					ngaySinh.setHint("Chưa nhập ngày sinh");
				}else if(nganhhoc.equals("")){
					nganhHoc.setHint("Chưa nhập ngành học");
//				}else if(malop.equals("")){
//					maLop.setHint("Chưa nhập mã lớp");
				}else {
				
					final SinhVien sv=new SinhVien();
					sv.setMaSV(maSV.getText().toString());
					sv.setTenSV(tenSV.getText().toString());
					sv.setGioitinh(nam.getText().toString());
					if(nam.isChecked()){
						sv.setGioitinh("Nam");
					}else{
						sv.setGioitinh("Nữ");
					}
					sv.setNgaysinh(ngaySinh.getText().toString());
					sv.setNganhhoc(nganhHoc.getText().toString());
					sv.setMalop(maLop.getSelectedItem().toString());
					final Connect_Database db=new Connect_Database(SinhVien.this);
					if(!sv.getMalop().equals(MaSV)){
						AlertDialog.Builder builder1 = new AlertDialog.Builder(SinhVien.this);
						builder1.setTitle("Chú ý");
						builder1.setMessage("Việc đổi mã lớp sẽ đưa SV qua một lớp khác,vẫn tiếp tục ?");
						builder1.setCancelable(true);

						builder1.setNegativeButton(
						    "Tiếp tục",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int id) {
						        	db.InsertSV(sv);
									ar=database.GetAllSinhVien1(MaSV);
							        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
							        lv.setAdapter(adapter);
							        dialogAdd.dismiss();
						        }
						    });

						builder1.setPositiveButton(
						    "Quay lại",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int id) {
						            dialog.cancel();
						        }
						    });

						AlertDialog alert11 = builder1.create();
						alert11.show();
					}else{
						db.InsertSV(sv);
						ar=database.GetAllSinhVien1(MaSV);
				        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
				        lv.setAdapter(adapter);
						dialogAdd.dismiss();
					}
					
				}
//				
			}
		});
		btnClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogAdd.dismiss();
				
			}
		});
	}
	private void XoaSV(final int id) {
		Intent intent = getIntent();
        String MaSV=intent.getStringExtra("MaLop");
		SinhVien sinhVien=new SinhVien();
		sinhVien.ID=id;
		Connect_Database db=new Connect_Database(SinhVien.this);
		db.XoaSV(sinhVien);
		ar=database.GetAllSinhVien1(MaSV);
        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
        lv.setAdapter(adapter);
	}
	protected void DelDialog() {
		AlertDialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Xóa SV");
		builder.setMessage("Bạn có muốn xóa tất cả SV ?");
		builder.setIcon(android.R.drawable.ic_delete);
		builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which ) { 
				Intent intent = getIntent();
		        String MaSV=intent.getStringExtra("MaLop");
				SinhVien sinhVien=new SinhVien();
				database.XoaAllSV(sinhVien);
				ar=database.GetAllSinhVien1(MaSV);
		        adapter = new ListAdapterSV(getApplicationContext(), R.layout.dong_sv, ar);
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
        // load file menu của chúng ta ở đây.
        getMenuInflater().inflate(R.menu.menu_sv, menu);
        return true;
    }
 
    // Hàm sử lý sự kiện khi click vào mỗi item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnAddsv) {
        	DialogThem();
        }
        if (id == R.id.mnDell) {
        	DelDialog();
			
        }
 
        return super.onOptionsItemSelected(item);
    }
    
	private int ID;
	private String MaSV;
	private String TenSV;
	private String Gioitinh;
	private String Ngaysinh;
	private String Nganhhoc;
	private String Malop;
	
	public SinhVien() {
		super();
	}

	public SinhVien(String maSV, String tenSV, String gioitinh, String ngaysinh, String nganhhoc, String malop) {
		super();
		MaSV = maSV;
		TenSV = tenSV;
		Gioitinh = gioitinh;
		Ngaysinh = ngaysinh;
		Nganhhoc = nganhhoc;
		Malop = malop;
	}

	public SinhVien(int iD, String maSV, String tenSV, String gioitinh, String ngaysinh, String nganhhoc, String malop) {
		super();
		ID = iD;
		MaSV = maSV;
		TenSV = tenSV;
		Gioitinh = gioitinh;
		Ngaysinh = ngaysinh;
		Nganhhoc = nganhhoc;
		Malop = malop;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
	}

	public String getTenSV() {
		return TenSV;
	}

	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}

	public String getGioitinh() {
		return Gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		Gioitinh = gioitinh;
	}
//	public void setGioiTinh(Boolean gioitinh){
//        if(gioitinh==true){
//        	Gioitinh= "Nam";
//        }else
//        	Gioitinh=  "Nữ";
//
//    }
//	

	public String getNganhhoc() {
		return Nganhhoc;
	}

	public void setNganhhoc(String nganhhoc) {
		Nganhhoc = nganhhoc;
	}

	public String getMalop() {
		return Malop;
	}

	public void setMalop(String malop) {
		Malop = malop;
	}

	public String getNgaysinh() {
		return Ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		Ngaysinh = ngaysinh;
	}


	
	
	
}
