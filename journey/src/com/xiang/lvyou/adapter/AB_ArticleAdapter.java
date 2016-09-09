
package com.xiang.lvyou.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serve.html.Article_info;
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
public class AB_ArticleAdapter extends BaseAdapter {
	private int i;
	private Context context;
	private List<Article_info> list;
	private List<Bitmap> bitmapList;

	public AB_ArticleAdapter(Context context, List<Article_info> list) {
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
					R.layout.abi_friendsdiary, null);
		}
		ImageView friendsheard=(ImageView) view.findViewById(R.id.abi_img_friendsheard);
		final ImageView image1=(ImageView) view.findViewById(R.id.abi_img_image1);
		final ImageView image2=(ImageView) view.findViewById(R.id.abi_img_image2);
		final ImageView image3=(ImageView) view.findViewById(R.id.abi_img_image3);
		TextView  name=(TextView) view.findViewById(R.id.abi_txt_name);
		TextView  title=(TextView) view.findViewById(R.id.abi_txt_title);
		TextView  content=(TextView) view.findViewById(R.id.abi_txt_content);
		TextView  time=(TextView) view.findViewById(R.id.abi_txt_time);

		Article_info item = this.list.get(arg0);
		
		name.setText(item.username);
		title.setText(item.header);
		content.setText(item.contents);
		time.setText(item.sendTime);
		image1.setImageResource(R.drawable.shop_photo_frame);
		image2.setImageResource(R.drawable.shop_photo_frame);
		image3.setImageResource(R.drawable.shop_photo_frame);
		 new MyURL().setimage(context, friendsheard, item.userimage);
		if(item.imageList!=null){
			switch (item.imageList.length) {
			default:
			
			case 3:
				 new MyURL().setimage(context, image3, item.imageList[2]);
			case 2:
				 new MyURL().setimage(context, image2, item.imageList[1]);
			case 1:
				 new MyURL().setimage(context, image1, item.imageList[0]);
			case 0:break;
			}
		}
		
		
		return view;
	}

	
	

}
