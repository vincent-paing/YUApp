package com.vapp.yangonuniversity;

import com.vapp.yangonuniversity.model.Department;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DepartmentInfo extends ActionBarActivity {
	
	private TextView txt_header;
	private TextView txt_info;
	private Database database;
	private Department department;
	private ActionBar actionbar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dept_info_page);
		actionbar = getSupportActionBar();
		txt_header = (TextView) findViewById(R.id.txt_dept_header);
		txt_info = (TextView) findViewById(R.id.txt_dept_info);
		database = new Database(getApplicationContext());
		Intent intent = getIntent();
		department = new Department();
		department = database.getDepartmentbyName(intent.getStringExtra("deptname"));
		txt_header.setText(department.getDept_name());
		String text = Utils.unescape(department.getDept_info());
		txt_info.setText(text);
		txt_header.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/coolvetica.ttf"));
		
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setTitle(department.getDept_name().substring(13));
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
			case android.R.id.home:
				// app icon in action bar clicked; go home
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item); 
			}
		}
}
