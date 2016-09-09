package com.xiang.lvyou;

import java.util.ArrayList;
import java.util.List;

import com.dang.view.AA_RecommendActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.serve.html.Scenic_info;
import com.xiang.lvyou.adapter.AA_ScenicAdapter;
import com.xiang.lvyou.url.Data;
import com.xiang.lvyou.url.MyURL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 搜索推荐模块
 * */

public class AA_SearchActivity extends Activity implements OnItemClickListener {

	private LinearLayout lin_food;
	private LinearLayout lin_scenery;
	private LinearLayout lin_more;

	private TextView friends;
	private TextView myDiary;
	private PullToRefreshListView lv;
	private List<Scenic_info> list;
	private AA_ScenicAdapter adapter;
	private MyJson myJson = new MyJson();

	//public static final String Get_rec_URL = "http://10.0.3.2:80/recommend/Service/getScenics.php?start=1&end=5";
	//public static final String Get_rec_URL = "http://10.0.3.2:9096/wantTravel/getFindJSON.php?start=1&end=5";


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_activity_search);
		initView();
	}

	private void initView() {
		lin_food = (LinearLayout) findViewById(R.id.aa_lin_food);
		lin_scenery = (LinearLayout) findViewById(R.id.aa_lin_scenery);
		lin_more = (LinearLayout) findViewById(R.id.aa_lin_more);
		lv = (PullToRefreshListView) findViewById(R.id.aa_listview);
		
		list = new ArrayList<Scenic_info>();
		adapter = new AA_ScenicAdapter(this, list);
		MyOnclickListener myOnclickListener = new MyOnclickListener();
		
		lin_food.setOnClickListener(myOnclickListener);
		lin_scenery.setOnClickListener(myOnclickListener);
		lin_more.setOnClickListener(myOnclickListener);
		lv.setOnItemClickListener(this);
		lv.setAdapter(adapter);
		ThreadPoolUtils.execute(new HttpGetThread(hand,
				new MyURL().getScenics("all")));
		

		//lisView的刷新部分
		lv.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new AsyncTask<Void, Void, Void>() {
					@Override
					protected Void doInBackground(Void... arg0) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						list.clear();
						ThreadPoolUtils.execute(new HttpGetThread(hand,
								new MyURL().getScenics("all")));
						return null;
					}
					@SuppressLint("NewApi")
					protected void onPostExecute(Void result) {
						lv.onRefreshComplete();
					};
				}.execute();
			}
		});
	}

	private class MyOnclickListener implements View.OnClickListener {
		public void onClick(View v) {
			int mID = v.getId();
			Intent intent = null;
			switch (mID) {
			case R.id.aa_lin_food: {
				intent = new Intent(getApplicationContext(),
						AA_RecommendActivity.class);
				intent.putExtra("title", "food");
				break;
			}
			case R.id.aa_lin_scenery: {
				intent = new Intent(getApplicationContext(),
						AA_RecommendActivity.class);
				intent.putExtra("title", "scenery");
				break;
			}
			case R.id.aa_lin_more: {
				intent = new Intent(getApplicationContext(),
						AA_RecommendActivity.class);
				intent.putExtra("title", "all");
				break;
			}
			}
			startActivity(intent);
		}

	}

	Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 404) {
				Toast.makeText(AA_SearchActivity.this, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(AA_SearchActivity.this, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				// 在activity当中获取网络交互的数据
				if (result != null) {
					// 1次网络请求返回的数据
					List<Scenic_info> ListInfo = myJson.getScenicInfo(result);
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int postion,
			long arg3) {
		Intent intent=new Intent(AA_SearchActivity.this,AAI_list_chilldActivity.class);
		Data.scenic=list.get(postion-1);
		
		Toast.makeText(AA_SearchActivity.this, Data.scenic.scenicname, 0).show();
		startActivity(intent);
		
	}
}
