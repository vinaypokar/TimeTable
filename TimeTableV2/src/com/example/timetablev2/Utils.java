package com.example.timetablev2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Utils {

	public static NotificationManager mManager;

	public static void generateNotification(Context context,String sec,String sub){ 
		
		mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		Intent intent1 = new Intent(context,MainActivity.class);
		Notification notification = new Notification(R.drawable.ic_launcher,"TimeTable", System.currentTimeMillis());
		intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, "AlarmManagerDemo", "You have this "+sub+" for this "+sec, pendingNotificationIntent);
		mManager.notify(0, notification);
	}
}
