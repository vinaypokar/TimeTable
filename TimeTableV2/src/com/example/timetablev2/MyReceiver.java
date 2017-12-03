package com.example.timetablev2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		/*Intent service1 = new Intent(context, MyAlarmService.class);
	     context.startService(service1);*/
		//Log.i("App", "called receiver method");
		try{
			Bundle b=intent.getExtras();
			String sec=b.getString("section");
			String sub=b.getString("subject");
			Utils.generateNotification(context,sec,sub);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
