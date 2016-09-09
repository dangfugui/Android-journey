
package com.xiang.lvyou.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serve.html.Friend_info;
import com.serve.html.LoadImg;
import com.serve.html.Scenic_info;
import com.serve.html.LoadImg.ImageDownloadCallBack;
import com.squareup.picasso.Picasso;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.MyURL;


/**
 * 这是find目录下景点推荐的适配器
 * 
 * 
 * */
public class ABA_FriendsListAdapter extends BaseAdapter {

	private Context context;
	private List<Friend_info> list;

	public ABA_FriendsListAdapter(Context context, List<Friend_info> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.abai_frienditem, null);
		}
		
		
		final ImageView friendsheard=(ImageView) view.findViewById(R.id.abai_img_friendsheard);
		TextView name=(TextView) view.findViewById(R.id.abai_txt_name);
		TextView introduce=(TextView) view.findViewById(R.id.abai_txt_introduce);
		TextView distance=(TextView) view.findViewById(R.id.abai_txt_distance);
		
		Friend_info item = this.list.get(arg0);
		
		name.setText(item.name);
		introduce.setText(item.introduce);
		distance.setText(item.grades);
		if(item.userheader.length()<5){
			//Picasso.with(context).load(list.getImage0()).error(R.drawable.shop_photo_frame).into(image0);
			friendsheard.setImageResource(R.drawable.shop_photo_frame);
			
		}else{
			new MyURL().setimage(context, friendsheard, item.userheader);
		}
		return view;
	}

}
