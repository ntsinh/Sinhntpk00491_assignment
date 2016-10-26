package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;

public class ListAdapterSV extends ArrayAdapter<SinhVien>{
	List<SinhVien> ar = null;
	public ListAdapterSV(Context context, int resource, List<SinhVien> objects) {
		super(context, resource, objects);
		ar=objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		LayoutInflater inflater = LayoutInflater.from(getContext());
		view=inflater.inflate(R.layout.dong_sv, null);
		SinhVien item= new SinhVien();
		item=ar.get(position);
		TextView tv1 = (TextView) view.findViewById(R.id.txtTitle);
		TextView tv2 = (TextView) view.findViewById(R.id.textView2);
		TextView tv3 = (TextView) view.findViewById(R.id.textView3);
		TextView tv4 = (TextView) view.findViewById(R.id.textView4);
		TextView tv5 = (TextView) view.findViewById(R.id.textView5);
		TextView tv6 = (TextView) view.findViewById(R.id.textView6);
		TextView maSV = (TextView) view.findViewById(R.id.txtMaSV);
		TextView tenSV = (TextView) view.findViewById(R.id.txtTenSV);
		TextView gioiTinh = (TextView) view.findViewById(R.id.txtGioitinh);
		TextView ngaySinh = (TextView) view.findViewById(R.id.txtNgaysinh);
		TextView nganh = (TextView) view.findViewById(R.id.txtNganh);
		TextView maLop = (TextView) view.findViewById(R.id.txtMaLop);
		Typeface typeFace=Typeface.createFromAsset(getContext().getAssets(),"fonts/UTM KHUCCAMTA.TTF");
		tv1.setTypeface(typeFace);
		tv2.setTypeface(typeFace);
		tv3.setTypeface(typeFace);
		tv4.setTypeface(typeFace);
		tv5.setTypeface(typeFace);
		tv6.setTypeface(typeFace);
		maSV.setTypeface(typeFace);
		tenSV.setTypeface(typeFace);
		gioiTinh.setTypeface(typeFace);
		ngaySinh.setTypeface(typeFace);
		nganh.setTypeface(typeFace);
		maLop.setTypeface(typeFace);
		
		maSV.setText(item.getMaSV());
		tenSV.setText(item.getTenSV());
		gioiTinh.setText(item.getGioitinh());
		ngaySinh.setText(item.getNgaysinh());
		nganh.setText(item.getNganhhoc());
		maLop.setText(item.getMalop());
		
		
		return view;
	}
	public void ReloadList(ArrayList<SinhVien> newlist){
		ar=newlist;
	}
}
