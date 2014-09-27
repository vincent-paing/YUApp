package com.vapp.yangonuniversity.adapter;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.vapp.yangonuniversity.R;
import com.vapp.yangonuniversity.Utils;
import com.vapp.yangonuniversity.model.Department;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DepartmentListAdapter extends ArrayAdapter<Department> {
	
	private LayoutInflater inflater;
	private ImageView dept_image;
	private TextView txt_deptname;
	private TextView txt_deptsummary;

	public DepartmentListAdapter(Context context, ArrayList<Department> dept_list) {
		super(context, R.layout.dept_rowitem, dept_list);
		inflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Department getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Department department = this.getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.dept_rowitem, null);
			txt_deptname = (TextView) convertView.findViewById(R.id.dept_row_name);
			dept_image = (ImageView) convertView.findViewById(R.id.dept_row_image);
			txt_deptsummary = (TextView) convertView.findViewById(R.id.dept_row_detail);
			convertView.setTag(new departmentViewHolder(txt_deptname, dept_image, txt_deptsummary));
		} else {
			departmentViewHolder viewHolder = (departmentViewHolder) convertView.getTag();
			txt_deptname = viewHolder.getName();
			dept_image = viewHolder.getImage();
			txt_deptsummary = viewHolder.getSummary();
		}
		
		
		int resID = convertView.getResources().getIdentifier(department.getDept_img(), "drawable", "com.vapp.yangonuniversity");
		Picasso.with(convertView.getContext()).load(resID).resize(100, 120).into(dept_image);

		txt_deptname.setText(department.getDept_name());
		txt_deptsummary.setText(Utils.unescape(department.getDept_info().substring(0, 150) + "....."));
		
		return convertView;
	}
	
	static class departmentViewHolder {
		  TextView name;
		  ImageView image;
		  TextView summary;
		  
		public departmentViewHolder(TextView name, ImageView image,
				TextView summary) {
			super();
			this.name = name;
			this.image = image;
			this.summary = summary;
		}
		
		public TextView getName() {
			return name;
		}
		public void setName(TextView name) {
			this.name = name;
		}
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public TextView getSummary() {
			return summary;
		}
		public void setSummary(TextView summary) {
			this.summary = summary;
		}
	
		  
	}
}
