package com.example.pswhelper;

import java.util.ArrayList;

import android.app.Activity;  
import android.content.Intent;
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;  
import android.widget.ListView;  
  
public class GridActivity extends Activity {  
  
    SQLiteDatabase sqldb;  

    final DataHelper helper = new DataHelper(this, DataHelper.DB_NAME, null, DataHelper.DB_VERSION);  
    // DbHelper类在DbHelper.java文件里面创建的  
    ListView lv;  
    ArrayList<Psw>  AllPsw = new ArrayList<Psw>();
    
    Button btnPrefenceSetting = null;
    Button btnHealthSetting = null;
    Button btnUploadEmail = null;
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        if (!LoginActivity.isLogin) {
            finish();
        }
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        sqldb = helper.getWritableDatabase();  
        lv = (ListView) findViewById(R.id.lv);  
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(GridActivity.this,DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("psw", AllPsw.get(arg2));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
        
        btnPrefenceSetting = (Button) findViewById(R.id.btn_preffence);
        btnHealthSetting = (Button) findViewById(R.id.btn_healthy_setting);
        btnUploadEmail = (Button) findViewById(R.id.btn_upload_email);
        btnPrefenceSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridActivity.this,PreferenceActivity.class);
                startActivity(intent);
            }
        });
        btnHealthSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridActivity.this,HealthActivity.class);
                startActivity(intent);
            }
        });
        btnUploadEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

		        final MailUtils sendmail = new MailUtils();
		        sendmail.setHost("smtp.163.com");
		        sendmail.setUserName("yzyspy@163.com");
		        sendmail.setPassWord("YZYspy20130909");
		      //  sendmail.setTo("chengyue@accfutures.com,305597562@qq.com,chengli_68@163.com");
		        sendmail.setTo("yangzhongyu@xiaomi.com");
		        sendmail.setFrom("yzyspy@163.com");
		        
		        sendmail.setSubject("MyPassword ");
		        

	        	StringBuilder sb  = new StringBuilder();
		        for(Psw p : AllPsw){
		        	sb.append(p.getWebsite() + " "+p.getName() + " "+p.getPsw() +"\n");
		        }
		        
		        sendmail.setContent(sb.toString());
		        // Mail sendmail = new
		        // Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","你好","胃，你好吗？");
		       // sendmail.attachfile("d:\\news.rar");
		       // sendmail.attachfile("/home/yangzhongyu/bin/repo.zip");

		        new Thread(new Runnable() {
					@Override
					public void run() {
						 sendmail.sendMail();
					}
				}).start();
			}
		});
    }  
  
    // 更新listview  
    public void updatelistview() {  

        Cursor cr = sqldb.query("t_psw", null, null, null, null, null,null);  
  
        String id = cr.getColumnName(0);// 获取第1列  
        String website = cr.getColumnName(1);// 获取第3列  
        String name = cr.getColumnName(2);// 获取第5列  
        String psw = cr.getColumnName(3);// 获取第6列  
        String[] ColumnNames = { id,website, name, psw }; 
  
        ListAdapter adapter = new MySimpleCursorAdapter(this,  
                R.layout.listviewlayout, cr, ColumnNames, new int[] { R.id.id,  
                        R.id.website, R.id.name, R.id.psw });  
        // layout为listView的布局文件，包括三个TextView，用来显示三个列名所对应的值  
        // ColumnNames为数据库的表的列名  
        // 最后一个参数是int[]类型的，为view类型的id，用来显示ColumnNames列名所对应的值。view的类型为TextView  
        lv.setAdapter(adapter);

        AllPsw.clear();
        while(cr.moveToNext())
        {
            Psw p = new Psw();
            p.setId(cr.getInt(0));
            p.setName(cr.getString(2));
            p.setWebsite(cr.getString(1));
            p.setPsw(cr.getString(3));
            AllPsw.add(p);
        }

    }  
    @Override
    protected void onResume() {
        super.onResume();
        this.updatelistview();
    }

    @Override  
    protected void onDestroy() {// 关闭数据库  
        super.onDestroy();  
        if (helper != null) {  
            helper.close();  
        }  
    }  
}  
