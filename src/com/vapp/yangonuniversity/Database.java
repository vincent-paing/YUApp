package com.vapp.yangonuniversity;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.internal.lo;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.vapp.yangonuniversity.model.Department;
import com.vapp.yangonuniversity.model.MarkerData;
public class Database extends SQLiteAssetHelper {
	
	private SQLiteDatabase database;
    private static final String DATABASE_NAME = "yuapp.db";
    private static final int DATABASE_VERSION = 3;

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		setForcedUpgrade();
	}
	
	public ArrayList<Department> getAllDepartment() {
		ArrayList<Department> department_list = new ArrayList<Department>();
		String get_all_dept = "SELECT * FROM tbl_department";
		database = getReadableDatabase();
		Cursor c = database.rawQuery(get_all_dept, null);
		if (c.moveToFirst()) {
			do {
				Department temp_Department = new Department();
				temp_Department.setDept_name(c.getString(0));
				temp_Department.setDept_info(c.getString(1));
				temp_Department.setDept_img(c.getString(2));
				temp_Department.setDept_location(c.getString(3));
				department_list.add(temp_Department);
			} while (c.moveToNext());
		}
		database.close();
		c.close();
		return department_list;
	}
	
	public Department getDepartmentbyName(String name) {
		Department temp_dept = new Department();
		database = getReadableDatabase();
		String get_dept_byname = "SELECT * FROM tbl_department WHERE name ='" + name + "'";
		Cursor c = database.rawQuery(get_dept_byname, null);
		if (c.moveToFirst()) {
			do {
				temp_dept.setDept_name(c.getString(0));
				temp_dept.setDept_info(c.getString(1));
				temp_dept.setDept_img(c.getString(2));
				temp_dept.setDept_location(c.getString(3));
			} while (c.moveToNext());
		}
		database.close();
		c.close();
		return temp_dept;
	}
	
	public ArrayList<MarkerData> getallMarkers() {
		ArrayList<MarkerData> markerdata_list = new ArrayList<MarkerData>();
		String get_all_marker = "SELECT * FROM tbl_marker";
		database = getReadableDatabase();
		Cursor c = database.rawQuery(get_all_marker, null);
		if (c.moveToFirst()) {
			do {
				MarkerData markerdata = new MarkerData();
				markerdata.setTitle(c.getString(0));
				markerdata.setLatitude(c.getDouble(1));
				markerdata.setLongitude(c.getDouble(2));
				markerdata.setIconres(c.getString(3));
				markerdata_list.add(markerdata);
			} while (c.moveToNext());
		} 
		
		database.close();
		c.close();
		return markerdata_list;		
	}
}
