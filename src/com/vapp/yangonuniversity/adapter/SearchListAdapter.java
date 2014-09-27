package com.vapp.yangonuniversity.adapter;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.vapp.yangonuniversity.R;
import com.vapp.yangonuniversity.model.MarkerData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchListAdapter extends ArrayAdapter<MarkerData> {

	private LayoutInflater inflater;
	private ImageView marker_img;
	private TextView txt_marektitle;
	
	public SearchListAdapter(Context context, ArrayList<MarkerData> data_list) {
		super(context, R.layout.search_rowitem, data_list);
		inflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MarkerData data = this.getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.search_rowitem, null);
		}
		
		marker_img = (ImageView) convertView.findViewById(R.id.search_rowimage);
		txt_marektitle = (TextView) convertView.findViewById(R.id.search_rowtitle);
		int resID = data.getIconres(convertView.getContext());
		Picasso.with(convertView.getContext()).load(resID).into(marker_img);
		
		txt_marektitle.setText(data.getTitle());
		return convertView;
	}

}
