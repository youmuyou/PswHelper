package com.example.pswhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pswhelper.GestureLockView.OnGestureFinishListener;
import com.onecomm.tools.image.ImageTool;

public class GestureLoginActivity extends Activity {

	private GestureLockView gv;
	private TextView textView,textView1;
	private Animation animation;
	private RelativeLayout layout;
	private float x,currentX,tempX;
	private RelativeLayout.LayoutParams params1;
	private int width,height;
	private ImageView imageView;
    protected static boolean isLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture_login);
		
		gv = (GestureLockView) findViewById(R.id.gv);
		textView = (TextView) findViewById(R.id.textview);
		imageView = (ImageView) findViewById(R.id.imageview);
		layout = (RelativeLayout) findViewById(R.id.layout);
		
		animation = new TranslateAnimation(-10, 10, 0, 0);
		animation.setDuration(50);
		animation.setRepeatCount(10);
		animation.setRepeatMode(Animation.REVERSE);
		
		gv.setKey("0124678"); // Z ����
		gv.setOnGestureFinishListener(new OnGestureFinishListener() {
			@Override
			public void OnGestureFinish(boolean success,String key) {
				Log.e("key", key);
				if (!success) {
					textView.setVisibility(View.VISIBLE);
					textView.setText("error");
					textView.startAnimation(animation);
				}else {
					textView.setText("OK");

                    isLogin = true;
                    Intent intent  = new Intent(GestureLoginActivity.this,GridActivity.class);
                    startActivity(intent);
                
				}
			}
		});
		
		/*ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			data.add("aaaaaaaaaaaaaaaa");
		}
		Bitmap bitmap = ImageTool.writeDataToImage(data, 400, 300, Color.WHITE, Color.BLACK, 30, 20, 30);
		imageView.setImageBitmap(bitmap);*/
	}
}




















