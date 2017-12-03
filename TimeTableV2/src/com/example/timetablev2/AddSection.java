package com.example.timetablev2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSection extends Activity {

	TableInsert in;
	String sec,sub;
	boolean check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_section);
		
		final EditText e=(EditText)findViewById(R.id.editText1);
		final EditText e1=(EditText)findViewById(R.id.editText2);
		Button b=(Button)findViewById(R.id.button1);
		in=new TableInsert(this);
		
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sec=e.getText().toString();
				sub=e1.getText().toString();
				if(sec.trim().length()<=0 || sub.trim().length()<=0){
					Toast.makeText(getApplicationContext(), "Fields Cannot be empty", Toast.LENGTH_LONG).show();
				}
				else{
					check=in.addSec(sec,sub);
					if(check){
						Intent i=new Intent(getApplicationContext(),AddPeriod.class);
						i.putExtra("section",sec);
						i.putExtra("subject",sub);
						startActivity(i);
					}
					else
						Toast.makeText(getApplicationContext(), "Class registration unsuccessful", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
