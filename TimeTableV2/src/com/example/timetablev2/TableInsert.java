package com.example.timetablev2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TableInsert extends SQLiteOpenHelper {

	String period="periods";
	String section="sections";
	public TableInsert(Context context) {
		super(context,"system",null,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists "+section+"(sname varchar(10) primary key,subject varchar(10) not null,aid INTEGER);");
		db.execSQL("create table if not exists "+period+"(sec varchar(10) not null,day varchar(20) not null,pone varchar(20) not null,ptwo varchar(20) not null,pthree varchar(20) not null,pfour varchar(20) not null,pfive varchar(20) not null,psix varchar(20) not null,pseven varchar(20) not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public boolean addSec(String sec,String sub)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues c=new ContentValues();
		c.put("sname",sec);
		c.put("subject", sub);
		long res=db.insert(section, null,c);
		if(res==-1)
			return false;
		else
			return true;
	}
	public ArrayList<String> displaySec()
	{
		ArrayList<String> list=new ArrayList<String>();
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c;
		c=db.rawQuery("select * from "+section, null);
		c.moveToFirst();
		while(!c.isAfterLast())
		{
			list.add(c.getString(0)+"      "+c.getString(1));
			c.moveToNext();
		}
		
		return list;
		
	}
	public boolean addPeriods(String s,String d,String p1,String p2,String p3,String p4,String p5,String p6,String p7)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put("sec",s);
		cv.put("day",d);
		cv.put("pone",p1);
		cv.put("ptwo",p2);
		cv.put("pthree",p3);
		cv.put("pfour",p4);
		cv.put("pfive",p5);
		cv.put("psix",p6);
		cv.put("pseven",p7);
		long res=db.insert(period, null, cv);
		if(res==-1)
			return false;
		else
			return true;
	}
	public boolean deleteSec(String sect){
		SQLiteDatabase db = this.getWritableDatabase();
		String q="delete from "+period+" where sec= '"+sect+"'";
		String q1="delete from "+section+" where sname= '"+sect+"'";
		db.execSQL(q);
		db.execSQL(q1);
        return true;
	}
	public Cursor getftt(String sect) {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from "+period+" where sec='"+sect+"'",null);
        return res;
    }
	public Cursor getttt(String sect) {
		SQLiteDatabase db=this.getReadableDatabase();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String tday = sdf.format(Calendar.getInstance().getTime());
		//+" and sec='"+sect+"'"
		String q="select * from "+period+" where day= '"+tday.trim()+"'"+" and sec='"+sect+"'";
        Cursor res = db.rawQuery(q,null);
        return res;
    }
	public Cursor getyp(String sect,String subj) {
		SQLiteDatabase db=this.getReadableDatabase();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String subje="'"+subj+"'";
		String tday = sdf.format(Calendar.getInstance().getTime());
		String str=subje+" then "+subje+" end";
		//" p where p.day= '"+tday.trim()+"'"+" and sec='"+sect+"'";
        Cursor res = db.rawQuery("select p.sec,p.day,case when p.pone="+str+",case when p.ptwo="+str+",case when p.pthree="+str+",case when p.pfour="+str+",case when p.pfive="+str+",case when p.psix="+str+",case when p.pseven="+str+" from "+period+" p where sec='"+sect+"'",null);
        return res;
    }
	public Cursor getStartTime(String sect,String subj){
		SQLiteDatabase db=this.getReadableDatabase();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String tday = sdf.format(Calendar.getInstance().getTime());
		String subje="'"+subj+"'";
		String str1=subje+" then '9:35' else 'null' end";
		String str2=subje+" then '10:25' else 'null' end";
		String str3=subje+" then '11:25' else 'null' end";
		String str4=subje+" then '12:15' else 'null' end";
		String str5=subje+" then '13:55' else 'null' end";
		String str6=subje+" then '14:45' else 'null' end";
		String str7=subje+" then '15:35' else 'null' end";
		Cursor res=db.rawQuery("select case when p.pone="+str1+",case when p.ptwo="+str2+",case when p.pthree="+str3+",case when p.pfour="+str4+",case when p.pfive="+str5+",case when p.psix="+str6+",case when p.pseven="+str7+" from "+period+" p where sec='"+sect+"' and p.day='"+tday+"'",null);
		return res;
	}
	public boolean setaid(int n,String sec){
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("update "+section+" set aid="+n+" where sname='"+sec+"'");
		return true;
	}
	public Cursor getaid(String sec){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor res=db.rawQuery("select aid from "+section+" where sname='"+sec+"'",null);
		return res;
	}
}
