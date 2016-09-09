package com.xiang.lvyou.url;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.impl.client.TunnelRefusedException;

import com.serve.html.Friend_info;
import com.xiang.lvyou.AB_FriendsActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class DataTool {
	private Context mcontext;
	private String filename="appdata";
	private String userkey="self-5";
	public DataTool(Context context) {
		this.mcontext = context;
	}
	
	/**
	 * 
	 * @param user 要保存的我的资料
	 */
	public void saveUser(Friend_info user){

		saveString(userkey+"userid", user.userid);
		saveString(userkey+"name", user.name);
		saveString(userkey+"username", user.username);
		saveString(userkey+"email", user.email);
		saveString(userkey+"userheader", user.userheader);
		saveString(userkey+"grades", user.grades);
		saveString(userkey+"phone", user.phone);
		saveString(userkey+"introduce", user.introduce);
		saveString(userkey+"country", user.country);
	}
	
	public Friend_info getUser(){
		Friend_info user=new Friend_info();
		user.userid=getString(userkey+"userid");
		user.name=getString(userkey+"name");
		user.username=getString(userkey+"username");
		user.email=getString(userkey+"email");
		user.userheader=getString(userkey+"userheader");
		user.grades=getString(userkey+"grades");
		user.phone=getString(userkey+"phone");
		user.introduce=getString(userkey+"introduce");
		user.country=getString(userkey+"country");
		if(user.userid==null||user.userid.equals("")){
			user.userid="2";
		}
		return user;
	}
	
	/**
	 * 保存参数
	 * @param name 姓名
	 * @param id id
	 */
	public void savesign(String name, String id) {
		 SharedPreferences preferences=mcontext.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.putString("id", id);
		editor.commit();
	}
	public void saveString(String key, String value) {
		 SharedPreferences preferences=mcontext.getSharedPreferences(filename, Context.MODE_PRIVATE);
		
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public String getString(String key){
		 SharedPreferences preferences=mcontext.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return preferences.getString(key, "");
	}
	/**
	 * 获取各项配置参数
	 * @return
	 */
	public Map<String, String> getsign(){
		 SharedPreferences preferences=mcontext.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", preferences.getString("name", ""));
		params.put("id", preferences.getString("id", ""));
		return params;
	}
	
}
