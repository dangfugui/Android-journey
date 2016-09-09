package com.dang.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.serve.html.HtmlSearch;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.Scenic_info;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.AAI_list_chilldActivity;
import com.xiang.lvyou.R;
import com.xiang.lvyou.AA_SearchActivity;
import com.xiang.lvyou.adapter.AA_ScenicAdapter;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.MyURL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AA_RecommendActivity extends Activity implements OnItemClickListener{
	
	private PullToRefreshListView listview;
	private TextView txt_title;
	private ImageView img_back;
	private String title;
	private AA_ScenicAdapter adapter;
	private List<Scenic_info> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaa_activity_recommend);
		initview();
	}
	@SuppressLint("NewApi") @SuppressWarnings("unchecked")
	private void initview() {
		// TODO 自动生成的方法存根
		txt_title=(TextView) findViewById(R.id.aaa_txt_title);
		img_back=(ImageView) findViewById(R.id.aaa_img_back);
		listview=(PullToRefreshListView) findViewById(R.id.aaa_listview);
	
		
		Intent intent=this.getIntent();
		title=intent.getStringExtra("title");

		if(title!=null){
				txt_title.setText(title);
		}
		addlist();
		MyOnclickListener mylistener= new MyOnclickListener();
		img_back.setOnClickListener(mylistener);
			
		}
	
	private void addlist() {
		// TODO 自动生成的方法存根
		
		list = new ArrayList<Scenic_info>();
		adapter = new AA_ScenicAdapter(this, list);
		listview.setAdapter(adapter);
		
		ThreadPoolUtils.execute(new HttpGetThread(hand,
				new MyURL().getScenics( title)));
		listview.setOnItemClickListener(this);
	}
	
	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "传输失败", 1).show();
			} else if (msg.what == 200) {
				String json = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (json != null) {
					// 1次网络请求返回的数据
					List<Scenic_info> ListInfo = new MyJson().getScenicInfo(json);
					if (ListInfo!= null) {
						for (Scenic_info info:ListInfo){
							list.add(info);
						}
						adapter.notifyDataSetChanged();
					}
				}
				adapter.notifyDataSetChanged();
			}
		};
	};
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int postion,
			long arg3) {
		Intent intent=new Intent(getApplicationContext(),AAI_list_chilldActivity.class);
		Data.scenic=list.get(postion-1);
		Toast.makeText(getApplicationContext(), Data.scenic.scenicname, 0).show();
		startActivity(intent);
		
	}
	
	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.aaa_img_back:
				finish();
				break;

			default:
				break;
			}
		}
	}
}
