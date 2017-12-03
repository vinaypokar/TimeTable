package com.example.timetablev2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPeriod extends Activity {

	String one,two,three,four,five,six,seven;
	String sec,sub;
	EditText pone,ptwo,pthree,pfour,pfive,psix,pseven;
	int count=0;
	boolean check;
	TableInsert pi;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_period);
		
		final String days[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		final TextView day=(TextView)findViewById(R.id.textView1);
		Button next=(Button)findViewById(R.id.button1);
		final EditText pone=(EditText)findViewById(R.id.editText1);
		final EditText ptwo=(EditText)findViewById(R.id.editText2);
		final EditText pthree=(EditText)findViewById(R.id.editText3);
		final EditText pfour=(EditText)findViewById(R.id.editText4);
		final EditText pfive=(EditText)findViewById(R.id.editText5);
		final EditText psix=(EditText)findViewById(R.id.editText6);
		final EditText pseven=(EditText)findViewById(R.id.editText7);
		day.setText(days[count]);
		Bundle b=getIntent().getExtras();
		sec=b.getString("section");
		sub=b.getString("subject");
		pi=new TableInsert(this);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(count==5)
				{
					one=pone.getText().toString().trim();
					two=ptwo.getText().toString().trim();
					three=pthree.getText().toString().trim();
					four=pfour.getText().toString().trim();
					five=pfive.getText().toString().trim();
					six=psix.getText().toString().trim();
					seven=pseven.getText().toString().trim();
					if(checkLength(one,two,three,four,five,six,seven))
						Toast.makeText(getApplicationContext(), "Fields cannnot be empty", Toast.LENGTH_SHORT).show();
					else{
						check=pi.addPeriods(sec,days[count], one, two, three, four, five, six, seven);
						if(!check)
							Toast.makeText(getApplicationContext(), "Cannot Insert", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(),MainActivity.class));
					}
				}
				else{
					one=pone.getText().toString().trim();
					two=ptwo.getText().toString().trim();
					three=pthree.getText().toString().trim();
					four=pfour.getText().toString().trim();
					five=pfive.getText().toString().trim();
					six=psix.getText().toString().trim();
					seven=pseven.getText().toString().trim();
					if(checkLength(one,two,three,four,five,six,seven))
						Toast.makeText(getApplicationContext(), "Fields cannnot be empty", Toast.LENGTH_SHORT).show();
					else{
						check=pi.addPeriods(sec,days[count], one, two, three, four, five, six, seven);
						if(!check)
							Toast.makeText(getApplicationContext(), "Cannot Insert", Toast.LENGTH_SHORT).show();
						count++;
						day.setText(days[count]);
						pone.setText("");
						ptwo.setText("");
						pthree.setText("");
						pfour.setText("");
						pfive.setText("");
						psix.setText("");
						pseven.setText("");
					}
				}
			}
		});
		
	}
	public boolean checkLength(String one,String two,String three,String four,String five,String six,String seven){
		if(one.length()<=0 || two.length()<=0 || three.length()<=0 || four.length()<=0 || five.length()<=0 || six.length()<=0 || seven.length()<=0){
			return true;
		}
		else{
			return false;
		}
	}
}
