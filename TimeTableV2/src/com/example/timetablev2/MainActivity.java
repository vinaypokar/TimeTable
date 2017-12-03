package com.example.timetablev2;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.timetablev2.SwipeDetector.Action;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {

	ArrayList<String> list;
	TableInsert i;
	ListView lv;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton b1=(ImageButton)findViewById(R.id.imageButton1);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),AddSection.class);
				startActivity(i);
			}
			
		});
		TextView tv1=(TextView)findViewById(R.id.tv1);
		lv=(ListView)findViewById(R.id.listview1);
		i=new TableInsert(getApplicationContext());
		list=new ArrayList<String>();
		list=i.displaySec();
		ArrayAdapter<String> ad = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, list);
		lv.setAdapter(ad);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(),arg0.getItemAtPosition(arg2)+" is clicked", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(),Display.class);
				String[] str=arg0.getItemAtPosition(arg2).toString().split("      ");
				i.putExtra("section",str[0]);
				i.putExtra("subject",str[1]);
				startActivity(i);
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
				String[] str=parent.getItemAtPosition(position).toString().split("      ");
				boolean check=i.deleteSec(str[0]);
				MainActivity.this.recreate();
				return true;
			}
			
		});
		
		final SwipeDetector swipeDetector=new SwipeDetector();
		lv.setOnTouchListener(swipeDetector);
		/*lv.setOnItemClickListener(new OnItemClickListener() {


		    @Override
		    public void onItemClick(AdapterView <? > parent, View view,
		        int position, long id) {
		        if (swipeDetector.swipeDetected()) {
		            if (swipeDetector.getAction() == SwipeDetector.Action.LR) {

		                Toast.makeText(getApplicationContext(),
		                    "Left to right", Toast.LENGTH_SHORT).show();

		            }
		            if (swipeDetector.getAction() == SwipeDetector.Action.RL) {

		                Toast.makeText(getApplicationContext(),
		                    "Right to left", Toast.LENGTH_SHORT).show();

		            }
		        }
		    }
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}



