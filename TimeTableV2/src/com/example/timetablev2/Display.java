package com.example.timetablev2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Display extends Activity {

	Button b1,b2,b3;
	String sec,sub;
	ToggleButton tb1;
	TableInsert in;
	PendingIntent pendingIntent;
	AlarmManager alarmManager;
	Intent myIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		
		in=new TableInsert(this);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		Bundle b=getIntent().getExtras();
		sec=b.getString("section");
		sub=b.getString("subject");
		//Toast.makeText(getApplicationContext(), clas+ "", Toast.LENGTH_SHORT).show();
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),DisplayData.class);
				i.putExtra("opt","one");
				i.putExtra("section",sec);
				i.putExtra("subject",sub);
				startActivity(i);
			}
			
		});
		b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),DisplayData.class);
				i.putExtra("opt","two");
				i.putExtra("section",sec);
				i.putExtra("subject",sub);
				startActivity(i);
			}
			
		});
		b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),DisplayData.class);
				i.putExtra("opt","three");
				i.putExtra("section",sec);
				i.putExtra("subject",sub);
				startActivity(i);
			}
			
		});
		tb1=(ToggleButton)findViewById(R.id.tb1);
		tb1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(tb1.getText().equals("ON")){
					//Toast.makeText(getApplicationContext(), "TB ON", Toast.LENGTH_SHORT).show();
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy hh mm a");
					String strDate[] = formatter.format(date).split(" ");
					Toast.makeText(getApplicationContext(), strDate[0]+" "+strDate[1]+" "+strDate[2]+" "+strDate[3]+" "+strDate[4]+" "+strDate[5], Toast.LENGTH_SHORT).show();
					int d,m,y,h,min,a;
					d=Integer.parseInt(strDate[0]);
					m=Integer.parseInt(strDate[1]);
					y=Integer.parseInt(strDate[2]);
					h=Integer.parseInt(strDate[3]);
					min=Integer.parseInt(strDate[4]);
					if(strDate[5].equals("AM"))
						a=0;
					else
						a=1;
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.MONTH, m-1);
					calendar.set(Calendar.YEAR, y);
					calendar.set(Calendar.DAY_OF_MONTH,d);
					calendar.set(Calendar.HOUR_OF_DAY, h);
					calendar.set(Calendar.MINUTE, min+1);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.AM_PM,a);
					myIntent = new Intent(Display.this,SetAlarm.class);
					myIntent.putExtra("section",sec);
					myIntent.putExtra("subject",sub);
					int aid=(int)System.currentTimeMillis();
					Log.i("aid",aid+"");
					in.setaid(aid, sec);
					pendingIntent = PendingIntent.getBroadcast(Display.this,aid , myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
					alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
					alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
				}
				else{
					Toast.makeText(getApplicationContext(), "TB OFF", Toast.LENGTH_SHORT).show();
					Intent myIntent = new Intent(Display.this,SetAlarm.class);
					Cursor c=in.getaid(sec);
					c.moveToFirst();
					int id=c.getInt(0);
					Log.i("id",id+"");
					PendingIntent pi=PendingIntent.getBroadcast(Display.this, id, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
					alarmManager.cancel(pi);
				}
			}
		});
	}
}
