package com.vapp.yangonuniversity;

import java.util.ArrayList;
import com.vapp.yangonuniversity.adapter.DepartmentListAdapter;
import com.vapp.yangonuniversity.model.Department;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DepartmentFragment extends Fragment implements OnItemClickListener {

	private ArrayList<Department> dept_list = new ArrayList<Department>();
	Context context;
	private ListView list_view;
	
	public DepartmentFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		context = rootView.getContext();
		list_view = (ListView) rootView.findViewById(R.id.dept_list);
		Database database = new Database(rootView.getContext());
		dept_list.addAll(Utils.SortDepartment(new ArrayList<Department>(database.getAllDepartment())));
		ArrayAdapter<Department> dept_adapter = new DepartmentListAdapter(context, dept_list);
		list_view.setAdapter(dept_adapter);
		list_view.setOnItemClickListener(this);
		registerForContextMenu(list_view);
		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent info_intent;
		info_intent = new Intent(view.getContext(), DepartmentInfo.class);
		info_intent.putExtra("deptname", dept_list.get(position).getDept_name());
		startActivity(info_intent);
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == R.id.dept_list) {
			MenuInflater inflater = getActivity().getMenuInflater();
			inflater.inflate(R.menu.department_context_menu, menu);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.see_more:
			Intent info_intent;
			info_intent = new Intent(context, DepartmentInfo.class);
			info_intent.putExtra("deptname", dept_list.get((int)info.id).getDept_name());
			startActivity(info_intent);
			return true;

		case R.id.find_on_map:
			Intent searchdone_intent;
			searchdone_intent = new Intent(context, MainActivity.class);
			searchdone_intent.putExtra("search",  dept_list.get((int)info.id).getDept_location());
			startActivity(searchdone_intent);
			return true;
			
		default:
			return super.onContextItemSelected(item);
		}
		
	}
}
