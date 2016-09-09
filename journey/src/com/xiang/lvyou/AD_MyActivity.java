package com.xiang.lvyou;


import com.serve.html.Friend_info;
import com.serve.html.HttpGetThread;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.login.ADA_LoginActivity;
import com.xiang.lvyou.login.ADB_RegistetActivity;
import com.xiang.lvyou.login.ADC_UserSetActivity;
import com.xiang.lvyou.url.DataTool;
import com.xiang.lvyou.url.MyURL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 我的模块
 * */

public class AD_MyActivity extends Activity {

	private TextView mMy_register, mMy_login;
	private TextView txt_myname;
	private TextView txt_introduce;
	private TextView txt_set;
	private TextView txt_sign_in;
	private TextView txt_logout;
	private ImageView img_header;
	private LinearLayout lin_updata;
	private LinearLayout lin_feedback;;
	private LinearLayout lin_about;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ad_activity_my);
		initView();
	}

	private void initView() {
		mMy_register = (TextView) findViewById(R.id.ad_txt_register);
		mMy_login = (TextView) findViewById(R.id.ad_txt_login);
		txt_myname=(TextView) findViewById(R.id.ad_txt_myname);
		txt_introduce=(TextView) findViewById(R.id.ad_txt_introduce);
		txt_set=(TextView) findViewById(R.id.ad_txt_set);
		txt_sign_in=(TextView) findViewById(R.id.ad_txt_sign_in);
		txt_logout=(TextView) findViewById(R.id.ad_txt_logout);
		img_header=(ImageView) findViewById(R.id.ad_img_header);
		lin_updata=(LinearLayout) findViewById(R.id.ad_linupdata);
		lin_feedback=(LinearLayout) findViewById(R.id.ad_linfeedback);
		lin_about=(LinearLayout) findViewById(R.id.ad_linabout);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		mMy_register.setOnClickListener(mOnclickListener);
		mMy_login.setOnClickListener(mOnclickListener);
		txt_set.setOnClickListener(mOnclickListener);
		txt_logout.setOnClickListener(mOnclickListener);
		img_header.setOnClickListener(mOnclickListener);
		txt_sign_in.setOnClickListener(mOnclickListener);
		lin_updata.setOnClickListener(mOnclickListener);
		lin_feedback.setOnClickListener(mOnclickListener);
		lin_about.setOnClickListener(mOnclickListener);
	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			switch (mID) {
			case R.id.ad_txt_register:
				Intent intent = new Intent(AD_MyActivity.this,
						ADB_RegistetActivity.class);
				startActivity(intent);
				break;
			case R.id.ad_txt_login:
				
				 Intent intent2 = new Intent(AD_MyActivity.this,
				 ADA_LoginActivity.class); startActivity(intent2);
				
				break;
			case R.id.ad_txt_set:
			case R.id.ad_img_header:
				Intent intent3=new Intent(AD_MyActivity.this,ADC_UserSetActivity.class);
				startActivity(intent3);
				break;
			case R.id.ad_txt_logout:
				Friend_info user=new Friend_info();
				new DataTool(getApplicationContext()).saveUser(user);
				txt_myname.setText("");
				Toast.makeText(getApplicationContext(), "log out", 1).show();
				break;
			case R.id.ad_txt_sign_in:
				Toast.makeText(getApplicationContext(), "sign in  +1", 1).show();
				break;
			case R.id.ad_linupdata:
				Toast.makeText(getApplicationContext(), "Has been the latest version", 1).show();
				break;
			case R.id.ad_linfeedback:
				break;
			case R.id.ad_linabout:
				dialog("about-journey"," For details please visit  http://dangfugui.cscces.net/");
				break;
			}
		}

	}
	  protected void onStart() {  
	        super.onStart();  
	        Friend_info my=new DataTool(getApplicationContext()).getUser();
	        txt_introduce.setText(my.introduce);
	        if(my.name.length()>1){
	        	txt_myname.setText(my.name);
	        }
	        if(my.userheader.length()>5){
	        	new MyURL().setimage(getApplicationContext(), img_header, my.userheader);
	        }
	    }  
	  private void dialog(String title,String content) {
			Builder builder=new AlertDialog.Builder(this);
			//AlertDialog.Builder builder=new Builder(this);
			//AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle(title);
			builder.setMessage(content);
			builder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					
						// TODO Auto-generated method stub
						String url = MyURL.localhost+MyURL.index; // web address
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(url));
						startActivity(intent);
					dialog.dismiss();
				}
			});
			builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
}
