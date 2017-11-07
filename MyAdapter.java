package com.example.ashwin.sqlitetry1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Ashwin on 11/7/2017.
 */

public class MyAdapter {
    Myhelper mhelper;

    public MyAdapter(Context context) {
        mhelper = new Myhelper(context);
    }
    public long insertData(String name, String pass)
    {
        SQLiteDatabase dbb = mhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Myhelper.NAME, name);
        contentValues.put(Myhelper.PASSWORD, pass);
        long id = dbb.insert(Myhelper.TABLE_NAME, null , contentValues);
        return id;
    }
    public String getData()
    {
        SQLiteDatabase db;
        db = mhelper.getWritableDatabase();
        String[] columns = {Myhelper.UID,Myhelper.NAME,Myhelper.PASSWORD};
        Cursor cursor =db.query(Myhelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(Myhelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(Myhelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(Myhelper.PASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }
    public  int delete(String uname)
    {
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(Myhelper.TABLE_NAME ,Myhelper.NAME+" = ?",whereArgs);
        return  count;
    }
    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = mhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Myhelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(Myhelper.TABLE_NAME,contentValues, Myhelper.NAME+" = ?",whereArgs );
        return count;
    }
}

class Myhelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="LavDataBase";
    private static final int DATABASE_Version = 1;
    public static final String TABLE_NAME="LavTable";
    public static final String UID="id";
    public static final String NAME="Name";
    public static final String PASSWORD="Password";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ PASSWORD+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    public Myhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Message.message(context,""+e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}
class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
