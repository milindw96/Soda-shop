package com.example.sharad.Soda1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

public static final String DATABASE_NAME="Soda.db";
    public static final String TABLE_NAME="Soda_db";
    public static final String DATE="date";
    public static final String ID="id";
    public static final String TIME="time";
    public static final String PRICE="price";





    public MySQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        //SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,time TEXT,date TEXT,price INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
public boolean insertData(String date,String time,Integer price){
        SQLiteDatabase db=this.getWritableDatabase();
    ContentValues  contentValues=new ContentValues();
    contentValues.put(DATE,date);
    contentValues.put(TIME,time);
    contentValues.put(PRICE,price);
    long  result = db.insert(TABLE_NAME,null,contentValues);
    if(result == -1){
        return false;
    }
    else
        return true;


}

public Cursor getAllData(){

SQLiteDatabase db= this.getWritableDatabase();
//Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
  Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
  //  Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where date= '2018/04/16'",null);


    return res;
}
public  Cursor getDate(String get){
    SQLiteDatabase db= this.getWritableDatabase();
    Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where date="+"'"+get+"'",null);
    return res;


}

public Cursor sum(String get){
    SQLiteDatabase db= this.getWritableDatabase();

    Cursor res=db.rawQuery("select SUM("+PRICE+") from "+TABLE_NAME+" where date="+"'"+get+"';",null);

    return res;


}
}
