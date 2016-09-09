
package com.xiang.lvyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.MyURL;


/**
 * 这是find目录下景点推荐的适配器
 * 
 * 
 * */
public class ABI_ArticleImageAdapter extends BaseAdapter {

	private Context context;
	private String[] list;

	public ABI_ArticleImageAdapter(Context context, String[] list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int arg0) {
		return list[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.abii_diary_image_item, null);
		}
		
		ImageView image=(ImageView) view.findViewById(R.id.abii_image);
		//image.setImageResource(R.drawable.aa_travel);	
		new MyURL().setimage(context,image,list[arg0]);
		return view;
	}

}
