package com.dang.view;

import com.serve.html.Article_info;
import com.xiang.lvyou.R;
import com.xiang.lvyou.adapter.ABI_ArticleImageAdapter;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.MyURL;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ABI_DiaryItem extends Activity{

	private TextView title;
	private ImageView img_back;
	private ImageView img_add;
	private ImageView heard;
	private TextView name;
	private TextView txt_title;
	private TextView time;
	private TextView content;
	private Article_info article;
	
	private LinearLayout mGallery;
	private LayoutInflater mInflater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aba_activity_diary);
		mGallery = (LinearLayout) findViewById(R.id.aba_liner);
		mInflater = LayoutInflater.from(this);
		initview();
	
	}
	
	private void initview() {
		// TODO 自动生成的方法存根
		article=Data.article;
		MyOnclickListener listener =new MyOnclickListener();
		title=(TextView) findViewById(R.id.aba_title);			
		title.setText(article.header);
		img_back=(ImageView) findViewById(R.id.aba_img_back);	
		img_back.setOnClickListener(listener);
		img_add=(ImageView) findViewById(R.id.aba_img_add);	
		img_add.setOnClickListener(listener);
		heard=(ImageView) findViewById(R.id.aba_img_friendsheard);
		new MyURL().setimage(this,heard, article.userimage);
		name=(TextView) findViewById(R.id.aba_txt_name);
		name.setText(article.username);
		txt_title=(TextView) findViewById(R.id.aba_txt_title);
		txt_title.setText(article.header);
		time=(TextView) findViewById(R.id.aba_txt_time);
		time.setText(article.sendTime);
		content=(TextView) findViewById(R.id.aba_txt_content);
		content.setText(article.contents);
		addimage();
	
	}
	
	
	
	private void addimage() {
		mGallery = (LinearLayout) findViewById(R.id.aba_liner);
		//View view = mInflater.inflate(R.layout.abii_diary_image_item,mGallery, false);
		for (int i = 0; i <article.imageList.length; i++)
		{
			View view = mInflater.inflate(R.layout.abii_diary_image_item,mGallery, false);
			
			ImageView img = (ImageView) view.findViewById(R.id.abii_image);
			//img.setImageResource(R.drawable.aa_travel);
			new MyURL().setimage(getApplicationContext(), img, article.imageList[i]);
			mGallery.addView(view);
			
		}
		
	}



	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.aba_img_back:
				finish();
				break;
			case R.id.aba_img_add:
				Toast.makeText(getApplicationContext(), "like", 1).show();
				break;
			default:
				break;
			}
		}
	}
	
	
}
