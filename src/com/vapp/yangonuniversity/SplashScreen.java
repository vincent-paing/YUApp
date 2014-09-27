package com.vapp.yangonuniversity;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity {
	
	private static int SPLASH_TIME_OUT = 5000;
	private TextView tv_yu;
	private TextView tv_va;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		tv_yu = (TextView) findViewById(R.id.txt_yu);
		tv_va = (TextView) findViewById(R.id.txt_va);
		tv_yu.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/staravenue.ttf"));
		tv_va.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/coolvetica.ttf"));
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				finish();
			}
		}, SPLASH_TIME_OUT);
	}


}
