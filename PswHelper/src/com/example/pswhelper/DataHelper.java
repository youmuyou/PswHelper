package com.example.pswhelper;

import android.content.Context;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteDatabase.CursorFactory;  
import android.database.sqlite.SQLiteOpenHelper;  
import android.util.Log;
  
public class DataHelper extends SQLiteOpenHelper {  
  
    public static String DB_NAME = "DB.sqlite";  
    public static String DB_TABLE = "num";  
    public static int DB_VERSION = 3;  
    @Override  
    public synchronized void close() {  
        // TODO Auto-generated method stub  
        super.close();  
    }  
  
    public DataHelper(Context context, String name, CursorFactory factory,  
            int version) {  
        super(context, name, factory, version);  
        // TODO Auto-generated constructor stub  
  
    }  
  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        // TODO Auto-generated method stub  
  
        String sql = "CREATE  TABLE t_psw (_id INTEGER PRIMARY KEY , website VARCHAR ,name VARCHAR,psw VARCHAR,memory VARCHAR,dianya VARCHAR)";
        db.execSQL(sql);
        String sql2 = "insert into t_psw (website,name,psw,memory,dianya) values(?,?,?,?,?)";
        db.execSQL(sql2, new Object[]{"csdn","yzy","111","1024","220v"});
  
    }  
     //升级DB_VERSION，自动调用onUpgrade
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        Log.d("yzy","oldVersion="+oldVersion+"newVersion="+newVersion);
        String sql2 = "insert into t_psw (website,name,psw,memory,dianya) values(?,?,?,?,?)";
        db.execSQL(sql2, new Object[]{"csdn","yzy","111","1024","220v"});
    }  
  
}  