
package com.xiang.lvyou.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.serve.html.Scenic_info;
import com.squareup.picasso.Picasso;
import com.xiang.lvyou.R;
import com.xiang.lvyou.url.MyURL;


/**
 * 这是find目录下景点推荐的适配器
 * 
 * 
 * */
public class AA_ScenicAdapter extends BaseAdapter {

	private Context context;
	private static List<Scenic_info> list;

	public AA_ScenicAdapter(Context context, List<Scenic_info> list) {
		this.context = context;
		AA_ScenicAdapter.list = list;
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
					R.layout.aaai_searchitem, null);
		}
		
		TextView name=(TextView) view.findViewById(R.id.aaai_txt_title);
		TextView des= (TextView) view.findViewById(R.id.aaai_introduce);
		ImageView image0 = (ImageView) view.findViewById(R.id.aaai_image);
		ImageView grade = (ImageView) view.findViewById(R.id.aaai_img_grade);
		Scenic_info item = AA_ScenicAdapter.list.get(arg0);

		name.setText(item.scenicname);
		des.setText(item.scenicdes);
		
		switch(item.star){
		case 0:
			grade.setImageResource(R.drawable.star0);
			break;
		case 1:
			grade.setImageResource(R.drawable.star1);
			break;
		case 2:
			grade.setImageResource(R.drawable.star2);
			break;
		case 3:
			grade.setImageResource(R.drawable.star3);
			break;
		case 4:
			grade.setImageResource(R.drawable.star4);
			break;
		case 5:
			grade.setImageResource(R.drawable.star5);
			break;
		default:
			grade.setImageResource(R.drawable.star0);
			break;
		}
		
		
		if(!item.imgpath[0].equals("")){
			new MyURL().setimage(context, image0, item.imgpath[0]);
			//Picasso.with(context).load(item.imgpath[0]).error(R.drawable.shop_photo_frame).into(image0);
		}
		return view;
	}

}
