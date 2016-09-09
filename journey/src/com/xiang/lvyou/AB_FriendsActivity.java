package com.xiang.lvyou;

import java.util.ArrayList;
import java.util.List;

import com.dang.view.ABA_MyFriendsActivity;
import com.dang.view.ABI_DiaryItem;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.serve.html.Article_info;
import com.serve.html.HttpGetThread;
import com.serve.html.MyJson;
import com.serve.html.ThreadPoolUtils;
import com.xiang.lvyou.adapter.AB_ArticleAdapter;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 好友模块
 * */
public class AB_FriendsActivity extends Activity {

	private TextView btn_friends;
	private TextView btn_diary;
	
	private PullToRefreshListView listview;
	private AB_ArticleAdapter adapter;
	private List<Article_info> list;
	private String getURL=new MyURL().getFriendsArticles();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ab_activity_friends);
		initView();
		addlist();
	}


	private void initView() {
		listview=(PullToRefreshListView) findViewById(R.id.ab_list_friends);
		btn_friends=(TextView) findViewById(R.id.ab_btn_friends);
		btn_diary=(TextView) findViewById(R.id.ab_btn_diary);
		
		MyOnClickListener myclicklistener = new MyOnClickListener();
		btn_friends.setOnClickListener(myclicklistener);
		btn_diary.setOnClickListener(myclicklistener);
		
		

	}
	
	private void addlist() {
		list = new ArrayList<Article_info>();
		adapter = new AB_ArticleAdapter(this, list);
		listview.setAdapter(adapter);
		
		ThreadPoolUtils.execute(new HttpGetThread(hand,getURL));
		//lisView的刷新部分
				listview.setOnRefreshListener(new OnRefreshListener<ListView>() {
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
								ThreadPoolUtils.execute(new HttpGetThread(hand,getURL));
								return null;
							}
							@SuppressLint("NewApi")
							protected void onPostExecute(Void result) {
								listview.onRefreshComplete();
							};
						}.execute();
					}
				});
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(getApplicationContext(),ABI_DiaryItem.class);
				//Toast.makeText(getApplicationContext(), list.get(position-1).articleid+ list.get(position-1).imageList.length, 1).show();
				Data.article=list.get(position-1);
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
					List<Article_info> ListInfo = new MyJson().getArticleInfo(result);
					if (ListInfo!= null) {
						for (Article_info info:ListInfo){
							list.add(info);
						}
						adapter.notifyDataSetChanged();
					}
				}
				adapter.notifyDataSetChanged();
			}
		};
	};

	

	private class MyOnClickListener implements View.OnClickListener {
		public void onClick(View v) {
			int id = v.getId();
			Intent intent=null;
			switch (id) {
			case R.id.ab_btn_friends:{
				intent=new Intent(getApplicationContext(),ABA_MyFriendsActivity.class);
				intent.putExtra("title", "friends");
				break;
			}case R.id.ab_btn_diary:{
				intent=new Intent(getApplicationContext(),com.dang.view.ABB_DiaryListActivity.class);
				intent.putExtra("title", "diary");
				break;
			}

			default:
				break;
			}
			startActivity(intent);
		}

	}
}
