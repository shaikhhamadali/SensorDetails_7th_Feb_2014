package com.shaikhhamadali.blogspot.sensordetails;

import java.util.List;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;

public class SensorDetails extends ListActivity {
	//declare Variables
	//SensorManager lets you access the device's sensors

	private SensorManager mySensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//create instance of sensor manager and get system service to interact with Sensor
		mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		//Check the availability of sensor
		if (mySensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
			//create instance of List and get Gravity Sensor from Sensor Manager 
			List<Sensor> gravitySensorList = mySensorManager.getSensorList(Sensor.TYPE_GRAVITY);
			//Set ListAdapter of Sensor  
			setListAdapter(new ArrayAdapter<Sensor>(this,android.R.layout.simple_list_item_1,gravitySensorList));
		}else{
			//if Sensor Not found show Toast
			Toast.makeText(getApplicationContext(), "No Gravity Sensor!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//Get position of item click and create instance of sensor 
		Sensor sensor = (Sensor)l.getItemAtPosition(position);
		//Create instance of Alert dialog 
		AlertDialog.Builder sensorDialog = new AlertDialog.Builder(SensorDetails.this);
		//set title
		sensorDialog.setTitle(sensor.getName());

		@SuppressWarnings("unchecked")
		//create instance of class and get Sensor class
		Class<Sensor> sensorClass = (Class<Sensor>)sensor.getClass();
		//get details of sensor like Vendor, Version Type etc
		String vendor = sensor.getVendor();
		int version = sensor.getVersion();
		int type = sensor.getType();
		int minDelay = sensor.getMinDelay();
		float power = sensor.getPower();
		float maxRange = sensor.getMaximumRange();
		float resolution = sensor.getResolution();
		//Create instance of LinearLayout for alert dialog
		LinearLayout dialogLayout = new LinearLayout(getApplicationContext());
		dialogLayout.setBackgroundColor(Color.BLACK);
		//Create instance of Layout Params Width match parent and Height wrap content
		LayoutParams dialogLayoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//Set layout params to LinearLayout
		dialogLayout.setLayoutParams(dialogLayoutParams);
		//Set Orientation of Layout as Vertical
		dialogLayout.setOrientation(LinearLayout.VERTICAL);
		//Create Controls to add in Linear Layout and show details of Sensor  
		TextView textSensorClass = new TextView(getApplicationContext());
		textSensorClass.setText(sensorClass.toString());
		TextView textVendor = new TextView(getApplicationContext());
		textVendor.setText("Vendor: " + vendor);
		TextView textVersion = new TextView(getApplicationContext());
		textVersion.setText("Version: " + String.valueOf(version));
		TextView textType = new TextView(getApplicationContext());
		textType.setText("Type: " + String.valueOf(type));
		TextView textMinDelay = new TextView(getApplicationContext());
		textMinDelay.setText("MinDelay: " + String.valueOf(minDelay) + " microsecond");
		TextView textPower = new TextView(getApplicationContext());
		textPower.setText("Power: " + String.valueOf(power) + " mA");
		TextView textMaxRange = new TextView(getApplicationContext());
		textMaxRange.setText("MaximumRange: " + String.valueOf(maxRange));
		TextView textResolution = new TextView(getApplicationContext());
		textResolution.setText("Resolution: " + String.valueOf(resolution));
		//Add Views in Linear Layout
		dialogLayout.addView(textSensorClass);
		dialogLayout.addView(textVendor);
		dialogLayout.addView(textVersion);
		dialogLayout.addView(textType);
		dialogLayout.addView(textMinDelay);
		dialogLayout.addView(textPower);
		dialogLayout.addView(textMaxRange);
		dialogLayout.addView(textResolution);
		//Create Instance of ScrollView 
		ScrollView scrollView = new ScrollView(getApplicationContext());
		// Set Layout Params of ScrollView width match parent and height warp content
		LayoutParams scrollViewLayoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//Set Layout Params
		scrollView.setLayoutParams(scrollViewLayoutParams);
		//Add LAyout in ScrollView
		scrollView.addView(dialogLayout);
		//Set View on Dialog as ScrollView
		sensorDialog.setView(scrollView);
		//Show Dialog
		sensorDialog.show();
	}

}
