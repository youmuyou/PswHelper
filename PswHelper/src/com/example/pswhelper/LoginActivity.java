package com.example.pswhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {
    static boolean isLogin = false;
    EditText psw = null;
    Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn = (Button) findViewById(R.id.btn);
        psw = (EditText) findViewById(R.id.psw);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("123".equals(psw.getText().toString())){
                    isLogin = true;
                    Intent intent  = new Intent(LoginActivity.this,GridActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        isLogin = false;
        psw.setText("");
    }

}
