package com.vapp.yangonuniversity;

import java.util.ArrayList;
import com.vapp.yangonuniversity.model.Department;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	public static ArrayList<Department> SortDepartment(ArrayList<Department> dept_list) {
		
		for (int i = 0; i < dept_list.size() - 1; i++) {
			for (int j = 0; j < dept_list.size() - i - 1; j++) {
				if (dept_list.get(j).getDept_name().compareTo(dept_list.get(j+1).getDept_name()) > 0) {
					Department temp = dept_list.get(j);
					dept_list.set(j, dept_list.get(j+1));
					dept_list.set(j+1, temp);
				}
			}
		}
		return dept_list;
	}
	
	public static String unescape(String description) {
		   return description.replaceAll("\\\\n", "\\\n").replaceAll("\\\\t", "\\\t");
	}
	
	
	public static boolean ConnectionDetector(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
		return false;
		
	}
}
