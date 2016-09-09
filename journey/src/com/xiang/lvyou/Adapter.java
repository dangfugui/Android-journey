package com.xiang.lvyou;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Adapter extends BaseAdapter {

	private Context context;
	private List<News> newsList;

	public Adapter(Context context, List<News> newsList) {
		this.context = context;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {
		return newsList.size();
	}

	@Override
	public News getItem(int arg0) {
		return newsList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.friends_story_item, null);
		}
		TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		TextView tvDesc = (TextView) view.findViewById(R.id.tvDesc);
		TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
		final ImageView ivPic = (ImageView) view.findViewById(R.id.ivPic);

		final News news = newsList.get(arg0);
		tvTitle.setText(news.getTitle());
		tvDesc.setText(news.getDesc());
		tvTime.setText(news.getTime());
		
		if(!news.getPic_url().equals("")){
			Picasso.with(context).load(news.getPic_url()).error(R.drawable.shop_photo_frame).into(ivPic);
		}
		return view;
	}

}
