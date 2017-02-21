package com.example.aditios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class MainActivity extends Activity {
	
	int i=0;
	EditText	etmessage;
	ListView lvContacts;
	GPSTracker gps;
	DatabaseHandler db;
	String name,score;
	String[] names=new String[1000];
	String[] phones=new String[1000];
	long start_time;
	int y=0;
	
	int rec=1;
	
	int count=0;
	 
	 private static String mFileName = null;
	 List<RowItem> rowItems;
	private MediaRecorder mRecorder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_2);
		
		lvContacts=(ListView)findViewById(R.id.lvContacts);
		etmessage=(EditText)findViewById(R.id.etMessage);
		gps = new GPSTracker(this);
		 
		int i=0;
		db= new DatabaseHandler(this);
		 List<Contact> contacts = db.getAllContacts();
	        for (Contact cn : contacts) {
	          String  log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Contact: " + cn.getPhone();
	           name=""+cn.getName();
	            score=""+cn.getPhone();
	            // Writing Contacts to log
	            names[i]=name;
	            phones[i]=score;
	                        Log.d("Name: ", log);
	            i++;
	        }
		rowItems = new ArrayList<RowItem>();
        for ( i = 0; i<5; i++) {
            RowItem item = new RowItem( names[i],phones[i]);
            rowItems.add(item);
        }
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
        lvContacts.setAdapter(adapter);


        db.close();
	
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	
	if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
		i++;
		if(i>2)
			i=0;
		if(i==2){
		Toast.makeText(this, "Message being sent", Toast.LENGTH_LONG).show();
		Send_Message();
		}
		return true;
	}
	return super.onKeyDown(keyCode, event);
}





public void Send_Message(){
	SmsManager sms = SmsManager.getDefault();
	for(int i=0;i<5;i++){
		Log.d("phone_number", ""+phones[i]);
		if(phones[i]!=null)
		sms.sendTextMessage(phones[i], null,etmessage.getText()+ "http://maps.google.com/maps?q=" + gps.getLatitude()+ "," + gps.getLongitude()+"", null, null);
	}
		startRec();
		while(true){
			if(System.currentTimeMillis()>start_time+1*60*1000){
				stopRec();
				break;
			}
		}
	
    }
public void startRec() {
    mRecorder = new MediaRecorder();
    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
    File graphshotsDirectory = new File("/sdcard/Os-Project/");
	// have the object build the directory structure, if needed.
	graphshotsDirectory.mkdirs();
	Calendar c = Calendar.getInstance(); 
	//int seconds = c.get(Calendar.SECOND);
    mFileName += "/Os-Project/Rec-"+c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR)+".3gp";
    mRecorder.setOutputFile(mFileName);
    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

    try {
        mRecorder.prepare();
        
    } catch (IOException e) {
        //Log.e(LOG_TAG, "prepare() failed");
    }
    start_time= System.currentTimeMillis();
    mRecorder.start();
}

public void stopRec() {
   
	mRecorder.stop();
    mRecorder.release();
    mRecorder = null;
}



public void Contacts(View v){
	Intent myint=new Intent(this,contactlist.class);
	startActivity(myint);
}


@Override
protected void onResume() {
	int i = 0;
	// TODO Auto-generated method stub
	db= new DatabaseHandler(this);
	 List<Contact> contacts = db.getAllContacts();
       for (Contact cn : contacts) {
         String  log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Contact: " + cn.getPhone();
          name=""+cn.getName();
           score=""+cn.getPhone();
           // Writing Contacts to log
           names[i]=name;
           phones[i]=score;
                       Log.d("Name: ", log);
           i++;
       }
	rowItems = new ArrayList<RowItem>();
   for ( i = 0; i<5; i++) {
       RowItem item = new RowItem( names[i],phones[i]);
       rowItems.add(item);
   }
   CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
   lvContacts.setAdapter(adapter);


   db.close();
	super.onResume();
}
}

