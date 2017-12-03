package com.example.timetablev2;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_data);
		
		Bundle b=getIntent().getExtras();
		String sect=b.getString("section");
		String subj=b.getString("subject");
		TableInsert pi=new TableInsert(getApplicationContext());
		TextView tv1=(TextView)findViewById(R.id.tv1);
		tv1.setText(sect);
		TableLayout tableLayout=(TableLayout)findViewById(R.id.tl);
	    
		TableRow rowHeader = new TableRow(this);
		rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
	    rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
	    String[] headerText={"DAY","9:40","10:30","11:30","12:20","2:00","2:50","3:40"};
	    for(String c:headerText) {
	        TextView tv = new TextView(this);
	        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
	              TableRow.LayoutParams.WRAP_CONTENT));
	        tv.setGravity(Gravity.CENTER);
	        tv.setTextSize(18);
	        tv.setPadding(15, 15, 15, 15);
	        tv.setText(c);
	        rowHeader.addView(tv);
	      }
	    tableLayout.addView(rowHeader);
	    Cursor cursor=null;
	    String option=b.getString("opt");
	    int count;
	    if(option.equals("one")){
	    	cursor=pi.getftt(sect);
	    }
	    else if(option.equals("two")){
	    	cursor=pi.getttt(sect);
	    }
	    else if(option.equals("three")){
	    	cursor=pi.getyp(sect,subj);
	    }
	    count=cursor.getCount();
	    if(count>0)
        {
           while (cursor.moveToNext()) {
              // Read columns data
        	   String day=cursor.getString(1);
               String one= cursor.getString(2);
               String two= cursor.getString(3);
               String three= cursor.getString(4);
               String four= cursor.getString(5);
               String five= cursor.getString(6);
               String six= cursor.getString(7);
               String seven= cursor.getString(8);
              TableRow row = new TableRow(this);
              row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                   TableLayout.LayoutParams.WRAP_CONTENT));
              if(count%2==0)
            	  row.setBackgroundColor(Color.GRAY);
             String[] colText={day,one,two,three,four,five,six,seven};
              for(String text:colText) {
            	  TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                      TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(16);
                tv.setPadding(2,2,2,2);
                tv.setText(text);
                row.addView(tv);
              }
              tableLayout.addView(row);
              count++;
           }
        }
	}
}
