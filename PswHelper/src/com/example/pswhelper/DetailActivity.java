package com.example.pswhelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class DetailActivity extends Activity {
    SQLiteDatabase sqldb;  

    final DataHelper helper = new DataHelper(this, DataHelper.DB_NAME, null, DataHelper.DB_VERSION);  
    
    EditText psw = null;
    EditText website = null;
    EditText name = null;
    EditText memory = null;
    EditText dianya = null;
    Button btnUpdate = null;
    Button btnSaveAsNew = null;
    Button btnDel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        sqldb = helper.getWritableDatabase();  
        
        psw = (EditText) findViewById(R.id.psw);
        website = (EditText) findViewById(R.id.website);
        name = (EditText) findViewById(R.id.name);
        memory = (EditText) findViewById(R.id.memory);
        dianya = (EditText) findViewById(R.id.dianya);
        
        final Psw p = (Psw)getIntent().getBundleExtra("bundle").getParcelable("psw");
        psw.setText(p.getPsw());
        website.setText(p.getWebsite());
        name.setText(p.getName());

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnSaveAsNew = (Button) findViewById(R.id.btn_sava_as_new);
        btnDel = (Button)findViewById(R.id.btn_del);
        btnSaveAsNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String sql2 = "insert into t_psw (website,name,psw,memory,dianya) values(?,?,?,?,?)";
                 if(!website.getText().toString().isEmpty()){
                     sqldb.execSQL(sql2, new Object[]{website.getText().toString(),name.getText().toString(),psw.getText().toString(),"1024","220v"});
                 }
                 
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql2 = "update  t_psw   set website=?,name=?,psw=?,memory=?,dianya=?  where  _id=?";
                sqldb.execSQL(sql2, new Object[]{website.getText().toString(),name.getText().toString(),psw.getText().toString(),"1024","220v",p.getId()});
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {

                String sql2 = "delete from   t_psw  where  _id=?";
                sqldb.execSQL(sql2, new Object[]{p.getId()});
            
            }
        });
        
    }

}
