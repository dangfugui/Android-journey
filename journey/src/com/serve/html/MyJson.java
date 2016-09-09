package com.serve.html;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.xiang.lvyou.News;

/**
 * Json字符串解析工具类
 */

public class MyJson {

	// 解析SearchList列表
	public List<Scenic_info> getScenicInfo(String value) {
		Log.e("dd",value);
		List<Scenic_info> list = null;
		try {
			JSONArray jay = new JSONArray(value);
			list = new ArrayList<Scenic_info>();
			for (int i = 0; i < jay.length(); i++) {
				JSONObject job = jay.getJSONObject(i);
				Scenic_info info = new Scenic_info();
				info.scenicname=(job.getString("scenicname"));
				info.scenicdes=(job.getString("scenicdes"));
				info.star=(job.getInt("star"));
				info.locale=job.getString("locale");
				if(job.getString("imgpath").length()>5){
					info.imgpath=(job.getString("imgpath")).split(",");
				}
				list.add(info);
			}
		} catch (JSONException e) {
		}
		return list;
	}
	
	
	
	// 解析商铺列表
		public List<News> getShopList(String value) {
			List<News> list = null;
			try {
				
				JSONArray jay = new JSONArray(value);
				list = new ArrayList<News>();
				for (int i = 0; i < jay.length(); i++) {
					JSONObject job = jay.getJSONObject(i);
					News info = new News();
					info.setTitle(job.getString("title"));
					info.setDesc(job.getString("desc"));
					info.setTime(job.getString("time"));
					info.setContent_url(job.getString("content_url"));
					info.setPic_url(job.getString("pic_url"));
					
					list.add(info);
				}
			} catch (JSONException e) {
			}
			return list;
		}



		public List<Friend_info> getFriendInfo(String json) {
			List<Friend_info> list = null;
			try {
				JSONArray jay = new JSONArray(json);
				list = new ArrayList<Friend_info>();
				for (int i = 0; i < jay.length(); i++) {
					JSONObject job = jay.getJSONObject(i);
					Friend_info info = new Friend_info();
					info.userid=job.getString("userid");
					info.name=job.getString("name");
					info.username=job.getString("username");
					info.grades=job.getString("grades");
					info.userheader=job.getString("userheader");
					info.introduce=job.getString("introduce");
					info.country=job.getString("country");
					info.phone=job.getString("phone");
					info.email=job.getString("email");
					list.add(info);
				}
			} catch (JSONException e) {
			}
			return list;
		}
		
		public List<Article_info> getArticleInfo(String json) {
			List<Article_info> list = null;
			try {
				JSONArray jay = new JSONArray(json);
				list = new ArrayList<Article_info>();
				for (int i = 0; i < jay.length(); i++) {
					JSONObject job = jay.getJSONObject(i);
					Article_info info = new Article_info();
					//info.name=job.getString("name");
					info.articleid=job.getString("articleid");
					info.username=job.getString("username");
					info.userimage=job.getString("userimage");
					info.header=job.getString("header");
					info.contents=job.getString("contents");
					info.sendTime=job.getString("sendTime");
					info.starcount=job.getString("starcount");
					if(job.getString("image").length()>5){
						info.imageList=(job.getString("image")).split(",");
					}else{
						info.imageList=null;
					}
					
					list.add(info);
				}
			} catch (JSONException e) {
			}
			return list;
		}
		
		
		
		// 解析附近用户的方法
		public List<UserInfo> getNearUserList(String result) {
			List<UserInfo> list = null;
			try {
				JSONArray jay = new JSONArray(result);
				list = new ArrayList<UserInfo>();
				for (int i = 0; i < jay.length(); i++) {
					JSONObject job = jay.getJSONObject(i);
					UserInfo info = new UserInfo();
					info.setUserid(job.getString("userid"));
					list.add(info);
				}
			} catch (JSONException e) {
			}
			return list;
		}
		
		



		

}
