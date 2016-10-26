package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Connect_Database extends SQLiteOpenHelper{

	public static final int Data_Version=1;
	private static final String Data_Name="QuanLySinhVien";
    private static final String  Table_Class="DsLop";
    private static final String Class_ID ="ID";
    private static final String Class_MaLop ="MaLop";
    private static final String Class_TenLop ="TenLop";
    private static final String Table_SV="SinhVien";
    private static final String SV_ID="ID";
    private static final String SV_MaSV="MaSV";
    private static final String SV_TenSV="TenSV";
    private static final String SV_GioiTinh="GioiTinh";
    private static final String SV_NgaySinh="NgaySinh";
    private static final String SV_NganhHoc="NganhHoc";
    private static final String SV_Malop="Malop";
    private static final String Table_User="Users";
    private static final String User_ID="ID";
    private static final String User_Usename="Usename";
    private static final String User_Pass="Password";
    private static final String User_Role="Chucvu";
	public Connect_Database(Context context) {
		super(context, Data_Name, null, Data_Version);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String Create_Table_Class = "CREATE TABLE " + Table_Class + "("
                + Class_ID + " INTEGER PRIMARY KEY, " + Class_MaLop + " TEXT,"
                + Class_TenLop + " TEXT " + ")";
		sqLiteDatabase.execSQL(Create_Table_Class);
		
		String Create_Table_SinhVien="CREATE TABLE "+ Table_SV + "("
				+ SV_ID + " INTEGER PRIMARY KEY, " + SV_MaSV + " TEXT, " + SV_TenSV + " TEXT, " 
				+ SV_GioiTinh + " TEXT, " + SV_NgaySinh + " TEXT, " 
				+ SV_NganhHoc + " TEXT, " + SV_Malop + " TEXT " + ")";
		sqLiteDatabase.execSQL(Create_Table_SinhVien);
		
		String Create_Table_User = "CREATE TABLE " + Table_User + "("
                + User_ID + " INTEGER PRIMARY KEY, " + User_Usename + " TEXT,"
                + User_Pass + " TEXT, " + User_Role + " TEXT" + ")";
		sqLiteDatabase.execSQL(Create_Table_User);
	}
	
	//Sinh Vien
	public void AddSinhVien(SinhVien sinhVien){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SV_MaSV, sinhVien.getMaSV());
		values.put(SV_TenSV, sinhVien.getTenSV());
		values.put(SV_GioiTinh, sinhVien.getGioitinh());
		values.put(SV_NgaySinh, sinhVien.getNgaysinh());
		values.put(SV_NganhHoc, sinhVien.getNganhhoc());
		values.put(SV_Malop, sinhVien.getMalop());
		sqLiteDatabase.insert(Table_SV, null, values);
		sqLiteDatabase.close();
	}
	
	public ArrayList<SinhVien> GetAllSinhVien(){
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		String selectQuery = "SELECT * FROM "+ Table_SV;
		SQLiteDatabase db= this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				SinhVien sinhVien = new SinhVien();
				sinhVien.setID(cursor.getInt(0));
				sinhVien.setMaSV(cursor.getString(cursor.getColumnIndex(SV_MaSV)));
				sinhVien.setTenSV(cursor.getString(cursor.getColumnIndex(SV_TenSV)));
				sinhVien.setGioitinh(cursor.getString(cursor.getColumnIndex(SV_GioiTinh)));
				sinhVien.setNgaysinh(cursor.getString(cursor.getColumnIndex(SV_NgaySinh)));
				sinhVien.setNganhhoc(cursor.getString(cursor.getColumnIndex(SV_NganhHoc)));
				sinhVien.setMalop(cursor.getString(cursor.getColumnIndex(SV_Malop)));
				dssv.add(sinhVien);
			}while(cursor.moveToNext());
		}
		
		return dssv;
	}
	public ArrayList<SinhVien> GetAllSinhVien1(String MaSV){
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		String selectQuery = "SELECT * FROM "+ Table_SV+ " where "+SV_Malop+"='"+MaSV+"'";
		SQLiteDatabase db= this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				SinhVien sinhVien = new SinhVien();
				sinhVien.setID(cursor.getInt(0));
				sinhVien.setMaSV(cursor.getString(cursor.getColumnIndex(SV_MaSV)));
				sinhVien.setTenSV(cursor.getString(cursor.getColumnIndex(SV_TenSV)));
				sinhVien.setGioitinh(cursor.getString(cursor.getColumnIndex(SV_GioiTinh)));
				sinhVien.setNgaysinh(cursor.getString(cursor.getColumnIndex(SV_NgaySinh)));
				sinhVien.setNganhhoc(cursor.getString(cursor.getColumnIndex(SV_NganhHoc)));
				sinhVien.setMalop(cursor.getString(cursor.getColumnIndex(SV_Malop)));
				dssv.add(sinhVien);
			}while(cursor.moveToNext());
		}
		
		return dssv;
	}
	public SinhVien GetSinhVien(int id){
		SinhVien sinhVien = new SinhVien();
		String selectQuery = "SELECT * FROM "+ Table_SV+" where "+Class_ID+"="+id;
		SQLiteDatabase db= this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				
				sinhVien.setID(cursor.getInt(0));
				sinhVien.setMaSV(cursor.getString(cursor.getColumnIndex(SV_MaSV)));
				sinhVien.setTenSV(cursor.getString(cursor.getColumnIndex(SV_TenSV)));
				sinhVien.setGioitinh(cursor.getString(cursor.getColumnIndex(SV_GioiTinh)));
				sinhVien.setNgaysinh(cursor.getString(cursor.getColumnIndex(SV_NgaySinh)));
				sinhVien.setNganhhoc(cursor.getString(cursor.getColumnIndex(SV_NganhHoc)));
				sinhVien.setMalop(cursor.getString(cursor.getColumnIndex(SV_Malop)));
			}while(cursor.moveToNext());
		}
		
		return sinhVien;
	}
	public void UpdateSV(SinhVien sinhVien){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SV_MaSV, sinhVien.getMaSV());
		values.put(SV_TenSV, sinhVien.getTenSV());
		values.put(SV_GioiTinh, sinhVien.getGioitinh());
		values.put(SV_NgaySinh, sinhVien.getNgaysinh());
		values.put(SV_NganhHoc, sinhVien.getNganhhoc());
		values.put(SV_Malop, sinhVien.getMalop());
		String query_update=SV_ID + "=?";
		db.update(Table_SV, values, query_update, new String[]{String.valueOf(sinhVien.getID())});
		
	}
	
	public void InsertSV(SinhVien sinhVien){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SV_MaSV, sinhVien.getMaSV());
		values.put(SV_TenSV, sinhVien.getTenSV());
		values.put(SV_GioiTinh, sinhVien.getGioitinh());
		values.put(SV_NgaySinh, sinhVien.getNgaysinh());
		values.put(SV_NganhHoc, sinhVien.getNganhhoc());
		values.put(SV_Malop, sinhVien.getMalop());
		db.insert(Table_SV, null, values);
	}
	
	public void XoaSV(SinhVien sinhVien){
		SQLiteDatabase db= this.getWritableDatabase();
		String query_delete = SV_ID + "=?";
		db.delete(Table_SV, query_delete, new String[] { String.valueOf(sinhVien.getID()) });
	}	
	public void XoaAllSV(SinhVien sinhVien){
			SQLiteDatabase db= this.getWritableDatabase();
			db.delete(Table_SV, null, null);
	}
	public ArrayList<SinhVien> getAllSinhVienTheoLop(int malop) {
        ArrayList<SinhVien> ClassSinhVien = new ArrayList<SinhVien>();

        String selectQuery = "SELECT  * FROM " + Table_SV+ "  where "+SV_Malop+" ="+ String.valueOf(malop);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
            	SinhVien sinhVienPoly = new SinhVien();
                sinhVienPoly.setMaSV(cursor.getString(cursor.getColumnIndex(SV_MaSV)));
                sinhVienPoly.setTenSV(cursor.getString(cursor.getColumnIndex(SV_TenSV)));
                sinhVienPoly.setGioitinh( cursor.getString(cursor.getColumnIndex(SV_GioiTinh)));
                sinhVienPoly.setNgaysinh(cursor.getString(cursor.getColumnIndex(SV_NgaySinh)));
                sinhVienPoly.setNganhhoc(cursor.getString(cursor.getColumnIndex(SV_NganhHoc)));
                sinhVienPoly.setID(cursor.getInt(cursor.getColumnIndex(SV_Malop)));
                ClassSinhVien.add(sinhVienPoly);
            } while (cursor.moveToNext());
        }
        return ClassSinhVien;
    }
	public int getSinhVienCount(int malop) {
        String countQuery = "SELECT * FROM "+Table_SV+" WHERE "+SV_Malop+"='"+malop+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int dem=cursor.getCount();
        cursor.close();
        return dem;
    }
	//End Sinh Vien
	
	//Lớp
	
	public ArrayList<DsLop> GetAllDsLop(){
		ArrayList<DsLop> Arraydslop = new ArrayList<DsLop>();
		String selectQuery = "SELECT * FROM "+ Table_Class;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				DsLop dsLop = new DsLop();
				dsLop.setID(cursor.getInt(0));
				dsLop.setMaLop(cursor.getString(cursor.getColumnIndex(Class_MaLop)));
				dsLop.setTenLop(cursor.getString(cursor.getColumnIndex(Class_TenLop)));
				Arraydslop.add(dsLop);
			}while(cursor.moveToNext());
		}
		
		return Arraydslop;
	}
	public DsLop GetDsLop(int ID){
		DsLop dsLop = new DsLop();
		String selectQuery = "SELECT * FROM "+ Table_Class+" where "+Class_ID+" = "+ String.valueOf(ID);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				dsLop.setID(cursor.getInt(0));
				dsLop.setMaLop(cursor.getString(cursor.getColumnIndex(Class_MaLop)));
				dsLop.setTenLop(cursor.getString(cursor.getColumnIndex(Class_TenLop)));
			}while(cursor.moveToNext());
		}
		
		return dsLop;
	}
	public void AddDsLop(DsLop dsLop){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Class_MaLop, dsLop.getMaLop());
		values.put(Class_TenLop, dsLop.getTenLop());
		values.put(Class_TenLop, dsLop.getTenLop());
		sqLiteDatabase.insert(Table_Class, null, values);
		sqLiteDatabase.close();
	}
	
	public void UpdateLop(DsLop dsLop){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Class_MaLop, dsLop.getMaLop());
		values.put(Class_TenLop, dsLop.getTenLop());
		String query_update=Class_ID + "=?";
		db.update(Table_Class,values ,query_update, new String[]{String.valueOf(dsLop.getID())});
	}
	public void InsertLop(DsLop dsLop){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Class_MaLop, dsLop.getMaLop());
		values.put(Class_TenLop, dsLop.getTenLop());
		db.insert(Table_Class, null, values);
	}
	
	public void Xoalop(DsLop dsLop){
		SQLiteDatabase db= this.getWritableDatabase();
		String query_delete = Class_ID + "=?";
		db.delete(Table_Class, query_delete, new String[] { String.valueOf(dsLop.getID()) });
	}	
	public void XoaAlllop(DsLop dsLop){
			SQLiteDatabase db= this.getWritableDatabase();
			db.delete(Table_Class, null, null);
	}
	
	
	//End Lớp
	//User
	public ArrayList<Users> GetAllUser(){
		ArrayList<Users> ArrayUser = new ArrayList<Users>();
		String selectQuery = "SELECT * FROM "+ Table_User;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Users user = new Users();
				user.setID(cursor.getInt(0));
				user.setUsename(cursor.getString(cursor.getColumnIndex(User_Usename)));
				user.setPassword(cursor.getString(cursor.getColumnIndex(User_Pass)));
				user.setRole(cursor.getString(cursor.getColumnIndex(User_Role)));
				ArrayUser.add(user);
			}while(cursor.moveToNext());
		}
		
		return ArrayUser;
	}
	public void AddUser(Users user){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User_Usename, user.getUsename());
		values.put(User_Pass, user.getPassword());
		values.put(User_Role, user.getRole());
		long a=sqLiteDatabase.insert(Table_User, null, values);
		sqLiteDatabase.close();
	}
	public void UpdateUser(Users user){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User_Usename, user.getUsename());
		values.put(User_Pass, user.getPassword());
		values.put(User_Role, user.getRole());
		String query_update=User_ID + "=?";
		db.update(Table_User,values ,query_update, new String[]{String.valueOf(user.getID())});
	}
	public void InsertUser(Users user){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User_Usename, user.getUsename());
		values.put(User_Pass, user.getPassword());
		values.put(User_Role, user.getRole());
		db.insert(Table_User, null, values);
		db.close();
	}
	
	public void XoaUser(Users user){
		SQLiteDatabase db= this.getWritableDatabase();
		String query_delete = User_ID + "=?";
		db.delete(Table_User, query_delete, new String[] { String.valueOf(user.getID()) });
	}
	public void XoaAllUser(Users user){
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete(Table_User, null, null);
}
	
	public boolean getSinlgeEntry(String userName,String PassWord){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM "+Table_User+" where Usename='"+userName+"' and Password='"+PassWord+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		int test=cursor.getCount();
		if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        String password= cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return false;                
    }
	//End user
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP IF EXISTS "+ Table_Class);
		db.execSQL("DROP IF EXISTS "+ Table_SV);
		db.execSQL("DROP IF EXISTS "+ Table_User);
		onCreate(db);
		
	}
	
	
	
	
}
