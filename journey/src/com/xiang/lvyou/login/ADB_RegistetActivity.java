package com.xiang.lvyou.login;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appkefu.lib.interfaces.KFIMInterfaces;
import com.serve.html.Friend_info;
import com.serve.html.HttpPostThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.DataTool;

public class ADB_RegistetActivity extends Activity {
	private EditText mRegistration_user, mRegistration_name,
			mRegistration_password, mRegistration_password2;
	private TextView mRegistration_OK;
	private ImageView mRegistration_back;
	private String url = null;
	private String value = null;
	private String username = null;
	private String password = null;
	private String password2 = null;
	private String email = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.adb_activity_registration);
		initView();
	}

	private void initView() {

		mRegistration_back = (ImageView) findViewById(R.id.Registration_back);
		mRegistration_user = (EditText) findViewById(R.id.Registration_user);
		mRegistration_name = (EditText) findViewById(R.id.Registration_name);
		mRegistration_password = (EditText) findViewById(R.id.Registration_password);
		mRegistration_password2 = (EditText) findViewById(R.id.Registration_password2);
		mRegistration_OK = (TextView) findViewById(R.id.Registration_OK);
		MyOnClickListener my = new MyOnClickListener();
		mRegistration_back.setOnClickListener(my);
		mRegistration_OK.setOnClickListener(my);

	}
	
	public boolean isEmail(String email) {

		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

		Pattern p = Pattern.compile(str);

		Matcher m = p.matcher(email);

		return m.matches();

		}

	

	private void reginstet() {
		url = Model.REGISTET;
		value = "{\"username\":\"" + username + "\",\"password\":\"" + password+ "\",\"email\":\"" + email+ "\"}";
		Log.e("qianpengyu", value);
		ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));
	}

	class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			int mId = v.getId();
			switch (mId) {
			case R.id.Registration_back:
				finish();
				break;

			case R.id.Registration_OK:

				username = mRegistration_name.getText().toString();
				password = mRegistration_password.getText().toString();
				password2 = mRegistration_password2.getText().toString();
				email = mRegistration_user.getText().toString();
					
				
				
				if (!username.equalsIgnoreCase(null)
						&& !password.equalsIgnoreCase(null)
						&& !username.equals("") && !password.equals("")) {
					if (username.length() >= 6 && password.length() >= 6 && password.equals(password2)&& isEmail(email)) {
						// reginstet();// 请求注册功能
						registerThread();// 注册app客服
					} else if(!password.equals(password2)) {
//						new  AlertDialog.Builder(RegistetActivity.this)    
//						.setTitle("提示" )  
//						.setMessage("两次输入的密码不一致" )  
//						.setPositiveButton("确定" ,  null )  
//						.show();  
						Toast.makeText(ADB_RegistetActivity.this, "两次输入的密码不一致", 0).show();
						mRegistration_password2.getText().clear();		
					}else if(!isEmail(email)) {
						Toast.makeText(getApplicationContext(), "邮箱格式不正确", 0).show();
						mRegistration_user.getText().clear();
						
					}else {
					
						Toast.makeText(ADB_RegistetActivity.this, "用户名或密码最少为6位", 1)
								.show();
					}
				} else {
					Toast.makeText(ADB_RegistetActivity.this, "请先填写用户名或密码", 1)
							.show();
				}

				break;

			}

		}
	}

	// INSERT INTO `quser`( `uname`, `upassword`) VALUES
	// ('111111','111111');
	Handler hand1 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(ADB_RegistetActivity.this, "请求失败，服务器故障", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(ADB_RegistetActivity.this, "服务器无响应", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				Log.e("qiangpengyu", "result:" + result);
				if (result.equals("ok")) {
					Toast.makeText(ADB_RegistetActivity.this, "注册成功,请登陆", 1).show();
					Intent intent = new Intent();
					intent.putExtra("NameValue", username);
					intent.putExtra("PasswordValue", password);
					setResult(2, intent);
					finish();
				} else if (result.trim().equals("no")) {
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					mRegistration_password2.setText("");
					mRegistration_user.setText("");
					// Toast.makeText(RegistetActivity.this, "用户名以存在,请重新注册",
					// 1)
					// .show();
					return;
				} else {
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					Toast.makeText(ADB_RegistetActivity.this, "注册失败", 1).show();
					return;
				}

			}
		};
	};

	public void registerThread() {

		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				Integer result = (Integer) msg.obj;

				if (result == 1) {
					Log.d("注册成功", "注册成功");
					reginstet();// 请求注册功能
					// Toast.makeText(RegistetActivity.this, "注册成功",
					// Toast.LENGTH_LONG).show();
				} else if (result == -400001) {
					Log.d("注册失败", "用户名长度最少为6");
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					mRegistration_password2.setText("");
					Toast.makeText(ADB_RegistetActivity.this,
							"注册失败:用户名长度最少为6(错误码:-400001)", Toast.LENGTH_LONG)
							.show();
				} else if (result == -400002) {
					Log.d("注册失败", "密码长度最少为6");
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					Toast.makeText(ADB_RegistetActivity.this,
							"注册失败:密码长度最少为6(错误码:-400002)", Toast.LENGTH_LONG)
							.show();
				} else if (result == -400003) {
					Log.d("注册失败", "此用户名已经被注册");
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					Toast.makeText(ADB_RegistetActivity.this, "注册失败:此用户名已经被注册",
							Toast.LENGTH_LONG).show();
				} else if (result == -400004) {
					Log.d("注册失败", "用户名含有非法字符");
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					Toast.makeText(ADB_RegistetActivity.this, "注册失败:用户名含有非法字符",
							Toast.LENGTH_LONG).show();
				} else if (result == 0) {
					Log.d("注册失败",
							"其他原因：有可能是短时间内重复注册，为防止恶意注册，服务器对同一个IP注册做了时间间隔限制，即10分钟内同一个IP只能注册一个账号");
					mRegistration_name.setText("");
					mRegistration_password.setText("");
					Toast.makeText(ADB_RegistetActivity.this, "注册失败:别太累了，等下再注册",
							Toast.LENGTH_LONG).show();
				}
			}
		};

		new Thread() {
			public void run() {
				Message msg = new Message();
				// 目前用户名为整个微客服唯一，建议开发者在程序内部将appkey做为用户名的后缀，
				// 这样可以保证用户名的唯一性
				// 注册接口，返回结果为int
				msg.obj = KFIMInterfaces.register(Model.APPKEY + username,
						password);
				handler.sendMessage(msg);
			}
		}.start();

	}
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "请求失败，服务器故障", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "服务器无响应", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
			
				Toast.makeText(getApplicationContext(), result, 1).show();
			}
			
		};
	};

}
