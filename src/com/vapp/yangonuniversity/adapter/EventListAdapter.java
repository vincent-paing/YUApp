package com.vapp.yangonuniversity.adapter;

import java.util.ArrayList;

import com.vapp.yangonuniversity.R;
import com.vapp.yangonuniversity.model.YUEvent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<YUEvent> {
	
	private LayoutInflater inflater;
	private TextView txt_eventname;
	private TextView txt_eventdes;
	private TextView txt_eventdate;
	
	public EventListAdapter(Context context, ArrayList<YUEvent> event_list) {
		super(context, R.layout.event_rowitem, event_list);
		inflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}
	
	@Override
	public YUEvent getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		YUEvent event = this.getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.event_rowitem, null);
			txt_eventname = (TextView) convertView.findViewById(R.id.event_row_name);
			txt_eventdes = (TextView) convertView.findViewById(R.id.event_row_description);
			txt_eventdate = (TextView) convertView.findViewById(R.id.event_row_date);
			convertView.setTag(new eventViewHolder(txt_eventname, txt_eventdes, txt_eventdate));
		} else {
			eventViewHolder viewHolder = (eventViewHolder) convertView.getTag();
			txt_eventname = viewHolder.getName();
			txt_eventdes = viewHolder.getDescription();
			txt_eventdate = viewHolder.getDate();
		}
		txt_eventname.setText(event.getName());
		txt_eventdes.setText(event.getDescription());
		txt_eventdate.setText(event.getDate());
		return convertView;
	}
	
	static class eventViewHolder {
		TextView name;
		TextView description;
		TextView date;
		
		public eventViewHolder(TextView name, TextView description, TextView date) {
			super();
			this.name = name;
			this.description = description;
			this.date = date;
		}

		public TextView getName() {
			return name;
		}

		public void setName(TextView name) {
			this.name = name;
		}

		public TextView getDescription() {
			return description;
		}

		public void setDescription(TextView description) {
			this.description = description;
		}

		public TextView getDate() {
			return date;
		}

		public void setDate(TextView date) {
			this.date = date;
		}
		
		
	}

}
