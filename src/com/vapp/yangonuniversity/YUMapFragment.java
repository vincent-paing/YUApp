package com.vapp.yangonuniversity;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.vapp.yangonuniversity.model.MarkerData;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class YUMapFragment extends Fragment{
	
	private GoogleMap googlemap;
	private Context context;
	private LatLng NEBOUND =  new LatLng(16.835285, 96.142251);
	private LatLng SWBOUND = new LatLng(16.825524, 96.128848);
	private LatLngBounds MapBoundary = new LatLngBounds(SWBOUND, NEBOUND);
	private LatLng CENTRE = new LatLng(16.828693, 96.135320);
	private Handler MapHandler;
	private int MAX_ZOOM = 20;
	private int MIN_ZOOM = 16;
	public static String findTitle = null;;
	private ArrayList<Marker> markers;
	private ArrayList<MarkerData> markerdata_list;
	
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
		context = rootView.getContext();
        setupMap();	
		return rootView;
	}
	
	
	private void setupMap() {
		
		if (googlemap == null) {
			googlemap = ((MapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
			if (googlemap != null) {
				initializeMap();
			}
		}
	}

	private void initializeMap() {
		CameraPosition cameraPosition = new CameraPosition.Builder().target(CENTRE).zoom(17).build();
		googlemap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		Database database = new Database(context);
		markerdata_list = new ArrayList<MarkerData>();
		markers = new ArrayList<Marker>();
		markerdata_list.addAll(database.getallMarkers());
		for (MarkerData data : markerdata_list) {
			Marker marker = googlemap.addMarker(new MarkerOptions().position(new LatLng(data.getLatitude(), data.getLongitude())).icon(BitmapDescriptorFactory.fromResource(data.getIconres(context))).title(data.getTitle()));
			markers.add(marker);
		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (googlemap != null) {
			initializeMap();
		}
		
		if (googlemap == null) {
			googlemap = ((MapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
			if (googlemap != null) {
				initializeMap();
			}
		}
		setHasOptionsMenu(true);
		if (getActivity().getIntent().getStringExtra("search")  != null) {
			String query = getActivity().getIntent().getStringExtra("search");
			for (Marker markdata : markers) {
				if (markdata.getTitle().equals(query)) {
					CameraPosition newPosition = new CameraPosition(markdata.getPosition(), 18, googlemap.getCameraPosition().tilt, googlemap.getCameraPosition().bearing);
					CameraUpdate update = CameraUpdateFactory.newCameraPosition(newPosition);
					googlemap.moveCamera(update);
					markdata.showInfoWindow();
				}
			}
		}
	}
	

	
	private LatLng correctLatLng(LatLngBounds cameraBounds) 	{
		double latitude = 0;
		double longitude = 0;
		if (cameraBounds.southwest.latitude < MapBoundary.southwest.latitude) {
			latitude = MapBoundary.southwest.latitude - cameraBounds.southwest.latitude;
		}
		if(cameraBounds.southwest.longitude < MapBoundary.southwest.longitude) {
	        longitude = MapBoundary.southwest.longitude - cameraBounds.southwest.longitude;
	    }
	    if(cameraBounds.northeast.latitude > MapBoundary.northeast.latitude) {
	        latitude = MapBoundary.northeast.latitude - cameraBounds.northeast.latitude;
	    }
	    if(cameraBounds.northeast.longitude > MapBoundary.northeast.longitude) {
	        longitude = MapBoundary.northeast.longitude - cameraBounds.northeast.longitude;
	    }
		return new LatLng(latitude, longitude);
	}
	
	private void fixMapBounds() {
		CameraPosition position = googlemap.getCameraPosition();
		VisibleRegion region = googlemap.getProjection().getVisibleRegion();
		float zoom = 0;
		if (position.zoom < MIN_ZOOM) zoom = MIN_ZOOM;
		if (position.zoom > MAX_ZOOM) zoom = MAX_ZOOM;
		LatLng correction = correctLatLng(region.latLngBounds);
		if (zoom != 0 || correction.latitude != 0 || correction.longitude != 0) {
			zoom = (zoom==0)? position.zoom:zoom;
			if (zoom == 0) zoom = position.zoom;
			double lat = position.target.latitude + correction.latitude;
			double lon = position.target.longitude + correction.longitude;
			CameraPosition newPosition = new CameraPosition(new LatLng(lat, lon), zoom, position.tilt, position.bearing);
			CameraUpdate update = CameraUpdateFactory.newCameraPosition(newPosition);
			googlemap.moveCamera(update);
		}
	}

	
	private void setUpHandler() {
		MapHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				fixMapBounds();
				sendEmptyMessageDelayed(0, 50);
			}
		};
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setUpHandler();
        MapHandler.sendEmptyMessageDelayed(0, 50);
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater); 
		inflater.inflate(R.menu.map_action_bar_menu, menu);
		
		SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));		
	}


}
