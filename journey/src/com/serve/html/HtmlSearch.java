package com.serve.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xiang.lvyou.R;



public class HtmlSearch {
	public List<HashMap<String, Object>> getSearchlist(){
		List <HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<10;i++){
			HashMap<String,Object> item=new HashMap<String, Object>();
			item.put("title","title"+i);
			item.put("image", R.drawable.aa_travel);
			item.put("introduce","introduce"+i);
			item.put("grade", R.drawable.star0);
			item.put("distance", i+"km");
			data.add(item);
			
		}
		return data;
	}
}
