package com.xiang.lvyou.login;


import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appkefu.lib.interfaces.KFIMInterfaces;
import com.appkefu.lib.service.KFMainService;
import com.appkefu.lib.service.KFXmppManager;
import com.appkefu.lib.utils.KFSLog;
import com.serve.html.Friend_info;
import com.serve.html.HttpPostThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.serve.html.UserInfo;
import com.xiang.lvyou.AD_MyActivity;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.DataTool;

public class ADA_LoginActivity extends Activity {

	private ImageView mLogin_back;
	private EditText mLogin_user, mLogin_password;
	private TextView mLogin_OK, mLogin_wangjimima, mLogin_zhuce;
	private String NameValue = null;
	private String PasswordValue = null;
	private String url = null;
	private String value = null;
	private MyJson myJson = new MyJson();

	// private KFSettingsManager mSettingsMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ada_activity_login);
		// mSettingsMgr = KFSettingsManager.getSettingsManager(this);
		initView();

	}

    

	private void initView() {
		mLogin_back = (ImageView) findViewById(R.id.Login_back);
		mLogin_OK = (TextView) findViewById(R.id.Login_OK);
		mLogin_user = (EditText) findViewById(R.id.Login_user);
		String name=new DataTool(getApplicationContext()).getString("username");
		if(name.length()>4){
			mLogin_user.setText(name);
		}
		mLogin_password = (EditText) findViewById(R.id.Login_password);
		mLogin_wangjimima = (TextView) findViewById(R.id.Login_wangjimima);
		mLogin_zhuce = (TextView) findViewById(R.id.Login_zhuce);
		MyOnClickListener my = new MyOnClickListener();
		mLogin_back.setOnClickListener(my);
		mLogin_OK.setOnClickListener(my);
		mLogin_wangjimima.setOnClickListener(my);
		mLogin_zhuce.setOnClickListener(my);
	}

	class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mId = v.getId();
			switch (mId) {
			case R.id.Login_back:
				finish();
				break;
			case R.id.Login_OK:
				NameValue = mLogin_user.getText().toString();
				new DataTool(ADA_LoginActivity.this).saveString("username",NameValue);
				PasswordValue = mLogin_password.getText().toString();
				Log.e("qianpengyu", "NameValue" + NameValue + "  PasswordValue"
						+ PasswordValue);
				if (NameValue.equalsIgnoreCase(null)
						|| PasswordValue.equalsIgnoreCase(null)
						|| NameValue.equals("") || PasswordValue.equals("")) {
					Toast.makeText(ADA_LoginActivity.this, "别闹,先把帐号密码填上", 1).show();
				} else {
					// 登录接口
					KFIMInterfaces.login(ADA_LoginActivity.this, Model.APPKEY
							+ NameValue, PasswordValue);
					login();
				}
				break;
			case R.id.Login_zhuce:
				Intent intent =new Intent(ADA_LoginActivity.this,ADB_RegistetActivity.class);
				startActivity(intent);
			}

		}

	}

	private void login() {
		url = Model.LOGIN;
		value = "{          \"username\":\"" + NameValue
				+ "\",             \"password\":\"" + PasswordValue + "\"}";
		//
		Log.e("qianpengyu", value);
		ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));
	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(ADA_LoginActivity.this, "请求失败，服务器故障", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(ADA_LoginActivity.this, "服务器无响应", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if(result==null) return;
				Log.e("qiangpengyu", result);
				if (result.equalsIgnoreCase("NOUSER")) {
					mLogin_user.setText("");
					mLogin_password.setText("");
					Toast.makeText(ADA_LoginActivity.this, "用户名不存在", 1).show();
					return;
				} else if (result.equalsIgnoreCase("NOPASS")) {
					mLogin_password.setText("");
					Toast.makeText(ADA_LoginActivity.this, "密码错误", 1).show();
					return;
				} else if (result != null) {
					Toast.makeText(ADA_LoginActivity.this, "登录成功"+result, 1).show();
					List<Friend_info> newList = myJson.getFriendInfo(result);
					if (newList != null) {
						Friend_info my=newList.get(0);
						Model.MYUSERINFO =my;

						new DataTool(getApplicationContext()).saveUser(my);
						if(my.name.length()<1){
							Intent intent = new Intent(ADA_LoginActivity.this,
							ADC_UserSetActivity.class);
							startActivity(intent);
						}else{		
							Intent intent = new Intent(ADA_LoginActivity.this,
									AD_MyActivity.class);
							startActivity(intent);
						}
					}
/******************************************************************************************************************/					
					/*Intent intent = new Intent(ADA_LoginActivity.this,
							UserInfoActivity.class);
					startActivity(intent);*/

					SharedPreferences sp = ADA_LoginActivity.this
							.getSharedPreferences("UserInfo", MODE_PRIVATE);
					Log.e("SharedPreferencesOld",
							sp.getString("UserInfoJson", "none"));
					SharedPreferences.Editor mSettinsEd = sp.edit();
					mSettinsEd.putString("UserInfoJson", result);
					// 提交保存
					mSettinsEd.commit();

					// 设置个人资料"NICKNAME"
					KFIMInterfaces.setVCardField(ADA_LoginActivity.this,
							"NICKNAME", Model.MYUSERINFO.username);

					Log.e("SharedPreferencesNew",
							sp.getString("UserInfoJson", "none"));
					finish();
				}
			}
		};
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == 2 && data != null) {
			NameValue = data.getStringExtra("NameValue");
			mLogin_user.setText(NameValue);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		String name=new DataTool(getApplicationContext()).getString("username");
		if(name.length()>4){
			mLogin_user.setText(name);
		}
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(KFMainService.ACTION_XMPP_CONNECTION_CHANGED);
		registerReceiver(mXmppreceiver, intentFilter);

	}

	@Override
	protected void onStop() {
		super.onStop();

		KFSLog.d("onStop");
		unregisterReceiver(mXmppreceiver);
	}

	private BroadcastReceiver mXmppreceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(KFMainService.ACTION_XMPP_CONNECTION_CHANGED)) {
				updateStatus(intent.getIntExtra("new_state", 0));
			}

		}
	};

	private void updateStatus(int status) {
		switch (status) {
		case KFXmppManager.CONNECTED:
			KFSLog.d("登录成功");
			break;
		case KFXmppManager.DISCONNECTED:
			KFSLog.d("未登录");
			break;
		case KFXmppManager.CONNECTING:
			KFSLog.d("登录中");
			break;
		case KFXmppManager.DISCONNECTING:
			KFSLog.d("登出中");
			break;
		case KFXmppManager.WAITING_TO_CONNECT:
		case KFXmppManager.WAITING_FOR_NETWORK:
			KFSLog.d("waiting to connect");
			break;
		default:
			throw new IllegalStateException();
		}
	}

}