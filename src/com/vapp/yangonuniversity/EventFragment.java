package com.vapp.yangonuniversity;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.vapp.yangonuniversity.adapter.EventListAdapter;
import com.vapp.yangonuniversity.model.YUEvent;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class EventFragment extends Fragment {
	private ArrayList<YUEvent> event_list = new ArrayList<YUEvent>();
	Context context;
	ArrayAdapter<YUEvent> event_adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		context = rootView.getContext();
		
		if (Utils.ConnectionDetector(context)) {
			ListView list_view = (ListView) rootView.findViewById(R.id.dept_list);
			event_adapter = new EventListAdapter(context, event_list);
			list_view.setAdapter(event_adapter);
			updateEvent();
		} else {
			Toast.makeText(context, "No Connection Detected", Toast.LENGTH_LONG).show();
		}
		
		return rootView;
	}
	
	
	
	public void updateEvent() {
		ParseQuery<YUEvent> query = ParseQuery.getQuery(YUEvent.class);
		query.findInBackground(new FindCallback<YUEvent>() {
			
			@Override
			public void done(List<YUEvent> objects, ParseException e) {
				// TODO Auto-generated method stub
				if (objects != null) { 
					event_list.clear();
					event_list.addAll(objects);
					event_adapter.notifyDataSetChanged();
				}
			}
		});
	}
	
	
}
