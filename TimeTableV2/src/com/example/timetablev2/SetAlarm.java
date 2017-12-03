package com.example.timetablev2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SetAlarm extends BroadcastReceiver {

	TableInsert ti;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		ti=new TableInsert(context);
		int i;
		Bundle b=intent.getExtras();
		String sec=b.getString("section");
		String sub=b.getString("subject");
		Cursor c=ti.getStartTime(sec, sub);
		int count=c.getColumnCount();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
		String strDate[] = formatter.format(date).split(" ");
		int d,m,y,h,min;
		d=Integer.parseInt(strDate[0]);
		m=Integer.parseInt(strDate[1]);
		y=Integer.parseInt(strDate[2]);
		if(c.getCount()>0){
			while(c.moveToNext()){
			  //Log.d("String",c.getString(0));
				for(i=0;i<count;i++){
					String str=c.getString(i);
					Log.d("String",str);
					if(!str.equals("null")){
						String myTime=str;
						String spl[]=str.split(":");
						h=Integer.parseInt(spl[0]);
						min=Integer.parseInt(spl[1]);
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.MONTH, m-1);
						calendar.set(Calendar.YEAR, y);
						calendar.set(Calendar.DAY_OF_MONTH,d);
						calendar.set(Calendar.HOUR_OF_DAY, h);
						calendar.set(Calendar.MINUTE, min);
						calendar.set(Calendar.SECOND, 0);
						Intent myIntent = new Intent(context, MyReceiver.class);
						myIntent.putExtra("subject",sub);
						myIntent.putExtra("section",sec);
						PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
						AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
						alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
						//calendar.add(Calendar.MINUTE, 50);
						//PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
						//AlarmManager alarmManager1 = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
						//alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent1);
					}
				}
			}
		}
		/*Date date1 = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd MM yyyy hh mm a");
		String strDate1[] = formatter1.format(date).split(" ");
		int d1,m1,y1,h1,min1,a1;
		d1=Integer.parseInt(strDate1[0]);
		m1=Integer.parseInt(strDate1[1]);
		y1=Integer.parseInt(strDate1[2]);
		h1=Integer.parseInt(strDate1[3]);
		min1=Integer.parseInt(strDate1[4]);
		if(strDate1[5].equals("AM"))
			a1=0;
		else
			a1=1;
		for(i=0;i<3;i++){
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, m1-1);
			calendar.set(Calendar.YEAR, y1);
			calendar.set(Calendar.DAY_OF_MONTH,d1);
			calendar.set(Calendar.HOUR_OF_DAY, h1);
			calendar.set(Calendar.MINUTE, min1+i);
			calendar.set(Calendar.SECOND, 0);
			Intent myIntent = new Intent(context, MyReceiver.class);
			myIntent.putExtra("subject",sub);
			myIntent.putExtra("section",sec);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
			AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		}*/
	}

}
