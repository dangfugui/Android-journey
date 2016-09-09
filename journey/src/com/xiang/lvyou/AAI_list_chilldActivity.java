package com.xiang.lvyou;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.MyURL;
/**
 * find目录下，listView的项
 * 
 * */
public class AAI_list_chilldActivity extends Activity {

	private ImageView grade1;
	private ViewPager viewPager;
	private ImageView back;
	private ImageView add;
	private TextView name;
	private TextView des;
	private TextView txt_locale;

	private List<ImageView> viewList = new ArrayList<ImageView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_a_list_chilld);
		init();
	}
	
	private void init(){
		
		viewPager = (ViewPager) findViewById(R.id.a_item_child_viewpager);
		back = (ImageView) findViewById(R.id.aaa_img_back);
		add = (ImageView) findViewById(R.id.aaa_img_add);
		name = (TextView) findViewById(R.id.aaa_txt_title);
		grade1 = (ImageView) findViewById(R.id.a_item_child_img_grade);
		des = (TextView) findViewById(R.id.a_item_child_tex_introduce);
		txt_locale=(TextView) findViewById(R.id.aai_locale);
		MyOnClicListener my = new MyOnClicListener();
		back.setOnClickListener(my);
		add.setOnClickListener(my);
		
		
		final String[] imageUrl =Data.scenic.imgpath;
		
		//设置景点的名字
		name.setText(Data.scenic.scenicname);
		//设置景点的描述
		des.setText(Data.scenic.scenicdes);
		txt_locale.setText(Data.scenic.locale);
		//设置景点的等级
		switch(Data.scenic.star){
		case 0:
			grade1.setImageResource(R.drawable.star0);	
			break;
		case 1:
			grade1.setImageResource(R.drawable.star1);
			break;
		case 2:
			grade1.setImageResource(R.drawable.star2);
			break;
		case 3:
			grade1.setImageResource(R.drawable.star3);
			break;
		case 4:
			grade1.setImageResource(R.drawable.star4);
			break;
		case 5:
			grade1.setImageResource(R.drawable.star5);
			break;
		default:
			grade1.setImageResource(R.drawable.star0);
			break;
		}
		
		//设置viewpager的属性
		viewPager.setAdapter(new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(viewList.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView imageView = new ImageView(AAI_list_chilldActivity.this);
				imageView.setScaleType(ScaleType.FIT_XY);
				if(!imageUrl[position].equals("")){
					new MyURL().setimage(getApplicationContext(), imageView, imageUrl[position]);
					//Picasso.with(getApplicationContext()).load(imageUrl[position]).error(R.drawable.ic_action_favorite_off_normal).into(imageView);
				}
				container.addView(imageView);
				viewList.add(imageView);
				return imageView;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {//动态返回图片的数量，返回的最大值时5
				return imageUrl.length;
			}
		});

		
	}

	private class MyOnClicListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.aaa_img_back:
				finish();
				break;
			case R.id.aaa_img_add:
				Toast.makeText(getApplicationContext(), "like", 1).show();
				break;
			default:
				break;
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.a_list_chilld, menu);
		return true;
	}

}
