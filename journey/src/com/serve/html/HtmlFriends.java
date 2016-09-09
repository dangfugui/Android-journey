package com.serve.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xiang.lvyou.R;

public class HtmlFriends {
	/**
	 * 
	 * @param start  开始的id
	 * @param end	 结束的id
	 * @return		List <HashMap<String, Object>>数据
	 * img_friendsheard  txt_name  txt_title  txt_time  txt_distance  img_image1  img_image2  img_image3  txt_cntent
	 */
	public List getFriendsDiary(int start,int end){
		List <HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
		for(int i=start;i<end;i++){
			HashMap<String,Object>item=new HashMap<String, Object>();
			item.put("friendsheard",R.drawable.aa_travel);
			item.put("name","name"+i);
			item.put("title","title"+i);
			item.put("time","time:"+i+":"+i);
			item.put("distance", i+"km");
			item.put("image1", R.drawable.aa_travel);
			item.put("image2", R.drawable.aa_travel);
			item.put("image3", R.drawable.aa_travel);
			item.put("cntent","cntent"+i);
			
			if(item.containsKey("title"));
			
			data.add(item);
		}
		return data;
	}
	
	/**
	 * 
	 * @param start  开始的id
	 * @param end	 结束的id
	 * @return		List <HashMap<String, Object>>数据
	 *  img_friendsheard	txt_name	txt_introduce	txt_distance
	 */
	public List getFriendsList(int start,int end){
		List <HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
		for(int i=start;i<end;i++){
			HashMap<String,Object>item=new HashMap<String, Object>();
			item.put("friendsheard",R.drawable.aa_travel);
			item.put("name","name"+i);
			item.put("introduce","introduce:"+i+":"+i);
			item.put("distance", i+"km");
			data.add(item);
		}
		return data;
	}
	
	
	
	/**
	 * 
	 * @param start  开始的id
	 * @param end	 结束的id
	 * @return		List <HashMap<String, Object>>数据
	 * img_friendsheard  txt_name  txt_title  txt_time  txt_distance  img_image1  img_image2  img_image3  txt_cntent
	 */
	public List getMysDiary(int start,int end){
		List <HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
		for(int i=start;i<end;i++){
			HashMap<String,Object>item=new HashMap<String, Object>();
			item.put("friendsheard",R.drawable.aa_travel);
			item.put("name","name"+i);
			item.put("title","title"+i);
			item.put("time","time:"+i+":"+i);
			item.put("distance", i+"km");
			item.put("image1", R.drawable.aa_travel);
			item.put("image2", R.drawable.aa_travel);
			item.put("image3", R.drawable.aa_travel);
			item.put("cntent","cntent"+i);
			data.add(item);
		}
		return data;
	}
}
