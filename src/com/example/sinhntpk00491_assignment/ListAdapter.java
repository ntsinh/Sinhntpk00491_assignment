package com.example.sinhntpk00491_assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<DsLop> {
	List<DsLop> ar = null;
	public ListAdapter(Context context, int resource, List<DsLop> objects) {
		super(context, resource, objects);
		ar= objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		if(view ==null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view= inflater.inflate(R.layout.dong_lop, null);
		}
		final DsLop p = ar.get(position);
		if(p!=null){
			TextView Malop = (TextView) view.findViewById(R.id.txtMalop);
			TextView Tenlop = (TextView) view.findViewById(R.id.txtTenlop);
			TextView tv1 = (TextView) view.findViewById(R.id.txtTitle);
			TextView tv2 = (TextView) view.findViewById(R.id.textView2);
			Typeface typeFace=Typeface.createFromAsset(getContext().getAssets(),"fonts/UTM KHUCCAMTA.TTF");
			Malop.setTypeface(typeFace);
			Tenlop.setTypeface(typeFace);
			tv1.setTypeface(typeFace);
			tv2.setTypeface(typeFace);
			Malop.setText(p.getMaLop());
			Tenlop.setText(p.getTenLop());
		}
//		DsLop item = new DsLop();
//		item=ar.get(position);
		
		return view;
	}
	public void ReloadList(ArrayList<DsLop> newlist){
		ar=newlist;
	}
}
