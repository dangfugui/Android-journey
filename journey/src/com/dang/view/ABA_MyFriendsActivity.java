package com.dang.view;


import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.serve.html.Friend_info;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.R;
import com.xiang.lvyou.adapter.ABA_FriendsListAdapter;
import com.xiang.lvyou.url.MyURL;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ShareCompat.IntentReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ABA_MyFriendsActivity extends Activity{
	private TextView txt_title;
	private ImageView img_back;
	private ImageView img_addfriends;
	private PullToRefreshListView listview;
	private ABA_FriendsListAdapter adapter;
	private List<Friend_info> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaa_activity_recommend);
		initview();
	}

	private void initview() {
		// TODO 自动生成的方法存根
		txt_title=(TextView) findViewById(R.id.aaa_txt_title);
		img_back=(ImageView) findViewById(R.id.aaa_img_back);
		img_addfriends=(ImageView) findViewById(R.id.aaa_img_add);
		listview=(PullToRefreshListView) findViewById(R.id.aaa_listview);
		Intent intent=this.getIntent();
		String title=intent.getStringExtra("title");
		if(title!=null){
			txt_title.setText(title);
		}
		MyOnclickListener mylistener= new MyOnclickListener();
		img_back.setOnClickListener(mylistener);
		img_addfriends.setOnClickListener(mylistener);
		addlist();
	}
	

	
	private void addlist() {
		// TODO 自动生成的方法存根
		
		list = new ArrayList<Friend_info>();
		adapter = new ABA_FriendsListAdapter(this, list);
		listview.setAdapter(adapter);

		ThreadPoolUtils.execute(new HttpGetThread(hand,
				new MyURL().getFriends()));
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(getApplicationContext(),com.dang.view.ABB_DiaryListActivity.class);
				intent.putExtra("title", list.get((int) id).name);
				intent.putExtra("userid", list.get((int) id).userid );
				startActivity(intent);  
			}
		});
		
	}
	
	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(getApplicationContext(), "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(getApplicationContext(), "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					List<Friend_info> ListInfo = new MyJson().getFriendInfo(result);
					if (ListInfo!= null) {
						for (Friend_info info:ListInfo){
							list.add(info);
						}
						adapter.notifyDataSetChanged();
					}
				}
				adapter.notifyDataSetChanged();
			}
		};
	};

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.aaa_img_back:
				finish();
				break;
			case R.id.aaa_img_add:
				Intent intent=new Intent(getApplicationContext(), ABAA_AddFriendsActivity.class);
				startActivity(intent);

			default:
				break;
			}
		}
	}
}
