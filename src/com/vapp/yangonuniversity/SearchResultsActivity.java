package com.vapp.yangonuniversity;

import java.util.ArrayList;

import com.vapp.yangonuniversity.adapter.SearchListAdapter;
import com.vapp.yangonuniversity.model.MarkerData;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResultsActivity extends Activity implements OnItemClickListener {
	private Database database;
	private ArrayList<MarkerData> datalist;
	private ListView list;
	private SearchListAdapter adapter;
	ArrayList<MarkerData> tempdata;
	ActionBar actionbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search_result);
		
		actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		list = (ListView) findViewById(R.id.search_list);
		database = new Database(getApplicationContext());
		datalist = new ArrayList<MarkerData>();
		tempdata = new ArrayList<MarkerData>();
		datalist.addAll(database.getallMarkers());
		adapter = new SearchListAdapter(getApplicationContext(), tempdata);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		handleIntent(getIntent());
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		setIntent(intent);
		handleIntent(intent);
	}
	
	private void handleIntent(Intent intent) {
		
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			actionbar.setTitle("Search Results : " + query);
			for (MarkerData data : datalist) {
				if (data.getTitle().toLowerCase().contains(query.toLowerCase())) {
					tempdata.add(data);
					adapter.notifyDataSetChanged();
					if (tempdata.isEmpty()) {
						Toast.makeText(getApplicationContext(), "No Results could be found", Toast.LENGTH_LONG).show();
					}
				} else {
					Log.v("datasearch", "No data found");
				}
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Intent searchdone_intent;
		searchdone_intent = new Intent(view.getContext(), MainActivity.class);
		searchdone_intent.putExtra("search", adapter.getItem(position).getTitle());
		startActivity(searchdone_intent);
	}
	
	
}
